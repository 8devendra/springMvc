package in.ameya.bankfair.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ameya.bankfair.model.Account;
import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.AccountService;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService service;
	
	@GetMapping("/welcome")
	public String home() {
		return "welcome";
	}
	@GetMapping()
	public String findCustomerById(@RequestParam int id,Model m) {
		List<Account> account=service.getBalanceByAccountNo(id);
		m.addAttribute("accounts", account);
		return "account/accounts"; 
	}

	@GetMapping("/all")
	public String findAllCustomer(Model m) {
		List<Account> findAll=service.getAllAccount();
		m.addAttribute("accounts",findAll);
		return "account/accounts";
		
	}
	
	@GetMapping("/add")
	public String addCu(Model m) {
		m.addAttribute("account",new Account());
		return "account/accountAdd";
	}
	
	@PostMapping("/accountAdd")
	//public String addCustomer(@RequestParam int id,@RequestParam String name) {
	public String addAccount(@ModelAttribute Account c) {
		service.createAccount(c);		
		return "redirect:/account/all";
	}
	
	
	@GetMapping("/delete")
	public String deleteAccountByNo(@RequestParam int accountNo) {
		service.deleteAccountByAccountNo(accountNo);
		System.out.println(accountNo);
		return "redirect:/account/all"; 
	}
	
	@PostMapping("/updateAcc")
	//public String updateCustomer(@RequestParam int id, @RequestParam String name) {
	public String updateAccount(@ModelAttribute Account a) {
		System.out.println(a.getAccountNo()+" 		"+a.getBalance());
//		Customer c =new Customer();
//		c.setCustomerId(id);
//		c.setCustomerName(name);
		service.updateAccountBalance(a);
		return "redirect:/account/all"; 
	}
	
	@GetMapping("/update")
	public String showUpdate(@RequestParam int accountNo, Model m) {
		Account acc=new Account();
		acc.setAccountNo(accountNo);
		m.addAttribute("account",acc);
		//m.addAttribute("account",acc);
		return "account/accountUpdate";
	}
	
	

}
