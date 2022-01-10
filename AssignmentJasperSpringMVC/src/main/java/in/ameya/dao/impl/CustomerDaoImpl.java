package in.ameya.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.ameya.dao.CustomerDao;
import in.ameya.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	JdbcTemplate jdbc;
	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT id, name FROM tbl_customer where id=?";

		List<Customer> customer= jdbc.query(sql, new CustomerRowMapper(),id);
		return customer.get(1);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		String sql="SELECT id, name FROM tbl_customer";

		List<Customer> customers= jdbc.query(sql, new CustomerRowMapper());
		return customers;
		
		
	}


	class CustomerRowMapper implements RowMapper<Customer>{

		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Customer customer=new Customer();
			customer.setCustomerId(rs.getInt(1));
			customer.setCustomerName(rs.getString(2));
			return customer;

		}

	}




}
