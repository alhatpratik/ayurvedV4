package project.EntityDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.OPD_Disease_Medicine_CasePaper;
import project.Entity.OPD_Disease_Symptoms_patient_improvement;
import project.Entity.Patient;
import project.model.OPD_case_paper_details;
import project.repositories.OPD_DiseaseMedicineCasePaperRepo;
import project.repositories.OPD_Disease_SymptomsImproveRepo;

@Component
public class OPD_CasePaperLogic {

	@Autowired
	OPD_Disease_SymptomsImproveRepo opd_dis_symptom_Improve_Repos;

	@Autowired
	OPD_DiseaseMedicineCasePaperRepo opd_dis_medicine_casePaper_Repos;

	@Autowired
	OPD_Disease_Symptoms_patient_improvement opd_symptoms;

	@Autowired
	OPD_Disease_Medicine_CasePaper opd_medicine;

	@Autowired
	OPD_case_paper_details opd_case_paper_details;

	public OPD_case_paper_details getOPD_casePaperMedicineSymptomDetails(Patient p)
	{
		String vyadhiName = p.getDiagnosis();
//		System.out.println("vyadhi name "+vyadhiName);
		String department = p.getDepartment();
//		System.out.println("department :"+department);

		List<OPD_Disease_Symptoms_patient_improvement> opd_dis_symptoms_list = opd_dis_symptom_Improve_Repos.getOpdCasePaperSymptomsDetails(vyadhiName);

		for(OPD_Disease_Symptoms_patient_improvement opd_cp : opd_dis_symptoms_list)
		{
			System.out.println(opd_cp.getC_o()+"   "+opd_cp.getDepartment());
			if(opd_cp.getDepartment().equalsIgnoreCase(department))
			{
				System.out.println("inside if block");
				opd_symptoms = opd_cp;
				opd_symptoms.setAhaar(opd_cp.getAhaar());
				opd_symptoms.setBlood_pressure(opd_cp.getBlood_pressure());
				opd_symptoms.setC_o(opd_cp.getC_o());
				opd_symptoms.setCvs(opd_cp.getCvs());
				opd_symptoms.setDepartment(opd_cp.getDepartment());
				opd_symptoms.setH_o(opd_cp.getH_o());
				opd_symptoms.setJiva(opd_cp.getJiva());
				opd_symptoms.setKshudha(opd_cp.getKshudha());
				opd_symptoms.setMal(opd_cp.getMal());
				opd_symptoms.setMutra(opd_cp.getMutra());
				opd_symptoms.setNadi(opd_cp.getNadi());
				opd_symptoms.setNetra(opd_cp.getNetra());
				opd_symptoms.setNidra(opd_cp.getNidra());
				opd_symptoms.setPulse(opd_cp.getPulse());
				opd_symptoms.setSr_no(opd_cp.getSr_no());
				opd_symptoms.setSymptoms(opd_cp.getSymptoms());
				opd_symptoms.setUdar(opd_cp.getUdar());
				opd_symptoms.setUr(opd_cp.getUr());
				opd_symptoms.setVyadhiName(opd_cp.getVyadhiName());
			}

		}


//		System.out.println("OPD Sympoms fetched successful");

//		System.out.println("---------------------------------------------------------------------");
//				System.out.println(opd_symptoms.getC_o());
//				System.out.println(opd_symptoms.getH_o());
//				System.out.println(opd_symptoms.getKshudha());
//				System.out.println(opd_symptoms.getVyadhiName());
//				System.out.println(opd_symptoms.getMutra());
//				System.out.println(opd_symptoms.getMutra());


		List<OPD_Disease_Medicine_CasePaper> opd_medicine_case_paper_list  = opd_dis_medicine_casePaper_Repos.getOpdCasePaperMedicineDetails(vyadhiName);

		for(OPD_Disease_Medicine_CasePaper opd_cp_medicine : opd_medicine_case_paper_list)
		{
//			System.out.println(opd_cp_medicine.getDepartment()+"   "+opd_cp_medicine.getRx1()+"   "+opd_cp_medicine.getVyadhiName()+"   "+opd_cp_medicine.getDepartment());

			if(opd_cp_medicine.getDepartment().equalsIgnoreCase(department))
			{
				opd_medicine = opd_cp_medicine;
			}

		}

//		System.out.println("OPD medicine fetched successful");

//		System.out.println("---------------------------------------------------------------------");
//				System.out.println(opd_medicine.getRx1());
//				System.out.println(opd_medicine.getVyadhiName());
//				System.out.println(opd_medicine.getBasti());
//				System.out.println(opd_medicine.getHematological());
//				System.out.println(opd_medicine.getSerological());
//				System.out.println(opd_medicine.getMicrobiological());

				opd_case_paper_details.setOpd_dis_med_paper(opd_medicine);
				opd_case_paper_details.setOpd_sym_pat_impro(opd_symptoms);
				opd_case_paper_details.setP(p);

//				System.out.println(opd_case_paper_details.getOpd_dis_med_paper().getVyadhiName()+"  vyadhi name after");
				
				
//		System.out.println("OPD case Paper created successful");

		return opd_case_paper_details;
	}
}
