package com.hcsc.quizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.quizApplication.model.Answers;

public interface AnswerRepository extends JpaRepository<Answers, Integer>{

}
