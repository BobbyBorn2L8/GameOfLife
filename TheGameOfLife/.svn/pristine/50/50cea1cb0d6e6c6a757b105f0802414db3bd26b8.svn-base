package gameOfLife;

import java.awt.Graphics2D;

import javax.swing.JOptionPane;

/**
 * @author bob-s
 * Extends Tile, its purpose is to determine what the player must do when they land on this tile
 */
public class OrangeTile extends Tile 
{
	String message;
	public OrangeTile(String imageName, int multiplier, String tileText, TileEffects effect) {
		super(0, 0, imageName, multiplier, tileText, effect);
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * When landed on it determines the action to take based on the TileEffects enum stored in this tile
	 */
	public void onLand(GamePlayer player, GameBoard board, Graphics2D panel)
	{
		message = getTileText();
		GameLogic.gameLog.addLog(getTileText());
		switch(getEffect1())
		{
			case FINE:
				payFine(player);
				break;
			case GET_CAT:
				getCat(player, panel);
				break;
			case GET_DOG:
				getDog(player, panel);
				break;
			case HAVE_DAUGHTER:
				haveGirl(player, panel);
				break;
			case HAVE_SON:
				haveBoy(player, panel);
				break;
			case REWARD:
				getReward(player);
				break;
			case CHOOSE_CAREER:
				fired(player, board);
				break;
			case PROMOTION:
				promoted(player);
				break;
			case STOCK_MARKET:
				stockMarketChange(board);
				break;
			case HOUSE_MARKET:
				houseMarketChange(board);
				break;
			default:
				break;
		}
		JOptionPane.showMessageDialog(board.getMainFrame(), message, "", JOptionPane.INFORMATION_MESSAGE, null);
	}

	/**
	 * @param player
	 * Subtracts the multiplier from the players balance
	 */
	public void payFine(GamePlayer player)
	{
		//GameSound.fineSound.play();
		player.subtractFromPlayerBankBalance(getMultiplier());
	}
	
	/**
	 * @param player
	 * @param panel
	 * Adds a cat to the players family arraylist if it isn't full already, if full
	 * will instead charge the player for pet insurance
	 */
	public void getCat(GamePlayer player, Graphics2D panel)
	{
		//GameSound.meowSound.play();
		if (player.getPlayerFamily().size() < 3)
		{
			player.getPet(false, panel);
		}
		else
		{
			petInsurance(player);
		}
	}
	
	/**
	 * @param player
	 * @param panel
	 * Adds a dog to the players familyt arraylist if it isn't full already, if full
	 * will instead charge the player for pet insurance
	 */
	public void getDog(GamePlayer player, Graphics2D panel)
	{
		//GameSound.barkSound.play();
		if (player.getPlayerFamily().size() < 3)
		{
			player.getPet(true, panel);
		}
		else
		{
			petInsurance(player);
		}
	}
	
	/**
	 * @param player
	 * @param panel
	 * Adds a girl to the players family arraylist if it isn't full already, if full
	 * will instead charage the player for a birthday present
	 */
	public void haveGirl(GamePlayer player, Graphics2D panel)
	{
		if (player.getPlayerFamily().size() < 3)
		{
			GameSound.crySound.play();
			player.haveChild(false, panel);
		}
		else
		{
			childBirthday(player);
		}
	}

	/**
	 * @param player
	 * @param panel
	 * Adds a boy to the players family arraylist if it isn't full already, if full
	 * will instead charage the player for a birthday present
	 */
	public void haveBoy(GamePlayer player, Graphics2D panel)
	{
		if (player.getPlayerFamily().size() < 3)
		{
			GameSound.crySound.play();
			player.haveChild(true, panel);
		}
		else
		{
			childBirthday(player);
		}
	}
	
	/**
	 * @param player
	 * Adds the current multiplier to the players balance
	 */
	public void getReward(GamePlayer player)
	{
		//GameSound.cashSound.play();
		player.addToPlayerBankBalance(getMultiplier());
	}
	
	/**
	 * @param player
	 * @param board
	 * THe player is fired from their current job and are forced to find a new job, players career is set to the newly 
	 * selected career, their old job is added make to the career deck
	 */
	public void fired(GamePlayer player, GameBoard board)
	{
		//GameSound.fineSound.play();
		Careers career;
		do
		{
			if (!player.isUniversityImmediately())
			{
				career = board.getCareers().drawThreeForDecision();
			}
			else
			{
				career = board.getUniCareers().drawThreeForDecision();
			}
		} while(career != null);
		if (!player.isUniversityImmediately())
		{
			board.getCareers().addCard(player.getPlayerCareer());
		}
		else
		{
			board.getUniCareers().addCard(player.getPlayerCareer());
		}
		player.setPlayerCareer(career);
	}
	
	/**
	 * @param player
	 * The players current salary is multiplied by the current multiplier
	 */
	public void promoted(GamePlayer player)
	{
		//GameSound.clappingSound.play();
		player.promoted(getMultiplier());
	}
	
	/**
	 * @param board
	 * This method determines from the multiplier whether the stock market is increasing in value or decreasing and adjusts
	 * prices of all assets accordingly
	 */
	public void stockMarketChange(GameBoard board)
	{
		board.getGameLogic().setCurrentStockMarketMultiplier(getMultiplier() / 100);
		if (getMultiplier() < 100)
		{
			//GameSound.fineSound.play();
			message = "THE STOCK MARKET HAS CRASHED!!!!  All assets are 50% cheaper to buy and sell";
		}
		else 
		{
			//GameSound.cashSound.play();
			message = "THE STOCK MARKET HAS ROCKETED UPWARDS!!!!  All assets are 50% dearer to buy and sell";
		}
	}
	
	/**
	 * @param board
	 * This method determines from the multiplier whether the house market is increasing in value or decreasing and adjusts
	 * prices of all houses accordingly
	 */
	public void houseMarketChange(GameBoard board)
	{
		board.getGameLogic().setCurrentHousingMarketMultiplier(getMultiplier() / 100);
		if (getMultiplier() < 100)
		{
			//GameSound.fineSound.play();
			message = "THE HOUSE MARKET HAS CRASHED!!!!  All houses are 50% cheaper to buy and sell";
		}
		else 
		{
			//GameSound.cashSound.play();
			message = "THE HOUSE MARKET HAS ROCKETED UPWARDS!!!!  All houses are 50% dearer to buy and sell";
		}
	}
	
	public void childBirthday(GamePlayer player)
	{
		boolean childFound = false;
		for (int iii = 0; iii < player.getPlayerFamily().size(); iii++)
		{
			if (player.getPlayerFamily().get(iii) instanceof ChildPeg)
			{
				childFound = true;
				break;
			}
		}
		if (childFound)
		{
			GameSound.childrenSound.play();
			player.subtractFromPlayerBankBalance(1000);
			message = "Child's birthday, pay £1000 to buy a present";
		}
		else
		{
			message = "Nothing happens";
		}
	}
	
	public void petInsurance(GamePlayer player)
	{
		boolean childFound = false;
		for (int iii = 0; iii < player.getPlayerFamily().size(); iii++)
		{
			if (player.getPlayerFamily().get(iii) instanceof PetPeg)
			{
				childFound = true;
				break;
			}
		}
		if (childFound)
		{
			player.subtractFromPlayerBankBalance(1000);
			message = "Pet insurance for the year, pay £1000";
		}
		else
		{
			message = "Nothing happens";
		}
	}
}
