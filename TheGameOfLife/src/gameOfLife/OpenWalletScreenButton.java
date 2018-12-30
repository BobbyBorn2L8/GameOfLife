package gameOfLife;

public class OpenWalletScreenButton extends GameEntity
{

	public OpenWalletScreenButton() {
		super(1350, 800, "wallet02.png");
		// TODO Auto-generated constructor stub
	}
	
	public void buttonPressedEffect(GameLogic gameLogic) {
		new GamePlayerWallet(gameLogic.getCurrentPlayer());
	}
}
