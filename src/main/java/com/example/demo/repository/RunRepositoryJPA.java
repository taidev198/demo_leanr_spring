package com.example.demo.repository;

import com.example.demo.user.run;
import org.springframework.data.repository.ListCrudRepository;

//extends ListCrudRepository class to using super's method like update, create, etc.
public interface RunRepositoryJPA extends ListCrudRepository<run, Integer> {
}
