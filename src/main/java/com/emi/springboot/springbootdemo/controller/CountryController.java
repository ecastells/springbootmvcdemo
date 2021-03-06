package com.emi.springboot.springbootdemo.controller;

import com.emi.springboot.springbootdemo.model.Country;
import com.emi.springboot.springbootdemo.service.CountryService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by Emi on 22/01/2018.
 */
@RestController()
@RequestMapping("/api")
@Api("CRUD operations over Country object")
public class CountryController {

    private static final String RESPONSE_STATUS_200 = "Successfully retrieved country/ies";
    private static final String RESPONSE_STATUS_201 = "Country created";
    private static final String RESPONSE_STATUS_204 = "No content";
    private static final String RESPONSE_STATUS_401 = "You are not authorized to view the resource";
    private static final String RESPONSE_STATUS_403 = "Accessing the resource you were trying to reach is forbidden";
    private static final String RESPONSE_STATUS_404 = "The resource you were trying to reach is not found";
    private static final String RESPONSE_STATUS_500 = "Ooops, something went wrong";
    private static final Integer DEFAULT_INDEX = 0;
    private static final Integer DEFAULT_SIZE = 1000;

    private CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ApiOperation(value = "View a List of all Countries",response = Country.class,produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RESPONSE_STATUS_200),
            @ApiResponse(code = 204, message = RESPONSE_STATUS_204),
            @ApiResponse(code = 401, message = RESPONSE_STATUS_401),
            @ApiResponse(code = 403, message = RESPONSE_STATUS_403),
            @ApiResponse(code = 404, message = RESPONSE_STATUS_404),
            @ApiResponse(code = 500, message = RESPONSE_STATUS_500)
    })
    @RequestMapping(value ="/countries", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCountries(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size){
        int pageValue = (page == null || page < 0) ? DEFAULT_INDEX : page;
        int sizeValue = (size == null || size < 0) ? DEFAULT_SIZE : size;

        Pageable pageable = new PageRequest(pageValue, sizeValue);
        Page<Country> countries = countryService.getCountries(pageable);
        return ResponseEntity.ok(countries);
    }


    @ApiOperation(value = "Get country by Id",response = Country.class, produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RESPONSE_STATUS_200),
            @ApiResponse(code = 204, message = RESPONSE_STATUS_204),
            @ApiResponse(code = 401, message = RESPONSE_STATUS_401),
            @ApiResponse(code = 403, message = RESPONSE_STATUS_403),
            @ApiResponse(code = 404, message = RESPONSE_STATUS_404),
            @ApiResponse(code = 500, message = RESPONSE_STATUS_500)
    })
    @RequestMapping(value ="/country/{id}" , method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity getCountry(@ApiParam(value = "Id of country to search.",required = true) @PathVariable Long id)
    {
        Country country = countryService.getCountryById(id);
        if(country == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(country);
    }


    @ApiOperation(value = "Add a new Country", response = Country.class, consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = RESPONSE_STATUS_201),
            @ApiResponse(code = 401, message = RESPONSE_STATUS_401),
            @ApiResponse(code = 403, message = RESPONSE_STATUS_403),
            @ApiResponse(code = 404, message = RESPONSE_STATUS_404),
            @ApiResponse(code = 500, message = RESPONSE_STATUS_500)
    })
    @RequestMapping(value = "/country",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity addCountry(@ApiParam(value = "Country to add",required = true) @Valid @RequestBody Country country){
        Country countryCreated = countryService.saveCountry(country);
        if (countryCreated == null){
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(countryCreated.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


    @ApiOperation(value = "Update an existing country", response = Country.class, consumes = "application/json", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = RESPONSE_STATUS_201),
            @ApiResponse(code = 401, message = RESPONSE_STATUS_401),
            @ApiResponse(code = 403, message = RESPONSE_STATUS_403),
            @ApiResponse(code = 404, message = RESPONSE_STATUS_404),
            @ApiResponse(code = 500, message = RESPONSE_STATUS_500)
    })
    @RequestMapping(value = "/country",method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
    public ResponseEntity updateCountry(@ApiParam(value = "Country object to be updated",required = true) @Valid @RequestBody Country country){
        Country countryUpdated;
        if(country.getId() == null){
            Country countryTemp = countryService.getCountryByAcronym(country.getAcronym());
            countryTemp.setName(country.getName());
            countryUpdated = countryService.saveCountry(countryTemp);
        } else {
            countryUpdated = countryService.saveCountry(country);
        }
        if(countryUpdated == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(countryUpdated);
    }


    @ApiOperation(value = "Delete an existing country")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = RESPONSE_STATUS_200),
            @ApiResponse(code = 401, message = RESPONSE_STATUS_401),
            @ApiResponse(code = 403, message = RESPONSE_STATUS_403),
            @ApiResponse(code = 404, message = RESPONSE_STATUS_404),
            @ApiResponse(code = 500, message = RESPONSE_STATUS_500)
    })
    @RequestMapping(value = "/country/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteCountry(@ApiParam(value = "Country ID to be deleted", required = true) @PathVariable Long id){
        if(countryService.getCountryById(id) == null){
           return ResponseEntity.notFound().build();
        }
        countryService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
