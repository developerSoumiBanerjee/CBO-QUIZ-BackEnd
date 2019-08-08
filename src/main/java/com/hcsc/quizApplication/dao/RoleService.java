package com.hcsc.quizApplication.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.model.Role;
import com.hcsc.quizApplication.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
	RoleRepository roleRepository;
    
    public Role findByRoleId(Long id){
    	return roleRepository.findOne(id);
    	
    }
}
