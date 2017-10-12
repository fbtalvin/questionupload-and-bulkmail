package com.fbt.util;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

@Service
public class SendingMailForStudent 
{

	@Autowired
	private JavaMailSender mailSender;  

	@Autowired
	VelocityEngine velocityEngine; 

	private final String fromEmailAddress = "info@talentex.in";
	
	// BIRTHDAY WISHES MAIL SENDER BY KISHORE
	public void sendBirthdayWishes(final String email, final String name, final String templateName) 
	{
		final String subject = "TalentEx - Wishes You A Happy Birthday "+name.toUpperCase();
		MimeMessagePreparator preparator = new MimeMessagePreparator() 
		{
			public void prepare(MimeMessage mimeMessage) throws Exception 
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setTo(email);
				message.setFrom(new InternetAddress(fromEmailAddress));
				message.setSubject(subject);

				Map<String, String> model = new HashMap<String, String>();
				model.put("name", name);
				model.put("email", email);
				
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "./templates/"+templateName+"", "UTF-8", model);
				message.setText(body, true);
			}
		};
		this.mailSender.send(preparator);
	}
	
	public void readExcelMail(final String toEmailAddress, final String subject, final String templatename)
	{
		MimeMessagePreparator preparator = new MimeMessagePreparator() 
		{
			public void prepare(MimeMessage mimeMessage) throws Exception 
			{
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setTo(toEmailAddress);
				message.setFrom(new InternetAddress(fromEmailAddress));
				message.setReplyTo(new InternetAddress("support@fbtech.in"));
				message.setSubject(subject);
				Map<String, String> model = new HashMap<String, String>();
				model.put("email",toEmailAddress);
				String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "./templates/"+templatename+"", "UTF-8", model);
				message.setText(body, true);
			}
		}; 
		this.mailSender.send(preparator);
	}

}
