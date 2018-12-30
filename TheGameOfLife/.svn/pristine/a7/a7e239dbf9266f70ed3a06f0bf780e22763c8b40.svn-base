package gameOfLife;

import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameEventsLog implements Serializable{

	private ArrayList<String> eventLog;
	private Date currentTime;
	private final int upperLimit = 10;
	private GameLogic gameLogic;

	/**
	 * Constructor for GameEventsLog object
	 * @param gameLogic
	 * @author Alex Crowley 40121793
	 */
	public GameEventsLog(GameLogic gameLogic) {
		eventLog = new ArrayList<String>();
		this.gameLogic = gameLogic;
	}
	
	/**
	 * Creates a string from all the logs in the eventLog arraylist
	 * @return returns the string of all logs
	 * @author Alex Crowley 40121793
	 */
	public String constructStringFromLogs()
	{
		String eventsString = "";
		
		for (String logString : eventLog) 
		{
			eventsString += logString + "\n";
		}
		
		return eventsString;
	}
	
	/**
	 * Opens a screen to show all the event logs using the String from the above method
	 * @author Alex Crowley 40121793
	 */
	public void displayGameEventsLogWindow()
	{
		new GameEventsLogWindow(constructStringFromLogs());
	}

	/**
	 * Adds the passed in log, and formats it to include the current time and the player who did the event
	 * @param logToAdd
	 * @author Alex Crowley 40121793
	 */
	public void addLog(String logToAdd) {
		eventLog.add(getCurrentTime() + ": " + gameLogic.getCurrentPlayer().getPlayerName() + " -> " + logToAdd);
	}

	/**
	 * @return the current system time formatted to only include the time and remove the date
	 * @author Alex Crowley 40121793
	 */
	public String getCurrentTime()
	{
		currentTime = new Date();
		return DateFormat.getTimeInstance().format(currentTime);
	}
	
	public void removeLogAtIndex(int index)
	{
		eventLog.remove(index);
	}
	
	public void clearAllEventLogs()
	{
		eventLog.clear();
	}
	
	/**
	 * Draws the logs to the canvas, currently unused (made redundant by the GameEventsLogWindow
	 * @param graphicsPanel
	 * @param xCoord starting x coordinate to draw to
	 * @param yCoord starting y coordinate to draw to
	 * @author Alex Crowley 40121793
	 */
	public void drawLogsToCanvas(Graphics2D graphicsPanel, int xCoord, int yCoord)
	{
		graphicsPanel.setFont(new Font("Arial", Font.BOLD, 16));
		int yCoordLocal = yCoord;
		int yCoordIncrement = graphicsPanel.getFontMetrics().getHeight();
		
		graphicsPanel.drawString("GAME LOG:", xCoord, yCoordLocal);
		yCoordLocal += yCoordIncrement;
		graphicsPanel.setFont(new Font("Arial", Font.BOLD, 12));
		yCoordIncrement = graphicsPanel.getFontMetrics().getHeight();
		
		for(int i = 0; i < eventLog.size(); i++)
		{
			System.out.println(eventLog.get(i));
			graphicsPanel.drawString(eventLog.get(i), xCoord, yCoordLocal);
			yCoordLocal += yCoordIncrement;
			
			if(i == upperLimit)
			{
				break;
			}
		}
	}

}
