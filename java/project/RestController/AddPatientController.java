package project.RestController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Patient;
import project.EntityDAO.RecordLogic;
import project.repositories.LoginInteface;

@RestController
@RequestMapping("/ayurved")
public class AddPatientController {


	@Autowired
	LoginInteface li;
	
	@Autowired
	RecordLogic rl;
	
	@PostMapping("/add/record")
	@CrossOrigin("*")
	public String addRecords()
	{
		System.out.println("upto this");
		return rl.addRecords(); 
	}
	
	
	

}
