package com.apap.tutorial6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}
	
	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
//	@Override
//	public void updateUserPassword(String username, String passwordLama, String passwordBaru, String konfirmasiPassword) {
//		if(passwordBaru.equals(konfirmasiPassword)) {
//			String pass = encrypt(passwordBaru);
//			userDb.findByUsername(username).setPassword(passwordBaru);
//		}
//	}

	@Override
	public UserRoleModel getUserByUsername(String username) {
		return userDb.findByUsername(username);
	}

	@Override
	public boolean isMatch(String rawPassword, String encodedPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	
}
