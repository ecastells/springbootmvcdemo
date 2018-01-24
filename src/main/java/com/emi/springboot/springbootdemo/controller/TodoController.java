package com.emi.springboot.springbootdemo.controller;

import com.emi.springboot.springbootdemo.model.Todo;
import com.emi.springboot.springbootdemo.service.CountryService;
import com.emi.springboot.springbootdemo.service.TodoService;
import com.emi.springboot.springbootdemo.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Emi on 11/12/2017.
 */
@Controller
@SessionAttributes("name")
public class TodoController {

    private static final String REDIRECT_LIST_TODOS = "redirect:/list-todos";
    public static final String LIST_COUNTRIES_VIEW = "listCountries";
    private TodoService todoService;
    private TodoValidator todoValidator;
    private CountryService countryService;

    @Autowired
    public TodoController(TodoService todoService, CountryService countryService, TodoValidator todoValidator) {
        this.todoValidator = todoValidator;
        this.countryService = countryService;
        this.todoService = todoService;
    }

    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public ModelAndView showTodos(ModelMap model){
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return new ModelAndView("list-todos", model);
    }

    @RequestMapping(value="/create-todo", method = RequestMethod.GET)
    public ModelAndView createTodo(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("todo", new Todo());
        modelAndView.addObject(LIST_COUNTRIES_VIEW, countryService.getCountries());
        modelAndView.setViewName("create-todo");
        return modelAndView;
    }

    /*It saves object into database. The @ModelAttribute puts request data
     *  into model object. You need to mention RequestMethod.POST method
     *  because default request is GET*/
    @RequestMapping(value="/save-todo",method = RequestMethod.POST)
    public ModelAndView saveTodo(ModelMap model, @ModelAttribute("todo") @Validated Todo todo, BindingResult errors){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("todo", todo);
        if (errors.hasErrors()) {
            modelAndView.setViewName("create-todo");
            modelAndView.addObject(LIST_COUNTRIES_VIEW, countryService.getCountries());
            modelAndView.addObject("errors", errors);
        } else {
            String name = (String) model.get("name");
            todo.setUser(name);
            todoService.saveTodo(todo);
            modelAndView.setViewName(REDIRECT_LIST_TODOS);
        }
        return modelAndView;
    }

    /* It displays object data into form for the given id.
     * The @PathVariable puts URL data into variable.*/
    @RequestMapping(value="/update-todo/{id}")
    public ModelAndView edit(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView();
        Todo todo = todoService.getTodoById(id);
        modelAndView.addObject("todo", todo);
        modelAndView.addObject(LIST_COUNTRIES_VIEW, countryService.getCountries());
        modelAndView.setViewName("edit-todo");
        return modelAndView;
    }

    /* It updates model object. */
    @RequestMapping(value="/edit-todo-save",method = RequestMethod.POST)
    public ModelAndView editSave(@ModelAttribute("todo") @Validated Todo todo, BindingResult errors){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("todo", todo);
        if (errors.hasErrors()) {
            modelAndView.setViewName("edit-todo");
            modelAndView.addObject(LIST_COUNTRIES_VIEW, countryService.getCountries());
            modelAndView.addObject("errors", errors);
        } else {
            todoService.saveTodo(todo);
            modelAndView.setViewName(REDIRECT_LIST_TODOS);
        }
        return modelAndView;
    }

    @RequestMapping(value="/delete-todo/{id}",method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable int id){
        todoService.deleteTodo(id);
        return new ModelAndView(REDIRECT_LIST_TODOS);
    }

    @InitBinder /*The @InitBinder annotation is method level annotation, used to initialize the WebDataBinder.
    The WebDataBinder class binds the web request parameters to model objects on each http request.*/
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof Todo) {
            binder.setValidator(todoValidator);
        }
    }
}
