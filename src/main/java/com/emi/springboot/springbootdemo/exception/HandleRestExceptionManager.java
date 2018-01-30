package com.emi.springboot.springbootdemo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Emi on 27/01/2018.
 */
@RestControllerAdvice  //Handle Exception with RestfulApi
public class HandleRestExceptionManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleRestExceptionManager.class);
    private static final String MESSAGE = "message";
    private static final String CODE = "code";
    private Map<String,Object> map = new LinkedHashMap<>();

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        populateMap(ex, "1");
        return new ResponseEntity(map, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleConstraintViolationException(ConstraintViolationException ex){
        populateMap(ex, "2");
        return new ResponseEntity(map, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException ex){
        populateMap(ex, "3");
        return new ResponseEntity(map, HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        populateMap(ex, "4");
        return new ResponseEntity(map, HttpStatus.EXPECTATION_FAILED);
    }

    private void populateMap(Exception ex, String code) {
        LOGGER.info("Exception: ",ex);
        map.clear();
        map.put(MESSAGE, ex.getMessage());
        map.put(CODE, code);
    }


}
