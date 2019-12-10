package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class VTimer extends Label implements Observer {

    private int seconds;
    private Timeline timer;
    private char whosTurn;
    private Othello othello;

    public VTimer(char whosTurn, Othello othello) {
        this.othello = othello;
        this.whosTurn = whosTurn;
        if (whosTurn == 'X') {
            this.setTextFill(Color.rgb(255, 255, 255));
        } else {
            this.setTextFill(Color.rgb(0, 0, 0));
        }
        this.setFont(new Font("Arial", 35));
    }

    private String minuteFormat(int seconds) {
        int minute = seconds / 60;
        seconds = seconds % 60;
        if (Integer.toString(seconds).length() == 1) {
            return (minute + ":" + "0" + seconds);
        } else {
            return (minute + ":" + seconds);

        }
    }

    public void setTime(int seconds) {
        this.seconds = seconds;
        this.setText(minuteFormat(seconds));
        this.timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            if (this.seconds == 0) {
                othello.setWhosTurn(OthelloBoard.EMPTY);
                othello.notifyObservers();
            } else {
                this.seconds--;
                this.setText(minuteFormat(this.seconds));
            }
        }));
        this.timer.setCycleCount(Timeline.INDEFINITE);
    }
    public void resetTimer(int seconds) {
    	this.seconds = seconds;
    	this.setText(minuteFormat(seconds));
    }

    @Override
    public void update(Observable o) {
        Othello othello = (Othello) o;
        if (othello.getWhosTurn() == this.whosTurn) {
            this.timer.play();
        } else {
            this.timer.stop();
        }
    }
}
