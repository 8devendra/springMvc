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

import in.ameya.bankfair.dao.AccountDao;
import in.ameya.bankfair.dao.impl.CustomerDaoImpl.CustomerRowMapper;
import in.ameya.bankfair.model.Account;

@Repository
@PropertySource("classpath:sql.properties")
public class AccountDaoImpl implements AccountDao{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Value("${sql.accountInsert}")
	private String accountInsert;
	@Value("${sql.accountFindByNo}")
	private String accountFindByNo;
	@Value("${sql.deleteAccountByNo}")
	private String deleteAccountByNo;
	@Value("${sql.updateAccountByNo}")
	private String updateAccountByNo;
	@Value("${sql.getAllAccount}")
	private String getAllAccount;
	
	
	@Override
	public Account createAccount(Account a) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO tbl_account (accountNo,balance) VALUES (?,?)";
		jdbc.update(accountInsert,a.getAccountNo(),a.getBalance());
		System.out.println("Account Created With JDBC");
		
		return null;
	}

	@Override
	public List<Account> getBalanceByAccountNo(int accountNo) {
		String sql="SELECT * FROM tbl_account WHERE accountNo=?";
		List<Account> account=jdbc.query(accountFindByNo, new AccountRowMapperAll(),accountNo);
		System.out.println("Account Balance XXXXXXX With JDBC");
		// TODO Auto-generated method stub
		return account;
	}
	@Override
	public Account deleteAccountByAccountNo(int accountNo) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM tbl_account where accountNo=?";
		jdbc.update(deleteAccountByNo,accountNo);
		System.out.println("Account Deleted With JDBC");
		return null;
	}

	@Override
	public Account updateAccountBalanceByAccountNo(Account a) {
		// TODO Auto-generated method stub
		String sql="UPDATE tbl_account SET balance=? WHERE  accountNo=?";
		jdbc.update(updateAccountByNo,a.getBalance(),a.getAccountNo());
		System.out.println("Updated By:- JDBC");
		
		return null;
	}
	
	

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_account";
		List<Account>accounts= jdbc.query(getAllAccount, new AccountRowMapperAll());
		return accounts;
	}
	class AccountRowMapperAll implements RowMapper<Account>{

		@Override
		public Account mapRow(ResultSet rs, int arg1) throws SQLException {
			// TODO Auto-generated method stub
			Account acc=new Account();
			acc.setAccountNo(rs.getInt(1));
			acc.setBalance(rs.getFloat(2));
			return acc;
		}
		
	}

	
	

}
