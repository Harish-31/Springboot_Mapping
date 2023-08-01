package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.TutorialModel;

public interface TutorialRepository extends JpaRepository<TutorialModel,Long> {
    
}
