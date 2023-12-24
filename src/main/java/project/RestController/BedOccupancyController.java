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
import project.Entity.Central_Admit;
import project.Entity.Patient;
import project.EntityDAO.BedOccupancyLogic;
import project.repositories.BedOccupancyRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/ayurved")
public class BedOccupancyController {

	@Autowired
	BedOccupancyLogic bedOccupancy_logic;
	
	@PostMapping("/bed/occupied")
	public List<BedOccupancy> getBedOccupancyPatients(@RequestBody Patient p)
	{
		LocalDate locald = p.getDate();

		System.out.println("Inside Central IPD");

		List<BedOccupancy> list = bedOccupancy_logic.getBedOccupancy_IPD_Details_List(locald);

		return list;
	}

}
