package project.EntityDAO;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Central_Admit;
import project.Entity.OPDPanchakarma;
import project.Entity.Patient;
import project.model.Summary;
import project.repositories.Central_OPDRepo;
import project.repositories.OPDAtyayikaRepo;
import project.repositories.OPDBalrogaRepo;
import project.repositories.OPDKayachikitsaRepo;
import project.repositories.OPDPanchakarmaRepo;
import project.repositories.OPDShalakyatantraRepo;
import project.repositories.OPDShalyatantraRepo;
import project.repositories.OPDStrirogaRepo;
import project.repositories.OPDSwastharakshanamRepo;

@Component
public class COPDLogic {

	@Autowired
	Central_OPDRepo opdi;

	@Autowired
	Constant constant;
	
	@Autowired
	RecordLogic recordLogic;
	
	@Autowired
	CentralAdmitLogic central_admit_logic;
	
	@Autowired
	Central_OPDRepo centralOpdRepo;
	
	@Autowired
	OPDKayachikitsaRepo opdKayaRepo;
	@Autowired
	OPDPanchakarmaRepo opdPanchRepo;
	@Autowired
	OPDShalakyatantraRepo opdShalakyaRepo;
	@Autowired
	OPDShalyatantraRepo opdShalyaRepo;
	@Autowired
	OPDStrirogaRepo opdStrirogRepo;
	@Autowired
	OPDBalrogaRepo opdBalrogRepo;
	@Autowired
	OPDAtyayikaRepo opdAtyayikaRepo;
	@Autowired
	OPDSwastharakshanamRepo opdSwasthaRepo;
	
	public List<Patient> fetchCOPD(LocalDate d)
	{
		System.out.println("11");

		List<Patient> list = opdi.getOPDPatients(d);
		
		
		for(Patient pt : list)
		{
			System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
		}
		
		return list;
	}

	public Patient getLastRecord()
	{
		Patient pt = opdi.getLastRecord();
		if(pt !=null)
		{
			// here we want last admitted records Yearly no. to show on dashboard count of total admitted patient
			// So as like another showing parameters we have fetched lastAdmitted record and changed value of last COPD Record's old no value
			
			Central_Admit last_Admitted_Record = central_admit_logic.getLastAdmittedAdmitRecord();
			double last_Admitted_Record_yr_no = last_Admitted_Record.getYearly_no();
			pt.setOld_no(last_Admitted_Record_yr_no);
			System.out.println(pt.getName()+"  "+pt.getDate());
		}
		
		return pt;
	}
	
	public String updateRecord(Patient p)
	{
		
		double record_no = p.getRecord_no();
		double new_no= p.getNew_no();
		double old_no = p.getOld_no();
		String name =p.getName();
		String address = p.getAddress();
		int age = p.getAge();
		char sex = p.getSex();
		String department = p.getDepartment();
		String diagnosis = p.getDiagnosis();
		double weight = p.getWeight();
		LocalDate date = p.getDate();
		
		
		try {
			opdi.updateRecord(weight, new_no,old_no,name, address, age, sex, department, diagnosis, date, record_no);
			return "Record Successfully updated";
		}catch(Exception e) {
			return "Error Occured";
		}
		
	}
	
	
	public String addSinglePatientRecord(Patient p) {
		
		p.setDischarge_date(p.getDate());
		p.setRecord_no(opdi.getLastRecord().getRecord_no()+1);
		p.setSr_no(opdi.getLastRecord().getSr_no()+1);
		
		if(p.getOld_no()==0) {
			p.setNew_no(opdi.getLastRecord().getNew_no());
		}
		
		String isIpdResponse = constant.checkPatientForOPDAndIPD(p);
		if(isIpdResponse.contains("Yes")) {
			System.out.println("IPD disease found");
			return "IPD Disease found ";
		}else{
			System.out.println("No IPD disease found");
			double copdYrNo=recordLogic.increaseCopdYrNoCount();
			p.setYearly_no(copdYrNo);
			centralOpdRepo.save(p);								// adding patient to central OPD
			
			if(p.getDepartment().equalsIgnoreCase(constant.kayachikitsa)) {
				p.setYearly_no(opdKayaRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdKayaRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.panchakarma)) {
				p.setYearly_no(opdPanchRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdPanchRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.shalakya)) {
				p.setYearly_no(opdShalakyaRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdShalakyaRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.shalya)) {
				p.setYearly_no(opdShalyaRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdShalyaRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.strirog)) {
				p.setYearly_no(opdStrirogRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdStrirogRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.balrog)) {
				p.setYearly_no(opdBalrogRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdBalrogRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.atyayika)) {
				p.setYearly_no(opdAtyayikaRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdAtyayikaRepo.getLastRecord().getRecord_no());
			}else if(p.getDepartment().equalsIgnoreCase(constant.swasthavrutta)) {
				p.setYearly_no(opdSwasthaRepo.getLastRecord().getYearly_no());
				p.setRecord_no(opdSwasthaRepo.getLastRecord().getRecord_no());
			}
			String response = recordLogic.addRecordToDOPD(p);	// adding patient to DOPD
			System.out.println("==="+response);
			return response;
		}

	}
}
