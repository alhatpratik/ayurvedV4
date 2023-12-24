package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Central_IPD;
import project.repositories.Central_IPDRepo;

@Component
public class CentralAdmitDischargeLogic {

	@Autowired
	Central_IPDRepo central_admit_discharge_repo;
	
	public List<Central_IPD> getCentralAdmitDischarge_IPD_Details_List(LocalDate d) 
	{
		return central_admit_discharge_repo.get_Central_Admit_Discharge_IPD_Patients(d);
	}
	
}
