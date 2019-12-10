package ca.utoronto.utm.othello.viewcontroller;


import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.strategy.PlayerStrategy;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OpponentChooserPanel extends GridPane implements EventHandler<ActionEvent>{
	
	private PlayerStrategy ps;
	private Othello othello;
	public OpponentChooserPanel() {
		// TODO Auto-generated constructor stub
		String[] buttonLabels = { "Human", "Random", "Greedy" };

		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinWidth(100);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		String command = ((Button) event.getSource()).getText();
		//this.setPlayerStrategy(OpponentFactory.create(command, othello));
		System.out.println(command);
		
	}

	public void setPlayerStrategy(PlayerStrategy strategy) {
		this.ps = strategy;
	}
}
