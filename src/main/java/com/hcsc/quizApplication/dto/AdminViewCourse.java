package com.hcsc.quizApplication.dto;

import java.util.List;

import com.hcsc.quizApplication.model.Answers;
import com.hcsc.quizApplication.model.Options;

public class AdminViewCourse {
	private int QnId;
	private String Qn;
	private int Qno;
	private List<Options> options;
	private String ImageName;
	private int courseId;
	private long resultId;
	private List<Answers> answer;
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
	public int getQno() {
		return Qno;
	}
	public void setQno(int qno) {
		Qno = qno;
	}
	public List<Options> getOptions() {
		return options;
	}
	public void setOptions(List<Options> options) {
		this.options = options;
	}
	public String getImageName() {
		return ImageName;
	}
	public void setImageName(String imageName) {
		ImageName = imageName;
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
	public List<Answers> getAnswer() {
		return answer;
	}
	public void setAnswer(List<Answers> answer) {
		this.answer = answer;
	}
	
	
}
