package in.ameya.bankfair.service;

import java.util.List;

import in.ameya.bankfair.model.Account;

public interface AccountService {
	Account createAccount(Account a);
	List<Account> getBalanceByAccountNo(int accountNo);
	Account deleteAccountByAccountNo(int accountNo);
	Account updateAccountBalance(Account a);
	List<Account> getAllAccount();

}
