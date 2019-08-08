package com.hcsc.quizApplication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.dao.CourseDao;
import com.hcsc.quizApplication.dto.Course;
import com.hcsc.quizApplication.model.Course_details;

@Service
public class CourseServiceImpl {
	
	@Autowired
	CourseDao course;
	
	public List<Course> getCourse(){
		List<Course_details> result= course.getCourses();
		List<Course> courses = new ArrayList<>();
		
		
		for(Course_details cr :result) {
			Course qrs = new Course();
			qrs.setCourse_id(cr.getId());
			qrs.setCourse_name(cr.getCourse());
			
			courses.add(qrs);
		}
		return courses;
		
	}

}
