package gameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GameMainMenu extends CommonScreenAttributes{
	
	private JFrame initialMenuFrame;
	private JButton newGame, gameGuide, loadGame;
	private JPanel pane = new JPanel(new GridBagLayout());
	private ImageIcon imageHeader;
	private JLabel headerImageLabel;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ArrayList<GamePlayer> players;
	
	/*
	 * The initial menu is incredibly simple, it has the image representing the game's titles and all necessary buttons to start a new game, load a game or open the game guide
	 */
	
	public GameMainMenu()
	{
		super();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		imageHeader = new ImageIcon(getClass().getResource("gameoflifequblogonew.png"));
		headerImageLabel = new JLabel(imageHeader);
		
		players = new ArrayList<>();
		
		newGame = new JButton("NEW GAME");
		newGame.setBackground(new Color(0, 0, 0));
		newGame.setForeground(Color.white);
		newGame.setFont(new Font("Arial", Font.BOLD, 20));
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				
				String numberOfPlayers = "";
				
				do
				{
					numberOfPlayers = JOptionPane.showInputDialog(null, "Number Of Players (2 - 4):");
					
				}while(!inputIsInt(numberOfPlayers) || Integer.parseInt(numberOfPlayers ) > 4 || Integer.parseInt(numberOfPlayers ) < 2);
				
				initialMenuFrame.dispose();
				new CustomiseCharacterScreen(players, Integer.parseInt(numberOfPlayers));

			}
		});
		
		loadGame = new JButton("LOAD GAME");
		loadGame.setBackground(new Color(0, 0, 0));
		loadGame.setForeground(Color.white);
		loadGame.setFont(new Font("Arial", Font.BOLD, 20));
		loadGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				loadGame();
			}
		});
			
		gameGuide = new JButton("GAME GUIDE");
		gameGuide.setBackground(new Color(0, 0, 0));
		gameGuide.setForeground(Color.white);
		gameGuide.setFont(new Font("Arial", Font.BOLD, 20));
		gameGuide.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				GameSound.clickSound.play();
				new GameRulesScreen();
			}
		});
		
		addComponent(pane, headerImageLabel, gbc, 0, 0, 1, 2);
		addComponent(pane, newGame, gbc, 0, 2, 1, 1);
		addComponent(pane, loadGame, gbc, 0, 3, 1, 1);
		addComponent(pane, gameGuide, gbc, 0, 4, 1, 1);
		
		pane.setBackground(new Color(239, 253, 222));
		
		initialMenuFrame = new JFrame("QUB Life Game");
		initialMenuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initialMenuFrame.add(pane);
		initialMenuFrame.setSize(400, 300);
		initialMenuFrame.setResizable(false);
		initialMenuFrame.setLocationRelativeTo(null);
		initialMenuFrame.setVisible(true);
		
		super.setMainFrame(initialMenuFrame);
	}
	
	public boolean inputIsInt(String inputString)
	{
		try
		{
			Integer.parseInt(inputString); 
		}
		catch(NumberFormatException | NullPointerException e) 
		{ 
			return false; 
		}
		
		return true;
	}

}
