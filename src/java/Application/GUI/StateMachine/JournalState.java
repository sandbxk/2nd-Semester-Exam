package Application.GUI.StateMachine;

import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;

public class JournalState extends ViewState {

    private Pane viewPane;
    private ToggleButton menuButton;

    public JournalState(Pane viewPane, ToggleButton menuButton) {
        this.viewPane = viewPane;
        this.menuButton = menuButton;
    }

    @Override
    public void disable() {
        viewPane.setVisible(false);
        menuButton.setDisable(false);
    }

    @Override
    public void enable() {
    viewPane.toFront();
    viewPane.setVisible(true);
    menuButton.setDisable(true);
    }
}
