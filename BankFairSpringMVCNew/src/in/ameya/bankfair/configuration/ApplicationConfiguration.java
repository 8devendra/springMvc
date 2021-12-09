package in.ameya.bankfair.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import in.ameya.bankfair.dao.AccountDao;
import in.ameya.bankfair.dao.CustomerDao;
import in.ameya.bankfair.dao.impl.AccountDaoImpl;
import in.ameya.bankfair.dao.impl.CustomerDaoImpl;
import in.ameya.bankfair.dao.impl.CustomerJpaDaoImpl;
import in.ameya.bankfair.service.AccountService;
import in.ameya.bankfair.service.CustomerService;
import in.ameya.bankfair.service.impl.AccountServiceImpl;
import in.ameya.bankfair.service.impl.CustomerServiceImpl;

@Configuration
@ComponentScan("in.ameya.bankfair")

@PropertySource("classpath:database.properties")
public class ApplicationConfiguration  {
	
	@Value("${database.driverClassName}")
	private String driverClassName;
	@Value("${database.url}")
	private String driverUrl;
	@Value("${database.username}")
	private String driverUsername;
	@Value("${database.password}")
	private String driverPassword;
	
	
	
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		
		JdbcTemplate jdbc =new JdbcTemplate(dataSource);
		return jdbc;
	}
	
	@Bean
	public SimpleJdbcCall getSimpleJdbcCall(DataSource dataSource) {
		SimpleJdbcCall jdbc=new SimpleJdbcCall(dataSource);
		return jdbc;
	}
	
	@Bean
	public DataSource getDateSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(driverUrl);
		dataSource.setUsername(driverUsername);
		dataSource.setPassword(driverPassword);
		return dataSource;
		
	}
	
	
	
//	@Bean
//	public CustomerService getCustomerService() {
//		CustomerServiceImpl service=new CustomerServiceImpl();
//		return service;
//		
//	}	
//	
	
	
//	@Bean
//	public CustomerDao getCustomerDao() {
//		//CustomerDaoImpl dao=new CustomerDaoImpl();
//		CustomerJpaDaoImpl dao=new CustomerJpaDaoImpl();
//		return dao;
//	}
//	
//	@Bean
//	public AccountDao getAccount() {
//		AccountDaoImpl dao =new AccountDaoImpl();
//		return dao;
//	}
	
	
//	@Bean
//	public AccountService getAccountService() {
//		AccountServiceImpl service=new AccountServiceImpl();
//		return service;
//	}
	
	

}
