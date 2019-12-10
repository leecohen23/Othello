package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.strategy.PlayerStrategy;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;


public class VMenu extends HBox {


    private TextField txtPlayerTime = new TextField();
    public VMenu(char player) {
        Label labelPlayer = new Label("Player " + player);
        this.setSpacing(5);
        this.setAlignment(Pos.CENTER_LEFT);
        labelPlayer.setFont(new Font("Arial", 15));
        txtPlayerTime.setPromptText("Number of Minutes");
        txtPlayerTime.setTooltip(new Tooltip("Number must be between 0 and 60"));
        this.getChildren().addAll(labelPlayer, txtPlayerTime);
    }

    public String getTime() {
        return txtPlayerTime.getText();
    }

}
