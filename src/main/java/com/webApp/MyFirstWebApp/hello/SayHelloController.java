package com.webApp.MyFirstWebApp.hello;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class SayHelloController {
	
	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello Pranshu! What are you learning?";
	}
	
	//to Complex USE VIEWS for HTML
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("<HTML>");
		sb.append("<HEAD>");
		sb.append("<TITLE>");
		sb.append("MY HTML PAGE");
		sb.append("</TITLE>");

		sb.append("<H2>");
		sb.append("<BODY>");
		sb.append("</H2>");
		
		sb.append("MY HTML PAGE BODY");
		sb.append("</BODY>");
		sb.append("</HEAD>");
		sb.append("</HTML>");
		
		return sb.toString();
		
	}
	
	//create jsp file with HTML code
	//path for JSP file - src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
	//save this path in application properties as it will be common for all JSP pages
	//Create Depedency <dependency>
	//	<groupId>org.apache.tomcat.embed</groupId>
	//	<artifactId>tomcat-embed-jasper</artifactId>
	//	<scope>provided</scope>
	//</dependency>
	@RequestMapping("/say-hello-jsp")
//	@ResponseBody don't use it here
	public String sayHelloJsp() {
		return "sayHello";
	}
}
