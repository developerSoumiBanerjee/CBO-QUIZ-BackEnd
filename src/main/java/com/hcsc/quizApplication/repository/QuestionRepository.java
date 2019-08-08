package com.hcsc.quizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.quizApplication.model.Questions;

public interface QuestionRepository extends JpaRepository<Questions, Integer>{
	

}
