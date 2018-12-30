package gameOfLife;

import javax.swing.JOptionPane;

public class GameBoardEndTurnButton extends GameEntity {
	
	private GameLogic gameLogic;

	/**
	 * Constructor of the GameBoardEndTurnButton
	 * @param xCoord the x coordinate of the button
	 * @param yCoord the y coordinate of the button
	 * @param imageName a dummy input as the image name is a constant 
	 * @param gameLogic the GameLogic object modified by the button
	 * @author Alex Crowley 40121793
	 */
	public GameBoardEndTurnButton(int xCoord, int yCoord, String imageName, GameLogic gameLogic) {
		super(xCoord, yCoord, "btn_EndTurn.png");
		// TODO Auto-generated constructor stub
		this.gameLogic = gameLogic;
	}
	
	/**
	 * This method is the effect of clicking the button, they player is asked if they want to end their turn, if they choose yes their turn is ended
	 * @author Alex Crowley 40121793
	 */
	public void buttonPressedEffect()
	{
		Object[] options = { "End Turn", "Cancel" };
		int choice = JOptionPane.showOptionDialog(gameLogic.getBoard().getMainFrame(), "Are you sure you wish to end your turn?", "Exit Turn?",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		             null, options, options[0]);
		
		if(choice == 0)
		{
			this.gameLogic.moveTurnToNextPlayer();
			this.gameLogic.getBoard().updateGameBoard();
		}
		else if(choice != 1)
		{
			buttonPressedEffect();
		}
	}

}
