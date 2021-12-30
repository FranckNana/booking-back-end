package com.booking.jwtService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booking.dao.UserDao;
import com.booking.model.DAOUser;
import com.booking.model.UserDTO;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		DAOUser user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + email);
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
	}
	
	public DAOUser save(UserDTO user) {
		DAOUser newUser = new DAOUser();
		
		newUser.setEmail(user.getEmail());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		
		return userDao.save(newUser);
	}
	
	public void delete(String email) {
		userDao.deleteByEmail(email);
	}
	
	public DAOUser findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}
	
	public List<DAOUser> findAllUser() {
		return userDao.findAll();
	}
}
