package in.ameya.bankfair.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.ameya.bankfair.model.Customer;
import in.ameya.bankfair.service.CustomerService;

@Service
//This Method is responsible for fatching user information from database for Spring Security
public class BankFairUserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerService customerService;
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Inload User"+userName);
		Customer foundCustomer= customerService.findCustomerByName(userName);
		User user=new User(foundCustomer.getCustomerName(), "ameya", AuthorityUtils.createAuthorityList("ROLES_USER"));
		return user;
	}

}
