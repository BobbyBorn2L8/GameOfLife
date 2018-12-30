package gameOfLife;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GamePlayer extends GameEntity implements Comparable<GamePlayer>, Serializable{
	
	/**
	 * Instance Variables
	 */
	private Careers playerCareer;
	private String playerName;
	private int playerAssetsValue, playerBankBalance, playerSalary, bankBalanceAlone;
	private TileNode currentTileNode;
	private int playerAge, movesRemaining, currentRanking, carValue, assetsValue, familyValue, houseValue, loanValue;
	private ArrayList<GameAsset> playerAssets;
	private ArrayList<FamilyPegs> playerFamily;
	private ArrayList<Loan> playerLoans;
	private boolean isFirstTurn, universityImmediately, inGame, canSpin ,gender, playerMarried, isStuck, leavingRedSquare, examSeason;
	private GameAssetHouse currentHouse;
	private PlayerCar playerCar;
	private GameBoardPlayerRetirementButton retireEarlyButton;
	private String imageNameForSave;
	
	/**
	 * This is the constructor for a game player whether it has been deserialised or not
	 * @param xCoord the players x-coordinate to be painted to
	 * @param yCoord the players y-coordinate to be painted to
	 * @param playerName the players name
	 * @param gender the players gender (true = male, false = female)
	 */
	public GamePlayer(int xCoord, int yCoord, String playerName, boolean gender) {
		super(xCoord, yCoord, "");
		this.playerAssets = new ArrayList<>();
		this.playerFamily = new ArrayList<FamilyPegs>();
		this.playerLoans = new ArrayList<Loan>();
		this.gender = gender;
		this.playerName = playerName;
		this.isFirstTurn = true;
		this.playerMarried = false;
		this.inGame = true;
		this.currentHouse = null;
		this.canSpin = true;
		this.playerAge = 20;
		this.carValue = 0;
		this.assetsValue = 0;
		this.houseValue = 0;
		this.familyValue = 0; 
		this.loanValue = 0;
		
		if (gender)
		{
			this.setEntityImageByName("token_Male.png");
			this.imageNameForSave = "token_Male.png";
		}
		else
		{
			this.setEntityImageByName("token_Female.png");
			this.imageNameForSave = "token_Female.png";
		}
		
		playerCar = new PlayerCar(0, 0, CarTypes.TIER1);
		movesRemaining = 0;
		leavingRedSquare = false;
		examSeason = false;
		retireEarlyButton = new GameBoardPlayerRetirementButton(0, 0, "", this);
	}
	
	// METHODS
	@Override
	/**
	 * @author Robert Kordula 40131366 & Alex Crowley 40121793
	 * @param graphicsPanel the pal to draw the player and their assets to
	 * Overriden the drawEntity method to include drawing the players family and car
	 */
	public void drawEntity(Graphics2D graphicsPanel)
	{
		playerCar.changeCarSprite();
		playerCar.drawEntity(graphicsPanel);
		super.drawEntity(graphicsPanel);
		for (int iii = 0; iii < playerFamily.size(); iii++)
		{
			playerFamily.get(iii).drawEntity(graphicsPanel);
		}
		graphicsPanel.setFont(new Font("Arial", Font.BOLD, 20));
	}
	
	/**
	 * This method draws the players name and their current ranking together
	 * @param graphicsPanel the panel to draw the player's name to
	 * @param xCoord the x-coordinate to draw the player's name to
	 * @param yCoord the y-coordinate to draw the player's name to
	 */
	public void drawPlayerName(Graphics2D graphicsPanel, int xCoord, int yCoord)
	{
		graphicsPanel.drawString("#" + currentRanking + " " + playerName, xCoord, yCoord);
	}
	
	/**
	 * This method draws all the players key stats to the graphics panel passed in
	 * @param graphicsPanel the panel to draw the stats to
	 * @param xCoord the x-coordinate to begin the draw at
	 * @param yCoord the y-coordinate to begin the draw at
	 */
	public void drawPlayerStats(Graphics2D graphicsPanel, int xCoord, int yCoord)
	{
		graphicsPanel.setFont(new Font("Arial", Font.BOLD, 20));
		
		/**
		 * If the player is still in the game we draw their stats with black text, if not we draw the stats grey
		 */
		if(this.inGame)
		{
			graphicsPanel.setColor(Color.BLACK);
		}
		else
		{
			graphicsPanel.setColor(Color.GRAY);
		}
		
		int xCoordLocal = xCoord;
		int yCoordLocal = yCoord;

		graphicsPanel.drawImage(getEntityImage(), xCoord, yCoord, null);
		xCoordLocal += getEntityImage().getWidth() + 10;
		drawPlayerName(graphicsPanel, xCoordLocal, yCoordLocal);
		yCoordLocal += graphicsPanel.getFontMetrics().getHeight();
		graphicsPanel.drawString("Married: " + isPlayerMarried(), xCoordLocal, yCoordLocal);
		yCoordLocal += graphicsPanel.getFontMetrics().getHeight();
		graphicsPanel.drawString("Gender: " + getGenderString(), xCoordLocal, yCoordLocal);
		yCoordLocal += graphicsPanel.getFontMetrics().getHeight();
		graphicsPanel.drawString("Bank Balance: �" + getPlayerBankBalance(), xCoordLocal, yCoordLocal);
		yCoordLocal += graphicsPanel.getFontMetrics().getHeight();
		
		/**
		 * If the player does not have a career their salary is N/A, otherwise it is their salary value
		 */
		if(playerCareer == null)
		{
			graphicsPanel.drawString("Salary: N/A", xCoordLocal, yCoordLocal);
		}
		else
		{
			graphicsPanel.drawString("Salary: �" + playerCareer.getSalary(), xCoordLocal, yCoordLocal);
		}
		
		yCoordLocal += graphicsPanel.getFontMetrics().getHeight();
		retireEarlyButton.setxCoord(xCoordLocal);
		retireEarlyButton.setyCoord(yCoordLocal);
		graphicsPanel.drawImage(retireEarlyButton.getEntityImage(), retireEarlyButton.getxCoord() - getEntityImage().getWidth() - 10, retireEarlyButton.getyCoord(), null);
		
	}
	
	/**
	 * @author Alex Crowley & Robert Kordula 40131366
	 * @param x
	 * @param board
	 * @param panel
	 * Method loops until it has used up all the moves or is forced to stop, each iteration uses movePlayerOnetile() to move
	 * the player one tile, in each iteration the tile the player is currently is checked to see if it is an instance of
	 * GreenTile or RedTile in which case the appropriate action is taken
	 * Whereas the other tiles only take effect when stopping on them 
	 */
	public void movePlayerXTiles(int x, GameBoard board, Graphics2D panel)
	{
		//GameSound.carSound.play();
		int i;
		for(i = 1; i <= x; i++)
		{
			// If a red tile is encountered we must do it's effect and break from the loop, this will generally mean i != x
			if(currentTileNode.getTile() instanceof RedTile && !leavingRedSquare)
			{
				boolean canContinue = ((RedTile)currentTileNode.getTile()).performAction(this, board, x - i, panel);
				
				if (!canContinue)
				{
					break;
				}
			}
			leavingRedSquare = false;
			movePlayerOneTile(board);
			
			//If a green tile is encountered we update the player's BankBalance with their salary//
			if (currentTileNode.getTile() instanceof GreenTile)
			{
				getPaid();
				board.updateGameBoard();
				GameLogic.gameLog.addLog("PAYDAY");
			}
		}
		/*When finishing on a tile, it checks if the tile is occupied by another player, if so it will move the player to the 
		next tile until it finds an empty tile*/
		while(checkTileTaken(board) && !(currentTileNode.getTile() instanceof RedTile))
		{
			delay();
			movePlayerOneTile(board);
		}
		//Determines outcome based on type of tile landed on
		if (!(currentTileNode.getTile() instanceof GreenTile) && !(currentTileNode.getTile() instanceof RedTile))
		{
			currentTileNode.getTile().onLand(this, board, panel);
		}
		else if (currentTileNode.getTile() instanceof RedTile && !isStuck)
		{
			isStuck = ((RedTile)currentTileNode.getTile()).performAction(this, board, 0, panel);
			
		}
		board.updateGameBoard();
	}
	
	/**
	 * This method moves a player one tile forwards and determines whether a branching choice must be made, and then the delay method allows the players to actually see their tokens 
	 * move forward
	 * @author Alex Crowley 40121793
	 * @param board the game board containing the tile lists 
	 */
	public void movePlayerOneTile(GameBoard board)
	{
		/**
		 * If the current node has a side list head node then the player must choose whether to branch off or continue forwards
		 */
		if(currentTileNode.getSideListHeadNode() != null && !currentTileNode.equals(MainGameBoardTiles.startNode))
		{
			branchingChoice(board);
		}
		else
		{
			delay();
			moveToNextNode(board);
		}
		
		board.updateGameBoard();
		
		/**
		 * @Author Robert Kordula 40131366
		 * Sets players positions to the current tile and iterates through the player's family and moves them relative to the player
		 */
		for(int i = 0; i < playerFamily.size(); i++)
		{
			playerFamily.get(i).movePegToPlayer(this, i);
		}
	}
	
	/**
	 * This method asks the player whether they wish to move into one of the branches or continue forwards, if the player tries to exit the JOptionPane the method recursively calls itself.
	 * @param board
	 */
	public void branchingChoice(GameBoard board)
	{
		Object[] options = { "Go To Branch", "Continue Forwards" };
		int choice = JOptionPane.showOptionDialog(null, "Please select your movement option!", "Please Make A Choice",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, options[0]);
		
		if(choice == 0)
		{	
			delay();
			moveToSideList(board);
		}
		else if(choice == 1)
		{
			delay();
			moveToNextNode(board);
		}
		else
		{
			branchingChoice(board);
		}
	}
	
	/**
	 * This method simply sets a system delay of 1.5 seconds so the player can see their token moving instead of it "leaping" to the final tile of their turn.
	 */
	public void delay()
	{
		try
		{
			Thread.sleep(1500);
		}
		catch(InterruptedException e) 
		{
			e.printStackTrace();
		    Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * This method is used to move the player to the side list of the current node
	 * @param board
	 */
	public void moveToSideList(GameBoard board)
	{
		currentTileNode = currentTileNode.getSideListHeadNode();
		currentTileNode.getTile().movePlayerCoordsToTile(this);
	}
	
	/**
	 * This method is used to move the player to the next node in the list
	 * @param board
	 */
	public void moveToNextNode(GameBoard board)
	{
		currentTileNode = currentTileNode.getNextTileNode();
		currentTileNode.getTile().movePlayerCoordsToTile(this);
	}
	
	/**
	 * This method checks if a given tile is taken in order to determine of the player can land there, it iterates through the players in the game and if another player is on the same tile
	 * the method return true, false if no players are on the same tile
	 * @param board
	 * @return true if the tile is taken, false otherwise
	 */
	public boolean checkTileTaken(GameBoard board)
	{
		for (GamePlayer player : board.getGameLogic().getPlayers()) 
		{
			
			if(!player.equals(this) && player.getCurrentTileNode() != null && player.getCurrentTileNode().equals(currentTileNode))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * @author Robert Kordula 40131366
	 */
	public void getPaid()
	{
		//GameSound.cashSound.play();
		JOptionPane.showMessageDialog(null, "PAYDAY!!!!!!!", "", JOptionPane.INFORMATION_MESSAGE, null);
		addToPlayerBankBalance(playerCareer.getSalary());
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param amount
	 */
	public void addToPlayerBankBalance(double amount)
	{
		playerBankBalance += (int)amount;
	}
	/**
	 * @author Robert Kordula 40131366
	 * @param amount
	 * Subtracts the given amount from the player's balance, if the player does not have the required 
	 * amount forces the player to take a loan, this will loop til the player can afford their purchase
	 */
	public void subtractFromPlayerBankBalance(double amount)
	{
		while (amount > playerBankBalance)
		{
			getLoan("You are �" + (amount - playerBankBalance) + " short, you need to take out a loan");
		}
		playerBankBalance -= amount;
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param message
	 * Gives the players a choice of the size of loan to take out, the loan is added to their inventory 
	 * and the money added to their balance
	 */
	public void getLoan(String message)
	{
		Object[] options = {"�100", "�200", "�500", "�1000", "�2000", "�5000"};
		int value = JOptionPane.showOptionDialog(null, message, "",  JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		if (value == 1)
		{
			Loan loan = new Loan(200);
			playerLoans.add(loan);
			addToPlayerBankBalance(200);
		}
		else if (value == 2)
		{
			Loan loan = new Loan(500);
			playerLoans.add(loan);
			addToPlayerBankBalance(500);
		}
		else if (value == 3)
		{
			Loan loan = new Loan(1000);
			playerLoans.add(loan);
			addToPlayerBankBalance(1000);
		}
		else if (value == 4)
		{
			Loan loan = new Loan(2000);
			playerLoans.add(loan);
			addToPlayerBankBalance(2000);
		}
		else if (value == 5)
		{
			Loan loan = new Loan(5000);
			playerLoans.add(loan);
			addToPlayerBankBalance(5000);
		}
		else
		{
			Loan loan = new Loan(100);
			playerLoans.add(loan);
			addToPlayerBankBalance(100);
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param loan
	 * @return
	 * When called if the player has enough money to pay off the loan the amount is subtracted from the players balance 
	 * and the loan removed from their inventory
	 * Returns true if loan is paid offf, false if the loan can't be paid off
	 */
	public boolean payOffLoan(Loan loan)
	{
		if (playerBankBalance > (loan.getLoanAmount() * 1.25))
		{
			playerLoans.remove(loan);
			subtractFromPlayerBankBalance(loan.getLoanAmount() * 1.25);
			return true;
		}
		return false;
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param panel
	 * If the player isn't already married adds a SpousePeg to their family that is the opposite gender of the player
	 * The SpousePeg is then set to a position relative to the player
	 */
	public void getMarried(Graphics2D panel)
	{
		if (!playerMarried)
		{
			SpousePeg spouse = new SpousePeg(0, 0, !gender);
			subtractFromPlayerBankBalance(500);
			playerFamily.add(0, spouse);
			spouse.movePegToPlayer(this, 0);
			spouse.drawEntity(panel);
			playerMarried = true;
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * Upgrades the players car to the next tier, subtracts 1000 from their balance
	 */
	public void upgradeCar()
	{
		playerCar.upgradeCar();
		subtractFromPlayerBankBalance(1000);
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param gender
	 * @param panel
	 * If the player's family is less than three, a ChildPeg of the input gender is added to the player's family
	 * The ChildPeg is then set to a position relative to the player
	 */
	public void haveChild(boolean gender, Graphics2D panel)
	{
		if (playerFamily.size() < 3 && playerMarried)
		{
			ChildPeg child = new ChildPeg(0, 0, gender);
			this.playerFamily.add(child);
			child.movePegToPlayer(this, playerFamily.size() - 1);
			child.drawEntity(panel);
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param type
	 * @param panel
	 * If the player's family is less than three, a PetPeg of the input type is added to the player's family
	 * The PetPeg is then set to a position relative to the player
	 */
	public void getPet(boolean type, Graphics2D panel)
	{
		PetPeg pet = new PetPeg(0, 0, type);
		if (playerFamily.size() < 3)
		{
			this.playerFamily.add(pet);
			pet.movePegToPlayer(this, playerFamily.size() - 1);
			pet.drawEntity(panel);
		}
	}
	
	/**@author Alex
	 * Modified by: Rachael 14/04/16
	 * 
	 * Method sets currentHouse equal to the house the user wishes to buy
	 * Method generates the current value of the house using the current housing market multiplier and the house base price
	 * This is completed using the generateNewHousePriceUsingMarket method from GameSpinner
	 * The player's bank account balance is reduced by the value generated
	 * 
	 * @param houseToBuy = house the user has chosen to buy
	 */
	public void buyHouse(GameAssetHouse houseToBuy)
	{
		GameSound.doorBellSound.play();
		subtractFromPlayerBankBalance(houseToBuy.getCurrentAssetValue());
		currentHouse = houseToBuy;
	}
	
	
	/**@author Alex
	 * Modified by: Rachael 14/04/16
	 * 
	 * Method generates the current value of the house using the current housing market multiplier and the house base price
	 * This is completed using the generateNewHousePriceUsingMarket method from GameSpinner
	 * The player's bank account balance is increased by the value generated
	 * Method sets currentHouse equal to null
	 */
	 
	public void sellAsset(GameAsset asset)
	{
		playerAssets.remove(asset);
		asset.sellAsset(this);
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param board
	 * Adds the players current house back into the appropriate deck, allowing other players to buy it
	 */
	public void sellHouse(GameBoard board)
	{
		switch (currentHouse.getDistrict())
		{
		case Cathedral:
			board.getCathedralHouses().addCard(currentHouse);
			break;
		case Falls:
			board.getFallsHouses().addCard(currentHouse);
			break;
		case Titanic:
			board.getTitanicHouses().addCard(currentHouse);
			break;
		case University:
			board.getUniHouses().addCard(currentHouse);
			break;
		default:
			break;
			
		}
		this.currentHouse.sellAsset(this);
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param house
	 * @param board
	 * Gives the player the current price of the house they wish to buy and if the player wishes to buy the current price is 
	 * subtracted from their balance and their current house is sold and the new house is set as their current hosue
	 */
	public void buyNewHouse(GameAssetHouse house, GameBoard board)
	{
		String message;
		if (house.getOriginalAssetValue() > house.getCurrentAssetValue())
		{
			message = "Due to recent stock decreases, we are able to offer this house below what was previously advertised: �";
		}
		else if (house.getOriginalAssetValue() < house.getCurrentAssetValue())
		{
			message = "Due to recent stock increaes, we regret to tell you the house now costs more than what was previously advertised: �";
		}
		else
		{
			message = "The house is available to buy at : �";
		}
		
		Object[] options = { "YES", "NO" };
		int choice = JOptionPane.showOptionDialog(null, message + house.getCurrentAssetValue() + " would you still like to buy " + house.getAssetName(),
		             "", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, options[0]);
		if (choice == 0)
		{
			if (getCurrentHouse() != null)
			{
				sellHouse(board);
			}
			buyHouse(house);
		}
	}

	/**
	 * Overriding compareTo allows us to rank the players, they are ranked by bank balance and in the case the bank balances are the same they are ranked by name alphabetically
	 */
	@Override
	public int compareTo(GamePlayer playerComparedTo) {
		
		if(this.playerBankBalance > playerComparedTo.getPlayerBankBalance())
		{
			return 1;
		}
		else if(this.playerBankBalance < playerComparedTo.getPlayerBankBalance())
		{
			return -1;
		}
		else if(this.playerName.compareToIgnoreCase(playerComparedTo.getPlayerName()) < 0)
		{
			return 1;
		}
		else if(this.playerName.compareToIgnoreCase(playerComparedTo.getPlayerName()) > 0)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param board
	 * Method checks to see if the player has a uni degree or not, and changes their career based on that
	 */
	public void changeCareer(GameBoard board)
	{
		if (!isUniversityImmediately())
		{
			changeCareer(board.getCareers());
		}
		else
		{
			changeCareer(board.getUniCareers());
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param careerDeck
	 * Given the deck provided this method uses the Deck<E> class to draw three random careers for the player to choose
	 */
	public void changeCareer(Decks<Careers> careerDeck)
	{
		Careers career = careerDeck.drawThreeForDecision();
		if (career != null)
		{
			careerDeck.addCard(getPlayerCareer());
			setPlayerCareer(career);
		}
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @param multiplier
	 * Multiplies the player's salary by the given amount
	 */
	public void promoted(int multiplier)
	{
		playerCareer.setSalary(playerCareer.getSalary() * (multiplier/100));
	}
	
	/**
	 * @author Robert Kordula 40131366
	 * @return
	 * Totals all the player's assets 
	 */
	public int getTotalAssetWorth()
	{
		double totalAssetWorth = 0;
		
		for (GameAsset gameAsset : playerAssets) {
			totalAssetWorth += gameAsset.getCurrentAssetValue();
		}
		
		return (int)totalAssetWorth;
	}

	// GETTERS AND SETTERS
	public boolean isStuck() {
		return isStuck;
	}

	public boolean isExamSeason() {
		return examSeason;
	}

	public void setExamSeason(boolean examSeason) {
		this.examSeason = examSeason;
	}

	public boolean isLeavingRedSquare() {
		return leavingRedSquare;
	}

	public void setLeavingRedSquare(boolean leavingRedSquare) {
		this.leavingRedSquare = leavingRedSquare;
	}

	public void setStuck(boolean isStuck) {
		this.isStuck = isStuck;
	}

	public String getGenderString()
	{
		if(gender)
		{
			return "Male";
		}
		else
		{
			return "Female";
		}
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPlayerAssets(int playerAssetsValue) {
		this.playerAssetsValue = playerAssetsValue;
	}

	public int getPlayerBankBalance() {
		return playerBankBalance;
	}

	public void setPlayerBankBalance(int playerBankBalance) {
		this.playerBankBalance = playerBankBalance;
	}

	public int getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(int playerAge) {
		this.playerAge = playerAge;
	}

	public int getMovesRemaining() {
		return movesRemaining;
	}

	public void setMovesRemaining(int movesRemaining) {
		this.movesRemaining = movesRemaining;
	}

	public int getPlayerSalary()
	{
		return playerSalary;
	}

	public PlayerCar getPlayerCar() {
		return playerCar;
	}

	public void setPlayerCar(PlayerCar playerCar) {
		this.playerCar = playerCar;
	}

	public void setPlayerSalary(int playerSalary)
	{
		this.playerSalary = playerSalary;
	}

	public int getPlayerAssetsValue() {
		return playerAssetsValue;
	}

	public void setPlayerAssetsValue(int playerAssetsValue) {
		this.playerAssetsValue = playerAssetsValue;
	}

	public TileNode getCurrentTileNode() {
		return currentTileNode;
	}

	public void setCurrentTileNode(TileNode currentTileNode) {
		this.currentTileNode = currentTileNode;
		
		if(currentTileNode != null)
		{
			currentTileNode.getTile().movePlayerCoordsToTile(this);
		}
	}
	
	public int getBankBalanceAlone()
	{
		return playerBankBalance - carValue - assetsValue - familyValue - houseValue - loanValue;
	}

	public void setPlayerAssets(ArrayList<GameAsset> playerAssets) {
		this.playerAssets = playerAssets;
	}
	
	public ArrayList<GameAsset> getPlayerAssets()
	{
		return playerAssets;
	}

	public Careers getPlayerCareer() {
		return playerCareer;
	}

	/**
	 * @author Robert Kordula 40131366
	 * @param playerCareer
	 * This methods sets the player's career as well as update the player's image to correspond with their career
	 */
	public void setPlayerCareer(Careers playerCareer) {
		this.playerCareer = playerCareer;
		String imageName = "";
		if (playerCareer.getCareerTitle().equalsIgnoreCase("Artist"))
		{
			imageName = imageName.concat("03");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Chef"))
		{
			imageName = imageName.concat("04");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Manager"))
		{
			imageName = imageName.concat("05");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Fireman"))
		{
			imageName = imageName.concat("07");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Police"))
		{
			imageName = imageName.concat("06");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Doctor"))
		{
			imageName = imageName.concat("02");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Accountant"))
		{
			imageName = imageName.concat("01");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Software Engineer"))
		{
			imageName = imageName.concat("08");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Chemical Engineer"))
		{
			imageName = imageName.concat("09");
		}
		else if (playerCareer.getCareerTitle().equalsIgnoreCase("Lawyer"))
		{
			imageName = imageName.concat("01");
		}
		else
		{
			imageName = imageName.concat("token");
		}
		
		if (isGender())
		{
			imageName = imageName.concat("_Male.png");
		}
		else
		{
			imageName = imageName.concat("_Female.png");
		}
		setEntityImageByName(imageName);
	}

	public boolean isFirstTurn() {
		return isFirstTurn;
	}

	public void setFirstTurn(boolean isFirstTurn) {
		this.isFirstTurn = isFirstTurn;
	}

	public boolean isUniversityImmediately() {
		return universityImmediately;
	}

	public void setUniversityImmediately(boolean universityImmediately) {
		this.universityImmediately = universityImmediately;
	}
	
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public ArrayList<FamilyPegs> getPlayerFamily() {
		return playerFamily;
	}

	public void setPlayerFamily(ArrayList<FamilyPegs> playerFamily) {
		this.playerFamily = playerFamily;
	}
	
	public boolean isPlayerMarried() {
		return playerMarried;
	}

	public void setPlayerMarried(boolean playerMarried) {
		this.playerMarried = playerMarried;
	}

	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public GameAssetHouse getCurrentHouse() {
		return currentHouse;
	}

	public void setCurrentHouse(GameAssetHouse currentHouse) {
		this.currentHouse = currentHouse;
	}

	public int getCurrentRanking() {
		return currentRanking;
	}

	public void setCurrentRanking(int currentRanking) {
		this.currentRanking = currentRanking;
	}

	public boolean isCanSpin() {
		return canSpin;
	}

	public void setCanSpin(boolean canSpin) {
		this.canSpin = canSpin;
	}	
	public ArrayList<Loan> getPlayerLoans() {
		return playerLoans;
	}

	public void setPlayerLoans(ArrayList<Loan> playerLoans) {
		this.playerLoans = playerLoans;
	}

	public GameBoardPlayerRetirementButton getRetireEarlyButton() {
		return retireEarlyButton;
	}

	public void setRetireEarlyButton(GameBoardPlayerRetirementButton retireEarlyButton) {
		this.retireEarlyButton = retireEarlyButton;
	}

	public int getCarValue() {
		return carValue;
	}

	public void setCarValue(int carValue) {
		this.carValue = carValue;
	}

	public int getAssetsValue() {
		return assetsValue;
	}

	public void setAssetsValue(int assetsValue) {
		this.assetsValue = assetsValue;
	}

	public int getFamilyValue() {
		return familyValue;
	}

	public void setFamilyValue(int familyValue) {
		this.familyValue = familyValue;
	}

	public int getHouseValue() {
		return houseValue;
	}

	public void setHouseValue(int houseValue) {
		this.houseValue = houseValue;
	}

	public int getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(int loanValue) {
		this.loanValue = loanValue;
	}

	public String getImageNameForSave() {
		return imageNameForSave;
	}

	public void setImageNameForSave(String imageNameForSave) {
		this.imageNameForSave = imageNameForSave;
	}
	
	
}
