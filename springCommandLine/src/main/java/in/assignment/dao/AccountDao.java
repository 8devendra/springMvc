package in.assignment.dao;

import java.util.List;

import in.assignment.model.Account;

public interface AccountDao {
	Account getAccountByNum(int number);
	List<Account> getAllAccount();
	
}
