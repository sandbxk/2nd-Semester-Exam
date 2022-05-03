package Application.GUI.StateMachine;

public abstract class TeacherViewStateMachine {


    private static TeacherViewStateMachine state;


    public abstract void disable();
    public abstract void enable();


    public static void changeState(State newState){
        newState.enable();

        if (state != null) {
            state.disable();
        }

        state = newState;
    }


    public TeacherViewStateMachine getState(){
        return this.state;
    }


}
