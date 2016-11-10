package org.fusionsoft.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.fusionsoft.model.Profile;
import org.fusionsoft.services.UserService;
import org.fusionsoft.services.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class EmployeesController {
	@Resource
	private UsersServiceImpl userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UsersServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView getCheckLogin(HttpServletRequest request) {
		String id = request.getParameter("userid");
		int userid = 0;
		if (id != null && !id.isEmpty()) {
			userid = Integer.parseInt(id.trim());
		}
		System.out.println(userid);
		List<Profile> profilelist = userService.findprofilesbyuserid(userid);
		ModelAndView model = new ModelAndView("employer/employees");
		model.addObject("profilelist", profilelist);

		return model;
	}
	
}
