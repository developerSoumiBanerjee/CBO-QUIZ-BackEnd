package com.hcsc.quizApplication.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.quizApplication.dao.AnswerDao;
import com.hcsc.quizApplication.dto.Answer;
import com.hcsc.quizApplication.dto.AnswersRequestDto;
import com.hcsc.quizApplication.model.Answers;

@Service
public class AnswersServiceImpl {

	@Autowired
	private AnswerDao answerDao;

	public Answer fetchResults(List<AnswersRequestDto> answerRequest) {
		int count = 0;
		int courseId = answerRequest.get(0).getCourseID();
		int resultID = answerRequest.get(0).getResultId();
		int noOfQuestion = answerRequest.size();
		Answer answer = new Answer();
		List<Answers> answerResult = answerDao.fetchResults(courseId,answerRequest.get(0).getQuestion_no());

		/*for (Answers questionResult : answerResult) {
			for (AnswersRequestDto answerDto : answerRequest) {
				
				if (answerDto.getQuestion_no() == (questionResult.getQuestions().getQnId()))
				{
					
					count++;
				}
			}*/
		for(AnswersRequestDto answerDto : answerRequest) {
			 List<String> correctAnswers =	answerResult.stream().filter(p->p.getQuestions().getqId()== answerDto.getQuestion_no()).map(Answers::getAnswer).collect(Collectors.toList());
			 
			 if ((null!=answerDto.getAnswer())&& (correctAnswers.toString().contentEquals(answerDto.getAnswer().toString()))) {
				 count++;
			 }
		}
		double answerResponse = ((double)count/(double)noOfQuestion)*100;
		answerDao.storeUserResults(answerResponse,resultID);
		answer.setCorrectAnswers(count);
		answer.setPercentage(answerResponse);
		return answer;

	}

}
