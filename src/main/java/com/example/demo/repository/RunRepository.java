package com.example.demo.repository;

import com.example.demo.user.run;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//do the logic code for the controller
@Repository
public class RunRepository {

   // private RunRepository runRepository;

    private List<run> runs = new ArrayList<>();
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<run> getRuns(){
        return jdbcClient.sql("select * from Run")//query data from database
                .query(run.class).list();//mapping data to class
    }

    public Optional<run> getRunById(Integer id){
        return jdbcClient.sql("SELECT * FROM run WHERE id = :id")
                .param("id",id)
                .query(run.class)
                .optional();
    }

    //don't forget 's' on param method to has many values
     public void createRun(run run){
       var updated = jdbcClient.sql("insert into Run values(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().toString()))
                .update();
       Assert.state(updated == 0, "Run already exists");
    }

    public void updateRun(run run, Integer id){
        var updated = jdbcClient.sql("update Run set title =?, started_on =?, completed_time =?, miles =?, location =? where id = ?")
                .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location(), id))
                .update();

        Assert.state(updated > 0, "Run already exists");
    }

    public void deleteRun(Integer id){
        jdbcClient.sql("delete from Run where id = ?")
                .param("id",id)
                .update();

    }

    public int countRuns(){
        return jdbcClient.sql("select * from Run")
                .query()
                .listOfRows().size();
    }

    public void saveAll(List<run> runs){
        runs.stream().forEach(this::createRun);
    }
}
