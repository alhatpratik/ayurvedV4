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
import project.EntityDAO.DateLogic;
import project.EntityDAO.SummaryLogic;
import project.model.Summary;

@RestController
@CrossOrigin("*")
@RequestMapping("/ayurved")
public class SummaryController {
	
	@Autowired
	SummaryLogic summary_logic;

	@Autowired
	DateLogic datelogic;
	
//	@PostMapping("/dopd/genderwise/monthly/summary")
//	@CrossOrigin("*")
//	public List<Summary> get_dopd_genderwise_Monthly_Summary()
//	{
//		System.out.println("DOPD Summary");
//
//		List<Summary> summary_list = summary_logic.getDOPD_Genderwise_Monthly_SummaryRecord();
//
//		return summary_list;
//	}
	
	@PostMapping("/dopd/genderwise/monthly/summary")
	@CrossOrigin("*")
	public List<Summary> get_dopd_genderwise_Monthly_Summary(@RequestBody Patient p)
	{
		System.out.println("DOPD Summary");
		
		String date = p.getName();
		
		int year_value  = Integer.parseInt(date);
		
		List<Summary> summary_list = summary_logic.getDOPD_Genderwise_Monthly_SummaryRecord(year_value);

		return summary_list;
	}
	
	
	@PostMapping("/admit/genderwise/monthly/summary")
	@CrossOrigin("*")
	public List<Summary> get_admit_Monthly_Summary(@RequestBody Patient p)
	{
		String date = p.getName();
		
		int year_value  = Integer.parseInt(date);
		
		List<Summary> admit_genderwise_summary_list = summary_logic.get_admit_Genderwise_Monthly_SummaryRecord(year_value);

		return admit_genderwise_summary_list;
	}
	
//	@PostMapping("/opd/procedure/yearly/summary")
//	@CrossOrigin("*")
//	public List<Summary> get_p_Summary()
//	{
//		
//		List<Summary> admit_genderwise_summary_list = summary_logic.get_admit_Genderwise_Monthly_SummaryRecord();
//
//		return admit_genderwise_summary_list;
//	}
	

	
	
}
