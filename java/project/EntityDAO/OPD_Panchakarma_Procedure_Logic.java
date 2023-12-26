package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.Entity.OPDPanchakarmaProcedureRegister;
import project.repositories.OPDPanchakarmaProcedureRepo;

@Component
public class OPD_Panchakarma_Procedure_Logic {

	@Autowired
	OPDPanchakarmaProcedureRepo opd_panch_procedure_Repo;
	
	public List<OPDPanchakarmaProcedureRegister> get_OPD_Karm_Details(LocalDate local_date)
	{
		List<OPDPanchakarmaProcedureRegister> procedure_list = opd_panch_procedure_Repo.get_OPD_Panchakarma_Procedure_Details(local_date);
		
		return procedure_list;
	}
	
}
