package in.ameya.dao;

import java.util.List;

import in.ameya.model.Customer;

public interface CustomerDao {
	
	Customer getCustomerById(int id);
	List<Customer> getAllCustomers();
}
