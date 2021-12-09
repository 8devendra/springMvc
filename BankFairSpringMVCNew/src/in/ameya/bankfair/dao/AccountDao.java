package in.ameya.bankfair.dao;

import java.util.List;

import in.ameya.bankfair.model.Account;

public interface AccountDao {
	Account createAccount(Account a);
	List<Account> getBalanceByAccountNo(int accountNo);
	Account deleteAccountByAccountNo(int accountNo);
	Account updateAccountBalanceByAccountNo(Account a);
	List <Account> getAllAccount();
}
