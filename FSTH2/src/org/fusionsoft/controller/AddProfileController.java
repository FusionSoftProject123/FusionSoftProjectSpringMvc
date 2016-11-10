package org.fusionsoft.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.fusionsoft.model.User;
import org.fusionsoft.services.UserService;
import org.fusionsoft.services.UsersServiceImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddProfileController {
	@Resource
	private UsersServiceImpl userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UsersServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/addprofile", method = RequestMethod.GET)
	public ModelAndView addprofile(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("employer/profile");

		return model;
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    sdf.setLenient(true);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true)); 
	}
	@RequestMapping(value = "/addProfile", method = RequestMethod.POST)
	public ModelAndView AddProfile(@ModelAttribute User user) {
		System.out.println("The User entered is "+user.getCreateddate());
		int userid = userService.SaveUser(user);
		System.out.println("The Userid Of The saved data is"+userid);
		ModelAndView model = new ModelAndView("employer/myprofile");
        return model;
	}
}
