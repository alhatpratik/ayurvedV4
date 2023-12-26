package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.Entity.IPDPanchakarmaProcedureRegister;
import project.repositories.IPDPanchakarmaProcedureRepo;

@Component
public class IPD_Panchakarma_Procedure_Logic {

	@Autowired
	IPDPanchakarmaProcedureRepo ipd_panch_procedure_Repo;
	
	public List<IPDPanchakarmaProcedureRegister> get_IPD_Karm_Details(LocalDate local_date)
	{
		List<IPDPanchakarmaProcedureRegister> procedure_list = ipd_panch_procedure_Repo.get_IPD_Panchakarma_Procedure_Details(local_date);
		
		return procedure_list;
	}
	
	
}
