package com.example.demo.user;

import java.time.LocalDateTime;
//the name of variables must be matched the name of fields database
public record run(Integer id, String title, LocalDateTime startedOn, LocalDateTime completedOn, int miles, Location location ) {

}
