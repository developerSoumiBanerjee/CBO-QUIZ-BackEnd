package com.hcsc.quizApplication.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.model.Course_details;
import com.hcsc.quizApplication.model.Options;
import com.hcsc.quizApplication.model.Questions;
import com.hcsc.quizApplication.model.Results;
import com.hcsc.quizApplication.model.User;
import com.hcsc.quizApplication.repository.ResultsRepository;

@Repository
public class QuestionDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ResultsRepository resultsRepository;
	
	public List<Questions> getQuestions(int course_id) {
		
		/*
		 * double randomDouble = Math.random(); randomDouble = randomDouble * 5 + 1; int
		 * random = (int) randomDouble; System.out.println(random);
		 */
		
		Query q = entityManager.createNativeQuery("SELECT c.course_name,q.q_id,q.q_no,q.questions,q.course_id FROM QUESTIONS q, course_details c WHERE q.course_id = "+course_id+" and  c.course_id=q.course_id  ",Questions.class);
		
		List<Questions> quest = q.getResultList();
		return quest;
		
	}
	
	public List<Options> getOptions(int course_id){
		Query q = entityManager.createNativeQuery("SELECT op.option_id,op.option_value,op.q_id FROM QUESTIONS q, Options op WHERE q.course_id = "+course_id+" AND q.q_id = op.q_id",Options.class);
		return q.getResultList();
	}
	
	
	public Results save(Results result) throws Exception{
		
		return resultsRepository.save(result);
		
	}
	

}
