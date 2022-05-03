package Application.GUI.StateMachine;

import java.util.HashMap;

public class StateMachine<T>
{
    private HashMap<T, AbstractState> states = new HashMap<>();

    private AbstractState current = null;

    public void addState(T key, AbstractState state)
    {
        this.states.put(key, state);
    }

    public void change(T key)
    {
        states.get(key).enable();

        if (current != null)
        {
            current.disable();
        }

        this.current = states.get(key);
    }

    public AbstractState getCurrent()
    {
        return current;
    }

}
