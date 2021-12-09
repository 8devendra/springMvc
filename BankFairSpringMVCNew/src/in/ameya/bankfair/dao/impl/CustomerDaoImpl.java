package in.ameya.bankfair.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.ameya.bankfair.dao.CustomerDao;
import in.ameya.bankfair.model.Customer;


@Repository
@PropertySource("classpath:sql.properties")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbc;
	
	@Value("${sql.customerInsert}")
	private String insertCustomer;
	
	@Value("${sql.customerFindByid}")
	private String customerFindById;
	
	@Value("${sql.deleteCustomerById}")
	private String deleteCustomerById;
	
	@Value("${sql.updateCustomerById}")
	private String updateCustomerByid;
	
	@Value("${sql.getAllCustomer}")
	private String getAllCustomer;
	
	@Override
	public Customer createCustomer(Customer c) {
		System.out.println();
		//String sql ="INSERT INTO tbl_customer (id,name) VALUES (?,?)";
		jdbc.update(insertCustomer,c.getCustomerId(),c.getCustomerName());
		System.out.println("Customer Inserted By:- JDBC");
		return null;
	}

	@Override
	public Customer findCustomerById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT id, name FROM tbl_customer where id=?";
		//jdbc.queryForObject(sql, new CustomerRowMapper(),id);
		List <Customer>customers=jdbc.query(customerFindById, new CustomerRowMapper(),id);
		System.out.println(id +" Customer found by using JDBC");
		return customers.get(0);
	}
	
	class CustomerRowMapper implements RowMapper<Customer>{

		@Override
		public Customer mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			System.out.println("IN MAPPER");
			Customer cust=new Customer();
			cust.setCustomerId(rs.getInt(1));
			cust.setCustomerName(rs.getString(2));
			return cust;
		}
		
	}

	@Override
	public Customer deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM tbl_customer WHERE id=?";
		jdbc.update(deleteCustomerById,id);
		System.out.println("Customer Deleted By:- JDBC");
		return null;
	}

	@Override
	public Customer updateCustomerById(Customer c) {
		// TODO Auto-generated method stub
		String sql="UPDATE tbl_customer SET name=? WHERE id=?";
		jdbc.update(updateCustomerByid,c.getCustomerName(),c.getCustomerId());
		System.out.println("Customer Updated By:- JDBC");
		return null;
	}

	@Override
	public List<Customer> findAllCustomers() {
		// TODO Auto-generated method stub
		String sql="SELECT id, name FROM tbl_customer";
		//jdbc.queryForObject(sql, new CustomerRowMapper(),id);
		List <Customer>customers=jdbc.query(getAllCustomer, new CustomerRowMapper());
		
		System.out.println(" Customer found by using JDBC");
		
		return customers;
	}

	@Override
	public Customer findCustomerByName(String userName) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject("select * from tbl_customer where name=?",new CustomerRowMapper(),userName);
		
	}
	
	
}
