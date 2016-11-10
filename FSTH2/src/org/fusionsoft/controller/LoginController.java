package org.fusionsoft.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.fusionsoft.model.User;
import org.fusionsoft.services.UserNotFoundService;
import org.fusionsoft.services.UserService;
import org.fusionsoft.services.UsersServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Resource
	private UsersServiceImpl userService;
	private static int userid;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UsersServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping(value ="/validatelogin", method = RequestMethod.GET)
	public String getCheckLogin(HttpServletRequest request) {
		System.out.println("test");
		return "login";
	}

	@RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
	public String getCheckLogin(ModelMap User, HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = null;

		try {
			user = userService.finduserbyusername(username);
			if (user.getPassword().equals(password)) {
                userid  = user.getId();
				return "redirect:/dashboard.htm?userid="+userid;
			} else {
				return "login";
			}
		} catch (UserNotFoundService e) {

			UserError errorUser = new UserError();
			errorUser.setErrorCode("401");
			errorUser.setErrorMessage(e.getMessage());
			return "login";
		}

	}
	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public ModelAndView getProfile() {
		User user = null;
		  System.out.println("The userid entered is"+userid);
			user = userService.finduserbyuserid(userid);
			System.out.println("The username is "+user.getUsername());
			ModelAndView model = new ModelAndView("employer/myprofile");
			model.addObject(user);
			return model;

	}
}
