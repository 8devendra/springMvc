package in.ameya.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ameya.model.Account;
import in.ameya.service.AccountService;

@RestController
@RequestMapping("/api/account")
public class AccountRestController {
	
	@Autowired
	public AccountService service;
	
	@RequestMapping("/number")
	public Account getBalanceByNumber(int num) {
		return service.getBalanceByAccountNo(num);
	}
	
	@RequestMapping("")
	public List<Account> getAllAccount(){
		return service.getAllAccount();
	}

}
