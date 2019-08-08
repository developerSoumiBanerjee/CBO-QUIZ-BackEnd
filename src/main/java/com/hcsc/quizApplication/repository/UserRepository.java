package com.hcsc.quizApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcsc.quizApplication.model.Course;
import com.hcsc.quizApplication.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
