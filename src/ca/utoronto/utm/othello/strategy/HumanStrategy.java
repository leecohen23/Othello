package ca.utoronto.utm.othello.strategy;

import ca.utoronto.utm.othello.model.*;

public class HumanStrategy extends PlayerStrategy {


    public HumanStrategy(PlayerAll player) {
        super(player);
        this.strategyId = PlayerStrategy.HUMAN;
    }

    public Move getMove() {
		return this.currentMove;
    }


}
