package ca.utoronto.utm.othello.strategy;
import ca.utoronto.utm.othello.model.*;

public class AdvancedStrategy extends PlayerStrategy{

    public AdvancedStrategy(PlayerAll player) {
        super(player);
        this.playerModel = new PlayerAdvanced(this.player.getOthello(), this.player.getPlayer());
        this.strategyId = PlayerStrategy.ADVANCED;
    }
}
