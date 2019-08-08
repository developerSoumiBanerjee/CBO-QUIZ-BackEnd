package com.hcsc.quizApplication.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.dao.AdminDao;
import com.hcsc.quizApplication.dto.AddQuestionsRequest;
import com.hcsc.quizApplication.dto.AdminViewCourse;
import com.hcsc.quizApplication.model.Answers;
import com.hcsc.quizApplication.model.Course_details;
import com.hcsc.quizApplication.model.Options;
import com.hcsc.quizApplication.model.Questions;

@Service
public class AdminServiceImpl {

	Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	AdminDao admin;
	/*
	 * public List<Questions> viewQuestions(int courseId){
	 * 
	 * 
	 * 
	 * }
	 */

	public void addQuestions(List<AddQuestionsRequest> request) {
		List<Questions> questions = new ArrayList<>();
		
		for(AddQuestionsRequest questRequest : request) {
			
			Questions questionRequest = new Questions();
			questionRequest.setqNo(questRequest.getqNo());
			questionRequest.setQuestions(questRequest.getQuestion());
			Course_details courses = new Course_details();
			courses.setCourse(questRequest.getCourseName());
			courses.setId(questRequest.getCourseId());
			questionRequest.setCourse_details(courses);
			questions.add(questionRequest);
		}
		
		List<Questions> questionList = admin.addQuestions(questions);
		logger.info("Question list response"+questionList);
		
		
		for(Questions lists : questionList) {
			for(AddQuestionsRequest questRequest : request) {
				if(questRequest.getqNo() == lists.getqNo()) {
					questRequest.setqId(lists.getqId());
				}
				
			}
			
	}
	}
	
	public void addOptions(List<AddQuestionsRequest> request) {
		List<Options> options = new ArrayList<>();
		List<Answers> answers = new ArrayList<>();
		for(AddQuestionsRequest questRequest : request) {
			
			for(String opt :questRequest.getOptions()) {
				Options option = new Options();
				Questions qs = new Questions();
				qs.setqId(questRequest.getqId());
				option.setQuestions(qs);
				option.setOptionsValue(opt);
				options.add(option);
			}
			
			for(String ans :questRequest.getAnswers()) {
				Answers answer = new Answers();
				Questions qs = new Questions();
				qs.setqId(questRequest.getqId());
				answer.setQuestions(qs);
				answer.setAnswer(ans);
				answers.add(answer);
			}
			
		}
		
		admin.addOptions(options);
		admin.addAnswers(answers);
	}
	
	public Boolean deleteQuestions(int qid) {
		
		List<Questions> questions = new ArrayList<>();
		List<Options> options = new ArrayList<>();
		List<Answers> answers = new ArrayList<>(); 
		
		/*for(AddQuestionsRequest questRequest : request) {
			
			Questions questionRequest = new Questions();
			questionRequest.setqId(questRequest.getqId());
			questionRequest.setQuestions(questRequest.getQuestion());
			Course_details courses = new Course_details();
			courses.setId(questRequest.getCourseId());
			questionRequest.setCourse_details(courses);
			questions.add(questionRequest);
			
			Options option = new Options();
			option.setQuestions(questionRequest);
			options.add(option);
			
			Answers answer = new Answers();
			answer.setQuestions(questionRequest);
			answers.add(answer);
		}*/
		
		admin.deleteOptions(qid);
		admin.deleteAnswers(qid);
		admin.deleteQuestions(qid);
		
		return true;
		
	}
	public boolean updateQuestions(AdminViewCourse course) {
		
			admin.updateQuestionDao(course);
			admin.updateOptions(course.getOptions());
			admin.updateAnswers(course.getAnswer());
		
		return false;
		
	}
	
}

