package gameOfLife;

import java.awt.*;

import javax.swing.*;

public class GameRulesScreen extends CommonScreenAttributes{
	
	private JFrame rulesScreenFrame;
	private JPanel pane = new JPanel(new GridBagLayout());
	private JScrollPane scroll = new JScrollPane(pane);
	private JTextArea mainText;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ImageIcon imageHeader;
	private JLabel mainRulesTitle;
	
	/*
	 * The GuideScreen changes colour scheme based on the character parameter, so no empty constructor is included, this screen is very basic and only serves to outline the basics of 
	 * playing the game
	 */
	
	public GameRulesScreen()
	{
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 5, 10, 20);
		
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		imageHeader = new ImageIcon(getClass().getResource("gameoflifequblogonew.png"));
		
		mainRulesTitle = new JLabel(imageHeader);
		
		String instructions ="WELCOME TO THE QUB LIFE GAME 2016\n"
				+ "--------------------------Introduction--------------------------\n"
				+ "You begin your life as an eager young 20 year\nold in the streets of Belfast."
				+ "  Your aim in life is\nto make as much money as possible before youreach retirement age."
				+ "  You can do this by\npassing exams in Queens University Belfast or\njump straight into work.  "
				+ "Climb the property\nladder and grow a family, take them to new and exciting places in Belfast collecting souvenirs\nas you go.  "
				+ "All will help towards your final scorewhen you hit that retirement party!\nGOOD LUCK!  "
				+ "\n\n-----------------------------Setup--------------------------------\n"
				+ "To get started click the 'NEW GAME' button on\nthe main menu.\n"
				+ "You will be prompted to enter how many\nplayers are playing.\n"
				+ "You must have a minimum of 2 and a maximum of 4.\n\n"
				+ "You will be prompted to enter when you would\nlike to retire.\n"
				+ "This will determine how long the game will last as the game ends when retirement age is\nreached.\n\n"
				+ "Recommended ages for number of players are as follows:\n"
				+ "2 players - 60\n"
				+ "3 players - 70\n"
				+ "4 players - 80\n"
				+ "You can also load a saved game by clicking\n'LOAD GAME' and selecting the save file.\nHit OK.\n\n"
				+ "New game > create character x number of\nplayer > game opens \n"
				+ "Load game > choose save file > game loads\n\n"
				+ "----------------------Customise character------------------\n"
				+ "The character customisation screen will allow\neach player to enter their name and gender\nbefore they start the game.\n"
				+ "Once all players have been entered then hit\n'START GAME' to begin.\n\n"
				+ "---------------------------First Turn-----------------------------\n"
				+ "Once all of the characters have been created\nyou are ready to have your first go.\n"
				+ "Each player will take it in turns to decide\nwhether they will to start of their career by\ngoing to university or go straight into a part-\ntime job.  "
				+ "Jumping straight into a part-time job will get you onto the board straight away so thatyou can begin travelling and buying items.  "
				+ "If\nyou go to university you will pay a fee up front\nand will not be able to earn any money until you pass your exams.  "
				+ "Once you pass your exams you have a chance to earn a higher paid job\nthan those who began work straight away.\n\n"
				+ "All of the players are lined up on the start line.\nThe game has started!\n\n"
				+ "--------------------------Players turn--------------------------\n"
				+ "To begin your go you must first spin the wheel\nin the center of the board by clicking it.\n"
				+ "This will decide how many tiles you can\nadvance forward.\n\n"
				+ "When a player lands on a tile the player will be prompted about what the tile function is.\n"
				+ "Each tile carries out a specific function. They\nare as follows:\n\n"
				+ "BLUE: Whenever you land on a blue tile you are\ngiven an action to perform on this tile you have the choice to perform the action stated on the \ntile\n"
				+ "GREEN: Payday. Whenever you pass the green tile you collect your annual salary\n"
				+ "ORANGE: Whenever you land on a orange tile\nyou must do what the tile says\nRED: Whenever the player passes or lands on\nthis tile the player is stopped and you have to\ndo what it says\n\n"
				+ "Once your have completed your turn you click \n\"END TURN\" to end your turn and start the next players turn.\n\n"
				+ "-------------------------Special spins-------------------------\n"
				+ "When someone spins a 1 the stock markets\nchanges, properties and stocks will change in\nprice when this happens\n"
				+ "When you spin a 10 you age by 10 years.\n\n"
				+ "----------------------------Loans---------------------------------\n"
				+ "If you have to pay more money than you have, you must take out a loan to fufill that purchase.  To pay back a loan you must pay 1.25 times the original amount borrowed. Any loans still in\nyour account at the end of the game will\nnegatively affect your score at the end\n\n"
				+ "------------------------Buying a house-----------------------\n"
				+ "When you land on a house space you are given the option to purchase a house from that area.  You can ignore and end your turn or you can\npurchase a house.\n\n"
				+ "Each player can only own one property at any\ngiven time in the game.\n"
				+ "Property prices change throughout the game so\nbe careful of when you buy a house\n\n"
				+ "-----------------------Ending outcomes---------------------\n"
				+ "As soon as all player retire the game is over.\nAll unpaid loans are deducted from each player.\n"
				+ "It isn't just about the money.\n"
				+ "The game assets such as the spouse and pet\nare liquefied and added to each players\nbalance.\n"
				+ "The player with the highest score is deemed\nthe winner.";

		mainText = new JTextArea(instructions);
		mainText.setFont(new Font("Arial", Font.BOLD, 18));
		mainText.setLineWrap(true);
		mainText.setEditable(false);
		mainText.setBackground(new Color(239, 253, 222));
		
		pane.setBackground(new Color(239, 253, 222));

		addComponent(pane, mainRulesTitle, gbc, 0, 0, 1, 1);
		addComponent(pane, mainText, gbc, 0, 1, 1, 4);
		
		rulesScreenFrame = new JFrame("Game Guide");

		rulesScreenFrame.setDefaultCloseOperation(rulesScreenFrame.DISPOSE_ON_CLOSE);
		rulesScreenFrame.add(scroll);
		rulesScreenFrame.setSize(imageHeader.getIconWidth() + 70, 450);
		rulesScreenFrame.setResizable(false);
		rulesScreenFrame.setLocationRelativeTo(null);
		rulesScreenFrame.setVisible(true);
	}

}
