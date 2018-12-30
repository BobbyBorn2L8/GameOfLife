package gameOfLife;

import java.io.Serializable;

/**
 * @author Robert Kordula 40131366
 *	This class is used to store loans for easy retrieval 
 */
public class Loan implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5192907478383211260L;
	private int loanAmount;

	public Loan(int amount) {
		loanAmount = amount;
	}
	
	/**
	 * @param player
	 * Loans are repayed at 125% the original amount
	 */
	public void repayLoan(GamePlayer player)
	{
		player.subtractFromPlayerBankBalance(loanAmount * 1.25);
	}
	
	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}
}
