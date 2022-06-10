package in.ameya.model;

import javax.validation.constraints.NotEmpty;

public class Account {

	private int accountNo;
	
	private float balance;
	@NotEmpty(groups = {WithBalance.class},message = "Account Type Should Not be empty.")
	private String accountType;
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	
	
}

