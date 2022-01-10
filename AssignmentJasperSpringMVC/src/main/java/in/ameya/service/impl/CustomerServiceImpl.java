package in.ameya.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ameya.dao.CustomerDao;
import in.ameya.model.Customer;
import in.ameya.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		
		return customerDao.getCustomerById(id);
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerDao.getAllCustomers();
	}

}
