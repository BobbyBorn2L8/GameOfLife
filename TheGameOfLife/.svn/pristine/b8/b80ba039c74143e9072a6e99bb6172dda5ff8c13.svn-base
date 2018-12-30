package gameOfLife;

/**
 * @author Robert Kordula 40131366
 *
 */
public class GameAssetAttraction extends GameAsset
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2067508107312183901L;
	private int currentEntryPrice;
	private int originalEntryPrice;
	
	public GameAssetAttraction(int originalEntryPrice, int originalAssetValue, String assetName, String assetImageName) 
	{
		super(originalAssetValue, assetName, assetImageName);
		this.originalEntryPrice = originalEntryPrice;
	}

	@Override
	//Override the GameAsset setCurrentAssetValue method, because GameAssetAttraction has an entry price as well as an asset price//
	public void setCurrentAssetValue(double stockMultiplier) {
		setCurrentAssetValue(getOriginalAssetValue() * stockMultiplier);
		currentEntryPrice = (int)(originalEntryPrice * stockMultiplier);
	}
	
	//Getters and setters//
	public int getCurrentEntryPrice() {
		return currentEntryPrice;
	}

	public void setCurrentEntryPrice(int currentEntryPrice) {
		this.currentEntryPrice = currentEntryPrice;
	}

}
