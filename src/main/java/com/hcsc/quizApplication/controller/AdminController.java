package com.hcsc.quizApplication.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hcsc.quizApplication.dao.Question_Dao;
import com.hcsc.quizApplication.dto.AddQuestionsRequest;
import com.hcsc.quizApplication.dto.AdminViewCourse;
import com.hcsc.quizApplication.model.Question_details;
import com.hcsc.quizApplication.service.AdminServiceImpl;


@RestController
public class AdminController {

	@Autowired
	Question_Dao question_Dao;
	@Autowired
	AdminServiceImpl admin;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/viewQuestions", method = RequestMethod.POST, consumes = "application/json")
	public List<AdminViewCourse> viewQuestions(@RequestBody String courseId) throws Exception
	{  
		JSONObject jsonObj = new JSONObject(courseId);
		int name = jsonObj.getInt("courseId"); 
		 

	List<AdminViewCourse> allQuestions = question_Dao.viewQuestions(name);
	
	return allQuestions;
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "admin/deleteQuestions", method = RequestMethod.POST, consumes = "application/json")
	public Boolean deleteQuestions(@RequestBody int qid)
	{
		Boolean isQuestionDeleted = admin.deleteQuestions(qid);
		
		return isQuestionDeleted;
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/admin/addQuestions", method = RequestMethod.POST, consumes = "application/json")
	public Boolean addQuestions(@RequestBody List<AddQuestionsRequest> questions)
	{
		admin.addQuestions(questions);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity("http://localhost:8080/admin/addOptions", questions, Boolean.class);
		
		return true;
	}
	
	@RequestMapping(value = "admin/addOptions", method = RequestMethod.POST, consumes = "application/json")
	public Boolean addOptions(@RequestBody List<AddQuestionsRequest> questions)
	{
		admin.addOptions(questions);
		return true;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/admin/updateQuestions", method = RequestMethod.POST, consumes = "application/json")
	public Boolean updateQuestions(@RequestBody AdminViewCourse questions)
	{
		admin.updateQuestions(questions);
		
		return true;
	}
}
