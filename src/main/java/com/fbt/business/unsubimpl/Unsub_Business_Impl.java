package com.fbt.business.unsubimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.business.interfaces.Unsub_Business_Interfaces;
import com.fbt.dao.entities.Unsubscribe_Details;
import com.fbt.dao.interfaces.Unsub_DAO_Interfaces;

@Service
public class Unsub_Business_Impl implements Unsub_Business_Interfaces
{

	@Autowired
	Unsub_DAO_Interfaces unsub_DAO_Interfaces;

	@Override
	public List<Unsubscribe_Details> getAllUnsubscribeDetails() 
	{
		List<Unsubscribe_Details> alrUnsubscribe_Details = unsub_DAO_Interfaces.getAllUnsubscribeDetails();
		return alrUnsubscribe_Details;
	}

}
