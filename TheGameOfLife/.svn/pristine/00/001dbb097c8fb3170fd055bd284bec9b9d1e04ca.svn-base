package gameOfLife;

/**
 * @author Robert Kordula 40131366
 * PetPeg extends FamilyPegs so all three kinds of pegs can be kept in one list, PetPeg deals with its own
 * movement related to the player its tied to
 */
public class PetPeg extends FamilyPegs
{
	//Boolean to store type of pet, true is dog, false is cat//
	private boolean type;
	public PetPeg(int xCoord, int yCoord, boolean type) 
	{
		super(xCoord, yCoord, "");
		this.setType(type);
		String imageName;
		if (type)
		{
			imageName = "pet_01.png";
		}
		else
		{
			imageName = "pet_02.png";
		}
		
	    setEntityImageByName(imageName);
	}
	
	@Override
	/**
	 * @param player
	 * @param position
	 * Takes in the player the peg is tied to and with the passed in position determines where it should be in relation to the 
	 * player when moving
	 */
	public void movePegToPlayer(GamePlayer player, int position) 
	{
		int XDifference = 0;
		int YDifference = 0;
		if (player.isPlayerMarried())
		{
			if (position == 1)
			{
				XDifference = getEntityImage().getWidth() + 2;
				YDifference = 5;
			}
			else if (position == 2)
			{
				XDifference = getEntityImage().getWidth() + 2;
				YDifference = player.getPlayerFamily().get(0).getyCoord() - player.getyCoord() + 15;
			}
		}
		else
		{
			if (position == 0)
			{
				XDifference = 0;
				YDifference = player.getEntityImage().getHeight() - getEntityImage().getHeight() + 15;
			}
			else if (position == 1)
			{
				XDifference = getEntityImage().getWidth() + 20;
				YDifference = 15;
			}
		}
		this.setxCoord(player.getxCoord() - XDifference);
		this.setyCoord(player.getyCoord() + YDifference);	
	}
	
	//Getters and setters//
	public boolean isType() {
		return type;
	}
	public void setType(boolean type) {
		this.type = type;
	}
}
