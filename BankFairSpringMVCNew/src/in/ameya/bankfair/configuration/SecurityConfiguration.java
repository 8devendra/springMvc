package in.ameya.bankfair.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import in.ameya.bankfair.security.service.impl.BankFairUserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //this will enable @preAuthorise anotation
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private BankFairUserDetailServiceImpl userDetails;
	

	
	//Authantication 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
//		auth.inMemoryAuthentication().withUser("ameya").password("ameya").roles("USER");//.and().withUser("dev").password("dev").roles("ADMIN");
//		auth.inMemoryAuthentication().withUser("dev").password("dev").roles("HR");
		auth.userDetailsService(userDetails);
		
	}
	//authorisation
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			super.configure(http);
			http.antMatcher("/**").authorizeRequests().antMatchers("/customer/delete").hasRole("ADMIN");
				
		}
	

}
