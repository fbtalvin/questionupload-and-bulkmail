package com.fbt.scaleme.dao.dataaccessquestionupload;


import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.dao.entities.Options;
import com.fbt.dao.entities.Question;
import com.fbt.dao.interfaces.ScalemeQuestionUploadDataaccess;


@Service
public class ScalemeQuestionUploadDataaccessImpl implements ScalemeQuestionUploadDataaccess{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void QuestionandAnswerUpload(Question question, Set<Options> option) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(question);
		for(Options option2 : option)
		{
			option2.setQuestion(question);
			session.save(option2);
		}
		transaction.commit();
		session.flush();
		session.close();
	}

}
