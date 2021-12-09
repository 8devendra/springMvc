package in.ameya.bankfair.service;

import java.util.List;

import in.ameya.bankfair.model.Customer;

public interface CustomerService {
	Customer createCustomer(Customer c);
	Customer findCustomerById(int id);
	Customer deleteCustomerById(int id);
	Customer updateCustomer(Customer c);
	List<Customer> getAllCustomer();

}
