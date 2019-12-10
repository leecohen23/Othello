package ca.utoronto.utm.othello.viewcontroller;


import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerAll;
import ca.utoronto.utm.othello.strategy.PlayerStrategy;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class OthelloTokenEventHandler implements EventHandler<ActionEvent> {

    private Othello othello;
    private VToken token;
    private PlayerAll player1, player2;

    public OthelloTokenEventHandler(Othello othello, VToken token, PlayerAll player1, PlayerAll player2) {
        this.othello = othello;
        this.token = token;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (othello.getWhosTurn() == OthelloBoard.P1 && this.player1.getStrategyId().equals(PlayerStrategy.HUMAN)) {
            player1.receiveMove(new Move(this.token.getRow(), this.token.getCol()));
            player1.update(this.othello);
        } else if (othello.getWhosTurn() == OthelloBoard.P2 && this.player2.getStrategyId().equals(PlayerStrategy.HUMAN)) {
            player2.receiveMove(new Move(this.token.getRow(), this.token.getCol()));
            player2.update(this.othello);
        }


        this.token.showToken(this.othello.getWhosTurn());
    }
}
