package com.fbt.dao.wishesImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fbt.dao.entities.Unsubscribe_Details;
import com.fbt.dao.interfaces.Wishes_DAO_Interfaces;

@Repository
public class Wishes_DAO_Impl implements Wishes_DAO_Interfaces
{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<Object> sendBirthdayMails() 
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createSQLQuery(" select u.username, u.first_name from talentex.job_seeker j left join talentex.user u on u.id = j.user_id where DATE_FORMAT(j.date_of_birth,'%m-%d') = DATE_FORMAT(NOW(),'%m-%d') ");
//		Query query = session.createSQLQuery(" select email, firstname from kishore.mails ");
		List<Object> alrObject = query.list();
		transaction.commit();
		session.flush();
		session.close();
		return alrObject;
	}

	@Override
	public List<Unsubscribe_Details> getAllUnsubscribeDetailsList()
	{
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery(" from Unsubscribe_Details ");
		List<Unsubscribe_Details> alrUnsubscribe_Details = query.list();
		transaction.commit();
		session.flush();
		session.close();
		return alrUnsubscribe_Details;
	}

}
