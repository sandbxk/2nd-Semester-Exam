package Application.GUI.StateMachine;

import java.util.HashMap;

public class StateMachine<T>
{
    private HashMap<T, IState> states = new HashMap<>();

    private IState current = null;

    public void addState(T key, IState state)
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

    public IState getCurrent()
    {
        return current;
    }

}
