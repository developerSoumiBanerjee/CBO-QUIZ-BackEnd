package com.hcsc.quizApplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Results {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESULT_ID", unique = true, nullable = false,updatable = false) 
	private long resultID;
	
	@Column(name="score")
	private double resultPercentage;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "course_id")
	private Course_details courses;

	public long getResultID() {
		return resultID;
	}

	public void setResultID(long resultID) {
		this.resultID = resultID;
	}

	public double getResultPercentage() {
		return resultPercentage;
	}

	public void setResultPercentage(double resultPercentage) {
		this.resultPercentage = resultPercentage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Course_details getCourses() {
		return courses;
	}

	public void setCourses(Course_details courses) {
		this.courses = courses;
	}
	
}
