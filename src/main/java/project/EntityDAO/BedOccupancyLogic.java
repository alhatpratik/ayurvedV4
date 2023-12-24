package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.BedOccupancy;
import project.Entity.Central_Admit;
import project.repositories.BedOccupancyRepo;

@Component
public class BedOccupancyLogic {

	@Autowired
	BedOccupancyRepo bedOccupancy_repo;
	
	public List<BedOccupancy> getBedOccupancy_IPD_Details_List(LocalDate d) 
	{
		return bedOccupancy_repo.getBedOccupancy_IPD_Patients(d);
	}
	
}
