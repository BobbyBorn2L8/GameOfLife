package gameOfLife;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class GamePlayerWallet extends CommonScreenAttributes{
	
	private Rectangle cashBounds, cardBounds, photoBounds;
	private JFrame walletFrame;
	private JPanel pane;
	private Canvas canvas;
	private ImageIcon walletImage;
	private JLabel walletLabel;
	private GridBagConstraints gbc = new GridBagConstraints();
	private GamePlayer player;
	
	/**
	 * Opens a GamePlayerWallet object and sets up the rectangle bounds of the screen
	 * @param player
	 * @author Alex Crowley 40121793
	 */
	public GamePlayerWallet(GamePlayer player)
	{
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		this.player = player;
		
		pane = new JPanel(new GridBagLayout());
		
		walletImage = new ImageIcon(getClass().getResource("walletimage.png"));
		walletLabel = new JLabel(walletImage);
		
		cashBounds = new Rectangle(0, 0, 630, 170);
		cardBounds = new Rectangle(0, 170, walletImage.getIconWidth() / 2, walletImage.getIconHeight() - 170);
		photoBounds = new Rectangle(walletImage.getIconWidth() / 2, 170, walletImage.getIconWidth() / 2, walletImage.getIconHeight() - 170);
		
		addComponent(pane, walletLabel, gbc, 0, 0, 1, 1);
		
		pane.addMouseListener(new MouseAdapter() {
						
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				checkBounds(e.getX(), e.getY(), player);
				
			}
		});
		
		walletFrame = new JFrame(player.getPlayerName() + "'s Wallet");
		walletFrame.setBackground(new Color(73,189,53));
		walletFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		walletFrame.add(pane);
		walletFrame.setSize(walletImage.getIconWidth(), walletImage.getIconHeight() + 35);
		walletFrame.setResizable(false);
		walletFrame.setLocationRelativeTo(null);
		walletFrame.setVisible(true);
		
		JOptionPane.showMessageDialog(walletFrame, "Click the cash to view your current bank balance, the credit cards to view your assets and the photo card to view your family and pets.");
	}
	
	/**
	 * Checks the bounds of the rectangles to see if the passed in coordinates lie within any fo the bounds
	 * @param xCoord
	 * @param yCoord
	 * @param player the player who's wallet we have opened
	 * @author Alex Crowley 40121793
	 */
	public void checkBounds(int xCoord, int yCoord, GamePlayer player)
	{
		if(cashBoundsTouched(xCoord, yCoord))
		{
			// Open bankbalance window
			System.out.println("Cash Bounds Touched");
			cashBoundsEffect();
		}
		else if(cardBoundsTouched(xCoord, yCoord))
		{
			// Open assets window
			System.out.println("Card Bounds Touched");
			
			if(player.getPlayerAssets().size() == 0)
			{
				JOptionPane.showMessageDialog(walletFrame, player.getPlayerName() + " has no assets!", "No Assets", JOptionPane.ERROR_MESSAGE, null);
			}
			else
			{
				new GamePlayerAssetScreen(player);
			}
		}
		else if(photoBoundsTouched(xCoord, yCoord))
		{
			// Open family and souvenir window
			System.out.println("Photo Bounds Touched");
			
			if(player.getPlayerFamily().size() == 0)
			{
				JOptionPane.showMessageDialog(walletFrame, player.getPlayerName() + " has no family!", "No Family", JOptionPane.ERROR_MESSAGE, null);
			}
			else
			{
				new GamePlayerFamilyScreen(player);
			}	
		}
		else
		{
			JOptionPane.showMessageDialog(walletFrame, "ERROR: Click the cash to view your current bank balance, the credit cards to view your assets and the photo card to view your family and pets.", "ERROR", JOptionPane.ERROR_MESSAGE, null);
		}
	}
	
	/**
	 * Informs the player of the bank balance and salary if the cash in the image was clicked
	 * @author Alex Crowley 40121793
	 */
	private void cashBoundsEffect()
	{
		JOptionPane.showMessageDialog(walletFrame, player.getPlayerName() + "'s Bank Balance Is: £" + player.getPlayerBankBalance() + "\n" + player.getPlayerName() + "'s Salary Is: £" + player.getPlayerCareer().getSalary());
	}
	
	/**
	 * Checks if the passed in coordinates are within the bounds of the cash rectangle
	 * @param xCoord
	 * @param yCoord
	 * @return true if the coordinates are within the cash rectangle
	 * @author Alex Crowley 40121793
	 */
	private boolean cashBoundsTouched(int xCoord, int yCoord)
	{
		return (xCoord >= cashBounds.x && xCoord <= (cashBounds.x + cashBounds.width)) && (yCoord >= cashBounds.y && yCoord <= (cashBounds.y + cashBounds.height));
	}
	
	/**
	 * Checks if the passed in coordinates are within the bounds of the cards rectangle
	 * @param xCoord
	 * @param yCoord
	 * @return true if the coordinates are within the card rectangle
	 * @author Alex Crowley 40121793
	 */
	private boolean cardBoundsTouched(int xCoord, int yCoord)
	{
		return (xCoord >= cardBounds.x && xCoord <= (cardBounds.x + cardBounds.width)) && (yCoord >= cardBounds.y && yCoord <= (cardBounds.y + cardBounds.height));
	}
	
	/**
	 * Checks if the passed in coordinates are within the bounds of the photo rectangle
	 * @param xCoord
	 * @param yCoord
	 * @return true if the coordinates are within the photo rectangle
	 * @author Alex Crowley 40121793
	 */
	private boolean photoBoundsTouched(int xCoord, int yCoord)
	{
		return (xCoord >= photoBounds.x && xCoord <= (photoBounds.x + photoBounds.width)) && (yCoord >= photoBounds.y && yCoord <= (photoBounds.y + photoBounds.height));
	}
	

}
