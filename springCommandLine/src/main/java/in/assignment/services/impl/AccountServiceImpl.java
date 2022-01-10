package in.assignment.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.assignment.dao.AccountDao;
import in.assignment.model.Account;
import in.assignment.services.AccountServices;
@Service
//@Transactional
//@Repository
public class AccountServiceImpl implements AccountServices {
	
	
	@Autowired
	private AccountDao accountDao;
	
	public AccountServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Account getBalanceByAccountNo(int number) {
		// TODO Auto-generated method stub
		return accountDao.getAccountByNum(number);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accountDao.getAllAccount();
	}

}
