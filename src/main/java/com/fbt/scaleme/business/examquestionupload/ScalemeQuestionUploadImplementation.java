package com.fbt.scaleme.business.examquestionupload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fbt.business.interfaces.ScalemeQuestionUpload;
import com.fbt.dao.entities.IndustrySkill;
import com.fbt.dao.entities.Options;
import com.fbt.dao.entities.Question;
import com.fbt.dao.interfaces.ScalemeQuestionUploadDataaccess;


@Service
public class ScalemeQuestionUploadImplementation implements ScalemeQuestionUpload{
	
	@Autowired
	ScalemeQuestionUploadDataaccess questionUploadDataaccess;
	
	@Override
	public void scalemeExamQuestionUploader(String inputFilePath) { 
		// TODO Auto-generated method stub
		List<Question> alrQuestions = null;
		alrQuestions = getProcessed(inputFilePath);
		System.out.println("question size"+alrQuestions.size());
		if(!alrQuestions.isEmpty())
		{
			for(Question question : alrQuestions)
			{
				 
					questionUploadDataaccess.QuestionandAnswerUpload(question, question.getOptions());
			}
			System.out.println("Question Uploaded successfully, check your talentex_mocktest database");	
		} 
		
	}
	
	public List<Question> getProcessed(String inputFilePath)
	{
		File file = new File(inputFilePath);
		List<Question> alrQuestions = new ArrayList<Question>();
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet1 = workbook.getSheet(0);
			Sheet sheet2 = workbook.getSheet(1);
			Question question = null;
			Options option = null;
			String questionNumber, skillId = null, questionText = null, questionMark = null, optionText = null, answerText = null, checkQuestionNumber, questionLevel = null;
			for(int i = 0; i < sheet1.getRows(); i++)
			{
				Cell cell = sheet1.getCell(0, i);
				Set<Options> setOptions = new HashSet<Options>();
				if(cell.getContents().isEmpty())
                {
                    break;
                }
                else
                {
				  for(int j = 0; j < sheet1.getColumns(); j++)
				  {
					  Cell cell1 = sheet1.getCell(j, i);
						  switch(j)
						  {
						   case 0 : questionNumber = cell1.getContents();
						   			if(!questionNumber.isEmpty()) 
						   			{
						   				for(int k = 0; k < sheet2.getRows(); k++)
						   				{
						   					Cell cell2 = sheet2.getCell(3, k);
						   					checkQuestionNumber = cell2.getContents();
						   					if(checkQuestionNumber.equals(questionNumber))
						   					{
						   						for(int d = 1; d < sheet2.getColumns(); d++)
						   						{
						   							Cell cell3 = sheet2.getCell(d, k);
						   							if(checkQuestionNumber.equals(questionNumber))
						   							{
						   							switch(d) 
						   							{
						   							case 1 : optionText = cell3.getContents();
						   									 break;
						   							case 2 : answerText = cell3.getContents();
				   									 		 break;	
						   							default: 
						   									break; 		 
						   							}
						   							
						   						}
						   						}
						   						option = new Options();
						   						option.setOption(optionText);
						   						option.setAnswer(Integer.parseInt(answerText));
						   						setOptions.add(option);
						   					}
						   					
						   				}
						   			}
						   			break;
						   case 1: skillId = cell1.getContents();
						   			break;
						   case 2: questionText = cell1.getContents();
						   			break;
						   case 3: questionMark = cell1.getContents();
				   					break;
						   case 4: questionLevel = cell1.getContents();	
						   			break;
						   default: 
									break;
						  }
						 
					  
				  }
				  question = new Question();
				  IndustrySkill industrySkill = new IndustrySkill();
				  industrySkill.setId(Integer.parseInt(skillId));
				  question.setQuestion(questionText);
				  question.setMark(Integer.parseInt(questionMark));
				  question.setIndustrySkill(industrySkill);
				  question.setOptions(setOptions);
				  question.setLevel(questionLevel);
				  alrQuestions.add(question);
                }
			}
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return alrQuestions;
	}
}
