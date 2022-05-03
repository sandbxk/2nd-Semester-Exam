package Application.GUI.StateMachine;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public abstract class ViewState {


    private ViewState state;


    public abstract void disable();
    public abstract void enable();


    public void changeState(ViewState newState, ViewState oldState){
        newState.enable();
        if (oldState != null)
        oldState.disable();
        this.state = newState;
    }

    public ViewState getState(){
        return this.state;
    }


}
