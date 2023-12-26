package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Central_Admit;
import project.Entity.OPDKayachikitsa;
import project.Entity.Patient;
import project.EntityDAO.CentralAdmitLogic;
import project.model.ListCIPD;


@RestController
@RequestMapping("/ayurved")
public class CentralAdmitController {
	
	@Autowired
	CentralAdmitLogic cipdLogic;

	@PostMapping("/central/admit")
	public Object getIPDPatients(@RequestBody Patient p) 
	{

		LocalDate locald = p.getDate();
		
		System.out.println("Inside Central IPD");
		
		List<Central_Admit> list = cipdLogic.getCentral_IPD_Details_List(locald);
		
		return list;
	}
	
}
