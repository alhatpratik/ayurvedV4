package project.EntityDAO;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Patient;
import project.model.Summary;
import project.repositories.Central_OPDRepo;

@Component
public class COPDLogic {

	@Autowired
	Central_OPDRepo opdi;

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
	
}
