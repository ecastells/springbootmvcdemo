package com.emi.springboot.springbootdemo.repository;

import com.emi.springboot.springbootdemo.model.Todo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Emi on 06/12/2017.
 */
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByUser(String user);
}
