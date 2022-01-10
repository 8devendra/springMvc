package in.ameya.service;

import java.util.List;

import in.ameya.model.Account;

public interface AccountService {
	Account getBalanceByAccountNo(int number);
	List<Account> getAllAccount();

}
