package in.ameya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ameya.dao.AccountDao;
import in.ameya.model.Account;
import in.ameya.service.AccountService;
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;
	
	public AccountServiceImpl() {
		super();
	}

	@Override
	public Account getBalanceByAccountNo(int number) {
		
		// TODO Auto-generated method stub
		//List<Account> ac=accountDao.getAccountByNum(number);
		return accountDao.getAccountByNum(number);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountDao.getAllAccount();
		//return null;
	}
	

}
