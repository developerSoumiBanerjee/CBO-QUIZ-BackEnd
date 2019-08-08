package com.hcsc.quizApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Questions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="q_id")
	private int qId;
	
	@Column(name="q_no")
	private int qNo;
	
	private String questions;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private Course_details course_details;

	public int getqId() {
		return qId;
	}

	public void setqId(int qId) {
		this.qId = qId;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public Course_details getCourse_details() {
		return course_details;
	}

	public void setCourse_details(Course_details course_details) {
		this.course_details = course_details;
	}

}
