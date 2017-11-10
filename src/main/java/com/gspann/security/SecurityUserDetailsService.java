package com.gspann.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gspann.entities.Users;
 import com.gspann.service.UserService;
/**
 * @author Ashish Jaiswal
 *
 */
@Service(value = "securityUserDetailsService")
public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Users users = userService.findUserByUsername(username);
		if(null==users) {
			throw new UsernameNotFoundException("Username not found" + username);
		}
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		return new User(users.getUsername(), users.getPassword(), users.isEnabled(), users.isAccountNonExpired(),
				users.isCredentialsNonExpired(), users.isAccountNonLocked(), authorities);
	}
}
