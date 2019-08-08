package com.hcsc.quizApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcsc.quizApplication.dao.CourseService;
import com.hcsc.quizApplication.dao.UserService;
import com.hcsc.quizApplication.model.Course;
import com.hcsc.quizApplication.model.Course_details;

@RestController
public class CourseController {


	@Autowired
	UserService userService;
	
	@Autowired
	CourseService courseService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addCourse")
	public boolean save(@RequestBody Course_details course) throws Exception {
	 courseService.save(course);
	 return true;

	}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/viewCourse")
	public List<Course_details> view() throws Exception {
		return courseService.listAllCourse();
	 

	}
}
