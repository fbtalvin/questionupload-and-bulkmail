package com.fbt.talentex.dao.dataaccessquestionupload;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fbt.dao.entities.QuestionMaster;
import com.fbt.dao.entities.QuestionOptionMapping;
import com.fbt.dao.interfaces.TalentexQuestionUploadDataaccess;

@Repository
public class TalentexQuestionUploadDataAccessImpl implements TalentexQuestionUploadDataaccess {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void QuestionandAnswerUpload(QuestionMaster questionMaster, Set<QuestionOptionMapping> questionOptionMappings) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(questionMaster);
		for(QuestionOptionMapping questionOptionMappings2 : questionOptionMappings)
		{
			questionOptionMappings2.setQuestionMaster(questionMaster);
			session.save(questionOptionMappings2);
		}
		transaction.commit();
		session.flush();
		session.close();
	}

}
