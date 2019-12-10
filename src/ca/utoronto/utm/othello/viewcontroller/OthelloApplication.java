package ca.utoronto.utm.othello.viewcontroller;


import ca.utoronto.utm.othello.model.*;

import ca.utoronto.utm.othello.strategy.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class OthelloApplication extends Application {
    // REMEMBER: To run this in the lab put
    // --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
    // in the run configuration under VM arguments.
    // You can import the JavaFX.prototype launch configuration and use it as well.

    private Scene menu, game;
    private static Othello othello = new Othello();
    public static PlayerAll player1 = new PlayerAll(othello, OthelloBoard.P1);
    public static PlayerAll player2 = new PlayerAll(othello, OthelloBoard.P2);
    private UndoCommand undoCommand = new UndoCommand(othello);


    @Override
    public void start(Stage stage) throws Exception {
        ChoiceBox<String> P1StrategyMenu = new ChoiceBox<>();
        ChoiceBox<String> P2StrategyMenu = new ChoiceBox<>();
        P1StrategyMenu.getItems().addAll("Human", "Random", "Greedy", "Advanced");
        P2StrategyMenu.getItems().addAll("Human", "Random", "Greedy", "Advanced");
        P1StrategyMenu.getSelectionModel().selectFirst();
        P2StrategyMenu.getSelectionModel().selectFirst();

        // MODEL
    	player1.setCommand(undoCommand);
    	player2.setCommand(undoCommand);
        player1.attach(player2);
        player2.attach(player1);

        // VIEW

        VGamePanel gamePanel = new VGamePanel(othello, player1, player2);
        VSideInfoPanel sideDisplay = new VSideInfoPanel(othello, undoCommand);
        VMenu menuP1Display = new VMenu('X');
        VMenu menuP2Display = new VMenu('O');


        BorderPane bp = new BorderPane();
        game = new Scene(bp);

        VBox bb = new VBox(5);
        Button btnStart = new Button("Start Game");
        bb.getChildren().addAll(menuP1Display, menuP2Display);
        menu = new Scene(bb);


        btnStart.setOnAction(e -> {
            try {
                int P1Seconds = Integer.parseInt(menuP1Display.getTime()) * 60;
                int P2Seconds = Integer.parseInt(menuP1Display.getTime()) * 60;
                if (P1Seconds <= 3600 && P2Seconds <= 3600 && 0 < P1Seconds && 0 < P2Seconds) {
                    sideDisplay.setP1Time(P1Seconds);
                    sideDisplay.setP2Time(P2Seconds);
                    setStrategy(player1,P1StrategyMenu.getValue());
                    setStrategy(player2,P2StrategyMenu.getValue());
                    othello.notifyObservers();
                    stage.setScene(game);
                    player1.update(othello);
                    player2.update(othello);
                } else {
                    System.out.println("Time not in bound of 0 - 60 minutes");
                }

            } catch (Exception e1) {
                System.out.println("Enter a number");
            }

        });
        
        
        menuP1Display.getChildren().addAll(P1StrategyMenu);
        menuP2Display.getChildren().addAll(P2StrategyMenu, btnStart);

        bp.setCenter(gamePanel);
        bp.setRight(sideDisplay);
        // SCENE

        stage.setTitle("Othello");
        stage.setScene(menu);
        stage.setResizable(false);


        // LAUNCH THE GUI
        stage.show();
    }

    public void setStrategy(PlayerAll player, String strategy) {
        switch (strategy) {
            case "Human":
                player.setStrategy(new HumanStrategy(player));
                if(player.getPlayer() == 'X') {
                	VSideInfoPanel.P1Display.strategyMenu.setValue("Human");
                }else {
                	VSideInfoPanel.P2Display.strategyMenu.setValue("Human");
                }
                break;
            case "Random":
                player.setStrategy(new RandomStrategy(player));
                if(player.getPlayer() == 'X') {
                	VSideInfoPanel.P1Display.strategyMenu.setValue("Random");
                }else {
                	VSideInfoPanel.P2Display.strategyMenu.setValue("Random");
                }
                break;
            case "Greedy":
                player.setStrategy(new GreedyStrategy(player));
                if(player.getPlayer() == 'X') {
                	VSideInfoPanel.P1Display.strategyMenu.setValue("Greedy");
                }else {
                	VSideInfoPanel.P2Display.strategyMenu.setValue("Greedy");
                }
                break;
            case "Advanced":
                player.setStrategy(new AdvancedStrategy(player));
                if(player.getPlayer() == 'X') {
                	VSideInfoPanel.P1Display.strategyMenu.setValue("Advanced");
                }else {
                	VSideInfoPanel.P2Display.strategyMenu.setValue("Advanced");
                }
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
