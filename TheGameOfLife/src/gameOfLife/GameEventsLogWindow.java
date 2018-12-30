package gameOfLife;

import java.awt.*;

import javax.swing.*;

public class GameEventsLogWindow extends CommonScreenAttributes{
	
	private JFrame eventsScreenFrame;
	private JPanel pane = new JPanel(new GridBagLayout());
	private JScrollPane scroll = new JScrollPane(pane);
	private JTextArea mainText;
	private GridBagConstraints gbc = new GridBagConstraints();
	private ImageIcon imageHeader;
	private JLabel eventsScreenTitle;
	
	/**
	 * Constructor to create and show a screen containing all the game logs which are passed in as a String object
	 * @param eventsLogToDisplay
	 * @author Alex Crowley 40121793
	 */
	public GameEventsLogWindow(String eventsLogToDisplay)
	{
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(10, 5, 10, 20);
		
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		imageHeader = new ImageIcon(getClass().getResource("gameoflifequblogonew.png"));
		
		eventsScreenTitle = new JLabel(imageHeader);

		mainText = new JTextArea(eventsLogToDisplay);
		mainText.setFont(new Font("Arial", Font.BOLD, 18));
		mainText.setLineWrap(true);
		mainText.setEditable(false);
		mainText.setBackground(new Color(232,181,30));
		
		pane.setBackground(new Color(239, 253, 222));

		addComponent(pane, eventsScreenTitle, gbc, 0, 0, 1, 1);
		addComponent(pane, mainText, gbc, 0, 1, 1, 4);
		
		eventsScreenFrame = new JFrame("Game Events Log");

		eventsScreenFrame.setDefaultCloseOperation(eventsScreenFrame.DISPOSE_ON_CLOSE);
		eventsScreenFrame.add(scroll);
		eventsScreenFrame.setSize(imageHeader.getIconWidth() + 70, 250);
		eventsScreenFrame.setResizable(false);
		eventsScreenFrame.setLocationRelativeTo(null);
		eventsScreenFrame.setVisible(true);
	}

}