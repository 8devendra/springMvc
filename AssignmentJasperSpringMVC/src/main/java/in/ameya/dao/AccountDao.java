package in.ameya.dao;

import java.util.List;

import in.ameya.model.Account;



public interface AccountDao {
	Account getAccountByNum(int number);
	List<Account> getAllAccount();
	

}

