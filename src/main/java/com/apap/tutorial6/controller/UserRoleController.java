package com.apap.tutorial6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user) {
		userService.addUser(user);
		return "home";
	}
	
//	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
//	private String updatePasswordSubmit(String username, String passwordLama, String passwordBaru, String konfirmasiPassword) {
//		userService.updateUserPassword(username, passwordLama, passwordBaru, konfirmasiPassword);
//		return "home";
//	}
	
	@RequestMapping(value="/updatePassword", method=RequestMethod.POST)
	private String updatePasswordSubmit(@ModelAttribute UserRoleModel userPalsu, String passwordBaru, String konfirmasiPassword, Model model) {
		UserRoleModel userAsli = userService.getUserByUsername(userPalsu.getUsername());
		
		if(userService.isMatch(userPalsu.getPassword(), userAsli.getPassword())) {
			if(passwordBaru.equals(konfirmasiPassword)) {
				userAsli.setPassword(passwordBaru);
				userService.addUser(userAsli);
				model.addAttribute("message", "password berhasil diubah!");
			} else {
				model.addAttribute("message", "password baru tidak sama");
			}
		} else {
			model.addAttribute("message", "password lama salah");
		}
		
		return "home";
	}

}
