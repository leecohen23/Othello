package ca.utoronto.utm.othello.model;

import ca.utoronto.utm.othello.strategy.PlayerStrategy;
import ca.utoronto.utm.othello.viewcontroller.MoveCommand;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;

public class PlayerAll extends Observable implements Observer {

    private Othello othello;
    private char player;
    private PlayerStrategy strategy;
    private boolean hasMove;
    public Move currentMove;
    private MoveCommand command;

    public PlayerAll(Othello othello, char player) {
        this.othello = othello;
        this.player = player;
    }

    public void makeMove() {
        if (othello.getWhosTurn() == this.player) {
            currentMove = this.strategy.getMove();
            this.othello.move(currentMove.getRow(), currentMove.getCol());
            this.hasMove = !this.strategy.strategyId.equals(PlayerStrategy.HUMAN);
            this.command.addMove(currentMove);
        }
    }

    public void setCommand(MoveCommand command) {
        this.command = command;
    }

    public void setStrategy(PlayerStrategy strategy) {
        this.strategy = strategy;
        this.hasMove = !this.strategy.strategyId.equals(PlayerStrategy.HUMAN);
    }
    
    public char getPlayer() {
        return player;
    }

    public Othello getOthello() {
        return othello;
    }

    public String getStrategyId() {
        return this.strategy.strategyId;
    }

    public void receiveMove(Move move) {
        if (othello.isValidMove(move)) {
            this.strategy.receiveMove(move);
            this.hasMove = true;
        }
    }

    @Override
    public void update(Observable o) {
        if (this.hasMove && !this.othello.isGameOver()) {
        	//System.out.println(this.player);
            this.makeMove();
            this.notifyObservers();
        }
    }
}
