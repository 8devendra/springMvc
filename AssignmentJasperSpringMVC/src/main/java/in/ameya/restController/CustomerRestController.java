package in.ameya.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ameya.model.Customer;
import in.ameya.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestController {
	@Autowired
	CustomerService customerService;
	@RequestMapping("")
	public List< Customer> getCustomers() {
		List<Customer> customers=customerService.getAllCustomer();		
		return customers;
	}
	
	@RequestMapping("/id")
	public Customer getCustomerById() {
		return customerService.getCustomerById(0);
	}

}
