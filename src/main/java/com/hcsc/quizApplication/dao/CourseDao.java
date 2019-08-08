package com.hcsc.quizApplication.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.model.Course_details;

@Repository
public class CourseDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Course_details> getCourses() {
		
		Query q = entityManager.createNativeQuery("SELECT cd.course_id,cd.course_name from Course_details cd",Course_details.class);
		
		List<Course_details> quest = q.getResultList();
		return quest;
		
	}
}
