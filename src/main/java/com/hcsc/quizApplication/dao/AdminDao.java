package com.hcsc.quizApplication.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.dto.AdminViewCourse;
import com.hcsc.quizApplication.model.Answers;
import com.hcsc.quizApplication.model.Options;
import com.hcsc.quizApplication.model.Questions;
import com.hcsc.quizApplication.repository.AnswerRepository;
import com.hcsc.quizApplication.repository.OptionsRepository;
import com.hcsc.quizApplication.repository.QuestionRepository;

@Repository
public class AdminDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	OptionsRepository optionsRepository;

	@Autowired
	AnswerRepository answerRepository;

	/*
	 * public List<Questions> viewQuestions(int courseID){
	 * 
	 * questionRepository.fin
	 * 
	 * 
	 * }
	 */

	@Transactional(value=TxType.REQUIRED)
	public List<Questions> addQuestions(List<Questions> questions) {
		
		List<Questions> questionList = new ArrayList<>();
		for(Questions qs : questions) {
			
			entityManager.merge(qs);
			entityManager.flush();
			Questions quest= view(qs).get(0);
			questionList.add(quest);
		}
		
		return questionList;
		

	}

	@Transactional
	public List<Questions> view(Questions ques) {
		Query q = entityManager.createNativeQuery("SELECT q.q_id,q.q_no,q.questions,q.course_id FROM QUESTIONS q WHERE q.course_id = "+ques.getCourse_details().getId()+" AND q.q_no= "+ques.getqNo()+" AND q.questions= \""+ques.getQuestions()+"\"",Questions.class);
		
		List<Questions> quest =  q.getResultList();
		return quest;
	}
	
	@Transactional(value=TxType.REQUIRED)
	public void addOptions(List<Options> option) {
		
		for(Options opt :option) {
			entityManager.merge(opt);
		}
		


	}

	@Transactional(value=TxType.REQUIRED)
	public void addAnswers(List<Answers> answer) {

		for(Answers ans : answer) {
			entityManager.merge(ans);
		}

	}
	
	@Transactional
	public void deleteAnswers(int qid) {
		
		
			Query query = entityManager.createNativeQuery("Delete from answers where q_id="+qid+"");
			query.executeUpdate();
		
	}
	
	@Transactional
	public void deleteOptions(int qid) {
		
		
			Query query = entityManager.createNativeQuery("Delete from options where q_id="+qid+"");
			query.executeUpdate();
		
	}
	
	@Transactional
	public void deleteQuestions(int qid) {
		
		
			Query query = entityManager.createNativeQuery("Delete from questions where q_id="+qid+"");
			query.executeUpdate();
		
	}
	
	@Transactional
	public void updateQuestionDao(AdminViewCourse course) {
		Query q = entityManager.createNativeQuery("Update QUESTIONS q set q.questions = '"+course.getQn()+"'   WHERE q.course_id = "+course.getCourseId()+" AND q.q_id= "+course.getQnId(),Questions.class);
		
		q.executeUpdate();
	}
	
	@Transactional
	public Boolean updateOptions(List<Options> options) {
		for(Options option:options) {
			Query q = entityManager.createNativeQuery("Update OPTIONS o set o.option_value = '"+option.getOptionsValue()+"'   WHERE o.option_id = "+option.getOptionId()+" AND o.q_id= "+option.getQuestions().getqId(),Questions.class);
			q.executeUpdate();
		}
		return true;
	}
	
	@Transactional
	public Boolean updateAnswers(List<Answers> answers) {
		for(Answers answer:answers) {
			Query q = entityManager.createNativeQuery("Update ANSWERS a set a.answer = '"+answer.getAnswer()+"'   WHERE a.ans_id = "+answer.getAnsId()+" AND a.q_id= "+answer.getQuestions().getqId(),Answers.class);
			q.executeUpdate();
		}
		return true;
	}
	
	
	
}
