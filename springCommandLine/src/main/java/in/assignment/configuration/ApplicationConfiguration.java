package in.assignment.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ComponentScan("in.assignment")
public class ApplicationConfiguration {
	String driverClassName="com.mysql.jdbc.Driver";
	String driverUrl="jdbc:mysql://localhost:3306/bankfair";
	String driverUsername="root";
	String driverPassword="";
	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		return jdbc;		
	}

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(driverUrl);
		dataSource.setUsername(driverUsername);
		dataSource.setPassword(driverPassword);
		return dataSource;
	}


}
