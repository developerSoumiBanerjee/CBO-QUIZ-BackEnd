package com.hcsc.quizApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.dao.CourseService;
import com.hcsc.quizApplication.dao.QuestionDao;
import com.hcsc.quizApplication.dto.Question;
import com.hcsc.quizApplication.model.Course_details;
import com.hcsc.quizApplication.model.Options;
import com.hcsc.quizApplication.model.Questions;
import com.hcsc.quizApplication.model.Results;
import com.hcsc.quizApplication.model.User;

@Service
public class QuestionServiceImpl {

	@Autowired
	QuestionDao dao;
	@Autowired
	CourseService courseService;

	public List<Question> getQuizQuestions(long userId,int courseName) throws Exception {
		//int courseName=courseService.viewCourseByName(courseByName);
		List<Questions> questionResult = dao.getQuestions(courseName);
		List<Options> optionResult = dao.getOptions(courseName);
		List<Question> questionResponse = new ArrayList<>();
		long resultId = createResults(userId,courseName);

		for (Questions question : questionResult) {
			List<String> options = optionResult.stream().filter(p -> p.getQuestions().getqId() == question.getqId())
					.map(Options::getOptionsValue).collect(Collectors.toList());
			Question qrs = new Question();
			qrs.setQnId(question.getqId());
			qrs.setQn(question.getQuestions());
			qrs.setOptions(options);
			qrs.setCourseId(question.getCourse_details().getId());
			qrs.setCourseName(question.getCourse_details().getCourse());
			qrs.setResultId(resultId);
			questionResponse.add(qrs);
		}
		
		return questionResponse;

	}
	
	private long createResults(long userId,int courseName) throws Exception {
		Results result = new Results();
		Course_details courses = new Course_details();
		courses.setId(courseName);
		result.setCourses(courses);
		result.setResultPercentage(0);
		User user = new User();
		user.setUser_id(userId);
		result.setUser(user);
		Results resultId = dao.save(result);
		
		return resultId.getResultID();
	}
}
