package com.fbt.business.wishesImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.business.interfaces.Wishes_Business_Interfaces;
import com.fbt.dao.entities.Unsubscribe_Details;
import com.fbt.dao.interfaces.Wishes_DAO_Interfaces;
import com.fbt.util.SendingMailForStudent;
import com.fbt.util.UIEMails;

@Service
public class Wishes_Business_Impl implements Wishes_Business_Interfaces
{
	
	@Autowired
	Wishes_DAO_Interfaces wishes_DAO_Interfaces;
	
	@Autowired
	SendingMailForStudent sendingMailForStudent;
	
	@Override
	public List<UIEMails> sendBirthdayMails() 
	{
		List<Object> alrObjects = wishes_DAO_Interfaces.sendBirthdayMails();
		
		List<Unsubscribe_Details> alrUnsubscribeDetails = wishes_DAO_Interfaces.getAllUnsubscribeDetailsList();
		
		List<UIEMails> uieMails = new ArrayList<UIEMails>();
		
		String templatename1 = "birthday_1.vm";
		String templatename2 = "birthday_2.vm";
		
		for (int i = 0; i < alrObjects.size(); i++)
		{
			Object[] objArray = (Object[]) alrObjects.get(i);
			
			UIEMails mails = new UIEMails();
			
			mails.setEmail(objArray[0].toString());
			mails.setName(objArray[1].toString());
			
			uieMails.add(mails);
		}
		
		ArrayList<UIEMails> alrMials = new ArrayList<UIEMails>();
		
		for (UIEMails mails : uieMails) 
		{
			for (Unsubscribe_Details unsubdetails :	alrUnsubscribeDetails) 
			{
				if (unsubdetails.getEmail().equals(mails.getEmail()))
				{
					if (unsubdetails.getUnsubscribe_Type().getId() == 1)
					{
						alrMials.add(mails);
					}
				}
			}
		}
		
		if (!alrMials.isEmpty()) 
		{
			for (UIEMails mails : alrMials)
			{
				uieMails.remove(mails);	
			}
		}
		
		UIEMails mails = new UIEMails();
		mails.setEmail("kishorekumar5464@gmail.com");
		mails.setName("Kicha");
		uieMails.add(uieMails.size(), mails);
		
		UIEMails mails2 = new UIEMails();
		mails2.setEmail("pradeepkannan91@gmail.com");
		mails2.setName("Pradeep kannan");
		uieMails.add(uieMails.size(), mails2);
		
		for (int i = 0; i < uieMails.size(); i++)
		{
			System.out.println("No ---> "+ i +" Mail id --> "+uieMails.get(i).getEmail() +"			Name is --> "+ uieMails.get(i).getName());
			
			try 
			{
				
				try 
				{
					if (i % 100 == 0)
					{
						System.out.println(" -----> Yella wait Pannula 100 Mail Annupiachu <-----");
						Thread.sleep(10000);
					}	
				}
				catch (Exception e) 
				{
					System.out.println("Ora oru vaati Exception Pottu Puttan....");
				}
				
				if (i % 2 == 0)
				{
					//System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII "+uieMails.get(i).getEmail());
					sendingMailForStudent.sendBirthdayWishes(uieMails.get(i).getEmail(), uieMails.get(i).getName(), templatename1);
				}
				else
				{
					//System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE "+uieMails.get(i).getEmail());
					sendingMailForStudent.sendBirthdayWishes(uieMails.get(i).getEmail(), uieMails.get(i).getName(), templatename2);
				}
				
			}	
			catch (Exception e)
			{
				System.out.println("Exception..."+e);
			}
		}
		
		return uieMails;
	}
	
}
