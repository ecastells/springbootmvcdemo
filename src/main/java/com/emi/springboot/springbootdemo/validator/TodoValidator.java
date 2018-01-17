package com.emi.springboot.springbootdemo.validator;

import com.emi.springboot.springbootdemo.model.Todo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Date;

/**
 * Created by Emi on 11/12/2017.
 */
@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == Todo.class;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Todo todo = (Todo)o;
        ValidationUtils.rejectIfEmpty(errors, "desc", "todo.desc.empty");
        ValidationUtils.rejectIfEmpty(errors, "targetDate", "todo.targetDate.empty");

        if (todo.getTargetDate() != null && ! todo.getTargetDate().after(new Date())){
            errors.rejectValue("targetDate", "todo.targetDate.invalid");
        }
    }
}
