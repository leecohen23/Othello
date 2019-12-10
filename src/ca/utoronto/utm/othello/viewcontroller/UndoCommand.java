package ca.utoronto.utm.othello.viewcontroller;
import java.util.Stack;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class UndoCommand implements EventHandler<ActionEvent>, MoveCommand{
	private Stack<Move> moveStack = new Stack<Move>();
	private Othello othello;
	public UndoCommand(Othello othello) {
		// TODO Auto-generated constructor stub
		this.othello = othello;
	}
	
	public void addMove(Move move) {
		this.moveStack.add(move);
	}
	public void clearMove() {
		this.moveStack.clear();
	}

	public void execute() {
		if(!this.moveStack.isEmpty()) {
			this.moveStack.pop();
			this.othello.restartGame();
			for (Move move: this.moveStack) {
				this.othello.move(move.getRow(), move.getCol());
			}
		}
	}

	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.execute();
		
	}

}
