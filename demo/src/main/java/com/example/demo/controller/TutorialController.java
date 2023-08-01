package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.TutorialModel;
import com.example.demo.repository.TutorialRepository;

@RestController
@RequestMapping("/api")

public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    //GET MAPPING
    @GetMapping("/show_all")
    public List<TutorialModel>getAllTutorials(){
        return (List<TutorialModel>) tutorialRepository.findAll();
    }

    //POST MAPPING
    @PostMapping("/create")
    public ResponseEntity<TutorialModel> createTutorial(@RequestBody TutorialModel tutorial){
        TutorialModel _tutorial = tutorialRepository
        .save(new TutorialModel(tutorial.getId(), tutorial.getFullname(), tutorial.getEmail(), tutorial.getPassword()));
        return new ResponseEntity<>(_tutorial, HttpStatus.OK);
    }

    //DELETE MAPPING
    @DeleteMapping("/delete")
    public ResponseEntity<TutorialModel> deleteTutorials(){
        tutorialRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //PUT MAPPING
    @PutMapping("/insert/{id}")
    public ResponseEntity<TutorialModel> updatetutorial(@PathVariable("id") Long id, @RequestBody TutorialModel tutorial){
        Optional<TutorialModel>__tutorial = tutorialRepository.findById(id);
        if(__tutorial.isPresent()){
            TutorialModel _tutorial = __tutorial.get();
            _tutorial.setFullname(tutorial.getFullname());
            _tutorial.setEmail(tutorial.getEmail());
            _tutorial.setPassword(tutorial.getPassword());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
