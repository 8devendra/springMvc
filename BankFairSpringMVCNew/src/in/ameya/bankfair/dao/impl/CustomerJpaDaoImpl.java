package in.ameya.bankfair.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import in.ameya.bankfair.dao.CustomerDao;
import in.ameya.bankfair.model.Customer;

public class CustomerJpaDaoImpl implements CustomerDao {
	

	@Override
	public Customer createCustomer(Customer c) {
		// TODO Auto-generated method stub
		System.out.println("Customer Inserted by using JPA");
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		System.out.println("Customer Found by using JPA");
		return null;
	}

	@Override
	public Customer deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerById(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

}
