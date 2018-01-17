package com.emi.springboot.springbootdemo.repository;

import com.emi.springboot.springbootdemo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Emi on 11/12/2017.
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);
}
