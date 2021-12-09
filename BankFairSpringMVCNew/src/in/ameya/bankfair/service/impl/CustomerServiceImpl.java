package in.ameya.bankfair.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ameya.bankfair.dao.CustomerDao;
import in.ameya.bankfair.dao.impl.CustomerSpDaoIml;
import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.CustomerService;
//Service Layer responcible for handaling business logic of 
//the application
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	public CustomerServiceImpl() {
		super();
		
		//this.customerDao=new CustomerSpDaoIml();
// TODO Auto-generated constructor stub
	}

	@Override
	public Customer createCustomer(Customer c) {
		// TODO Auto-generated method stub
		//////	 Assignment 	
		//CustomerDao customerDao =new CustomerDaoImpl();
		customerDao.createCustomer(c);
		///// 	Assignment End
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		//CustomerDao obj=new CustomerDaoImpl();
		return customerDao.findCustomerById(id);	
		
	}

	@Override
	public Customer deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomerById(id);
		return null;
	}

	@Override
	public Customer updateCustomer(Customer c) {
		// TODO Auto-generated method stub
		customerDao.updateCustomerById(c);
		return null;
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		//customerDao.findAllCustomers()
		System.out.print(customerDao.findAllCustomers());
		return customerDao.findAllCustomers();
	}

	@Override
	public Customer findCustomerByName(String userName) {
		// TODO Auto-generated method stub
		
		return this.customerDao.findCustomerByName(userName);
	}
	

}
