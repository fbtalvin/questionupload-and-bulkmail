package com.fbt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fbt.business.interfaces.BulkMailBusiness;
import com.fbt.business.interfaces.Wishes_Business_Interfaces;

@Controller
public class BulkMailController {

	@Autowired
	BulkMailBusiness bulkMailBusiness;

	@Autowired
	Wishes_Business_Interfaces wishes_Business_Interfaces;
	
	
	//BIRTHDAY WISHES URL BY KISHORE
	@RequestMapping(value="/birthday_wishes") 
	public ModelAndView index(ModelAndView model) throws IOException, ServletException 
	{ 
		model = new ModelAndView("birthday");
		
		//System.out.println("============================="+new java.util.Date().toString());
		wishes_Business_Interfaces.sendBirthdayMails();
		//System.out.println("*****************************\n\n");
		
		return model;
	
	}
	
	@RequestMapping(value = "/")
	public ModelAndView Login(ModelAndView model, HttpSession session, HttpServletRequest request) throws IOException {
		model = new ModelAndView("login");
		if (session.getAttribute("valid") != null) {
			model = new ModelAndView("activity");
		}
		return model;
	}

	@RequestMapping(value = "/loginverify")
	public ModelAndView LoginVerify(ModelAndView model, HttpSession session, HttpServletRequest request)
			throws IOException 
	{
		model = new ModelAndView("login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("admin".equalsIgnoreCase(username) && "password".equalsIgnoreCase(password)) 
		{
			session.setAttribute("valid", "valid");
			model = new ModelAndView("activity");
		}
		return model;
	}

	@RequestMapping(value = "/excelmail")
	public ModelAndView Test(ModelAndView model, HttpSession session, HttpServletRequest request) throws IOException 
	{
		model = new ModelAndView("error");
		if (session.getAttribute("valid") != null) 
		{
			if (session.getAttribute("alreadysend") == null) 
			{
				bulkMailBusiness.readExcelMail();
				session.setAttribute("alreadysend", "alreadysend");
				model = new ModelAndView("success");
			}
			else 
			{
				model = new ModelAndView("success2");
			}
		}
		return model;
	}

	@RequestMapping(value = "/logout")
	public ModelAndView Logout(ModelAndView model, HttpSession session, HttpServletRequest request) throws IOException {
		model = new ModelAndView("redirect:/");
		session.invalidate();
		return model;
	}

	@RequestMapping(value = "/back")
	public ModelAndView BacktoActivity(ModelAndView model, HttpSession session, HttpServletRequest request)
			throws IOException {
		model = new ModelAndView("redirect:/");
		return model;
	}

}