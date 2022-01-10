package in.assignment.services;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.assignment.model.Account;
@Repository
public interface AccountServices {
	Account getBalanceByAccountNo(int number);
	List<Account> getAllAccount();
}
