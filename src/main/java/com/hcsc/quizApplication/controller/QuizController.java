package com.hcsc.quizApplication.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hcsc.quizApplication.dto.Answer;
import com.hcsc.quizApplication.dto.AnswersRequestDto;
import com.hcsc.quizApplication.dto.Course;
import com.hcsc.quizApplication.dto.Question;
import com.hcsc.quizApplication.service.AnswersServiceImpl;
import com.hcsc.quizApplication.service.CourseServiceImpl;
import com.hcsc.quizApplication.service.QuestionServiceImpl;

@RestController
public class QuizController {
	
	@Autowired
	QuestionServiceImpl questionImpl;
	
	@Autowired
	CourseServiceImpl courseImpl;
	
	@Autowired
	AnswersServiceImpl answerImpl;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/quiz/fetchQuestions",method = RequestMethod.POST, consumes="application/json")
	public List<Question> getQuestions(@RequestBody AnswersRequestDto courseName) throws Exception {
		System.out.println(courseName);
		
		return questionImpl.getQuizQuestions(courseName.getUserId(),courseName.getCourseID());
		
	}
	

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/quiz/courses",method = RequestMethod.GET)
	public List<Course> getCourse() {
		
		return courseImpl.getCourse();
		
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/quiz/fetchResults",method = RequestMethod.POST, consumes="application/json")
	public Answer getAnswersResult(@RequestBody List<AnswersRequestDto> answerRequest) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<AnswersRequestDto> jsonToAnswerList;
    	objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    	Answer answerResult = new Answer();
    	try {
			String arrayToJson = objectMapper.writeValueAsString(answerRequest);
			TypeReference<List<AnswersRequestDto>> mapType = new TypeReference<List<AnswersRequestDto>>() {};
	    	jsonToAnswerList = objectMapper.readValue(arrayToJson, mapType);
	    	answerResult =  answerImpl.fetchResults(jsonToAnswerList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answerResult;
		
	}
}
