package com.emi.springboot.springbootdemo.controller;

import com.emi.springboot.springbootdemo.model.Country;
import com.emi.springboot.springbootdemo.service.CountryService;
import com.emi.springboot.springbootdemo.service.LoginService;
import com.emi.springboot.springbootdemo.service.TodoService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Emi on 24/01/2018.
 */
@RunWith(SpringRunner.class) //providing the functionality to launch a Spring TestContext Framework
@WebMvcTest(value = CountryController.class, secure = false) //used for unit testing Spring MVC application, launch only CountryController
public class CountryControllerTest {

    @Autowired //It allows us to execute requests against the test context.
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryServiceMock;

    @MockBean
    private LoginService loginServiceMock;

    @MockBean
    private TodoService todoServiceMock;

    private Country country;

    @Before
    public void setUp() throws Exception {
        country = new Country("AR", "Argentina");
        country.setId(1L);
    }

    @After
    public void tearDown() throws Exception {
        country = null;
    }

    @Test
    public void getCountries() throws Exception {
        Page<Country> page = new PageImpl<>(Arrays.asList(country));
        String expectedResult = "{\"content\":[{\"id\":1,\"acronym\":\"AR\",\"name\":\"Argentina\"}],\"totalElements\":1,\"last\":true,\"totalPages\":1,\"size\":0,\"number\":0,\"sort\":null,\"first\":true,\"numberOfElements\":1}";
        Mockito.when(countryServiceMock.getCountries(Mockito.anyObject())).thenReturn(page);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/countries?page=1&size=1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), false);

        Mockito.verify(countryServiceMock, Mockito.times(1)).getCountries(Mockito.anyObject());
    }

    @Test
    public void getCountry() throws Exception {

        Mockito.when(countryServiceMock.getCountryById(Mockito.eq(1L))).thenReturn(country);
        String expectedResult = "{\"id\":1,\"acronym\":\"AR\",\"name\":\"Argentina\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/country/1").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(expectedResult, result.getResponse().getContentAsString(), true);

        Mockito.verify(countryServiceMock, Mockito.times(1)).getCountryById(Mockito.eq(1L));
    }

    @Test
    public void addCountry() throws Exception {

        Mockito.when(countryServiceMock.saveCountry(Mockito.eq(country))).thenReturn(country);

        String countryJson = "{\"id\":1,\"acronym\":\"AR\",\"name\":\"Argentina\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/country")
                .accept(MediaType.APPLICATION_JSON).content(countryJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        assertEquals("http://localhost/api/country/1",
                response.getHeader(HttpHeaders.LOCATION));

        Mockito.verify(countryServiceMock, Mockito.times(1)).saveCountry(Mockito.eq(country));
    }

    @Test
    public void updateCountry() throws Exception {

        Mockito.doNothing().when(countryServiceMock).deleteCountry(Mockito.eq(1L));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                "/api/country/1").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        Mockito.verify(countryServiceMock, Mockito.times(1)).deleteCountry(Mockito.eq(1L));
    }

    @Test
    public void deleteCountry() throws Exception {

        Mockito.when(countryServiceMock.saveCountry(Mockito.eq(country))).thenReturn(country);

        String countryJson = "{\"id\":1,\"acronym\":\"AR\",\"name\":\"Argentina\"}";

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/country")
                .accept(MediaType.APPLICATION_JSON).content(countryJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        JSONAssert.assertEquals(countryJson, result.getResponse().getContentAsString(), true);

        Mockito.verify(countryServiceMock, Mockito.times(1)).saveCountry(Mockito.eq(country));
    }
}