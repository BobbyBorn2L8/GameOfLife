package gameOfLife;

import java.awt.Graphics2D;

/**
 * @author Robert Kordula 40131366
 * This tile when passed or landed on adds the player's salary to their balance
 */
public class GreenTile extends Tile {

	public GreenTile() {
		super(0, 0, "green_01.png", 0, "PAYDAY", null);
	}

	@Override
	public void onLand(GamePlayer player, GameBoard board, Graphics2D panel)
	{
		player.getPaid();
	}

}
