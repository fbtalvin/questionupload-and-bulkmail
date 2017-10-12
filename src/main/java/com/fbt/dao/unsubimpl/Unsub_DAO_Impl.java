package com.fbt.dao.unsubimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fbt.dao.entities.Unsubscribe_Details;
import com.fbt.dao.interfaces.Unsub_DAO_Interfaces;

@Repository
public class Unsub_DAO_Impl implements Unsub_DAO_Interfaces
{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Unsubscribe_Details> getAllUnsubscribeDetails() 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(" from Unsubscribe_Details ");
		List<Unsubscribe_Details> alrUnsubscribe_Details = query.list();
		transaction.commit();
		session.clear();
		session.close();
		return alrUnsubscribe_Details;
	}

}
