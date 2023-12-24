package project.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Patient;
import project.EntityDAO.COPDLogic;
import project.EntityDAO.OPD_CasePaperLogic;
import project.EntityDAO.RecordLogic;
import project.model.OPD_case_paper_details;

@RestController
@RequestMapping("/ayurved")
@CrossOrigin("*")
public class OPD_CasePaperController {

	@Autowired
	OPD_CasePaperLogic opd_case_paper_logic;

	@Autowired
	COPDLogic copd_logic;
	
	
	@PostMapping("/opd/casepaper")
	@CrossOrigin("*")
	public OPD_case_paper_details getOPD_Case_Paper_Details(@RequestBody Patient e)
	{
		System.out.println(" Opd paper 1 Success");
		System.out.println(e.getDiagnosis()+"  "+e.getName()+"  "+e.getNew_no()+"  "+e.getOld_no());
		OPD_case_paper_details opd_case_paper = opd_case_paper_logic.getOPD_casePaperMedicineSymptomDetails(e);

		return opd_case_paper;
	}


	@PostMapping("/opd/daily/casepaper")
	@CrossOrigin("*")
	public List<OPD_case_paper_details> getAllOPDcasePapersForAday(@RequestBody Patient p)
	{
		List<OPD_case_paper_details> opd_cp_details_list = new ArrayList<>();
		
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

		
		int count = 0;

		for(Patient pt : list)
		{
//			OPD_case_paper_details opd_case_paper = getOPD_Case_Paper_Details(pt);
			OPD_case_paper_details opd_case_paper = opd_case_paper_logic.getOPD_casePaperMedicineSymptomDetails(pt);
			OPD_case_paper_details opdcp = new OPD_case_paper_details();
			
			opdcp.setP(opd_case_paper.getP());
			opdcp.setOpd_dis_med_paper(opd_case_paper.getOpd_dis_med_paper());
			opdcp.setOpd_sym_pat_impro(opd_case_paper.getOpd_sym_pat_impro());
			
			opd_cp_details_list.add(opdcp);
			System.out.println(opdcp.getP().getName()+"  -------------------- ");
		}
		
		
		for(OPD_case_paper_details opd_cp : opd_cp_details_list)
		{
			count++;
			System.out.println("===========================================================");
			System.out.println(opd_cp.getP().getSr_no()+" "+opd_cp.getP().getName()+" from Daily OPD Sheet");
		}
		
		System.out.println(opd_cp_details_list.size());
		return opd_cp_details_list;
	}

}
