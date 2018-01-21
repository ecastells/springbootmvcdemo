package com.emi.springboot.springbootdemo.service;

import java.util.Date;
import java.util.List;

import com.emi.springboot.springbootdemo.model.Country;
import com.emi.springboot.springbootdemo.model.Todo;
import com.emi.springboot.springbootdemo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

/**
 * Created by Emi on 11/12/2017.
 */
@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Transactional
    public Todo saveTodo(Todo todo){
        return todoRepository.save(todo);
    }

    public void deleteTodo(long id){
        todoRepository.delete(id);
    }

    public Todo getTodoById(long id){
        return todoRepository.findOne(id);
    }

    public List<Todo> retrieveTodos(String user) {
        return todoRepository.findByUser(user);
    }
}
