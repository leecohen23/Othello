package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerAll;
import ca.utoronto.utm.othello.strategy.AdvancedStrategy;
import ca.utoronto.utm.othello.strategy.GreedyStrategy;
import ca.utoronto.utm.othello.strategy.HumanStrategy;
import ca.utoronto.utm.othello.strategy.PlayerStrategy;
import ca.utoronto.utm.othello.strategy.RandomStrategy;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VSidePanel extends VBox implements Observer {

    private char player;
    private int playerSeconds = 0;
    private VTimer playerTimer;
    public ChoiceBox<String> strategyMenu = new ChoiceBox<>();

    public VSidePanel(Othello othello, char player) {
        this.setSpacing(20);
        this.setMinWidth(100);
        this.setAlignment(Pos.CENTER);
        this.player = player;
        

        playerTimer = new VTimer(this.player, othello);
        Label txtPlayerTitle = new Label("Player " + this.player);
        VStatus txtPlayerTokens = new VStatus(Character.toString(this.player));
        
        this.strategyMenu.getItems().addAll("Human", "Random", "Greedy", "Advanced");
        this.strategyMenu.getSelectionModel().selectFirst();
        
        Button apply = new Button("Apply");

        this.setMinWidth(125);
        this.setMinHeight(380);
        this.setAlignment(Pos.TOP_CENTER);

        if (player == 'X') {
            txtPlayerTitle.setTextFill(Color.rgb(255, 255, 255));
        } else {
            txtPlayerTitle.setTextFill(Color.rgb(0, 0, 0));
        }
        txtPlayerTokens.setFont(new Font("Arial", 15));
        playerTimer.setTime(playerSeconds);

        othello.attach(this);
        othello.attach(playerTimer);
        othello.attach(txtPlayerTokens);
        
        
        this.getChildren().addAll(txtPlayerTitle, playerTimer, txtPlayerTokens, strategyMenu);
    }

    public void setTime(int seconds) {
        playerTimer.setTime(seconds);
    }

    public void resetTime(int seconds) {
        playerTimer.resetTimer(seconds);
    }

    @Override
    public void update(Observable o) {
        Othello othello = (Othello) o;
        if (!othello.isGameOver()) {
            if (othello.getWhosTurn() == this.player) {
                if (this.player == 'X') {
                    this.setStyle("-fx-background-color: #000000;-fx-border-color: #FF7043;-fx-border-width: 4");
                } else {
                    this.setStyle("-fx-background-color: #ffffff;-fx-border-color: #FF7043;-fx-border-width: 4");
                }
            } else {
                if (this.player == 'X') {
                    this.setStyle("-fx-background-color: #000000;");
                } else {
                    this.setStyle("-fx-background-color: #ffffff;");
                }
            }
        } else {
            if (othello.getWinner() == OthelloBoard.EMPTY) {
                this.setStyle("-fx-background-color: #ddc340;");
            } else if (othello.getWinner() == this.player) {
                this.setStyle("-fx-background-color: #008000;");
            } else {
                this.setStyle("-fx-background-color: #B71C1C;");
            }
        }
    }
    
    /**
    public PlayerStrategy getStra(PlayerAll player, String strategy) {
        switch (strategy) {
        case "Human":
            return new HumanStrategy(player);
        case "Random":
        	return new RandomStrategy(player);
        case "Greedy":
        	return new GreedyStrategy(player);
        case "Advanced":
        	return new AdvancedStrategy(player);
        }
        return new AdvancedStrategy(player);
    }
    **/
    

}
