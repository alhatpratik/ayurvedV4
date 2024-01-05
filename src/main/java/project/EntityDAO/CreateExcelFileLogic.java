package project.EntityDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Patient;

@Component
public class CreateExcelFileLogic {
	
	Random random = new Random();
	
	@Autowired
	DateLogic dateLogic;

	public String getExcelFile(String departmentName,LocalDate local_date) // this reads patientDataRead File and -> patientDataWrite File 
	{
		
		int dailyOPDPatient = randomNumberToGetDailyOPD();
		int changeDaysCount = 1;
		
		System.out.println("Department Name : "+departmentName+"   "+local_date);

		List<Patient> list_of_patients = new ArrayList<>();

		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\patientDataRead.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setName(arr[0].toLowerCase().trim());
				}
				catch(Exception e) {
					return "Patient Name not found Or Invalid";
				}
				
				try {
					p.setAddress(arr[1].toLowerCase().trim());
				}
				catch(Exception e) {
					return "Address not found Or Invalid";
				}
				
				try {
					System.out.println("11");
					p.setAge((int)Double.parseDouble(arr[2]));
				}
				catch(Exception e) {
					return "Age not found Or Invalid";
				}
				
				try {
					char c = (char)arr[3].charAt(0);
					System.out.println("char c = "+c);
					p.setSex(c);
					System.out.println("12");
				}
				catch(Exception e) {
					return "Gender not found Or Invalid";
				}

				try {
					
					int min = 1;
					int max = 95;
					p.setYearly_no((int)random.nextInt((max - min) + 1) + min);
				}
				catch(Exception e) {
					return "Yearly Number not found Or Invalid";
				}

				list_of_patients.add(p);
			}

			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}

		//---------------------------------------------- WRITE FILE ----------------------------


		String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\patientDataWrite.xlsx";

		try {

			FileInputStream file_input = new FileInputStream(excelFileLocation);

			try {

				Workbook work_book = WorkbookFactory.create(file_input);
				Sheet sheet = work_book.getSheetAt(0);

				for(int i=0;i<list_of_patients.size();i++)
				{
					
					Row dataRow = sheet.createRow(i);
					dataRow.createCell(0).setCellValue(list_of_patients.get(i).getName());
					dataRow.createCell(1).setCellValue(list_of_patients.get(i).getAddress());
//					if(departmentName.equalsIgnoreCase("balrog"))
//					{
//
//					}
//					else
//					{
					System.out.println("age for "+list_of_patients.get(i).getName()+" is "+list_of_patients.get(i).getAge());
						dataRow.createCell(2).setCellValue(list_of_patients.get(i).getAge());
//					}

					dataRow.createCell(3).setCellValue((char)list_of_patients.get(i).getSex());
					
					System.out.println("yearly no ::: "+list_of_patients.get(i).getYearly_no());
					if(list_of_patients.get(i).getYearly_no()>=1 && list_of_patients.get(i).getYearly_no()<=50) {
						
						System.out.println("inside kaya checking for strirog ");
						System.out.println(" "+list_of_patients.get(i).getName()+"   "+list_of_patients.get(i).getAge()+"   "+list_of_patients.get(i).getSex()+"   "+list_of_patients.get(i).getYearly_no());
						
						boolean flag = (char)list_of_patients.get(i).getSex()=='F';
						System.out.println("is Female ? ::: "+flag+"  :::  "+list_of_patients.get(i).getSex());
						
						if(list_of_patients.get(i).getAge()<=14) {
							
							departmentName = "Balrog";
						}else if((char)list_of_patients.get(i).getSex()=='F' && ((int)(list_of_patients.get(i).getYearly_no()%2) == 0) ) {
							departmentName = "strirog";
						}else {
							departmentName = "kaychikitsa";
						}
						
					}else if(list_of_patients.get(i).getYearly_no()>50 && list_of_patients.get(i).getYearly_no()<=65) {
						
						System.out.println("inside panch checking for strirog ");
						
						System.out.println(" "+list_of_patients.get(i).getName()+"   "+list_of_patients.get(i).getAge()+"   "+list_of_patients.get(i).getSex()+"   "+list_of_patients.get(i).getYearly_no());
						
						boolean flag = (char)list_of_patients.get(i).getSex()=='F';
						System.out.println("is Female ? ::: "+flag+"  :::  "+list_of_patients.get(i).getSex());
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else if((char)list_of_patients.get(i).getSex()=='F' && ((list_of_patients.get(i).getYearly_no()%2) == 0) ) {
							departmentName = "strirog";
						}else {
							departmentName = "panchakarma";
						}
					
					}
					else if(list_of_patients.get(i).getYearly_no()>65 && list_of_patients.get(i).getYearly_no()<=80 ) {
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else {
							departmentName = "shalya";
						}
						
					}
					else if(list_of_patients.get(i).getYearly_no()>80 && list_of_patients.get(i).getYearly_no()<=90) {
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else {
							departmentName = "Shalakya";
						}
					}
					else {
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else {
							departmentName = "Atyayika";
						}
					}
					dataRow.createCell(4).setCellValue(""+departmentName);
					
					if( i > (dailyOPDPatient*changeDaysCount)) {
						
						changeDaysCount = changeDays(changeDaysCount);
						dailyOPDPatient = randomNumberToGetDailyOPD();
						local_date = changeDateToNextDay(local_date);
					}
					
					dataRow.createCell(7).setCellValue(local_date);
					
					int min = 1;
					int max = 100;
					dataRow.createCell(8).setCellValue((int)random.nextInt((max - min) + 1) + min);
					
					if(departmentName.equalsIgnoreCase("kaychikitsa"))
					{
						String [] kayaPatients = new String[16];
						kayaPatients[0] = "shwaas";
						kayaPatients[1] = "agnimandya";
						kayaPatients[2] = "ajirna";
						kayaPatients[3] = "anidra";
						kayaPatients[4] = "brama";
						kayaPatients[5] = "janushul";
						kayaPatients[6] = "jwar";
						kayaPatients[7] = "kasa";
						kayaPatients[8] = "Malavashtambha";
						kayaPatients[9] = "prameha";
						kayaPatients[10] = "sandhivat";
						kayaPatients[11] = "sarvanga kandu";
						kayaPatients[12] = "vatrakta";
						kayaPatients[13] = "khalitya";
						kayaPatients[14] = "palitya";
						kayaPatients[15] = "shwaas";

						int result = (int)(Math.random()*(16-0)); 

						dataRow.createCell(5).setCellValue(""+kayaPatients[result]);
					}
					else if(departmentName.equalsIgnoreCase("panchakarma"))
					{
						String [] panchPatients = new String[11];
						panchPatients[0] = "anidra";
						panchPatients[1] = "januvat";
						panchPatients[2] = "karshya";
						panchPatients[3] = "kativat";
						panchPatients[4] = "malabandha";
						panchPatients[5] = "prushthavat";
						panchPatients[6] = "sandhivat";
						panchPatients[7] = "sarvangmarda";
						panchPatients[8] = "vatrakta";
						panchPatients[9] = "khalitya";
						panchPatients[10] = "palitya";

						int result = (int)(Math.random()*(11-0)); 

						dataRow.createCell(5).setCellValue(""+panchPatients[result]);
					}
					else if(departmentName.equalsIgnoreCase("strirog"))
					{
						String [] strirogPatients = new String[3];
						strirogPatients[0] = "grantyartav";
						strirogPatients[1] = "stanyakshya";
						strirogPatients[2] = "rajonivrutti";

						int result = (int)(Math.random()*(3-0)); 

						dataRow.createCell(5).setCellValue(""+strirogPatients[result]);

					}
					else if(departmentName.equalsIgnoreCase("Balrog"))
					{
						int weight = (int)(Math.random()*(12-1)+1);
						dataRow.createCell(2).setCellValue(weight);

						String [] balrogPatients = new String[12];
						balrogPatients[0] = "pratishyaya";
						balrogPatients[1] = "pandu";
						balrogPatients[2] = "karshya";
						balrogPatients[3] = "karshya";
						balrogPatients[4] = "udarshool";		
						balrogPatients[5] = "shayyamutrata";
						balrogPatients[6] = "shirshul";
						balrogPatients[7] = "vran";
						balrogPatients[8] = "mutradah";
						balrogPatients[9] = "shvitra";
						balrogPatients[10] = "colic pain";
						balrogPatients[11] = "conjutauitis";

						int result = (int)(Math.random()*(12-0)); 
						dataRow.createCell(5).setCellValue(""+balrogPatients[result]);




					}
					else if(departmentName.equalsIgnoreCase("Shalya"))
					{
						String [] shalyaPatients = new String[3];
						shalyaPatients[0] = "chippa";
						shalyaPatients[1] = "vrushanvat";
						shalyaPatients[2] = "kunakha";

						int result = (int)(Math.random()*(3-0)); 

						dataRow.createCell(5).setCellValue(""+shalyaPatients[result]);
					}
					else if(departmentName.equalsIgnoreCase("Shalakya"))
					{
						String [] shalakyaPatients = new String [9];
						shalakyaPatients[0] = "drushtimandya";
						shalakyaPatients[1] = "gilayu shoph";
						shalakyaPatients[2] = "gilayu shoph";
						shalakyaPatients[3] = "khalitya";
						shalakyaPatients[4] = "mukhapak";
						shalakyaPatients[5] = "netrabhishyandya";
						shalakyaPatients[6] = "pinas";
						shalakyaPatients[7] = "pratishyaya";
						shalakyaPatients[8] = "suryavarta";

						int result = (int)(Math.random()*(9-0)); 

						dataRow.createCell(5).setCellValue(""+shalakyaPatients[result]);


					}
					else if(departmentName.equalsIgnoreCase("Atyayika"))
					{
						String [] atyayikaPatients = new String [3];
						atyayikaPatients [0] = "udarshool";
						atyayikaPatients [1] = "chhardi";
						atyayikaPatients [2] = "urshool";

						int result = (int)(Math.random()*(3-0)); 

						dataRow.createCell(5).setCellValue(""+atyayikaPatients[result]);

					}
					else if(departmentName.equalsIgnoreCase("Swasthavrutta"))
					{
						String [] swasthavruttaPatients = new String [8];
						swasthavruttaPatients[0] = "grudrasi";
						swasthavruttaPatients[1] = "hridrog";
						swasthavruttaPatients[2] = "khalitya";
						swasthavruttaPatients[3] = "Malavashtambha";
						swasthavruttaPatients[4] = "nidranash";
						swasthavruttaPatients[5] = "prameha";
						swasthavruttaPatients[6] = "shirshool";
						swasthavruttaPatients[7] = "udavarta";

						int result = (int)(Math.random()*(8-0)); 

						dataRow.createCell(5).setCellValue(""+swasthavruttaPatients[result]);
					}


					if(list_of_patients.get(i).getAge()>=40)
					{
						int weight = (int)(Math.random()*(90-50)+50);
//						int weight = (int)(Math.random()*(120-50)+50);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=30 && list_of_patients.get(i).getAge()<40)
					{
						int weight = (int)(Math.random()*(80-50)+50);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=15 && list_of_patients.get(i).getAge()<30)
					{
						int weight = (int)(Math.random()*(60-40)+40);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=15 && list_of_patients.get(i).getAge()<25)
					{
						int weight = (int)(Math.random()*(55-35)+35);
						dataRow.createCell(6).setCellValue(weight);
					}

					else if(list_of_patients.get(i).getAge()>=10 && list_of_patients.get(i).getAge()<15)
					{
						int weight = (int)(Math.random()*(32-25)+25);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=8 && list_of_patients.get(i).getAge()<10)
					{
						int weight = (int)(Math.random()*(30-20)+20);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=6 && list_of_patients.get(i).getAge()<8)
					{
						int weight = (int)(Math.random()*(25-15)+15);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=4 && list_of_patients.get(i).getAge()<6)
					{
						int weight = (int)(Math.random()*(17-13)+13);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=2 && list_of_patients.get(i).getAge()<4)
					{
						int weight = (int)(Math.random()*(14-10)+10);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=1)
					{
						int weight = (int)(Math.random()*(9-6)+6);
						dataRow.createCell(6).setCellValue(weight);
					}

				}

				file_input.close();
				FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
				work_book.write(file_output_stream);
				file_output_stream.close();
				System.out.println("Excel sheet Updated Successfully");

			} catch (EncryptedDocumentException | IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		return "Data Updated Successfully ...!";
	}

	public String getIPDExcelFile(String departmentName,LocalDate local_date)
	{
		System.out.println("Department Name : "+departmentName+"   "+local_date);

		List<Patient> list_of_patients = new ArrayList<>();

		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\ipdRead.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setName(arr[0].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Name not found Or Invalid";
				}
				
				try {
					p.setAddress(arr[1].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Address not found Or Invalid";
				}
				
				try {
					p.setAge((int)Double.parseDouble(arr[2]));
				} catch (Exception e) {
					return "Patient Age not found Or Invalid";
				}
				

				try {
					System.out.println("11");
					p.setSex(arr[3].charAt(0));
				} catch (Exception e) {
					return "Patient gender not found Or Invalid";
				}
				
				try {
					System.out.println("12");
					p.setYearly_no((int)Double.parseDouble(arr[4])); // this is random number
				} catch (Exception e) {
					return "Patient Random generated number not found Or Invalid";
				}
				

				list_of_patients.add(p);
			}

			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}





		//---------------------------------------------- WRITE FILE ----------------------------


		String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\ipdWrite.xlsx";

		try {

			FileInputStream file_input = new FileInputStream(excelFileLocation);

			try {

				Workbook work_book = WorkbookFactory.create(file_input);
				Sheet sheet = work_book.getSheetAt(0);

				for(int i=0;i<list_of_patients.size();i++)
				{
					
					//-------------------------------------------------------------------------------------------
					
					if(list_of_patients.get(i).getYearly_no()>=1 && list_of_patients.get(i).getYearly_no()<=50) {
						
						System.out.println("inside kaya checking for strirog ");
						System.out.println(" "+list_of_patients.get(i).getName()+"   "+list_of_patients.get(i).getAge()+"   "+list_of_patients.get(i).getSex()+"   "+list_of_patients.get(i).getYearly_no());
						
						boolean flag = (char)list_of_patients.get(i).getSex()=='F';
						System.out.println("is Female ? ::: "+flag+"  :::  "+list_of_patients.get(i).getSex());
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else if((char)list_of_patients.get(i).getSex()=='F' && ((int)(list_of_patients.get(i).getYearly_no()%2) == 0) ) {
							departmentName = "strirog";
						}else {
							departmentName = "kaychikitsa";
						}
						
					}else if(list_of_patients.get(i).getYearly_no()>50 && list_of_patients.get(i).getYearly_no()<=65) {
						
						System.out.println("inside panch checking for strirog ");
						
						System.out.println(" "+list_of_patients.get(i).getName()+"   "+list_of_patients.get(i).getAge()+"   "+list_of_patients.get(i).getSex()+"   "+list_of_patients.get(i).getYearly_no());
						
						boolean flag = (char)list_of_patients.get(i).getSex()=='F';
						System.out.println("is Female ? ::: "+flag+"  :::  "+list_of_patients.get(i).getSex());
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else if((char)list_of_patients.get(i).getSex()=='F' && ((list_of_patients.get(i).getYearly_no()%2) == 0) ) {
							departmentName = "strirog";
						}else {
							departmentName = "panchakarma";
						}
					
					}
					else if(list_of_patients.get(i).getYearly_no()>65 && list_of_patients.get(i).getYearly_no()<=80 ) {
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else {
							departmentName = "shalya";
						}
						
					}
					else {
						
						if(list_of_patients.get(i).getAge()<=14) {
							departmentName = "Balrog";
						}else {
							departmentName = "Shalakya";
						}
						
					}
					//-------------------------------------------------------------------------------------------
					
					Row dataRow = sheet.createRow(i+1);
					dataRow.createCell(0).setCellValue(list_of_patients.get(i).getName());
					dataRow.createCell(1).setCellValue(list_of_patients.get(i).getAddress());
					if(departmentName.equalsIgnoreCase("balrog"))
					{

					}
					else
					{
						dataRow.createCell(2).setCellValue(list_of_patients.get(i).getAge());
					}

					dataRow.createCell(3).setCellValue((char)list_of_patients.get(i).getSex());
					dataRow.createCell(4).setCellValue(""+departmentName);
					dataRow.createCell(7).setCellValue(local_date);
					if(departmentName.equalsIgnoreCase("kaychikitsa"))
					{
						if(list_of_patients.get(i).getAge()>=40)
						{
							String [] kayaPatients = new String[5];
							kayaPatients[0] = "aamvat";
							kayaPatients[1] = "gradhrasi";
							kayaPatients[2] = "katigatvat";
							kayaPatients[3] = "manyagatvat";
							kayaPatients[4] = "sandhigatvat";

							int result = (int)(Math.random()*(5-0)); 

							dataRow.createCell(5).setCellValue(""+kayaPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));

						}
						else
						{
							String [] kayaPatients = new String[9];
							kayaPatients[0] = "aamlapitta";
							kayaPatients[1] = "atisar";
							kayaPatients[2] = "nidranash";
							kayaPatients[3] = "grahani";
							kayaPatients[4] = "pandu";
							kayaPatients[5] = "parshnirshul";
							kayaPatients[6] = "shwas";
							kayaPatients[7] = "sthoulya";
							kayaPatients[8] = "vatajkas";

							int result = (int)(Math.random()*(9-0)); 

							dataRow.createCell(5).setCellValue(""+kayaPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}

					}
					else if(departmentName.equalsIgnoreCase("panchakarma"))
					{

						if(list_of_patients.get(i).getAge()>=40)
						{
							String [] panchPatients = new String[6];
							panchPatients[0] = "aamvat";
							panchPatients[1] = "katigatvat";
							panchPatients[2] = "Malavashtambha";
							panchPatients[3] = "manyagatvat";
							panchPatients[4] = "sandhigatvat";
							panchPatients[5] = "GRADHRASI";

							int result = (int)(Math.random()*(6-0)); 

							dataRow.createCell(5).setCellValue(""+panchPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));

						}
						else
						{
							String [] panchPatients = new String[2];
							panchPatients[0] = "nidranash";
							panchPatients[1] = "sthoulya";

							int result = (int)(Math.random()*(2-0)); 

							dataRow.createCell(5).setCellValue(""+panchPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}

					}
					else if(departmentName.equalsIgnoreCase("strirog"))
					{

						if(list_of_patients.get(i).getAge()>=18 && list_of_patients.get(i).getAge()>=35)
						{
							dataRow.createCell(5).setCellValue("stri vyandhyatva");

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));

						}
						else if(list_of_patients.get(i).getAge()>=14 && list_of_patients.get(i).getAge()<=45)
						{
							String [] strirogPatients = new String[8];
							strirogPatients[0] = "anartav";
							strirogPatients[1] = "PCOD";
							strirogPatients[2] = "garbhini atisar";
							strirogPatients[3] = "garbhini chhardi";
							strirogPatients[4] = "granthibhut rajpravartan";
							strirogPatients[5] = "raktpradar";
							strirogPatients[6] = "kashtartav";
							strirogPatients[7] = "aniyamit rajpravartan";
							
							int result = (int)(Math.random()*(8-0)); 

							dataRow.createCell(5).setCellValue(""+strirogPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
							
						}
						else if(list_of_patients.get(i).getAge()>=14)
						{
							String [] strirogPatients = new String[6];
							strirogPatients[0] = "puyayukt yonistrav";
							strirogPatients[1] = "shwetpradar";
							strirogPatients[2] = "yonikandu";
							strirogPatients[4] = "yoni daurgandhya";
							strirogPatients[5] = "yonidah";
							
							int result = (int)(Math.random()*(6-0));

							dataRow.createCell(5).setCellValue(""+strirogPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
							
						}
						
					}
					else if(departmentName.equalsIgnoreCase("Balrog"))
					{
						int weight = (int)(Math.random()*(12-1)+1);
						dataRow.createCell(2).setCellValue(weight);

						if(list_of_patients.get(i).getAge()>=1 && list_of_patients.get(i).getAge()>=3)
						{
							String [] balrogPatients = new String[3];
							balrogPatients[0] = "atisar";
							balrogPatients[1] = "vataj atisar";
							balrogPatients[2] = "pittaj atisar";
							
							int result = (int)(Math.random()*(3-0));

							dataRow.createCell(5).setCellValue(""+balrogPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}
						else if(list_of_patients.get(i).getAge()>=1 && list_of_patients.get(i).getAge()>=5)
						{
							String [] balrogPatients = new String[3];
							balrogPatients[0] = "krumi";
							balrogPatients[1] = "visham jwar";
							balrogPatients[2] = "bahupitta kamla";
							
							int result = (int)(Math.random()*(3-0));

							dataRow.createCell(5).setCellValue(""+balrogPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}
						else if(list_of_patients.get(i).getAge()>=3 && list_of_patients.get(i).getAge()>=6)
						{
							String [] balrogPatients = new String[2];
							balrogPatients[0] = "vishuchika";
							balrogPatients[1] = "pandu";
							
							int result = (int)(Math.random()*(2-0));

							dataRow.createCell(5).setCellValue(""+balrogPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}
						else if(list_of_patients.get(i).getAge()>=3 && list_of_patients.get(i).getAge()>=6)
						{
							dataRow.createCell(5).setCellValue("sthoulya");

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
						}
					}
					else if(departmentName.equalsIgnoreCase("Shalya"))
					{
						if(list_of_patients.get(i).getAge()>=35 && list_of_patients.get(i).getAge()>=50)
						{
							String [] shalyaPatients = new String[6];
							shalyaPatients[0] = "arsh";
							shalyaPatients[1] = "parikartika";
							shalyaPatients[2] = "bhagandar";
							shalyaPatients[3] = "gudbhransh";
							shalyaPatients[4] = "agantujravan";
							shalyaPatients[5] = "mutrashmari";
							
							int result = (int)(Math.random()*(6-0));

							dataRow.createCell(5).setCellValue(""+shalyaPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));

						}
						

					}
					else if(departmentName.equalsIgnoreCase("Shalakya"))
					{
						if(list_of_patients.get(i).getAge()>=50 && list_of_patients.get(i).getAge()>=70)
						{
							dataRow.createCell(5).setCellValue("terizium");

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
	
						}
						else if(list_of_patients.get(i).getAge()>=25 && list_of_patients.get(i).getAge()>=35)
						{
							dataRow.createCell(5).setCellValue("netradah");

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));
	
						}
						else if(list_of_patients.get(i).getAge()>=25 && list_of_patients.get(i).getAge()>=35)
						{
							String [] shalakyaPatients = new String [4];
							shalakyaPatients [0] = "pterigium";
							shalakyaPatients [1] = "dacryocystits";
							shalakyaPatients [2] = "lagan";
							shalakyaPatients [3] = "netradah";
							
							int result = (int)(Math.random()*(4-0));

							dataRow.createCell(5).setCellValue(""+shalakyaPatients[result]);

							int admit_days = (int)(Math.random()*(10-6))+6;

							dataRow.createCell(8).setCellValue(local_date.plusDays(admit_days));

						}
						
					}
					
					if(list_of_patients.get(i).getAge()>=40)
					{
						int weight = (int)(Math.random()*(120-50)+50);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=30 && list_of_patients.get(i).getAge()<40)
					{
						int weight = (int)(Math.random()*(80-50)+50);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=15 && list_of_patients.get(i).getAge()<30)
					{
						int weight = (int)(Math.random()*(60-40)+40);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=15 && list_of_patients.get(i).getAge()<25)
					{
						int weight = (int)(Math.random()*(55-35)+35);
						dataRow.createCell(6).setCellValue(weight);
					}

					else if(list_of_patients.get(i).getAge()>=10 && list_of_patients.get(i).getAge()<15)
					{
						int weight = (int)(Math.random()*(32-25)+25);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=8 && list_of_patients.get(i).getAge()<10)
					{
						int weight = (int)(Math.random()*(30-20)+20);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=6 && list_of_patients.get(i).getAge()<8)
					{
						int weight = (int)(Math.random()*(25-15)+15);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=4 && list_of_patients.get(i).getAge()<6)
					{
						int weight = (int)(Math.random()*(17-13)+13);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=2 && list_of_patients.get(i).getAge()<4)
					{
						int weight = (int)(Math.random()*(14-10)+10);
						dataRow.createCell(6).setCellValue(weight);
					}
					else if(list_of_patients.get(i).getAge()>=1)
					{
						int weight = (int)(Math.random()*(9-6)+6);
						dataRow.createCell(6).setCellValue(weight);
					}

				}

				file_input.close();
				FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
				work_book.write(file_output_stream);
				file_output_stream.close();
				System.out.println("Excel sheet Updated Successfully");

			} catch (EncryptedDocumentException | IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		return "Data Updated Successfully ...!";
		
	}
	
	
	
	public int randomNumberToGetDailyOPD() {
		int dailyOPD = random.nextInt(11) + 80;
		System.out.println("Generated Number::: "+dailyOPD);
		return dailyOPD;
	}
	
	public int changeDays(int changeDaysCount) {
		changeDaysCount = changeDaysCount + 1;
        return changeDaysCount;
	}

	public LocalDate changeDateToNextDay(LocalDate local_date) {
		
		return local_date.plusDays(1);
	}
	
	
//-------------------------------------- FOR FINAL DATA --------------------------------------------
	
	
	public String getFinalExcelFile()
	{

		List<Patient> list_of_patients = new ArrayList<>();

		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\patientDataWrite.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setName(arr[0].toLowerCase().trim());
				} catch (Exception e) {

				System.out.println("Patient Name not found or Invalid");
				}
				
				try {
					p.setAddress(arr[1].toLowerCase().trim());
				} catch (Exception e) {
					System.out.println("Patient Address not found or Invalid");
				}
				
				try {
					System.out.println("11 age");
					System.out.println("11 and age is :: "+((int)Double.parseDouble(arr[2])));
					p.setAge((int)Double.parseDouble(arr[2]));
				} catch (Exception e) {
					System.out.println("Patient Age not found or Invalid");
				}
				

				try {
					
					char s = (char)(int)Double.parseDouble(arr[3]);
					System.out.println("sex we get is :: "+s);
					p.setSex(s);
					System.out.println("conversion in char successful");
				} catch (Exception e) {
					System.out.println("Patient Gender not found or Invalid");
				}
				
				try {
					p.setDepartment(arr[4].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Department not found or Invalid";
				}
				
				try {
					p.setDiagnosis(arr[5].toLowerCase().trim());
				} catch (Exception e) {
					System.out.println("Patient diagnosis not found or Invalid");
				}
				
				try {
					p.setWeight((int)Double.parseDouble(arr[6]));
				} catch (Exception e) {
					System.out.println("Patient weight not found or Invalid");
				}
				
				try {
					
					int dateInt = (int)Double.parseDouble(arr[7]);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					p.setDate(admitDate);
					
				} catch (Exception e) {
					System.out.println("Patient Admit Date not found or Invalid");
				}
				
				
				if(((int)Double.parseDouble(arr[8]))<40) {
					if((((int)Double.parseDouble(arr[8])%2)==0)) {
						p.setDischarge_date(p.getDate().plusDays(4));
					}else {
						p.setDischarge_date(p.getDate().plusDays(3));
					}
					
				}
				

				list_of_patients.add(p);
			}

			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}





		//---------------------------------------------- WRITE FILE ----------------------------


		String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\patientFinalData.xlsx";

		try {

			FileInputStream file_input = new FileInputStream(excelFileLocation);

			try {

				Workbook work_book = WorkbookFactory.create(file_input);
				Sheet sheet = work_book.getSheetAt(0);

				for(int i=0;i<list_of_patients.size();i++)
				{
					
					Row dataRow = sheet.createRow(i);
					dataRow.createCell(0).setCellValue(i+1);
					dataRow.createCell(1).setCellValue(list_of_patients.get(i).getName());
					dataRow.createCell(2).setCellValue(list_of_patients.get(i).getAddress());
					dataRow.createCell(3).setCellValue(list_of_patients.get(i).getAge());
					dataRow.createCell(4).setCellValue(list_of_patients.get(i).getSex());
					dataRow.createCell(5).setCellValue(list_of_patients.get(i).getDepartment());
					dataRow.createCell(6).setCellValue(list_of_patients.get(i).getDiagnosis());
					dataRow.createCell(7).setCellValue(list_of_patients.get(i).getWeight());
					
					dataRow.createCell(8).setCellValue(list_of_patients.get(i).getDate());
					
					if(list_of_patients.get(i).getDischarge_date() != null) {
						dataRow.createCell(9).setCellValue(list_of_patients.get(i).getDischarge_date());
					}

					
				}

				file_input.close();
				FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
				work_book.write(file_output_stream);
				file_output_stream.close();
				System.out.println("Excel sheet Updated Successfully PatientDataWrite.xlsx");

			} catch (EncryptedDocumentException | IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		return "Data Uploaded successfully";
	}
	
	
//-------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------
	
	
	public String generateFinalData(){

		List<Patient> list_of_patients = new ArrayList<>();
		List<Patient> list_of_old_patients = new ArrayList<>();
		
		
		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\patientFinalData.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				Patient old_p = new Patient();
				
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setNew_no((int)Double.parseDouble(arr[0]));
				} catch (Exception e) {
					return "Patient New Number not found or Invalid";
				}
				
				

				try {
					p.setName(arr[1].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Name not found or Invalid";
				}
				
				
				try {
					p.setAddress(arr[2].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Address not found or Invalid";
				}
				
				
				try {
					p.setAge((int)Double.parseDouble(arr[3]));
				} catch (Exception e) {
					return "Patient Age not found or Invalid";
				}
				
				
				try {
					
					char sex = (char)(int)Double.parseDouble(arr[4]);
					System.out.println("sex we get is :: "+sex);
					p.setSex(sex);
					
				} catch (Exception e) {
					return "Patient Gender not found or Invalid";
				}
				
				
				try {
					p.setDepartment(arr[5].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Department not found or Invalid";
				}
				
				
				try {
					p.setDiagnosis(arr[6].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Diagnosis not found or Invalid";
				}
				
				
				try {
					p.setWeight((int)Double.parseDouble(arr[7]));
				} catch (Exception e) {
					return "Patient Weight not found or Invalid";
				}
				
				
				try {
					
					System.out.println("inside date");
					System.out.println("inside date ::: "+arr[8]);
					int dateInt = (int)Double.parseDouble(arr[8]);
					System.out.println("conversion in int ::: "+dateInt);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					p.setDate(admitDate);
					
//					p.setDate(dateLogic.getDate(arr[8]));
					
				} catch (Exception e) {
					System.out.println("Patient Admit Date not found or Invalid");
					return "Patient Admit Date not found or Invalid";
				}
				
				
				//---------------------------------------------------------------------
				
						
				try {
					old_p.setNew_no((int)Double.parseDouble(arr[0]));
				} catch (Exception e) {
					System.out.println("Patient Serial Number not found or Invalid");
					return "Patient Serial Number not found or Invalid";
				}
				
				
				try {
					old_p.setName(arr[1].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Name not found or Invalid";
				}
				
				
				try {
					old_p.setAddress(arr[2].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Adderess not found or Invalid";
				}
				
				
				try {
					old_p.setAge((int)Double.parseDouble(arr[3]));
				} catch (Exception e) {
					return "Patient Age not found or Invalid";
				}
				
				
				try {
					
					char sex = (char)(int)Double.parseDouble(arr[4]);
					System.out.println("sex we get is :: "+sex);
					old_p.setSex(sex);
				} catch (Exception e) {
					return "Patient gender not found or Invalid";
				}
				
				
				try {
					old_p.setDepartment(arr[5].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Department not found or Invalid";
				}
				
				
				try {
					old_p.setDiagnosis(arr[6].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Diagnosis not found or Invalid";
				}
				
				
				try {
					old_p.setWeight((int)Double.parseDouble(arr[7]));
				} catch (Exception e) {
					return "Patient weight not found or Invalid";
				}
				
				
				try {
					
					int dateInt = (int)Double.parseDouble(arr[8]);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					old_p.setDate(admitDate);
					
//					old_p.setDate(dateLogic.getDate(arr[8]));
				
				} catch (Exception e) {
					return "Patient Admit Date not found or Invalid";
				}
				
				
				//---------------------------------------------------------------------
				System.out.println("size of array ::: "+arr.length);
				
				list_of_patients.add(p);
				
				if(arr.length > 9) {
					
					try {
						
						int dateInt = (int)Double.parseDouble(arr[9]);
						LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
						System.out.println("localdate ::: "+admitDate);
						old_p.setDate(admitDate);
//						old_p.setDate(dateLogic.getDate(arr[9]));
						
					} catch (Exception e) {
						return "Patient Admit Date not found or Invalid";
					}
					
					
					try {
						old_p.setOld_no(p.getNew_no());
					} catch (Exception e) {
						return "Patient Old Number not found or Invalid";
					}
					
					
					try {
						old_p.setNew_no(0);
					} catch (Exception e) {
						return "Patient Serial Number not found or Invalid";
					}
					
					list_of_old_patients.add(old_p);
				}
				
			}
			
			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}


		//---------------------------------------------- WRITE FILE ----------------------------


		String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\patientFinalGeneratedData.xlsx";

		try {

			FileInputStream file_input = new FileInputStream(excelFileLocation);

			try {
				
//				int count = 0;
				Workbook work_book = WorkbookFactory.create(file_input);
				
				Sheet sheet = work_book.getSheetAt(0);
				
				for(Patient pt : list_of_old_patients) {
					list_of_patients.add(pt);
				}

				list_of_patients.sort((p1, p2) -> {
					return p1.getDate().compareTo(p2.getDate());
				});
				
				for(int i=0;i<list_of_patients.size();i++)
				{
					
					Row dataRow = sheet.createRow(i);
					dataRow.createCell(0).setCellValue(list_of_patients.get(i).getNew_no());
					dataRow.createCell(1).setCellValue(list_of_patients.get(i).getOld_no());
					dataRow.createCell(2).setCellValue(list_of_patients.get(i).getName());
					dataRow.createCell(3).setCellValue(list_of_patients.get(i).getAddress());
					dataRow.createCell(4).setCellValue(list_of_patients.get(i).getAge());
					dataRow.createCell(5).setCellValue(list_of_patients.get(i).getSex());
					dataRow.createCell(6).setCellValue(list_of_patients.get(i).getDiagnosis());
					dataRow.createCell(7).setCellValue(list_of_patients.get(i).getDepartment());
					dataRow.createCell(8).setCellValue(list_of_patients.get(i).getWeight());
					dataRow.createCell(9).setCellValue(list_of_patients.get(i).getDate());
					
				}
				
				file_input.close();
				FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
				work_book.write(file_output_stream);
				file_output_stream.close();
				System.out.println("Excel sheet Updated Successfully patientFinalData.xlsx");

			} catch (EncryptedDocumentException | IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		return "Data uploaded Successfully ...!";
	}
	
	public String generateDailyData(){

		List<Patient> list_of_patients = new ArrayList<>();
		List<Patient> list_of_old_patients = new ArrayList<>();
		List<Patient> list_of_new_patients = new ArrayList<>();
		
		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\ManageDailyData.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setNew_no((int)Double.parseDouble(arr[0]));
				} catch (Exception e) {
					return "Patient New Number not found or Invalid";
				}
				
				try {
					p.setOld_no((int)Double.parseDouble(arr[1]));
				} catch (Exception e) {
					return "Patient Old Number not found or Invalid";
				}
				
				
				
				try {
					p.setName(arr[2].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Name not found or Invalid";
				}
				
				
				try {
					p.setAddress(arr[3].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Address not found or Invalid";
				}
				
				
				try {
					p.setAge((int)Double.parseDouble(arr[4]));
				} catch (Exception e) {
					return "Patient Age not found or Invalid";
				}
				
				
				try {
					
					char sex = (char)(int)Double.parseDouble(arr[4]);
					System.out.println("sex we get is :: "+sex);
					p.setSex(sex);
//					p.setSex(arr[5].charAt(0));
				} catch (Exception e) {
					return "Patient Gender not found or Invalid";
				}
				
				try {
					p.setDepartment(arr[6].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Department not found or Invalid";
				}
				
				try {
					p.setDiagnosis(arr[7].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Diagnosis not found or Invalid";
				}
				
				try {
					p.setWeight((int)Double.parseDouble(arr[8]));
				} catch (Exception e) {
					return "Patient Weight not found or Invalid";
				}
				
				
				try {
					int dateInt = (int)Double.parseDouble(arr[9]);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					p.setDate(admitDate);
//					p.setDate(dateLogic.getDate(arr[9]));
				} catch (Exception e) {
					return "Patient Admit Date not found or Invalid";
				}
				
				
				System.out.println("size of array ::: "+arr.length);
				
				list_of_patients.add(p);
				
			}
			
			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}


		//---------------------------------------------- WRITE FILE ----------------------------

		Map<Integer, Patient> mapOfOldAndNewPatients = new HashMap<>();
		Random randomNumberGenerator = new Random();
		
		String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\ManagedData\\ManagedData.xlsx";

		try {

			FileInputStream file_input = new FileInputStream(excelFileLocation);

			try {
				
				Workbook work_book = WorkbookFactory.create(file_input);
				
				Sheet sheet = work_book.getSheetAt(0);
				
				for(Patient pt : list_of_patients) {
					
					if(pt.getNew_no()==0 || pt.getOld_no()!=0) {
						list_of_old_patients.add(pt);
					}
					else {
//						oldPatientMap.put(pt.getNew_no(), pt);
						list_of_new_patients.add(pt);
					}
					
				}
				System.out.println("1-");
				System.out.println();
				System.out.println("new pt list size "+list_of_new_patients.size()+"  old pt list size "+list_of_old_patients.size());
				int listSize = list_of_new_patients.size()+list_of_old_patients.size();
				System.out.println("final list size::: "+listSize);
				List<Patient>list_of_new_and_old_patients = new ArrayList<>((list_of_new_patients.size()+list_of_old_patients.size()));
				List<Integer> genRandomNumbersForOldPtList = new ArrayList<Integer>();
				List<Integer> genRandomNumbersForNewAndOldPtList = new ArrayList<Integer>();
				System.out.println("2-");
				
				int size = list_of_old_patients.size();
				
				while(size>0) {
					 
					int generatedNumberForOldList = randomNumberGenerator.nextInt(list_of_old_patients.size()); // upto 15
					System.out.println("random no. for old List ::: "+generatedNumberForOldList);
					
					if(genRandomNumbersForOldPtList.contains(generatedNumberForOldList)) {
						System.out.println("inside continue");
						continue;
					}
					else {
						System.out.println("inside else 1");
						genRandomNumbersForOldPtList.add(generatedNumberForOldList);
						Patient gotPt = list_of_old_patients.get(generatedNumberForOldList);
						System.out.println("got it patients : "+gotPt.getOld_no());
						
						int genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()))+1;
						
						if(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
							while(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
								genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()));
								System.out.println("finally generated no");
							}
						}
						
							mapOfOldAndNewPatients.put(genRandomForNewAndOldList, gotPt);
							genRandomNumbersForNewAndOldPtList.add(genRandomForNewAndOldList);

					}
					size--;
					System.out.println("size ::: "+size);
					if(size==(-1)) {
						System.out.println("old list got empty...");
						break;
					}
					
				} // while loop ends
				
				int newPtCount =0;
				System.out.println("new list size:: "+list_of_new_patients.size()+"    old list size : "+list_of_old_patients.size());
				int allSize = (list_of_new_patients.size()+list_of_old_patients.size());
				
				for(int i=0;i<allSize;i++) {
					System.out.println("I value :: "+i+"   newPtCount :: "+newPtCount);
					if(mapOfOldAndNewPatients.containsKey(i)) {
						System.out.println("old pt assigned at "+i+" pt Name :: "+mapOfOldAndNewPatients.get(i).getName());
						continue;
					}
					else {
						if(newPtCount>=list_of_new_patients.size()) {
							break;
						}
						mapOfOldAndNewPatients.put(i, list_of_new_patients.get(newPtCount));
						System.out.println("new pt assigned at "+i+" pt Name :: "+list_of_new_patients.get(newPtCount).getNew_no());
						newPtCount++;
					}
					
				}
				
				for(int i=0;i<((list_of_new_patients.size()+list_of_old_patients.size()));i++) {
					list_of_new_and_old_patients.add(mapOfOldAndNewPatients.get(i));
				}
				int j=0;
				for (Patient ptt : list_of_new_and_old_patients) {
					if(ptt != null) {
						System.out.println("patient at "+j+" position ::: "+ptt);
						j++;
					}
					
				}
				System.out.println("size of list_of_new_and_old_patients ::: "+list_of_new_and_old_patients);
				System.out.println("4-");
				
				for(int i=0;i<list_of_new_and_old_patients.size();i++)
				{
					
					Row dataRow = sheet.createRow(i);
					dataRow.createCell(0).setCellValue(list_of_new_and_old_patients.get(i).getNew_no());
					dataRow.createCell(1).setCellValue(list_of_new_and_old_patients.get(i).getOld_no());
					dataRow.createCell(2).setCellValue(list_of_new_and_old_patients.get(i).getName());
					dataRow.createCell(3).setCellValue(list_of_new_and_old_patients.get(i).getAddress());
					dataRow.createCell(4).setCellValue(list_of_new_and_old_patients.get(i).getAge());
					dataRow.createCell(5).setCellValue(list_of_new_and_old_patients.get(i).getSex());
					dataRow.createCell(6).setCellValue(list_of_new_and_old_patients.get(i).getDiagnosis());
					dataRow.createCell(7).setCellValue(list_of_new_and_old_patients.get(i).getDepartment());
					dataRow.createCell(8).setCellValue(list_of_new_and_old_patients.get(i).getDate());
					dataRow.createCell(9).setCellValue(list_of_new_and_old_patients.get(i).getDate());
					dataRow.createCell(10).setCellValue(list_of_new_and_old_patients.get(i).getWeight());
					dataRow.createCell(11).setCellValue(list_of_new_and_old_patients.get(i).getDate());
					
				}
				System.out.println("5-");
				file_input.close();
				FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
				work_book.write(file_output_stream);
				file_output_stream.close();
				System.out.println("Excel sheet Updated Successfully");

			} catch (EncryptedDocumentException | IOException e) {

				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

		return "Data Sheet uploaded Successfully ...!";
	}
	
	
	
	public String generateDailyDataBulk(){

		List<Patient> list_of_patients = new ArrayList<>();
		List<Patient> list_of_new_and_old_patients = new ArrayList<>();
		
		LocalDate backupDate = LocalDate.of(1900, 1, 1);
		boolean flag = false;
		
		String fileName = "ManageDailyData";
		
		
		//---------------------------------------- READ FILE -----------------------------------

		String file_path = "D:\\Ayurved_BAMS_Project\\Documentations\\patientFinalGeneratedData.xlsx";

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream(file_path);

			} catch (FileNotFoundException e) {

				System.out.println("File Not Found");
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				System.out.println("problem in workbook");
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				System.out.println("1");
				Row row = rowIt.next();

				System.out.println("2");
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit+"  Here");				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				
				
				try {
					int dateInt = (int)Double.parseDouble(arr[9]);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					p.setDate(admitDate);
				} catch (Exception e) {
					return "Patient Admit Date not found or Invalid";
				}
				if(flag == false) {
					flag = true;
					backupDate = p.getDate();
				}
				
				if(p.getDate().isEqual(backupDate)) {
					
				}else{
					try {
						
//						String isFileCreated = createFiles(fileName,p.getDate().getDayOfMonth()-1);
//						System.out.println("file created successfully with name :: "+fileName+""+(p.getDate().getDayOfMonth()-1));
						List<Patient> new_list_of_new_and_old_patients = addAllPatientsToNewListOfNewAndOldPatients(list_of_patients,fileName,p.getDate().getDayOfMonth()-1);
						System.out.println("file written successfullyyy ... ");
						
						for(Patient newListSinglePatient : new_list_of_new_and_old_patients) {
							list_of_new_and_old_patients.add(newListSinglePatient);
						}
						
						backupDate = p.getDate();
						list_of_patients.clear();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try {
					p.setNew_no((int)Double.parseDouble(arr[0]));
				} catch (Exception e) {
					return "Patient New Number not found or Invalid";
				}
				
				try {
					p.setOld_no((int)Double.parseDouble(arr[1]));
				} catch (Exception e) {
					return "Patient Old Number not found or Invalid";
				}
				
				try {
					p.setName(arr[2].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Name not found or Invalid";
				}
				
				try {
					p.setAddress(arr[3].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Address not found or Invalid";
				}
				
				
				try { 
					p.setAge((int)Double.parseDouble(arr[4]));
				} catch (Exception e) {
					return "Patient Age not found or Invalid";
				}
				
				try {
					
					char sex = (char)(int)Double.parseDouble(arr[5]);
					System.out.println("sex we get is :: "+sex);
					p.setSex(sex);
//					p.setSex(arr[5].charAt(0));
				} catch (Exception e) {
					return "Patient Gender not found or Invalid";
				}
				
				try {
					p.setDepartment(arr[6].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Department not found or Invalid";
				}
				
				try {
					p.setDiagnosis(arr[7].toLowerCase().trim());
				} catch (Exception e) {
					return "Patient Diagnosis not found or Invalid";
				}
				
				try {
					p.setWeight((int)Double.parseDouble(arr[8]));
				} catch (Exception e) {
					return "Patient Weight not found or Invalid";
				}
				
				
				try {
					int dateInt = (int)Double.parseDouble(arr[9]);
					LocalDate admitDate = LocalDate.of(1900, 1, 1).plusDays(dateInt - 2);
					System.out.println("localdate ::: "+admitDate);
					p.setDate(admitDate);
//					p.setDate(dateLogic.getDate(arr[9]));
				} catch (Exception e) {
					return "Patient Admit Date not found or Invalid";
				}
				
				
				System.out.println("size of array ::: "+arr.length);
				
				list_of_patients.add(p);
				
			}
			
			try {

				f_in.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		} 
		catch(NullPointerException e)
		{
			System.out.println("Cannot perform Action");
		}

		try {
			String isFileCreated = createFiles(fileName,0);
			writeFile(list_of_new_and_old_patients,fileName,0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("file created successfully with name :: "+fileName);
		

		return "Data Sheet uploaded Successfully ...!";
		
	}
	
	
	
	public void writeFile(List<Patient> list_of_new_and_old_patients,String fileName, int fileNumber) {
		
		//---------------------------------------------- WRITE FILE ----------------------------

				String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\patient"+fileName+""+fileNumber+".xlsx";

				try {

					FileInputStream file_input = new FileInputStream(excelFileLocation);

					try {
						
						Workbook work_book = WorkbookFactory.create(file_input);
						Sheet sheet = work_book.getSheetAt(0);
					
						for(int i=0;i<list_of_new_and_old_patients.size()-1;i++)
						{
							if(list_of_new_and_old_patients.get(i) != null) {
								Row dataRow = sheet.createRow(i);
								dataRow.createCell(0).setCellValue(i+1);
								dataRow.createCell(1).setCellValue(i+1);
								dataRow.createCell(2).setCellValue(list_of_new_and_old_patients.get(i).getNew_no());
								dataRow.createCell(3).setCellValue(list_of_new_and_old_patients.get(i).getOld_no());
								dataRow.createCell(4).setCellValue(list_of_new_and_old_patients.get(i).getName());
								dataRow.createCell(5).setCellValue(list_of_new_and_old_patients.get(i).getAddress());
								dataRow.createCell(6).setCellValue(list_of_new_and_old_patients.get(i).getAge());
								dataRow.createCell(7).setCellValue(list_of_new_and_old_patients.get(i).getSex());
								dataRow.createCell(8).setCellValue(list_of_new_and_old_patients.get(i).getDiagnosis());
								dataRow.createCell(9).setCellValue(list_of_new_and_old_patients.get(i).getDepartment());
								dataRow.createCell(10).setCellValue(list_of_new_and_old_patients.get(i).getDate());
								dataRow.createCell(11).setCellValue(list_of_new_and_old_patients.get(i).getDate());
								dataRow.createCell(12).setCellValue(list_of_new_and_old_patients.get(i).getWeight());
								dataRow.createCell(13).setCellValue(i+1);
							}
							
						}
						
						System.out.println("5-");
						file_input.close();
						FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
						work_book.write(file_output_stream);
						file_output_stream.close();
						System.out.println("Excel sheet Updated Successfully");

					} catch (EncryptedDocumentException | IOException e) {

						e.printStackTrace();
					}

				} catch (FileNotFoundException e) {

					e.printStackTrace();

				}
		
		
	}
	
	public List<Patient> addAllPatientsToNewListOfNewAndOldPatients(List<Patient> list_of_patients,String fileName, int fileNumber) {
		
		List<Patient> list_of_old_patients = new ArrayList<>();
		List<Patient> list_of_new_patients = new ArrayList<>();
		
		//---------------------------------------------- WRITE FILE ----------------------------

				Map<Integer, Patient> mapOfOldAndNewPatients = new HashMap<>();
				Random randomNumberGenerator = new Random();
				
				try {
					
					for(Patient pt : list_of_patients) {
						
						if(pt.getNew_no()==0 || pt.getOld_no()!=0) {
							list_of_old_patients.add(pt);
						}
						else {
							list_of_new_patients.add(pt);
						}
						
					}
					System.out.println("1-");
					System.out.println();
					System.out.println("new pt list size "+list_of_new_patients.size()+"  old pt list size "+list_of_old_patients.size());
					int listSize = list_of_new_patients.size()+list_of_old_patients.size();
					System.out.println("final list size::: "+listSize);
					List<Patient>list_of_new_and_old_patients = new ArrayList<>((list_of_new_patients.size()+list_of_old_patients.size()));
					List<Integer> genRandomNumbersForOldPtList = new ArrayList<Integer>();
					List<Integer> genRandomNumbersForNewAndOldPtList = new ArrayList<Integer>();
					System.out.println("2-");
					
					int size = list_of_old_patients.size();
					
					while(size>0) {
						 
						int generatedNumberForOldList = randomNumberGenerator.nextInt(list_of_old_patients.size()); // upto 15
						System.out.println("random no. for old List ::: "+generatedNumberForOldList);
						
						if(genRandomNumbersForOldPtList.contains(generatedNumberForOldList)) {
							System.out.println("inside continue");
							continue;
						}
						else {
							System.out.println("inside else 1");
							genRandomNumbersForOldPtList.add(generatedNumberForOldList);
							Patient gotPt = list_of_old_patients.get(generatedNumberForOldList);
							System.out.println("got it patients : "+gotPt.getOld_no());
							
							int genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()))+1;
							
							if(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
								while(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
									genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()));
									System.out.println("finally generated no");
								}
							}
							
								mapOfOldAndNewPatients.put(genRandomForNewAndOldList, gotPt);
								genRandomNumbersForNewAndOldPtList.add(genRandomForNewAndOldList);

						}
						size--;
						System.out.println("size ::: "+size);
						if(size==(-1)) {
							System.out.println("old list got empty...");
							break;
						}
						
					} // while loop ends
					
					int newPtCount =0;
					System.out.println("new list size:: "+list_of_new_patients.size()+"    old list size : "+list_of_old_patients.size());
					int allSize = (list_of_new_patients.size()+list_of_old_patients.size());
					
					for(int i=0;i<allSize;i++) {
						System.out.println("I value :: "+i+"   newPtCount :: "+newPtCount);
						if(mapOfOldAndNewPatients.containsKey(i)) {
							System.out.println("old pt assigned at "+i+" pt Name :: "+mapOfOldAndNewPatients.get(i).getName());
							continue;
						}
						else {
							if(newPtCount>=list_of_new_patients.size()) {
								break;
							}
							mapOfOldAndNewPatients.put(i, list_of_new_patients.get(newPtCount));
							System.out.println("new pt assigned at "+i+" pt Name :: "+list_of_new_patients.get(newPtCount).getNew_no());
							newPtCount++;
						}
						
					}
					
					for(int i=0;i<((list_of_new_patients.size()+list_of_old_patients.size()));i++) {
						list_of_new_and_old_patients.add(mapOfOldAndNewPatients.get(i));
					}
					int j=0;
					for (Patient ptt : list_of_new_and_old_patients) {
						if(ptt != null) {
							System.out.println("patient at "+j+" position ::: "+ptt);
							j++;
						}
						
					}
					System.out.println("size of list_of_new_and_old_patients ::: "+list_of_new_and_old_patients);
					System.out.println("4-");
					

					System.out.println("5-");
					System.out.println("Excel sheet Updated Successfully");
					
					return list_of_new_and_old_patients;

				} catch (EncryptedDocumentException e) {
					e.printStackTrace();
				}
				
				return null;
				
		
	}
	
	// ----------------------------------- CREATE EXCEL FILES ---------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------
	
	
	
	
	
	public String createFiles(String fileName, int fileNumber) throws Exception {
        // Create the directory if it doesn't exist
        createDirectoryIfNotExists("D:/ayurved1");

        // Generate Excel file
        
            createExcelFile("D:\\Ayurved_BAMS_Project\\Documentations\\patient"+fileName+""+fileNumber+ ".xlsx");
        return "file created successfullyyyyyyyyyyyyy ...";
    }

    private void createExcelFile(String filePath) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        // Add some sample data to the Excel sheet
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Name");
        headerRow.createCell(1).setCellValue("Age");
        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue("John Doe");
        dataRow.createCell(1).setCellValue(30);

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file created: " + filePath);
        } catch (IOException e) {
            System.err.println("Error creating Excel file: " + e.getMessage());
        }
    }

    private void createDirectoryIfNotExists(String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
    }
	
	
	
	
	
	
	
	
//	public void writeFile(List<Patient> list_of_patients,String fileName, int fileNumber) {
//	
//	List<Patient> list_of_old_patients = new ArrayList<>();
//	List<Patient> list_of_new_patients = new ArrayList<>();
//	
//	//---------------------------------------------- WRITE FILE ----------------------------
//
//			Map<Integer, Patient> mapOfOldAndNewPatients = new HashMap<>();
//			Random randomNumberGenerator = new Random();
//			
//			String excelFileLocation = "D:\\Ayurved_BAMS_Project\\Documentations\\ManagedData\\"+fileName+""+fileNumber+".xlsx";
//
//			try {
//
//				FileInputStream file_input = new FileInputStream(excelFileLocation);
//
//				try {
//					
//					Workbook work_book = WorkbookFactory.create(file_input);
//					
//					Sheet sheet = work_book.getSheetAt(0);
//					
//					for(Patient pt : list_of_patients) {
//						
//						if(pt.getNew_no()==0 || pt.getOld_no()!=0) {
//							list_of_old_patients.add(pt);
//						}
//						else {
////							oldPatientMap.put(pt.getNew_no(), pt);
//							list_of_new_patients.add(pt);
//						}
//						
//					}
//					System.out.println("1-");
//					System.out.println();
//					System.out.println("new pt list size "+list_of_new_patients.size()+"  old pt list size "+list_of_old_patients.size());
//					int listSize = list_of_new_patients.size()+list_of_old_patients.size();
//					System.out.println("final list size::: "+listSize);
//					List<Patient>list_of_new_and_old_patients = new ArrayList<>((list_of_new_patients.size()+list_of_old_patients.size()));
//					List<Integer> genRandomNumbersForOldPtList = new ArrayList<Integer>();
//					List<Integer> genRandomNumbersForNewAndOldPtList = new ArrayList<Integer>();
//					System.out.println("2-");
//					
//					int size = list_of_old_patients.size();
//					
//					while(size>0) {
//						 
//						int generatedNumberForOldList = randomNumberGenerator.nextInt(list_of_old_patients.size()); // upto 15
//						System.out.println("random no. for old List ::: "+generatedNumberForOldList);
//						
//						if(genRandomNumbersForOldPtList.contains(generatedNumberForOldList)) {
//							System.out.println("inside continue");
//							continue;
//						}
//						else {
//							System.out.println("inside else 1");
//							genRandomNumbersForOldPtList.add(generatedNumberForOldList);
//							Patient gotPt = list_of_old_patients.get(generatedNumberForOldList);
//							System.out.println("got it patients : "+gotPt.getOld_no());
//							
//							int genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()))+1;
//							
//							if(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
//								while(genRandomNumbersForNewAndOldPtList.contains(genRandomForNewAndOldList)) {
//									genRandomForNewAndOldList = randomNumberGenerator.nextInt((list_of_new_patients.size()+list_of_old_patients.size()));
//									System.out.println("finally generated no");
//								}
//							}
//							
//								mapOfOldAndNewPatients.put(genRandomForNewAndOldList, gotPt);
//								genRandomNumbersForNewAndOldPtList.add(genRandomForNewAndOldList);
//
//						}
//						size--;
//						System.out.println("size ::: "+size);
//						if(size==(-1)) {
//							System.out.println("old list got empty...");
//							break;
//						}
//						
//					} // while loop ends
//					
//					int newPtCount =0;
//					System.out.println("new list size:: "+list_of_new_patients.size()+"    old list size : "+list_of_old_patients.size());
//					int allSize = (list_of_new_patients.size()+list_of_old_patients.size());
//					
//					for(int i=0;i<allSize;i++) {
//						System.out.println("I value :: "+i+"   newPtCount :: "+newPtCount);
//						if(mapOfOldAndNewPatients.containsKey(i)) {
//							System.out.println("old pt assigned at "+i+" pt Name :: "+mapOfOldAndNewPatients.get(i).getName());
//							continue;
//						}
//						else {
//							if(newPtCount>=list_of_new_patients.size()) {
//								break;
//							}
//							mapOfOldAndNewPatients.put(i, list_of_new_patients.get(newPtCount));
//							System.out.println("new pt assigned at "+i+" pt Name :: "+list_of_new_patients.get(newPtCount).getNew_no());
//							newPtCount++;
//						}
//						
//					}
//					
//					for(int i=0;i<((list_of_new_patients.size()+list_of_old_patients.size()));i++) {
//						list_of_new_and_old_patients.add(mapOfOldAndNewPatients.get(i));
//					}
//					int j=0;
//					for (Patient ptt : list_of_new_and_old_patients) {
//						if(ptt != null) {
//							System.out.println("patient at "+j+" position ::: "+ptt);
//							j++;
//						}
//						
//					}
//					System.out.println("size of list_of_new_and_old_patients ::: "+list_of_new_and_old_patients);
//					System.out.println("4-");
//					
//					for(int i=0;i<list_of_new_and_old_patients.size();i++)
//					{
//						
//						Row dataRow = sheet.createRow(i);
//						dataRow.createCell(0).setCellValue(list_of_new_and_old_patients.get(i).getNew_no());
//						dataRow.createCell(1).setCellValue(list_of_new_and_old_patients.get(i).getOld_no());
//						dataRow.createCell(2).setCellValue(list_of_new_and_old_patients.get(i).getName());
//						dataRow.createCell(3).setCellValue(list_of_new_and_old_patients.get(i).getAddress());
//						dataRow.createCell(4).setCellValue(list_of_new_and_old_patients.get(i).getAge());
//						dataRow.createCell(5).setCellValue(list_of_new_and_old_patients.get(i).getSex());
//						dataRow.createCell(6).setCellValue(list_of_new_and_old_patients.get(i).getDiagnosis());
//						dataRow.createCell(7).setCellValue(list_of_new_and_old_patients.get(i).getDepartment());
//						dataRow.createCell(8).setCellValue(list_of_new_and_old_patients.get(i).getDate());
//						dataRow.createCell(9).setCellValue(list_of_new_and_old_patients.get(i).getDate());
//						dataRow.createCell(10).setCellValue(list_of_new_and_old_patients.get(i).getWeight());
//						dataRow.createCell(11).setCellValue(list_of_new_and_old_patients.get(i).getDate());
//						
//					}
//					System.out.println("5-");
//					file_input.close();
//					FileOutputStream file_output_stream = new FileOutputStream(excelFileLocation);
//					work_book.write(file_output_stream);
//					file_output_stream.close();
//					System.out.println("Excel sheet Updated Successfully");
//
//				} catch (EncryptedDocumentException | IOException e) {
//
//					e.printStackTrace();
//				}
//
//			} catch (FileNotFoundException e) {
//
//				e.printStackTrace();
//
//			}
//	
//	
//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
