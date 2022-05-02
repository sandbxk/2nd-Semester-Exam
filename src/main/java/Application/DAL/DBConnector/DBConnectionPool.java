package Application.DAL.DBConnector;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class DBConnectionPool {

    private ConcurrentLinkedQueue<DBConnection> pool;

    private ScheduledExecutorService executorService;


    /**
     * Creates a new DB Connection Pool with only a minimum amount of objects given. No checks are performed for this constructor.
     * @param minObjects
     */
    public DBConnectionPool(final int minObjects)
    {
        initialize(minObjects);
    }


    /**
     * Creates a new DB Connection Pool. A check will be performed on a seperate thread every validationInterval seconds,
     * to see if the pool still has the given amount of minimum and maximum objects.
     * @param minObjects Minimum number of objects in the pool.
     * @param maxObjects Maximum number of objects in the pool.
     * @param validationInterval Time in seconds for periodical checking of minObjects / maxObjects conditions in a separate thread.
     */
    public DBConnectionPool(final int minObjects, final int maxObjects, final long validationInterval)
    {
        initialize(minObjects);

        // check pool conditions in a separate thread
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay((() -> {
            int size = pool.size();

            if (size < minObjects) {
                int sizeToBeAdded = minObjects + size;
                for (int i = 0; i < sizeToBeAdded; i++) {
                    pool.add(createObject());
                }
            } else if (size > maxObjects) {
                int sizeToBeRemoved = size - maxObjects;
                for (int i = 0; i < sizeToBeRemoved; i++) {
                    pool.poll();
                }
            }
        }), validationInterval, validationInterval, TimeUnit.SECONDS);

    }

    /**
     * Gets the next free DBConnection object from the pool. If the pool doesn't contain any objects,
     * a new object will be created and given to the caller of this method back.
     * @return
     */
    public DBConnection borrowObject() {
        DBConnection object;
        if ((object = pool.poll()) == null)
        {
            object = createObject();
        }
        return object;
    }


    /**
     * Returns the DBConnection object to the pool.
     * @param object to be returned
     */
    public void returnObject(DBConnection object) {
        if (object == null) {
            return;
        }
        this.pool.offer(object);
    }


    /**
     * Closes the pool.
     */
    public void shutdown()
    {
        if (executorService != null)
        {
            executorService.shutdown();
        }
    }


    /**
     * Creates a new DB Connection object.
     * @return the new DB Connection object
     */
    protected abstract DBConnection createObject();



    private void initialize(final int minObjects)

    {
        pool = new ConcurrentLinkedQueue<DBConnection>();

        for (int i = 0; i < minObjects; i++) {
            pool.add(createObject());
        }
    }


}
