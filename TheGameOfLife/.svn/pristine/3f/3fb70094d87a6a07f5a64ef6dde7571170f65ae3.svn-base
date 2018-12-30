package gameOfLife;

/**
 * @author Robert Kordula 40131366
 * Abstract class for all pegs that can be added to the players car
 */
public abstract class FamilyPegs extends GameEntity
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5532598597170565868L;

	public FamilyPegs(int xCoord, int yCoord, String imageName) 
	{
		super(xCoord, yCoord, imageName);
	}
	
	/**
	 * @param player
	 * @param position
	 * Abstract method makes sure all objects that inherit FamilyPegs has a method to move the peg
	 */
	public abstract void movePegToPlayer(GamePlayer player, int position);
}
