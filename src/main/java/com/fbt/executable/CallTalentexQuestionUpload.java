package com.fbt.executable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fbt.business.interfaces.TalentexQuestionUpload;
import com.fbt.talentex.business.questionupload.TalentexQuestionUploadImplementation;

@ComponentScan
public class CallTalentexQuestionUpload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CallTalentexQuestionUpload callTalentexQuestionUpload = new CallTalentexQuestionUpload();
		callTalentexQuestionUpload.talentexExamQuestionUploadStart();
	}
	
	public void talentexExamQuestionUploadStart()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("questionUploadContext.xml");
		TalentexQuestionUpload talentexQuestionUpload = (TalentexQuestionUpload) applicationContext.getBean(TalentexQuestionUploadImplementation.class);
		talentexQuestionUpload.talentexExamQuestionUploader("/home/brkarthik/Desktop/fine.xls");
	}

}
