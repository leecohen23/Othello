package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerAll;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VGamePanel extends GridPane {


    public VGamePanel(Othello othello, PlayerAll player1, PlayerAll player2) {


        VToken[][] allTokens = new VToken[8][8];
        populateAllTokens(allTokens);

        this.setStyle("-fx-background-color: green;");

        for (VToken[] tokens : allTokens) {
            for (int i = 0; i < allTokens.length; i++) {
                VToken currentToken = tokens[i];
                currentToken.setOnAction(new OthelloTokenEventHandler(othello, tokens[i], player1, player2));
                currentToken.hoverProperty().addListener(e -> {
                    othello.notifyObservers();
                    currentToken.showToken(othello.getWhosTurn());
                });
                this.addColumn(i, currentToken);
                othello.attach(currentToken);
            }
        }
    }

    private void populateAllTokens(Button[][] allTokens) {
        for (int i = 0; i < allTokens.length; i++) {
            for (int j = 0; j < allTokens[i].length; j++) {
                allTokens[i][j] = new VToken(i, j);
            }
        }
    }

}
