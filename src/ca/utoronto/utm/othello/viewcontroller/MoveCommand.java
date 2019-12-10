package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;

public abstract interface MoveCommand extends EventHandler<ActionEvent> {

	public abstract void execute();
	public void addMove(Move move);

}
