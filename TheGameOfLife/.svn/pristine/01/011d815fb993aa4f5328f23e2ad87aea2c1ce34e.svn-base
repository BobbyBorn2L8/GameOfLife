package gameOfLife;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * @author Robert Kordula 40131366
 * A window to allow the current player to view and pay off their loans
 */
public class LoanScreen extends CommonScreenAttributes
{
	private JFrame mainFrame;
	private JPanel pane = new JPanel();
	private JScrollPane scroll;
	private Font font = new Font("Serif", Font.PLAIN, 25);
	public LoanScreen(GamePlayer player)
	{	
		//Frame setup
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(new Color(239, 253, 222));
		
		JPanel jpanel = new JPanel();
		jpanel.setBackground(new Color(131, 253, 0));
		jpanel.setLayout(new GridBagLayout());
		
		JLabel loanNo = new JLabel("Loan No.");
		loanNo.setFont(font);
		JLabel amountBorrowed = new JLabel("Amount Borrowed");
		amountBorrowed.setFont(font);
		JLabel amountToPay = new JLabel("Amount to Pay");
		amountToPay.setFont(font);
		JLabel payOffLoan = new JLabel("Pay Off");
		payOffLoan.setFont(font);
		
		jpanel.add(loanNo);
		jpanel.add(Box.createRigidArea(new Dimension(30, 0)));
		jpanel.add(amountBorrowed);
		jpanel.add(Box.createRigidArea(new Dimension(40, 0)));
		jpanel.add(amountToPay);
		jpanel.add(Box.createRigidArea(new Dimension(35, 0)));
		jpanel.add(payOffLoan);
		jpanel.add(Box.createRigidArea(new Dimension(105, 0)));
		
		pane.add(jpanel);
		
		int iii = 0;
		for (Iterator<Loan> iterator = player.getPlayerLoans().iterator(); iterator.hasNext();)
		{
			iii++;
			Panel panel = new Panel(player, (Loan)iterator.next(), iii);
			
			pane.add(panel);
		}
		
		int windowSize = 100 + player.getPlayerLoans().size() * 100;
		if (windowSize > 650)
		{
			windowSize = 650;
		}
		
		scroll = new JScrollPane(pane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBackground(new Color(239, 253, 222));
		mainFrame = new JFrame(player.getPlayerName() + "'s Assets");
		mainFrame.setBackground(new Color(239, 253, 222));
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.add(scroll);
		mainFrame.setSize(700, windowSize);
		mainFrame.setVisible(true);
		//End Frame setup//
	}

	private class Panel extends JPanel
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JLabel loanNo, amountBorrowed, amountToRepay;
		private JButton payOffLoan;
		
		//Each loan has its own panel, that is removed when the loan is paid off//
		public Panel(GamePlayer player, Loan loan, int loanNo)
		{
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			setPreferredSize(new Dimension(700, 100));
			
			this.loanNo = new JLabel(String.valueOf(loanNo));
			this.loanNo.setFont(font);
			this.loanNo.setPreferredSize(new Dimension(50, 100));
			amountBorrowed = new JLabel("£" + loan.getLoanAmount());
			amountBorrowed.setFont(font);
			amountBorrowed.setPreferredSize(new Dimension(100, 100));
			amountToRepay = new JLabel("£" + (int)(loan.getLoanAmount() * 1.25));
			amountToRepay.setFont(font);
			amountToRepay.setPreferredSize(new Dimension(100, 100));
			payOffLoan = new JButton("Repay Loan");
			payOffLoan.setPreferredSize(new Dimension(50, 100));
			payOffLoan.setFont(font);
			payOffLoan.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Object[] options = { "YES", "NO" };
					int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to pay off this loan?  It will cost you £" + (int)(loan.getLoanAmount() * 1.25),
					             "", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
					             null, options, options[0]);
					if (choice == 0)
					{
						player.payOffLoan(loan);
						JOptionPane.showMessageDialog(null, "Loan was paid off", "", JOptionPane.INFORMATION_MESSAGE, null);
						hidePanel();
					}
				}
			});
			if (loan.getLoanAmount() * 1.25 > player.getPlayerBankBalance())
			{
				payOffLoan.setEnabled(false);
			}
			
			this.add(this.loanNo);
			this.add(Box.createRigidArea(new Dimension(100, 0)));
			this.add(amountBorrowed);
			this.add(Box.createRigidArea(new Dimension(150 , 0)));
			this.add(amountToRepay);
			this.add(Box.createRigidArea(new Dimension(100 , 0)));
			this.add(payOffLoan);
			this.setBackground(new Color(239, 253, 222));
			this.setVisible(true);
		}
		
		public void hidePanel()
		{
			this.setVisible(false);
		}
	}
}
