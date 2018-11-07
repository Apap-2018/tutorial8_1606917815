package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	public String encrypt(String password);
//	void updateUserPassword(String username, String passwordLama, String passwordBaru, String konfirmasiPassword);
	UserRoleModel getUserByUsername(String username);
	boolean isMatch(String password, String password2);

}
