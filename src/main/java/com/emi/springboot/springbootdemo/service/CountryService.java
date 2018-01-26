package com.emi.springboot.springbootdemo.service;

import com.emi.springboot.springbootdemo.model.Country;
import com.emi.springboot.springbootdemo.model.Todo;
import com.emi.springboot.springbootdemo.repository.CountryRepository;
import com.emi.springboot.springbootdemo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by Emi on 21/01/2018.
 */
@Service
public class CountryService {

    private CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Only To get Initial Values
    @Autowired
    private TodoRepository todoRepository;

    @PostConstruct
    @Transactional
    public void initializedCountry(){
        countryRepository.save(new Country("US", "United States"));
        countryRepository.save(new Country("IT", "Italy"));
        countryRepository.save(new Country("UK", "United Kingdom"));
        countryRepository.save(new Country("FR", "France"));
        countryRepository.save(new Country("AR", "Argentina"));

        todoRepository.save(new Todo("emi", "Learn Spring MVC", new Date(),true, countryRepository.findByAcronym("IT")));
        todoRepository.save(new Todo("emi", "Learn Spring Boot", new Date(),false, countryRepository.findByAcronym("AR")));
        todoRepository.save(new Todo("emi", "Learn Spring Data", new Date(),false, countryRepository.findByAcronym("US")));
    }

    public Page<Country> getCountries(Pageable pageable){
        return countryRepository.findAllByOrderByAcronymAsc(pageable);
    }

    @Transactional
    public Country saveCountry(Country country){
        return countryRepository.save(country);
    }

    public void deleteCountry(long id){
        countryRepository.delete(id);
    }

    public Country getCountryById(long id){
        return countryRepository.findOne(id);
    }

    public Country getCountryByAcronym(String acronym){
        return countryRepository.findByAcronym(acronym);
    }

}
