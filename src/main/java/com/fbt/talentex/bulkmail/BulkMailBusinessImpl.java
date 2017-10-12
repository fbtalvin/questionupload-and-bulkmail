package com.fbt.talentex.bulkmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbt.business.interfaces.BulkMailBusiness;
import com.fbt.business.interfaces.Unsub_Business_Interfaces;
import com.fbt.dao.entities.Unsubscribe_Details;
import com.fbt.util.SendingMailForStudent;

@Service
public class BulkMailBusinessImpl implements BulkMailBusiness 
{

	@Autowired
	SendingMailForStudent sendingMailForStudent;
	
	@Autowired
	Unsub_Business_Interfaces unsub_Business_Interfaces;

	@Override
	public void readExcelMail() throws IOException 
	{
		// Mail Excel Should be .xlsx format only
		// String excelFilePath = "/home/mukesh/corp_3001_4000.xlsx";
		String excelFilePath = "/home/FBTadmin/Documents/test.xlsx";
		
		String templatename = "test.vm";
		String mailsubject = "Reminder - Complete Your Enrollment Process";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		List<String> alrEmail = new ArrayList<String>();
		
		Set<String> alrNoDupMail = new HashSet<String>();
		
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) 
		{
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) 
			{
				Cell cell = cellIterator.next();

				switch (cell.getCellType()) 
				{
					case Cell.CELL_TYPE_STRING:
						alrEmail.add(cell.getStringCellValue());
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						System.out.print(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue());
						break;
				}
			}
			System.out.println();
		}

		workbook.close();
		inputStream.close();
		
		Date date = new Date();
		
		List<Unsubscribe_Details> alrUnsubscribe_Details = unsub_Business_Interfaces.getAllUnsubscribeDetails();
		
		ArrayList<String> alrMails = new ArrayList<String>();
		ArrayList<String> otherMails = new ArrayList<String>();
		
		for (String email : alrEmail) 
		{
			System.out.println("##################");
			for (Unsubscribe_Details unsubscribe_Details : alrUnsubscribe_Details) 
			{
				System.out.println("@@@@@@@@@@@@@@@@@@");
				if (unsubscribe_Details.getEmail().equals(email))
				{
					Date updateDate = unsubscribe_Details.getCommented_time();
					
					long diff = date.getTime() - updateDate.getTime();
				    diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
				   	
					if (unsubscribe_Details.getUnsubscribe_Type().getId() == 1)
					{
						System.out.println("$$$$$$$$$$$$$$$$$");
						alrMails.add(email);
					}
					else if (unsubscribe_Details.getUnsubscribe_Type().getId() == 2) 
					{
						if (diff > 7)
					    {
							System.out.println("-----------");
							otherMails.add(email);
					    }
						else
						{
							System.out.println("************");
							alrMails.add(email);
						}
					}
					else if (unsubscribe_Details.getUnsubscribe_Type().getId() == 3) 
					{
						if (diff > 30)
					    {
							System.out.println("!!!!!!!!!!!!!!!");
							otherMails.add(email);
					    }
						else
						{
							System.out.println("^^^^^^^^^^^^^^");
							alrMails.add(email);
						}					
					}
					else if (unsubscribe_Details.getUnsubscribe_Type().getId() == 4) 
					{
						if (diff > 275)
					    {
							System.out.println("``````````````````````");
							otherMails.add(email);
					    }
						else
						{
							System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
							alrMails.add(email);
						}
					}
					else
					{
						System.out.println("1111111111111111111111");
						otherMails.add(email);
					}
				}
			}		    
		}
		alrEmail.addAll(otherMails);
		
		if (!alrMails.isEmpty()) 
		{
			System.out.println("2222222222222222222222");
			for (String mails : alrMails)
			{
				alrEmail.remove(mails);	
			}
		}
		
		alrNoDupMail.addAll(alrEmail);
		
		if (!alrNoDupMail.isEmpty()) 
		{
			Integer count = 0;
			
			for (String email : alrNoDupMail) 
			{
				count++;

				if (count % 100 == 0) 
				{
					try 
					{
						System.out.println("Yealla wait pannula 100 mail annupi tried aai poi kidakuran...");
						Thread.sleep(10000);
					}
					catch (Exception e) 
					{
						System.out.println("I CATCH....");
					}
				} 

				try 
				{
					System.out.println("MAIL -----> "+email);
					sendingMailForStudent.readExcelMail(email, mailsubject, templatename);
				}
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
			}
		}
	}
}
