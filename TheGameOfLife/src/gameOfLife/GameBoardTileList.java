package gameOfLife;

import java.awt.Graphics2D;
import java.io.Serializable;

public class GameBoardTileList implements Serializable{
	
	private TileNode headTileNode;
	private int listSize;
	private final int TILE_SPACING_PX = 10;
	
	/**
	 * Constructor of a GameBoardTileList object
	 * @author Alex Crowley 40121793
	 */
	public GameBoardTileList()
	{
		this.listSize = 0;
	}
	
	/**
	 * This method adds the passed in node to the list, using the direction to set the new tiles coordinates based of it's predecessor. If the node being added is the first node
	 * it is set as the head node and the direction is not important
	 * @param tileNodeToAdd
	 * @param direction
	 * @author Alex Crowley 40121793
	 */
	public void addNode(TileNode tileNodeToAdd, char direction)	
	{
		if(listSize == 0)
		{
			this.headTileNode = tileNodeToAdd;
		}
		else
		{
			TileNode currentNode = this.headTileNode;
			
			while(currentNode.getNextTileNode() != null)
			{
				currentNode = currentNode.getNextTileNode();
			}
			
			if(direction == 'u' || direction == 'U')
			{
				tileNodeToAdd.getTile().setxCoord(currentNode.getTile().getxCoord());
				tileNodeToAdd.getTile().setyCoord(currentNode.getTile().getyCoord() - currentNode.getTile().getEntityImage().getHeight() - TILE_SPACING_PX);
			}
			else if(direction == 'd' || direction == 'D')
			{
				tileNodeToAdd.getTile().setxCoord(currentNode.getTile().getxCoord());
				tileNodeToAdd.getTile().setyCoord(currentNode.getTile().getyCoord() + currentNode.getTile().getEntityImage().getHeight() + TILE_SPACING_PX);
			}
			else if(direction == 'r' || direction == 'R')
			{
				tileNodeToAdd.getTile().setxCoord(currentNode.getTile().getxCoord() + currentNode.getTile().getEntityImage().getWidth() + TILE_SPACING_PX);
				tileNodeToAdd.getTile().setyCoord(currentNode.getTile().getyCoord());
			}
			else if(direction == 'l' || direction == 'L')
			{
				tileNodeToAdd.getTile().setxCoord(currentNode.getTile().getxCoord() - currentNode.getTile().getEntityImage().getWidth() - TILE_SPACING_PX);
				tileNodeToAdd.getTile().setyCoord(currentNode.getTile().getyCoord());
			}
			
			currentNode.setNextTileNode(tileNodeToAdd);
		}
		
		listSize++;
	}
	
	/**
	 * Iterates through all the nodes in the list drawing the individual nodes, if there are no nodes in the list this method simply returns
	 * @param graphicsPanel
	 * @author Alex Crowley 40121793
	 */
	public void drawList(Graphics2D graphicsPanel)
	{
		if(listSize == 0)
		{
			return;
		}
		else
		{
			TileNode currentNode = this.headTileNode;
		
			for(int i = 1; i <= listSize; i++)
			{
				currentNode.getTile().drawEntity(graphicsPanel);
				currentNode = currentNode.getNextTileNode();
			}
		}
	}

	public TileNode getHeadTileNode() {
		return headTileNode;
	}

	public void setHeadTileNode(TileNode headTileNode) {
		this.headTileNode = headTileNode;
	}

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
}

class TileNode implements Serializable{
	
	private Tile tile;
	private TileNode nextTileNode;
	private TileNode sideListHeadNode;
	
	/**
	 * Constructor for a TileNode object
	 * @param tile
	 * @param sideListHeadNode the head node of a side list, null in almost all cases
	 * @author Alex Crowley 40121793
	 */
	public TileNode(Tile tile, TileNode sideListHeadNode)
	{
		this.tile = tile;
		this.sideListHeadNode = sideListHeadNode;
	}
	
	/**
	 * Sets the side lift head node of the current tile, used in the case that the current tile node is sued to connect to the start of a quarter
	 * @param sideListHeadNode
	 * @param direction the direction in which to add the sideListHeadNode, used in the same way as the addNode method of the GameBoardTileList object
	 * @author Alex Crowley 40121793
	 */
	public void setSideListHeadNode(TileNode sideListHeadNode, char direction) {
		this.sideListHeadNode = sideListHeadNode;
		
		if(direction == 'u' || direction == 'U')
		{
			sideListHeadNode.getTile().setxCoord(getTile().getxCoord());
			sideListHeadNode.getTile().setyCoord(getTile().getyCoord() - getTile().getEntityImage().getHeight() - 10);
		}
		else if(direction == 'd' || direction == 'D')
		{
			sideListHeadNode.getTile().setxCoord(getTile().getxCoord());
			sideListHeadNode.getTile().setyCoord(getTile().getyCoord() + getTile().getEntityImage().getHeight() + 10);
		}
		else if(direction == 'r' || direction == 'R')
		{
			sideListHeadNode.getTile().setxCoord(getTile().getxCoord() + getTile().getEntityImage().getWidth() + 10);
			sideListHeadNode.getTile().setyCoord(getTile().getyCoord());
		}
		else if(direction == 'l' || direction == 'L')
		{
			sideListHeadNode.getTile().setxCoord(getTile().getxCoord() - getTile().getEntityImage().getWidth() - 10);
			sideListHeadNode.getTile().setyCoord(getTile().getyCoord());
		}
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public TileNode getNextTileNode() {
		return nextTileNode;
	}

	public void setNextTileNode(TileNode nextTileNode) {
		this.nextTileNode = nextTileNode;
	}

	public TileNode getSideListHeadNode() {
		return sideListHeadNode;
	}


}
