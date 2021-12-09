package in.ameya.bankfair.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.CustomerService;
import jdk.nashorn.internal.ir.annotations.Reference;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
private CustomerService service;
	@GetMapping("/welcome")
	public String home() {
		System.out.print("In Home");
		return "welcome";
	}
	@GetMapping()//"/customer"
	public String findCustomerById(@RequestParam int id,Model m) {
		Customer findCustomer= service.findCustomerById(id);
		List<Customer> customers=new ArrayList<Customer>();
		customers.add(findCustomer);
		m.addAttribute("customer", customers);
		return "customer/customer"; 
	}

	@GetMapping("/all")///customerAll
	public String findAllCustomer(Model m) {
		List<Customer> findAll=service.getAllCustomer();
		m.addAttribute("customer",findAll);
		
		return "customer/customer";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomerById(@RequestParam int customerId) {
		service.deleteCustomerById(customerId);
		System.out.println(customerId);
		return "redirect:/customer/all"; 
	}
	@PostMapping("/updateCust")
	//public String updateCustomer(@RequestParam int id, @RequestParam String name) {
	public String updateCustomer(@ModelAttribute Customer c) {
		System.out.println(c.getCustomerId()+" 		"+c.getCustomerName());
//		Customer c =new Customer();
//		c.setCustomerId(id);
//		c.setCustomerName(name);
		service.updateCustomer(c);
		return "redirect:/customer/all";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")//only accessable to admin 
	@GetMapping("/update")
	public String showUpdate(@RequestParam int customerId, Model m) {
		Customer cust=new Customer();
		cust.setCustomerId(customerId);
		m.addAttribute("customer",cust);
		return "customer/customerUpdate";
	}
	
	@GetMapping("/add")
	public String addCu(Model m) {
		m.addAttribute("customer", new Customer());
		return "customer/customerAdd";
	}
	
	@PostMapping("/addCustomer")
	//public String addCustomer(@RequestParam int id,@RequestParam String name) {
	public String addCustomer(@ModelAttribute Customer c) {
		
//		Customer c =new Customer();
//		c.setCustomerId(id);
//		c.setCustomerName(name);
		service.createCustomer(c);		
		return "redirect:/customer/all";
	}
	
}
