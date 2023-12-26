package project.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Patient;
import project.EntityDAO.COPDLogic;
import project.EntityDAO.DateLogic;

@RestController
@RequestMapping("/ayurved")
//@CrossOrigin("*")
public class COPDController {
	
	@Autowired
	COPDLogic copd_logic;
	
	@Autowired
	DateLogic dl;
	
	@PostMapping("/copd")
	@CrossOrigin("*")
	public List<Patient> details(@RequestBody Patient p)
	{
		System.out.println(""+p.getDate());
		
		LocalDate locald = p.getDate();
		System.out.println(1);
		List<Patient> list = copd_logic.fetchCOPD(locald);
		
		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
	}
	
	
	@PostMapping("/copd/last_record")
	@CrossOrigin("*")
	public Patient getLastRecord()
	{
		Patient p = copd_logic.getLastRecord();
		return p;
	}
	
}
