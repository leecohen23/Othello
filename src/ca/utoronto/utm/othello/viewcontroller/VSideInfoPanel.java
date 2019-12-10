package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerAdvanced;
import ca.utoronto.utm.othello.model.PlayerAll;
import ca.utoronto.utm.othello.strategy.AdvancedStrategy;
import ca.utoronto.utm.othello.strategy.GreedyStrategy;
import ca.utoronto.utm.othello.strategy.HumanStrategy;
import ca.utoronto.utm.othello.strategy.RandomStrategy;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VSideInfoPanel extends VBox {
    public static VSidePanel P1Display, P2Display;
    private int P1Seconds, P2Seconds;
    private MoveCommand command;

    public VSideInfoPanel(Othello othello, UndoCommand command) {

        Button btnRestart = new Button("Restart Game");
        Button btnHint = new Button("press for hint");
        Button btnundo = new Button("Undo");
        Button apply1 = new Button("Apply");
        Button apply2 = new Button("Apply");
        HintEventHandler X = new HintEventHandler(othello, btnHint);
        this.command = command;
        btnundo.setOnAction(command);
        btnHint.setOnAction(X);
        othello.attach(X);
        HBox playerPanel = new HBox();
        HBox optionsPanel = new HBox(10);

        P1Display = new VSidePanel(othello, 'X');
        P1Display.getChildren().addAll(apply1);
        P1Display.setMinWidth(100);
        P1Display.setMinHeight(380);

        P1Display.setAlignment(Pos.TOP_CENTER);
        P1Display.setStyle("-fx-background-color: #000000;");

        P2Display = new VSidePanel(othello, 'O');
        P2Display.getChildren().addAll(apply2);
        P2Display.setMinWidth(100);
        P2Display.setMinHeight(380);

        P2Display.setAlignment(Pos.TOP_CENTER);
        P2Display.setStyle("-fx-background-color: #ffffff;");

        optionsPanel.setAlignment(Pos.CENTER);
        
        btnRestart.setOnAction(e -> {
            P1Display.resetTime(P1Seconds);
            P2Display.resetTime(P2Seconds);
            othello.restartGame();
            othello.notifyObservers();
            P1Display.strategyMenu.setValue("Human");
            this.setStrategy(OthelloApplication.player1, "Human");
            P2Display.strategyMenu.setValue("Human");
            this.setStrategy(OthelloApplication.player2, "Human");
            OthelloApplication.player1.update(othello);
            OthelloApplication.player2.update(othello);
            command.clearMove();
        });

        playerPanel.getChildren().addAll(P1Display, P2Display);
        optionsPanel.getChildren().addAll(btnHint,btnundo, btnRestart);


        this.getChildren().addAll(playerPanel, optionsPanel);
        
        apply1.setOnAction(e->{
        	this.setStrategy(OthelloApplication.player1,P1Display.strategyMenu.getValue());
        	if(othello.getWhosTurn() == OthelloApplication.player1.getPlayer()) {
        		OthelloApplication.player1.update(othello);
        	}
        });
        
        apply2.setOnAction(e->{
        	this.setStrategy(OthelloApplication.player2,P2Display.strategyMenu.getValue());
        	if(othello.getWhosTurn() == OthelloApplication.player2.getPlayer()) {
        		OthelloApplication.player2.update(othello);
        	}
        });
    }

    public void setP1Time(int seconds) {
        this.P1Seconds = seconds;
        P1Display.setTime(this.P1Seconds);
    }

    public void setP2Time(int seconds) {
        this.P2Seconds = seconds;
        P2Display.setTime(this.P1Seconds);
    }
    
    public void setStrategy(PlayerAll player, String strategy) {
        switch (strategy) {
            case "Human":
                player.setStrategy(new HumanStrategy(player));
                break;
            case "Random":
                player.setStrategy(new RandomStrategy(player));
                break;
            case "Greedy":
                player.setStrategy(new GreedyStrategy(player));
                break;
            case "Advanced":
                player.setStrategy(new AdvancedStrategy(player));
                break;
        }
    }
}
