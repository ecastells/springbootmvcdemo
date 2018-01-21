package com.emi.springboot.springbootdemo.repository;

import com.emi.springboot.springbootdemo.model.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Emi on 21/01/2018.
 */
public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByAcronym(String acronym);
    List<Country> findAllByOrderByAcronymAsc();
}
