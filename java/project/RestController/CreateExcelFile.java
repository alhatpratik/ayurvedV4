package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.Entity.OPDKayachikitsa;
import project.Entity.Patient;
import project.EntityDAO.CreateExcelFileLogic;

@RestController
@CrossOrigin("*")
@RequestMapping("/ayurved")
public class CreateExcelFile {

	@Autowired
	CreateExcelFileLogic createExcelFileLogic_object;

	@PostMapping("/create/excelfile")
	@CrossOrigin("*")
	public void createExcelFileForDisease(@RequestBody Patient p)
	{
		String departmentName = p.getName();
		LocalDate local_date = p.getDate();
		
		System.out.println(departmentName+"  "+local_date);
		
		createExcelFileLogic_object.getExcelFile(departmentName,local_date);
		createExcelFileLogic_object.getFinalExcelFile();
		createExcelFileLogic_object.generateFinalData();
		createExcelFileLogic_object.generateDailyDataBulk();
	}
	
	
	@PostMapping("/create/ipd/excelfile")
	@CrossOrigin("*")
	public void createIPDExcelFileForDisease(@RequestBody Patient p)
	{
		String departmentName = p.getName();
		LocalDate local_date = p.getDate();
		
		System.out.println(departmentName+"  "+local_date);
		
		createExcelFileLogic_object.getIPDExcelFile(departmentName,local_date);

	}
	
	@PostMapping("/create/final/excelfile")
	@CrossOrigin("*")
	public void getFinalExcelFile() {
		createExcelFileLogic_object.getFinalExcelFile();
		
	}
	
	@PostMapping("/generate/final/excelfile")
	@CrossOrigin("*")
	public void generateFinalExcelFile() {
		createExcelFileLogic_object.generateFinalData();
	}
	
	@PostMapping("/generate/daily/excelfile")
	@CrossOrigin("*")
	public void generateDailyData() {
		createExcelFileLogic_object.generateDailyData();
	}
	
	
	@PostMapping("/create/multiple/excelfile")
	@CrossOrigin("*")
	public void createFiles() {
		try {
			createExcelFileLogic_object.generateDailyDataBulk();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

