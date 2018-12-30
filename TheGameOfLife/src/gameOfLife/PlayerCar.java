package gameOfLife;

import java.io.Serializable;

/**
 * @author Robert Kordula 40131366
 * Used to store the details of the players card and allow it to be drawn
 */
public class PlayerCar extends GameEntity implements Serializable
{
	private CarTypes playerCar;
	
	public PlayerCar(int xCoord, int yCoord, CarTypes carTier) 
	{
		super(xCoord, yCoord, "");
		this.playerCar = carTier;
		//changeCarSprite();
	}
	
	/**
	 * When called will upgrade the car by one tier, if it hasn't already reached tier4
	 */
	public void upgradeCar()
	{
		switch (playerCar)
		{
			case TIER1:
				playerCar = CarTypes.TIER2;
				changeCarSprite();
				break;
			case TIER2:
				playerCar = CarTypes.TIER3;
				changeCarSprite();
				break;
			case TIER3:
				playerCar = CarTypes.TIER4;
				changeCarSprite();
				break;
			default:
		}
	}
	
	/**
	 * Uppdates the cars sprite based on the carTypes enum
	 */
	public void changeCarSprite()
	{
		switch(playerCar)
		{
		case TIER2:
			setEntityImageByName("newCar_02.png");
			break;
		case TIER3:
			setEntityImageByName("newCar_03.png");
			break;
		case TIER4:
			setEntityImageByName("newCar_04.png");
			break;
		default:
			setEntityImageByName("newCar_01.png");
		}
	}

	//Getters and setters//
	public CarTypes getPlayerCar() {
		return playerCar;
	}

	public void setPlayerCar(CarTypes playerCar) {
		this.playerCar = playerCar;
	}
}
