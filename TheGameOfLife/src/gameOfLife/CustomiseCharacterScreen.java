package gameOfLife;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class CustomiseCharacterScreen extends CommonScreenAttributes {
	
	private JFrame customizationScreenFrame;
	private JPanel pane = new JPanel(new GridBagLayout());
	private GridBagConstraints gbc = new GridBagConstraints();
	private JButton startButton;
	private JLabel imageLabel;
	private ImageIcon characterIcon;
	private JComboBox<String> genderOption;
	private JLabel addNameLabel;
	private JTextArea nameCharacter;
	private JMenuBar menuBar;
	private boolean gender;
	
	/**
	 * Creates a new CustomiseCharacterScreen 
	 * @param numberOfPlayers the number of players left to create
	 * @author Alex Crowley 40121793
	 */
	public CustomiseCharacterScreen(ArrayList<GamePlayer> players, int numberOfPlayers)
	{
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(5, 10, 5, 10);
		
		// set up character naming section
		
		addNameLabel= new JLabel("Character Name:");
		addNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addNameLabel.setForeground(Color.black);
	
	    nameCharacter = new JTextArea();
		nameCharacter.setFont(new Font("Arial", Font.BOLD, 20));
		nameCharacter.setLineWrap(true);
		
		addComponent(pane, addNameLabel, gbc, 0, 0, 3, 1);
		addComponent(pane, nameCharacter,gbc, 3, 0, 3, 1);
		
		// set up character gender options
		
		characterIcon = new ImageIcon(getClass().getResource("token_no_gender.png"));
		imageLabel = new JLabel(characterIcon);	
		
		genderOption = new JComboBox();
		genderOption.addItem("Choose A Gender");
		genderOption.addItem("Male");
		genderOption.addItem("Female");
		
		genderOption.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(genderOption.getSelectedItem().equals("Male"))
				{
					characterIcon = new ImageIcon(getClass().getResource("token_Male_large.png"));
					imageLabel.setIcon(characterIcon);
					gender = true;
				}
				else if(genderOption.getSelectedItem().equals("Female"))
				{
					characterIcon = new ImageIcon(getClass().getResource("token_Female_large.png"));
					imageLabel.setIcon(characterIcon);
					gender = false;
				}
			}
		});
		
		addComponent(pane, genderOption, gbc, 0, 1, 6, 1);
		addComponent(pane, imageLabel, gbc, 0, 2, 6, 5);
		
		/**
		 * If we have reached the last player to be created the next step is to start the game, if not we continue making characters
		 */
		if(players.size() + 1 == numberOfPlayers)
		{
			startButton = new JButton("START GAME");
		}
		else
		{
			startButton = new JButton("NEXT CHARACTER");
		}
		
		startButton.setFont(new Font("Arial", Font.BOLD, 20));
		startButton.setBackground(Color.black);
		startButton.setForeground(Color.white);
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameSound.clickSound.play();
				
				if(formComplete())
				{
					if(players.size() + 1 == numberOfPlayers)
					{
						players.add(new GamePlayer(0, 0, nameCharacter.getText(), gender));
						customizationScreenFrame.dispose();
						GameBoard gameBoard = new GameBoard(players);
						gameBoard.showCanvas();
					}
					else
					{
						customizationScreenFrame.dispose();
						players.add(new GamePlayer(0, 0, nameCharacter.getText(), gender));
						new CustomiseCharacterScreen(players, numberOfPlayers);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(customizationScreenFrame, "Please complete the entire form before moving on!");
				}
			}
		});
		
		pane.setBackground(new Color(239, 253, 222));
		addComponent(pane, startButton, gbc, 0, 7, 6, 1);
		
		customizationScreenFrame = new JFrame("Customise Character");
		customizationScreenFrame.setDefaultCloseOperation(customizationScreenFrame.EXIT_ON_CLOSE);
		customizationScreenFrame.add(pane);
		customizationScreenFrame.setSize(420, 400);
		customizationScreenFrame.setResizable(false);
		customizationScreenFrame.setLocationRelativeTo(null);
		customizationScreenFrame.setVisible(true);
	}
	
	/**
	 * @return true if the form is complete, false otherwise
	 * @author Alex Crowley 40121793
	 */
	public boolean formComplete()
	{
		return !(nameCharacter.getText() == null || nameCharacter.getText().equals("") || characterIcon.getImage() == null || genderOption.getSelectedItem().equals("Choose A Gender"));
	}

}
