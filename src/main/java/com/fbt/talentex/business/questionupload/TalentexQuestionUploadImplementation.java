package com.fbt.talentex.business.questionupload;

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

import com.fbt.business.interfaces.TalentexQuestionUpload;
import com.fbt.dao.entities.ChapterMaster;
import com.fbt.dao.entities.CourseMaster;
import com.fbt.dao.entities.QuestionMaster;
import com.fbt.dao.entities.QuestionOptionMapping;
import com.fbt.dao.interfaces.TalentexQuestionUploadDataaccess;

@Service
public class TalentexQuestionUploadImplementation implements TalentexQuestionUpload{
	
	@Autowired
	TalentexQuestionUploadDataaccess questionUploadDataaccess;
	
	@Override
	public void talentexExamQuestionUploader(String inputFilePath) { 
		// TODO Auto-generated method stub
		List<QuestionMaster> alrQuestionMasters = null;
		alrQuestionMasters = getProcessed(inputFilePath);
		System.out.println("question size"+alrQuestionMasters.size());
		if(!alrQuestionMasters.isEmpty())
		{
			for(QuestionMaster questionMaster : alrQuestionMasters)
			{
				 
					questionUploadDataaccess.QuestionandAnswerUpload(questionMaster, questionMaster.getQuestionOptionMappings());
			}
			System.out.println("Question Uploaded successfully, check your talentex_sme database");
		} 
		
	}
	
	public List<QuestionMaster> getProcessed(String inputFilePath)
	{
		File file = new File(inputFilePath);
		List<QuestionMaster> alrQuestions = new ArrayList<QuestionMaster>();
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet1 = workbook.getSheet(0);
			Sheet sheet2 = workbook.getSheet(1);
			QuestionMaster questionMaster = null;
			QuestionOptionMapping questionOptionMapping = null;
			CourseMaster courseMaster = null;
			ChapterMaster chapterMaster = null;
			String questionNumber,  questionText = null, questionImage = null, optionText = null, optionImage = null, answerText = null, checkQuestionNumber;
			Integer course_id = null, chapter_id = null, questionMark = null, questionType = null, questionLevel = null, optionType = null;
			for(int i = 0; i < sheet1.getRows(); i++)
			{
				Cell cell = sheet1.getCell(0, i);
				Set<QuestionOptionMapping> setOptions = new HashSet<QuestionOptionMapping>();
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
						   					Cell cell2 = sheet2.getCell(5, k);
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
						   							case 1 : if(cell3.getContents().equalsIgnoreCase("TEXT ONLY")){
						   								          optionType = 11;
						   							          }else if(cell3.getContents().equalsIgnoreCase("TEXT IMAGE")){
						   							        	optionType = 12;
						   							          }else if(cell3.getContents().equalsIgnoreCase("IMAGE TEXT")){
						   							        	optionType = 21;
						   							          }else if(cell3.getContents().equalsIgnoreCase("IMAGE ONLY")){
						   							        	optionType = 22;
						   							          }
						   							case 2 : optionText = cell3.getContents();
						   							         break;
						   							case 3 : optionImage = cell3.getContents();
				   									         break;         
						   							case 4 : answerText = cell3.getContents();
				   									 		 break;	
						   							default: 
						   									break; 		 
						   							}
						   							
						   						}
						   						}
						   						questionOptionMapping = new QuestionOptionMapping();
						   						questionOptionMapping.setAnswer(answerText);
						   						questionOptionMapping.setOptionImage(optionImage);
						   						questionOptionMapping.setOptionText(optionText);
						   						questionOptionMapping.setOptionType(optionType);
						   						setOptions.add(questionOptionMapping);
						   					}
						   					
						   				}
						   			}
						   			break;
						   case 1: if(cell1.getContents().equalsIgnoreCase("TEXT ONLY")){
							           questionType = 11;
						           }else if(cell1.getContents().equalsIgnoreCase("TEXT IMAGE")){
						        	   questionType = 12;
						           }else if(cell1.getContents().equalsIgnoreCase("IMAGE TEXT")){
						        	   questionType = 21;
						           }else if(cell1.getContents().equalsIgnoreCase("IMAGE ONLY")){
						        	   questionType = 22;
						           }
						   			break;
						   case 2: course_id = Integer.parseInt(cell1.getContents());
				   				   break;
						   case 3: chapter_id = Integer.parseInt(cell1.getContents());
				   			       break;
						   case 4: questionText = cell1.getContents();
						   		   break;
						   case 5: if(!cell1.getContents().equalsIgnoreCase("nil"))
							        questionImage = cell1.getContents();
				   			       break;			
						   case 6: questionMark = Integer.parseInt(cell1.getContents());
				   				   break;
						   default: 
									break;
						  }
						 
					  
				  }
				  questionMaster = new QuestionMaster();
				  chapterMaster = new ChapterMaster();
				  chapterMaster.setId(chapter_id);
				  questionMaster.setChapterMaster(chapterMaster);
				  courseMaster = new CourseMaster();
				  courseMaster.setId(course_id);
				  questionMaster.setCourseMaster(courseMaster);
				  questionMaster.setMark(questionMark);
				  questionMaster.setQuestionImage(questionImage);
				  //questionMaster.setQuestionLevel(questionLevel);
				  questionMaster.setQuestionText(questionText);
				  questionMaster.setQuestionType(questionType);
				  questionMaster.setQuestionOptionMappings(setOptions);
				  alrQuestions.add(questionMaster);
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
