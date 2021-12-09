package in.ameya.bankfair.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ameya.bankfair.dao.AccountDao;
import in.ameya.bankfair.model.Account;
import in.ameya.bankfair.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	
	public AccountServiceImpl() {
		super();
		
	}

	@Override
	public Account createAccount(Account a) {
		// TODO Auto-generated method stub
		accountDao.createAccount(a);
		return null;
	}

	@Override
	public List<Account> getBalanceByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		return accountDao.getBalanceByAccountNo(accountNo);
		
	}

	@Override
	public Account deleteAccountByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		accountDao.deleteAccountByAccountNo(accountNo);
		return null;
	}

	@Override
	public Account updateAccountBalance(Account a) {
		// TODO Auto-generated method stub
		accountDao.updateAccountBalanceByAccountNo(a);
		return null;
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountDao.getAllAccount();
		
	}
	
}
