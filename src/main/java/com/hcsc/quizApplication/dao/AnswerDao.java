package com.hcsc.quizApplication.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.dto.Answer;
import com.hcsc.quizApplication.model.Answers;

@Repository
public class AnswerDao {
		
	@PersistenceContext
	private EntityManager entityManager;
	
	
	
	public List<Answers> fetchResults(int courseId,int qNo){
		
		Query q = entityManager.createNativeQuery("SELECT ans.q_id,ans.answer,ans.ans_id FROM answers ans,questions q WHERE q.course_id= "+courseId+" AND q.q_id = ans.q_id AND q.q_id= "+qNo,Answers.class);
		
		List<Answers> answers = q.getResultList();
		return answers;
		
	}
	
	@Transactional
	public void storeUserResults(double score, int resultId) {
		Query query = entityManager.createNativeQuery("UPDATE results  SET score ="+score+" WHERE result_id="+resultId+"");
	        query.executeUpdate();
		
	}
	
	@Transactional
	public List<Answers> fetchResultsAdmin(int courseId, int qid){
		
		Query q = entityManager.createNativeQuery("SELECT ans.ans_id,ans.answer,ans.q_id FROM answers ans,questions q WHERE q.course_id= "+courseId+" AND q.q_id = ans.q_id AND ans.q_id= "+qid,Answers.class);
		
		List<Answers> answers = q.getResultList();
		return answers;
		
	}

}
