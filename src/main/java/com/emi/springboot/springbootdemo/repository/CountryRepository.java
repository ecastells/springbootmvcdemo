package com.emi.springboot.springbootdemo.repository;

import com.emi.springboot.springbootdemo.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Emi on 21/01/2018.
 */

public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {
    Country findByAcronym(String acronym);
    Page<Country> findAllByOrderByAcronymAsc(Pageable var1);
}
