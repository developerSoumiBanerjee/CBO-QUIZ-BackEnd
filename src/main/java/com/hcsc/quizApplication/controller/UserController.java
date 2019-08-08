package com.hcsc.quizApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.quizApplication.dao.CourseService;
import com.hcsc.quizApplication.dao.RoleService;
import com.hcsc.quizApplication.dao.UserService;
import com.hcsc.quizApplication.model.Course;
import com.hcsc.quizApplication.model.Results;
import com.hcsc.quizApplication.model.Role;
import com.hcsc.quizApplication.model.User;
@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	CourseService courseService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, consumes="application/json")
	public User save(@RequestBody User user) {
		try {
		Role role = roleService.findByRoleId(1L);
        user.setRoles(role);
		User userNew=userService.save(user);
		return userNew;
		}catch(Exception e)
		{
			return null;
		}
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST, consumes="application/json")
	public long loginUser(@RequestBody User user) throws Exception {
		List<User> userList = userService.listAllUsers();
		
		for(User user1:userList) {
			
			if(user1.getUser_name().equals(user.getUser_name()) && user1.getPwd().equals(user.getPwd()) && user1.getRoles().getRole().equals("USER"))
				return user1.getUser_id();
		
		}
		//throw new Exception("Incorrect credentials or userId not registered ");
		return 0;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/loginAdmin", method = RequestMethod.POST, consumes="application/json")
	public boolean loginAdmin(@RequestBody User user) throws Exception {
		List<User> userList = userService.listAllUsers();
		
		
		for(User user1:userList) {
			
			if(user1.getUser_name().equals(user.getUser_name()) && user1.getPwd().equals(user.getPwd()) && user1.getRoles().getRole().equals("ADMIN"))
				return true;
		
		}
		//throw new Exception("Admin credentials incorrect");
		return false;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/viewresult", method = RequestMethod.POST, consumes="application/json")
	public boolean viewResult(@RequestBody Results result) throws Exception {
		return true;
	}
	
	
	
	
}
