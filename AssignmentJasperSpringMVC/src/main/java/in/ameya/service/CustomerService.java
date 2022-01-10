package in.ameya.service;

import java.util.List;

import in.ameya.model.Customer;

public interface CustomerService {
	Customer getCustomerById(int id);
	List<Customer> getAllCustomer();

}
