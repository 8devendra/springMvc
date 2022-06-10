package in.ameya.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import in.ameya.dao.AccountDao;
import in.ameya.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Account getAccountByNum(int number) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_account WHERE accountNo=?";
		List<Account> acc=jdbc.query(sql, new AccountRowMapper(),number);
		return acc.get(1);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_account";
		return jdbc.query(sql, new AccountRowMapper());
	}

	class AccountRowMapper implements RowMapper<Account>{

		@Override
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Account ac=new Account();
			ac.setAccountNo(rs.getInt(1));
			ac.setBalance(rs.getFloat(2));
			ac.setAccountType(rs.getString(3));
	//		System.out.println(ac.getAccountNo());

			return ac;
		}

	}


}
