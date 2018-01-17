package com.emi.springboot.springbootdemo.controller;

import com.emi.springboot.springbootdemo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Emi on 11/12/2017.
 */
@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value={"/", "/login", "/index"}, method = RequestMethod.GET)
    public ModelAndView showLoginPage(ModelMap model){
        return new ModelAndView("login", model);
    }

    @RequestMapping(value={"/", "/login", "/index"}, method = RequestMethod.POST)
    public ModelAndView showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
        if (!loginService.validateUser(name, password)) {
            model.put("errorMessage", "Invalid Credentials");
            return new ModelAndView("login", model);
        }

        model.put("name", name);
        model.put("password", password);
        return new ModelAndView("welcome", model);
    }
}
