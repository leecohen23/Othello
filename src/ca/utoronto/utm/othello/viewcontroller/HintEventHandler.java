package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerAdvanced;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;

public class HintEventHandler implements EventHandler<ActionEvent>, Observer {

    private Othello othello;
    private Button buttonO;
    private char currPlayer;


    public HintEventHandler(Othello othello, Button buttonO) {

        this.othello = othello;
        this.buttonO = buttonO;
        this.currPlayer = othello.getWhosTurn();


    }

    @Override
    public void handle(ActionEvent actionEvent) {
        this.currPlayer = othello.getWhosTurn();
        PlayerAdvanced p = new PlayerAdvanced(othello, othello.getWhosTurn());
        Move move_p = p.getMove();
        int k = move_p.getRow();
        int l = move_p.getCol();
        this.buttonO.setText("(" + k + ", " + l + ")");
        if (othello.isGameOver()){this.buttonO.setText("Game is over!");}
//            if (this.player == 'X'){
//                this.buttonX.setText("("+k+", "+l+")");
//        }
//            else{
//                this.buttonO.setText("("+k+", "+l+")");
//            }
    }

    @Override
    public void update(Observable o) {
        if (othello.getWhosTurn() != this.currPlayer) {
            this.buttonO.setText("press for hint");
        }
        this.currPlayer = othello.getWhosTurn();
    }}

