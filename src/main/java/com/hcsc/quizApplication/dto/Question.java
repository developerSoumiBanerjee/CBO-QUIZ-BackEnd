package com.hcsc.quizApplication.dto;

import java.util.List;

public class Question {
	
	private int QnId;
	private String Qn;
	private int Qno;
	private List<String> Options;
	private String courseName;
	private int courseId;
	private long resultId;
	private List<String> answer;
	
	public int getQnId() {
		return QnId;
	}
	public void setQnId(int qnId) {
		QnId = qnId;
	}
	public String getQn() {
		return Qn;
	}
	public void setQn(String qn) {
		Qn = qn;
	}
	public List<String> getOptions() {
		return Options;
	}
	public void setOptions(List<String> options) {
		Options = options;
	}
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public long getResultId() {
		return resultId;
	}
	public void setResultId(long resultId) {
		this.resultId = resultId;
	}
	public List<String> getAnswer() {
		return answer;
	}
	public void setAnswer(List<String> answer) {
		this.answer = answer;
	}
	public int getQno() {
		return Qno;
	}
	public void setQno(int qno) {
		Qno = qno;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
}
