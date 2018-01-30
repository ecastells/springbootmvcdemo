package com.emi.springboot.springbootdemo.auditory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;

/**
 * Created by Emi on 29/01/2018.
 */
public class AuditListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditListener.class);

    @PrePersist
    private void beforeCreate(Object object) {
        log("Before CREATE: {} {}", object);
    }

    @PreUpdate
    private void beforeUpdate(Object object) {
        log("Before UPDATE: {} {}", object);
    }

    @PreRemove
    private void beforeDelete(Object object) {
        log("Before DELETE: {} {}", object);
    }

    @PostPersist
    private void afterCreate(Object object) {
        log("After CREATE: {} {}", object);
    }

    @PostUpdate
    private void afterUpdate(Object object) {
        log("After UPDATE: {} {}", object);
    }

    @PostRemove
    private void afterDelete(Object object) {
        log("After DELETE: {} {}", object);
    }

    private void log(String message, Object object){
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug(message, object.getClass(), object.toString());
        }
    }

}
