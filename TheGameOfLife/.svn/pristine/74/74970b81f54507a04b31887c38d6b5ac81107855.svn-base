package gameOfLife;

import javax.swing.JOptionPane;

/**
 * @author Robert Kordula 40131366
 * An clickable entity to open the LoanScreen
 */
public class OpenLoans extends GameEntity
{

	public OpenLoans() {
		super(1600, 800, "letters01.png");
	}
	
	/**
	 * @param logic
	 * When clicked if the player has any loans will bring up the loans screen
	 */
	public void buttonPressed(GameLogic logic)
	{
		if (logic.getCurrentPlayer().getPlayerLoans().size() > 0)
		{
			new LoanScreen(logic.getCurrentPlayer());
		}
		else
		{
			JOptionPane.showConfirmDialog(null, "Congratulations you have no loan");
		}
	}
}
