package jp.co.axa.apidemo.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	/**
	 *  Set a user for accessing rest API's
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return new User("admin","password",new ArrayList<>());
	}

}
