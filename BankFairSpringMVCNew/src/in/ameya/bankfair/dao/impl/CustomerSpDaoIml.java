package in.ameya.bankfair.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import in.ameya.bankfair.dao.CustomerDao;
import in.ameya.bankfair.model.Customer;

//@Repository
public class CustomerSpDaoIml implements CustomerDao {

	@Autowired
	private SimpleJdbcCall jdbcCall;

	@Override
	public Customer createCustomer(Customer c) {
		// TODO Auto-generated method stub
		
		jdbcCall.withProcedureName("insertCustomerSp").execute(c.getCustomerId(),c.getCustomerName());
		System.out.println("Customer Created With SP");
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		Map<String,Object>customers=jdbcCall.withProcedureName("findCustomerById").returningResultSet("customers",
				new CustomerDaoImpl().new CustomerRowMapper()).execute(id);
		
		List<Customer> cus=(List<Customer>)customers.get("customers");
				for(Customer c:cus) {
					System.out.println("ID: "+c.getCustomerId()+" Name:" +c.getCustomerName());
					
				}
		
		
		
		System.out.println("Customer find by id With SP");
		//System.out.println(customers.get(0).toString());
		//Customer custOBjNew= new Customer();
		
		
//		custOBjNew.setCustomerId(101);
//		custOBjNew.setCustomerName("New Cs");
		return cus.get(0);
	}

	@Override
	public Customer deleteCustomerById(int id) {
		
		jdbcCall.withProcedureName("deleteCustomerById").execute(id);
		System.out.println("Deleted Customer by id With SP");
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer updateCustomerById(Customer c) {
		// TODO Auto-generated method stub
		jdbcCall.withProcedureName("updateCustomerById").execute(c.getCustomerName(),c.getCustomerId());
		System.out.println("Update Customer by id With SP");
		
		return null;
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		
		Map<String,Object>customers= jdbcCall.withProcedureName("fatchAllCustomerSp").returningResultSet("customers", 
				new CustomerDaoImpl().new CustomerRowMapper()).execute();
		
		List<Customer> cus=(List<Customer>)customers.get("customers");
		
		return cus;
		
	}

	@Override
	public Customer findCustomerByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
