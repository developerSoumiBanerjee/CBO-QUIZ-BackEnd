package com.hcsc.quizApplication.dto;

public class Questions {
	
	private int question_no;
	private String questions;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	
	
	public int getQnId() {
		return question_no;
	}
	public void setQnId(int qnId) {
		question_no = qnId;
	}
	public String getQn() {
		return questions;
	}
	public void setQn(String qn) {
		questions = qn;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	
}
