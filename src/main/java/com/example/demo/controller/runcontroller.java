package com.example.demo.controller;

import com.example.demo.repository.RunRepository;
import com.example.demo.repository.RunRepositoryJPA;
import com.example.demo.user.run;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class runcontroller {

    private final RunRepositoryJPA runRepository;

    public runcontroller(RunRepositoryJPA runRepository) {
        this.runRepository = runRepository;
    }

    @RequestMapping("/hello")
    List<run> findAll(){
        return runRepository.findAll();
    }
    @RequestMapping("/{id}")
    Optional<run> findById(@PathVariable int id){
        return runRepository.findById(id);
    }
}
