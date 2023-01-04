package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer> {

}
