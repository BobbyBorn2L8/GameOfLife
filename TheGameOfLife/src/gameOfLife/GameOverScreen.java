package gameOfLife;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOverScreen extends CommonScreenAttributes{
	
	/**
	 * Instance Variables
	 */
	private JFrame gameOverFrame;
	private JButton mainMenu, exitGame, overviewButton;
	private JPanel pane = new JPanel(new GridBagLayout());
	private ImageIcon imageHeader;
	private JLabel headerImageLabel;
	private GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * Constructor for a GameOverScreen which shows the players ranked and presents the options of going to the main menu or exiting the game
	 * @param rankedPlayers
	 * @param board
	 * @author Alex Crowley 40121793
	 */
	public GameOverScreen(ArrayList<GamePlayer> rankedPlayers, GameBoard board)
	{	
		board.setEnabled(false);
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		imageHeader = new ImageIcon(getClass().getResource("gameoflifequblogonew.png"));
		headerImageLabel = new JLabel(imageHeader);
		headerImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		addComponent(pane, headerImageLabel, gbc, 0, 0, 1, 2);
		
		int gridx = 0, gridy = 2, gridwidth = 1;

		int height = 350;
		int width = imageHeader.getIconWidth() + 100;
		
		for(int x = 0; x < rankedPlayers.size(); x++)
		{
			GamePlayer player = rankedPlayers.get(x);
			
			JLabel title = new JLabel("#" + (x + 1) + " - " + " �" + player.getPlayerBankBalance() + " " + 
			player.getPlayerName() );
			title.setBackground(new Color(239, 253, 222));
			title.setFont(new Font("Arial", Font.BOLD, 40));
			title.setForeground(Color.black);
			title.setHorizontalAlignment(SwingConstants.LEFT);
			
			addComponent(pane, title, gbc, gridx, gridy, gridwidth, 1);
			gridy++;
			height += title.getHeight();
			
			if(x > 2)
			{
				height += title.getFontMetrics(title.getFont()).getHeight() + 20;
			}
			
			if(title.getFontMetrics(title.getFont()).stringWidth(title.getText()) > width)
			{
				width = title.getFontMetrics(title.getFont()).stringWidth(title.getText()) + 100;
			}
		}
		
		mainMenu = new JButton("MAIN MENU");
		mainMenu.setBackground(new Color(0, 0, 0));
		mainMenu.setForeground(Color.white);
		mainMenu.setFont(new Font("Arial", Font.BOLD, 20));
		mainMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				gameOverFrame.dispose();
				board.getMainFrame().dispose();
				new GameMainMenu();
			}
		});
		
		exitGame = new JButton("EXIT GAME");
		exitGame.setBackground(new Color(0, 0, 0));
		exitGame.setForeground(Color.white);
		exitGame.setFont(new Font("Arial", Font.BOLD, 20));
		exitGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				gameOverFrame.dispose();
				board.getMainFrame().dispose();
				System.exit(0);
			}
		});
		
		overviewButton = new JButton("OVERVIEW");
		overviewButton.setBackground(new Color(0, 0, 0));
		overviewButton.setForeground(Color.white);
		overviewButton.setFont(new Font("Arial", Font.BOLD, 20));
		overviewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				Object[] options = {"OK"};
			    JOptionPane.showOptionDialog(gameOverFrame,  constructOverviewString(rankedPlayers), 
			    "Overview", JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			}
		});
		
		addComponent(pane, overviewButton, gbc, gridx, gridy, gridwidth, 1);
		gridy++;
		addComponent(pane, mainMenu, gbc, gridx, gridy, gridwidth, 1);
		gridy++;
		addComponent(pane, exitGame, gbc, gridx, gridy, gridwidth, 1);
		
		pane.setBackground(new Color(239, 253, 222));

		height += 75;
		gameOverFrame = new JFrame("GAME OVER!!!");
		gameOverFrame.setBackground(new Color(239, 253, 222));
		gameOverFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		gameOverFrame.add(pane);
		gameOverFrame.setResizable(false);
		gameOverFrame.setSize(width, height);
		gameOverFrame.setLocationRelativeTo(null);
		gameOverFrame.setVisible(true);
		
		GameSound.clappingSound.play();
		
		Object[] options = { "OK" };
		int choice = JOptionPane.showOptionDialog(gameOverFrame, "Congratulations to " + 
		rankedPlayers.get(0).getPlayerName() + " for winning the QUB Life Game!!!","We Have A Winner!",
		             JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		             null, options, options[0]);
	
	}
	
	public String constructOverviewString(ArrayList<GamePlayer> players)
	{
		String overview = "";
		for (GamePlayer player : players) {
			overview += consructPlayerOverviewString(player) + "\n\n";
		}
		return overview;
	}
	
	public String consructPlayerOverviewString(GamePlayer player)
	{
		String playerOverview = "";
		playerOverview += player.getPlayerName().toUpperCase() +":\n" + "Bank Balance: �" 
		+ player.getBankBalanceAlone() + "\n" + "Assets Value: �" + player.getAssetsValue() 
		+ "\n" + "Car Value: �" + player.getCarValue() + "\n" + "Family Value: �" + 
		player.getFamilyValue() + "\n" + "House Value: �" + player.getHouseValue() + "\n";     
		
		if(player.getLoanValue() == 0)
		{
			playerOverview += "Loan Value: �" + player.getLoanValue();
		}
		else
		{
			playerOverview += "Loan Value: � -" + player.getLoanValue();
		}
		
		return playerOverview;
	}

}
