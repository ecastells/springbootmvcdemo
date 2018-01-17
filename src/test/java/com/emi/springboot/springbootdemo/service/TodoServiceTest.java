package com.emi.springboot.springbootdemo.service;

import com.emi.springboot.springbootdemo.model.Todo;
import com.emi.springboot.springbootdemo.model.User;
import com.emi.springboot.springbootdemo.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Emi on 12/12/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    private TodoService todoService;

    @Before
    public void setUp() throws Exception {
        todoService = new TodoService();
        todoService.setTodoRepository(todoRepository);
    }

    @Test
    public void shouldGetTodoWhenInvokeRetrieveTodosWithTestUser(){
        Todo todo = new Todo("test", "Learn Spring MVC", new Date(),false);
        List<Todo> list = Arrays.asList(todo);
        Mockito.when(todoRepository.findByUser("test")).thenReturn(list);

        List<Todo> listResponse = todoService.retrieveTodos("test");
        assertEquals(list.size(), listResponse.size());
        assertEquals(list.get(0), listResponse.get(0));
    }
}