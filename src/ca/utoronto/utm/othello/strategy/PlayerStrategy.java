package ca.utoronto.utm.othello.strategy;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.model.PlayerAll;

public class PlayerStrategy {

    protected PlayerAll player;
    protected Player playerModel;
    public static final String HUMAN = "human", GREEDY = "greedy", RANDOM = "random", DEFAULT = "default", ADVANCED = "advanced";
    public String strategyId = DEFAULT;
    protected Move currentMove = new Move(-1, -1);

    public PlayerStrategy(PlayerAll player) {
        this.player = player;
    }

    public Move getMove() { return this.playerModel.getMove(); }

    public void receiveMove(Move move) {
        this.currentMove = move;
    }
}
