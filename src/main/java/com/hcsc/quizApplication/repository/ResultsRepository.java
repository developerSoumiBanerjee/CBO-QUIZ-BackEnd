package com.hcsc.quizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.quizApplication.model.Results;

public interface ResultsRepository extends JpaRepository<Results, Long>{

}
