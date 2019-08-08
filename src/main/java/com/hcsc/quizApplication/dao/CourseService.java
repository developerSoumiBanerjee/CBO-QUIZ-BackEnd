package com.hcsc.quizApplication.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.model.Course;
import com.hcsc.quizApplication.model.Course_details;
import com.hcsc.quizApplication.model.User;
import com.hcsc.quizApplication.repository.CourseRepository;
import com.hcsc.quizApplication.repository.UserRepository;

@Service
public class CourseService {
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	UserRepository userRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public Course_details save(Course_details course) throws Exception {

		courseRepository.save(course);

		return course;

	}
	
	public List<Course_details> listAllCourse(){
		
	return courseRepository.findAll();
	}

	public int viewCourseByName(String name) {

		Query q = entityManager.createNativeQuery("SELECT cd.course_id,cd.course_name from Course_details cd where cd.course_name="+name,
				Course_details.class);
		

		Course_details course = (Course_details) q.getResultList().get(0);
		return course.getId();
	}
}
