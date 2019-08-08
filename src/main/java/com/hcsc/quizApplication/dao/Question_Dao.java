package com.hcsc.quizApplication.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.dto.AdminViewCourse;
import com.hcsc.quizApplication.dto.Answer;
import com.hcsc.quizApplication.dto.Question;
import com.hcsc.quizApplication.model.Answers;
import com.hcsc.quizApplication.model.Course_details;
import com.hcsc.quizApplication.model.Options;
import com.hcsc.quizApplication.model.Question_details;
import com.hcsc.quizApplication.model.Questions;

@Repository
public class Question_Dao {
	@Autowired
	QuestionDao dao;
	@Autowired
	private AnswerDao answerDao;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";
	
	public static boolean updateQuestion(Question_details questionDetails) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		Boolean response = false;
		int i =0;
		
		System.out.println(questionDetails.toString());
		try{
		      Class.forName("com.mysql.jdbc.Driver");

		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      ps = conn.prepareStatement("Update question_details set answer = ?,option1 = ?,option2 = ?,option3 = ?,option4 = ?,questions = ? where course_id = ? and question_no = ?");
		      ps.setString(1, questionDetails.getAnswer());
		      ps.setString(2, questionDetails.getOption1());
		      ps.setString(3, questionDetails.getOption2());
		      ps.setString(4, questionDetails.getOption3());
		      ps.setString(5, questionDetails.getOption4());
		      ps.setString(6, questionDetails.getQuestions());
		      ps.setInt(7, questionDetails.getCourse_details().getId());
		      ps.setInt(8, questionDetails.getQuestionNo());
		      i = ps.executeUpdate();
		      
		      if(i==1)
		    	  response = true;
		      else
		    	  response = false;
		      
		      ps.close();
		      conn.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(ps!=null)
		            ps.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return response;
	}

	public static Boolean addQuestion(Question_details questionDetails) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		Boolean response = false;
		int i =0;
		
		try{
		      Class.forName("com.mysql.jdbc.Driver");

		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      ps = conn.prepareStatement("insert into question_details ( answer, option1, option2, option3, option4, questions, course_id)" + " values (?, ?, ?, ?, ?, ?, ?)");
		     
		      ps.setString(1, questionDetails.getAnswer());
		      ps.setString(2, questionDetails.getOption1());
		      ps.setString(3, questionDetails.getOption2());
		      ps.setString(4, questionDetails.getOption3());
		      ps.setString(5, questionDetails.getOption4());
		      ps.setString(6, questionDetails.getQuestions());
		      ps.setInt(7, questionDetails.getCourse_details().getId());
		      
		      i = ps.executeUpdate();
		      
		      if(i==1)
		    	  response = true;
		      else
		    	  response = false;
		      
		      ps.close();
		      conn.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(ps!=null)
		            ps.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return response;
	}
	public static Boolean deleteQuestion(Question_details questionDetails) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		Boolean response = false;
		int i =0;
		
		try{
		      Class.forName("com.mysql.jdbc.Driver");

		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		      ps = conn.prepareStatement("Delete from question_details where course_id=? and question_no = ?");
		      ps.setInt(1, questionDetails.getCourse_details().getId());
		      ps.setInt(2, questionDetails.getQuestionNo());
		      i = ps.executeUpdate();
		      
		      if(i==1)
		    	  response = true;
		      else
		    	  response = false;
		      
		      ps.close();
		      conn.close();
		   }catch(SQLException se){
		      
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(ps!=null)
		            ps.close();
		      }catch(SQLException se2){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
		return response;
	}

	

	public List<AdminViewCourse> viewQuestions(int courseName) throws Exception {
		List<Questions> questionResult = dao.getQuestions(courseName);
		List<Options> optionResult = dao.getOptions(courseName);
		List<AdminViewCourse> questionResponse = new ArrayList<>();
		

		for (Questions question : questionResult) {
			List<Options> options = optionResult.stream().filter(p -> p.getQuestions().getqId() == question.getqId())
					.collect(Collectors.toList());
			AdminViewCourse qrs = new AdminViewCourse();
			qrs.setQnId(question.getqId());
			qrs.setQno(question.getqNo());
			qrs.setQn(question.getQuestions());
			qrs.setOptions(options);
			qrs.setCourseId(question.getCourse_details().getId());
			List<Answers> answerResult = answerDao.fetchResultsAdmin(courseName,qrs.getQnId());
			qrs.setAnswer(answerResult);
			questionResponse.add(qrs);
		}
		
		return questionResponse;

	}
	
/*	public static List<Questions> viewQuestions(int courseId) {
		List<Questions> allQuestions = new ArrayList<Questions>();
		Questions questionDetail = null;

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			ps = conn.prepareStatement("SELECT q.q_id,q.q_no,q.questions,q.course_id FROM QUESTIONS q WHERE q.course_id = ? ");
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				questionDetail = new Questions();
				Course_details cd= new Course_details();
				questionDetail.setCourse_details(cd);
				questionDetail.setqNo(rs.getInt("q_no"));
				questionDetail.setQuestions(rs.getString("questions"));
				
				questionDetail.getCourse_details().setId(rs.getInt("course_id"));
				allQuestions.add(questionDetail);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return allQuestions;
	}*/
}

