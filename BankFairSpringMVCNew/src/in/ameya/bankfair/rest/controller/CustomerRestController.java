package in.ameya.bankfair.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	@Autowired
	private CustomerService service;
	
	@GetMapping()
	public List<Customer> findCustomerById() {
		
		System.out.println("In Customer");
		Customer foundCustomer= service.findCustomerById(102);
		List<Customer> custList=new ArrayList<Customer>();
		custList.add(foundCustomer);
		//Customer customer=new Customer();
		//customer.setCustomerId(10);
		//customer.setCustomerName("New Customer");
		System.out.println("In Customer"+foundCustomer);
		return custList;
	}
	@GetMapping("/all")
	public List<Customer> getAllCustomer() {
		System.out.println("In Customer");
		List<Customer> foundCustomer= service.getAllCustomer();
		//Customer customer=new Customer();
		//customer.setCustomerId(10);
		//customer.setCustomerName("New Customer");
		System.out.println("In Customer"+foundCustomer);
		return foundCustomer;
	}
	
	@GetMapping("/delete")
	public String deletCustomerById() {
		service.deleteCustomerById(40);
		return "delete";
	}
	
	@GetMapping("/update")
	public String updateCustomer() {
		Customer cs=new Customer();
		cs.setCustomerId(41);
		cs.setCustomerName("New Cust");
		service.updateCustomer(cs);
		return "updated";
	}
	@GetMapping("/create")
	public String addCustomer() {
		Customer cs=new Customer();
		cs.setCustomerId(40);
		cs.setCustomerName("New Cust");
		service.createCustomer(cs);
		return "Created";
	}
	
	
	@PostMapping("/create")
	public Customer addCust(@RequestBody Customer c) {
		return service.createCustomer(c);
	}
	@PostMapping("/update")
	public Customer updateCust(@RequestBody Customer c) {
		return service.updateCustomer(c);
	}
	@PostMapping("/delete")
	public Customer deleteCust(@RequestBody Customer c) {	
		return service.deleteCustomerById(c.getCustomerId());
	}
	@PostMapping()
	public Customer findCust(@RequestBody Customer c) {
		return service.findCustomerById(c.getCustomerId());
	}
	
	@PostMapping("/all")
	public List <Customer> allCust() {
		return service.getAllCustomer();
	}
	
		
	
	
	
	
	
	

}
