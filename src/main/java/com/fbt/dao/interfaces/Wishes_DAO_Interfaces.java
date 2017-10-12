package com.fbt.dao.interfaces;

import java.util.List;

import com.fbt.dao.entities.Unsubscribe_Details;

public interface Wishes_DAO_Interfaces 
{
	List<Object> sendBirthdayMails();
	
	List<Unsubscribe_Details> getAllUnsubscribeDetailsList();
}
