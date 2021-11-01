package com.manny.demo;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("home")
	public String home(String name, HttpSession session) {
		
		System.out.print("hi" + name);
		session.setAttribute("name", name);
		return "home";
		
	}
}
