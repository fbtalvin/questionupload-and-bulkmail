package com.fbt.executable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fbt.business.interfaces.ScalemeQuestionUpload;
import com.fbt.scaleme.business.examquestionupload.ScalemeQuestionUploadImplementation;

@ComponentScan
public class CallScalemeQuestionUpload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CallScalemeQuestionUpload callScalemeQuestionUpload = new CallScalemeQuestionUpload();
		callScalemeQuestionUpload.scalemeExamQuestionUploadStart();

	}
	
	public void scalemeExamQuestionUploadStart()
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("questionUploadContext.xml");
		ScalemeQuestionUpload scalemeQuestionUpload = (ScalemeQuestionUpload) applicationContext.getBean(ScalemeQuestionUploadImplementation.class);
		scalemeQuestionUpload.scalemeExamQuestionUploader("/home/brkarthik/Desktop/AIX.xls");
	}

}
