package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.IPDPanchakarmaProcedureRegister;
import project.Entity.OPDPanchakarmaProcedureRegister;
import project.Entity.Patient;
import project.EntityDAO.IPD_Panchakarma_Procedure_Logic;
import project.EntityDAO.OPD_Panchakarma_Procedure_Logic;
import project.model.OPD_case_paper_details;

@RestController
@RequestMapping("/ayurved")
@CrossOrigin("*")
public class PanchakarmaProcedureController {

	@Autowired
	OPD_Panchakarma_Procedure_Logic opd_panch_procedure_logic;
	
	@Autowired
	IPD_Panchakarma_Procedure_Logic ipd_panch_procedure_logic;
	
	@PostMapping("/opd/procedures")
	@CrossOrigin("*")
	public List<OPDPanchakarmaProcedureRegister> get_OPD_Karm_Details(@RequestBody Patient p)
	{
		System.out.println(" Opd karm 1 Success");
		
		LocalDate loc_date = p.getDate();
		
		System.out.println(loc_date);
		 
		List<OPDPanchakarmaProcedureRegister> procedure_list = opd_panch_procedure_logic.get_OPD_Karm_Details(loc_date);
				
		for(OPDPanchakarmaProcedureRegister op :procedure_list)
		{
			System.out.println(op.getName()+"  "+op.getDepartment()+" "+" "+op.getDiagnosis()+" "+op.getDate());
		}
		
		return procedure_list;
	}

	
	@PostMapping("/ipd/procedures")
	@CrossOrigin("*")
	public List<IPDPanchakarmaProcedureRegister> get_IPD_Karm_Details(@RequestBody Patient p)
	{
		System.out.println(" Opd karm 1 Success");
		
		LocalDate loc_date = p.getDate();
		
		System.out.println(loc_date);
		 
		List<IPDPanchakarmaProcedureRegister> procedure_list = ipd_panch_procedure_logic.get_IPD_Karm_Details(loc_date);
				
		for(IPDPanchakarmaProcedureRegister op :procedure_list)
		{
			System.out.println(op.getName()+"  "+op.getDepartment()+" "+" "+op.getDiagnosis()+" "+op.getDate());
		}
		
		return procedure_list;
	}
	
	
	
}
