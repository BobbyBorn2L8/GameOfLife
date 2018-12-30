package gameOfLife;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public abstract class GameEntity implements Serializable{
	
	/**
	 * Instance Variables
	 */
	private int xCoord, yCoord;
	transient private BufferedImage entityImage;
	private String entityImageName;
	
	/**
	 * Constrctor for a GameEntity
	 * @param xCoord the x coordinate of the entity
	 * @param yCoord the y coordinate of the entity
	 * @param imageName the name of the image used for the game entity
	 * @author Alex Crowley 40121793
	 */
	public GameEntity(int xCoord, int yCoord, String imageName)
	{
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.entityImage = null;
		
	    setEntityImageByName(imageName);
	}
	
	/**
	 * This method draws the entity's image to the canvas at its current x/y coordinates
	 * @param graphicsPanel
	 * @author Alex Crowley 40121793
	 */
	public void drawEntity(Graphics2D graphicsPanel)
	{
		graphicsPanel.drawImage(entityImage, xCoord, yCoord, null);
	}
	
	/**
	 * @author Alex Crowley 40121793
	 * @param xCoord the x coordinate to test as being within the bounds of the entity image
	 * @param yCoord the y coordinate to test as being within the bounds of the entity image
	 * @return true if the coordinates are within the bounds of the entity image, false otherwise
	 */
	public boolean entityTouched(int xCoord, int yCoord)
	{
		return (xCoord >= this.xCoord && xCoord <= (this.xCoord + entityImage.getWidth()) 
				&& yCoord >= this.yCoord && yCoord <= (this.yCoord + entityImage.getHeight()));
	}
	
	/**
	 * This method sets the entity image by the passed in imageName, the method saves this name for use when the entity is saved
	 * @author Alex Crowley 40121793
	 * @param imageName
	 */
	public void setEntityImageByName(String imageName)
	{
		try
	    {
	    	this.entityImage = ImageIO.read(getClass().getResource(imageName));
	    	this.entityImageName = imageName;
	    }
	    catch (IOException e) 
	    {
	        System.out.println(e.toString());
	        e.printStackTrace();
	    }
	    
	}
	
	/**
	 * Slightly modified getter whereby if the player is saved and then loaded the image is set and then returned
	 * @return the entity's image
	 */
	public BufferedImage getEntityImage()
	{
		if(this.entityImage == null)
		{
			this.setEntityImageByName(entityImageName);
		}
		
		return entityImage;
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}
	
	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public void setEntityImage(BufferedImage entityImage) {
		this.entityImage = entityImage;
	}
}
