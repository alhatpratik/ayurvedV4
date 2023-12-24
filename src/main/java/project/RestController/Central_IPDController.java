package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.BedOccupancy;
import project.Entity.Central_IPD;
import project.Entity.Patient;
import project.EntityDAO.BedOccupancyLogic;
import project.EntityDAO.CentralAdmitDischargeLogic;

@RestController
@CrossOrigin("*")
@RequestMapping("/ayurved")
public class Central_IPDController {

	@Autowired
	CentralAdmitDischargeLogic central_admit_discharge_logic;
	
	@PostMapping("/central/ipd")
	public List<Central_IPD> getBedOccupancyPatients(@RequestBody Patient p)
	{
		LocalDate locald = p.getDate();

		System.out.println("Inside Central Admit Discharge");

		List<Central_IPD> list = central_admit_discharge_logic.getCentralAdmitDischarge_IPD_Details_List(locald);

		return list;
	}

}
