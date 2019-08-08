package com.hcsc.quizApplication.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.model.Role;
import com.hcsc.quizApplication.model.User;
import com.hcsc.quizApplication.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> listAllUsers() {
		return userRepository.findAll();
	}

}
