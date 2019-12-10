package ca.utoronto.utm.othello.strategy;
import ca.utoronto.utm.othello.model.*;

public class RandomStrategy extends PlayerStrategy{

	public RandomStrategy(PlayerAll player) {
		super(player);
		this.playerModel = new PlayerRandom(this.player.getOthello(), this.player.getPlayer());
		this.strategyId = PlayerStrategy.RANDOM;
	}
}
