package in.ameya.bankfair.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.ameya.bankfair.model.Account;
import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.AccountService;

@RestController
@RequestMapping("/api/account") //this is write on top it will applicable method of same class (Prefix /api)
public class AccountRestController {
	@Autowired
	private AccountService service;
	
	@GetMapping("/all")
	public List<Account> getAllAccount(){
		System.out.println("All Accounts");
		List<Account> accounts=service.getAllAccount();
		return accounts; 
	}
	
	@GetMapping()
	public List<Account> getAccountByNo(@RequestParam int accountNo){
		List<Account> acc=service.getBalanceByAccountNo(accountNo);
		return acc;
	}
	
	@GetMapping("/update")
	public String updateAccount(@RequestParam int accountNo,@RequestParam float balance) {
		Account ac=new Account();
		ac.setAccountNo(accountNo);
		ac.setBalance(balance);
		service.updateAccountBalance(ac);
		return "Update";
		}
	@GetMapping("/delete")
	public String deleteAccount(@RequestParam int accountNo) {
		service.deleteAccountByAccountNo(1111);
		return "delete";
		}
	
	
	
	@PostMapping("/create")
	public Account addAcc(@RequestBody Account a) {
		return service.createAccount(a);
	}
	@PutMapping("/update/{accountNo}/{balance}")
	public Account updateAcc(@PathVariable int accountNo, @PathVariable float balance) {
		Account a=new Account();
		a.setAccountNo(accountNo);
		a.setBalance(balance);
		return service.updateAccountBalance(a);
	}
	@DeleteMapping("/delete/{accountNo}")
	public Account deleteAcc(@PathVariable int accountNo) {
		return service.deleteAccountByAccountNo(accountNo);
	}
	
	@PostMapping()
	public List<Account> findAcc(@RequestBody Account a) {
		return service.getBalanceByAccountNo(a.getAccountNo());
	}
	
	@PostMapping("/all")
	public List<Account> findAll(){
		return service.getAllAccount();
	}
	

}
