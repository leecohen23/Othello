package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VStatus extends Text implements Observer {

    private String displayMessage;

    public VStatus(String displayMessage) {
        this.displayMessage = displayMessage;
        if (displayMessage.equals("X")) {
            this.setFill(Color.rgb(255, 255, 255));
        } else {
            this.setFill(Color.rgb(0, 0, 0));
        }
        this.setFont(new Font("Arial", 15));
    }

    @Override
    public void update(Observable o) {
        Othello othello = (Othello) o;
        switch (this.displayMessage) {
            case "Turn":
                this.setText(String.format("Who's turn: %s", othello.getWhosTurn()));
                break;
            case "O":
                this.setText(String.format("Tokens: %d", othello.getCount('O')));
                break;
            case "X":
                this.setText(String.format("Tokens: %d", othello.getCount('X')));
                break;
            case "Winner":
                this.setText(String.format("Winner: %s", othello.getWinner()));
                break;
        }
    }
}
