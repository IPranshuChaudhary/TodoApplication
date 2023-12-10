package com.webApp.MyFirstWebApp.login;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

//Dispacher Servlet

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	
	@RequestMapping("/")
	public String goToWelcomePage(ModelMap model) {
		model.put("name", getLoggedInUserName());
		return "welcome";
	}
	
	public String getLoggedInUserName() {
		
		return SecurityContextHolder.getContext().getAuthentication().getName();
		
	}
	
}
