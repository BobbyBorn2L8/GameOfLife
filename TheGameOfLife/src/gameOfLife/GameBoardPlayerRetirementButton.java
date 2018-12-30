package gameOfLife;

import javax.swing.JOptionPane;

public class GameBoardPlayerRetirementButton extends GameEntity {
	
	private GamePlayer player;

	/**
	 * The constructor for a GameBoardPlayerRetirementButton
	 * @param xCoord the x coordinate of the button
	 * @param yCoord the y coordinate of the button
	 * @param imageName dummy input as the image name is constant
	 * @param player the player this button can modify
	 * @author Alex Crowley 40121793
	 */
	public GameBoardPlayerRetirementButton(int xCoord, int yCoord, String imageName, GamePlayer player) {
		super(xCoord, yCoord, "btn_retireearly.png");
		this.player = player;
	}

	/**
	 * This method initially checks if the player is in the game, if not they player is notified they cannot use the button
	 * @param gameLogic
	 * @author Alex Crowley 40121793
	 */
	public void buttonPressedEffect(GameLogic gameLogic) {
		// TODO Auto-generated method stub
		
		if(player.isInGame())
		{
			retirePlayerOption(gameLogic);
		}
		else
		{
			Object[] options = {"OK"};
		    JOptionPane.showOptionDialog(gameLogic.getBoard().getMainFrame(),  player.getPlayerName() + " is already retired!", "Cannot Retire", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		}	
	}
	
	/**
	 * This method checks if the player wishes to retire early, if they do the player retires if not no other action is taken
	 * @param gameLogic
	 * @author Alex Crowley 40121793
	 */
	private void retirePlayerOption(GameLogic gameLogic)
	{
		Object[] options = { "Retire Early", "Remain In Game" };
		int choice = JOptionPane.showOptionDialog(null, "Are you sure you wish to retire early?", "Retire Early?",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, options[0]);
		
		if(choice == 0)
		{
			player.setInGame(false);
			gameLogic.endGameTotalForPlayer(player);
			player.setCurrentTileNode(null);
			
			gameLogic.getBoard().updateGameBoard();
			
			if(gameLogic.checkAllPlayersRetired())
			{
				gameLogic.endGame();
				gameLogic.getBoard().updateGameBoard();
			}
		}
	}

}
