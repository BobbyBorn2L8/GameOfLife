package gameOfLife;

import java.awt.Graphics2D;
import java.io.Serializable;

/**
 * @author Robert Kordula 40131366
 * Base class for all tiles, contains common methods and abstract method onLand of which each tile reacts to
 * differently
 */
public abstract class Tile extends GameEntity implements Serializable{
	
	private GamePlayer playerOnTile;
	private int multiplier;
	private TileEffects effect1;
	private String tileText;
	private boolean tileActivated;
	
	public Tile(int xCoord, int yCoord, String imageName, int multiplier, String tileText, TileEffects effect1)
	{
		super(xCoord, yCoord, imageName);
		this.playerOnTile = null;
		this.multiplier = multiplier;
		this.tileText = tileText;
		this.effect1 = effect1;
		tileActivated = false;
	}

	/**
	 * @param player
	 * @param board
	 * @param panel
	 * Abstract method for all instances that inherit TIle, they must implement their own interpretation 
	 * of onLand() 
	 */
	public abstract void onLand(GamePlayer player, GameBoard board, Graphics2D panel);
	
	/**
	 * @param playerOnTile
	 * Common method for all tiles, moves the player and their car to this tile
	 */
	public void movePlayerCoordsToTile(GamePlayer playerOnTile)
	{
		playerOnTile.getPlayerCar().setxCoord(this.getxCoord());
		playerOnTile.getPlayerCar().setyCoord(this.getyCoord() + 5);
		playerOnTile.setxCoord(playerOnTile.getPlayerCar().getxCoord() + 33);
		playerOnTile.setyCoord(playerOnTile.getPlayerCar().getyCoord() - playerOnTile.getEntityImage().getHeight() + 34);
	}
	
	//Getters and Setters///
	public int getMultiplier()
	{
		return multiplier;
	}

	public void setMultiplier(int multiplier)
	{
		this.multiplier = multiplier;
	}

	public String getTileText()
	{
		return tileText;
	}

	public void setTileText(String tileText)
	{
		this.tileText = tileText;
	}

	public boolean isTileActivated()
	{
		return tileActivated;
	}

	public void setTileActivated(boolean tileActivated)
	{
		this.tileActivated = tileActivated;
	}

	public GamePlayer getPlayerOnTile() {
		return playerOnTile;
	}

	public void setPlayerOnTile(GamePlayer playerOnTile) {
		this.playerOnTile = playerOnTile;

	}

	public TileEffects getEffect1()
	{
		return effect1;
	}

	public void setEffect1(TileEffects effect1)
	{
		this.effect1 = effect1;
	}
	
}