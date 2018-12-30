package gameOfLife;

import java.io.Serializable;

/**
 * 
 * @author Josh & Daniel & Alex (Heavy Edits)
 * 
 * A game asset is any object that can be liquefied and
 * valued at the end of the game
 *
 */

public abstract class GameAsset extends GameEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4544912325178827349L;
	// INSTANCE VARIABLES
	/**
	 * The value of this asset
	 */
	private int originalAssetValue;
	private int currentAssetValue;
	private String assetName;
	
	
	// CONSTRUCTORS
	public GameAsset(int currentAssetValue, String assetName, String assetImageName)
	{
		super(0, 0, assetImageName);
		this.originalAssetValue = currentAssetValue;
		this.currentAssetValue = currentAssetValue;
		this.assetName = assetName;
	}

	// METHODS
	public void sellAsset(GamePlayer assetOwner)
	{
		assetOwner.addToPlayerBankBalance(getCurrentAssetValue());
	}
	
	public void buyAsset(GamePlayer assetBuyer)
	{
		assetBuyer.subtractFromPlayerBankBalance(getCurrentAssetValue());
	}
	
	// GETTERS AND SETTERS
	
	public int getOriginalAssetValue() {
		return originalAssetValue;
	}

	public void setOriginalAssetValue(int currentAssetValue) {
		this.originalAssetValue = currentAssetValue;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public int getCurrentAssetValue() {
		return currentAssetValue;
	}

	public void setCurrentAssetValue(double stockMultiplier) {
		currentAssetValue = (int)(originalAssetValue * stockMultiplier);
	}

}
