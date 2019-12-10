package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class VToken extends Button implements Observer {

    private int row, col;
    private String color;
    private Circle circle = new Circle(50);
    private Rectangle rectangle = new Rectangle(50, 50);
    private char player;

    public VToken(int row, int col) {
        this.row = row;
        this.col = col;
        this.color = null;
    }

    @Override
    public void update(Observable o) {
        Othello othello = (Othello) o;
        this.player = othello.getToken(this.row, this.col);
        this.setMinSize(50, 50);
        this.setStyle("-fx-base: green;");
        if (this.player == 'X') {
            this.color = "black";
            this.setStyle("-fx-base: black;");
            this.setShape(circle);
        } else if (this.player == 'O') {
            this.color = "white";
            this.setStyle("-fx-base: white;");
            this.setShape(circle);
        } else {
            this.setShape(rectangle);
            Othello boardCopy = othello.copy();
            if (boardCopy.move(this.row, this.col)) {
                this.setStyle("-fx-base: #00ad00;");
            }
            this.setText(Character.toString(othello.getToken(this.row, this.col)));
        }
    }

    public void showToken(char player) {
        if (this.player == OthelloBoard.EMPTY) {
            if (player == 'X') {
                this.color = "black";
                this.setStyle("-fx-base: black;");
                this.setShape(circle);
            } else {
                this.color = "white";
                this.setStyle("-fx-base: white;");
                this.setShape(circle);
            }
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getColor() {
        return this.color;
    }
}
