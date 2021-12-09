package in.ameya.bankfair.dao;
import java.util.List;

import in.ameya.bankfair.model.Customer;

//DAO:- (Data Access Object)
//This is design pattern in java, and responsible for interacting
//with database to java application
public interface CustomerDao {
	Customer createCustomer(Customer c);
	Customer findCustomerById(int id);
	Customer deleteCustomerById(int id);
	Customer updateCustomerById(Customer c);
	
	List<Customer> findAllCustomers();
	Customer findCustomerByName(String userName);
}
