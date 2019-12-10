package ca.utoronto.utm.othello.strategy;

import ca.utoronto.utm.othello.model.*;
import ca.utoronto.utm.othello.model.PlayerAll;

public class GreedyStrategy extends PlayerStrategy {

    public GreedyStrategy(PlayerAll player) {
        super(player);
        this.playerModel = new PlayerGreedy(this.player.getOthello(), this.player.getPlayer());
        this.strategyId = PlayerStrategy.GREEDY;
    }
}
