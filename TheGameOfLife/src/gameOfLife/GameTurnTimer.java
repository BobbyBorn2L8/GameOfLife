package gameOfLife;

import java.io.Serializable;
import java.util.Timer;
import java.util.TimerTask;

public class GameTurnTimer extends Timer implements Serializable{
	
	private GameLogic gameLogic;
	private final int GAME_TURN_TIME = 60;
	
	public GameTurnTimer(GameLogic gameLogic)
	{
		this.gameLogic = gameLogic;
	}
	
	public void beginTurnTiming()
	{
		gameLogic.getCurrentPlayer().setCanSpin(true);
		
		if(gameLogic.getCurrentPlayer().isFirstTurn())
		{
			gameLogic.playerChoiceWorkVSQueens(gameLogic.getCurrentPlayer());
		}
		
		this.schedule(new TimerTask() {
			
			@Override
			public void run() 
			{
				// TODO Auto-generated method stub
				
				if(gameLogic.getCurrentPlayer().isCanSpin())
				{
					gameLogic.moveTurnToNextPlayer();
					beginTurnTiming();
				}
				else
				{
					beginTurnTiming();
				}
			}
		}, GAME_TURN_TIME * 1000);
	}
	
	public void endTurnTiming()
	{
		this.cancel();
	}
	

}
