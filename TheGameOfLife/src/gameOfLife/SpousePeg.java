package gameOfLife;

/**
 * @author Robert Kordula 40131366
 * PetPeg extends FamilyPegs so all three kinds of pegs can be kept in one list, PetPeg deals with its own
 * movement related to the player its tied to
 */
public class SpousePeg extends FamilyPegs
{
	private boolean gender;
	
	public SpousePeg(int xCoord, int yCoord, boolean gender) 
	{
		super(xCoord, yCoord, "");
		String imageName;
		if (gender)
		{
			imageName = "token_Male.png";
		}
		else
		{
			imageName = "token_Female.png";
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
		this.setxCoord(player.getxCoord());
		this.setyCoord(player.getyCoord() + player.getEntityImage().getHeight() - getEntityImage().getHeight() + 20);
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}
}
