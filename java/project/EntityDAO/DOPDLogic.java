package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.OPDAtyayika;
import project.Entity.OPDBalroga;
import project.Entity.OPDKayachikitsa;
import project.Entity.OPDPanchakarma;
import project.Entity.OPDShalakyatantra;
import project.Entity.OPDShalyatantra;
import project.Entity.OPDStriroga;
import project.Entity.OPDSwastharakshanam;
import project.repositories.OPDAtyayikaRepo;
import project.repositories.OPDBalrogaRepo;
import project.repositories.OPDKayachikitsaRepo;
import project.repositories.OPDPanchakarmaRepo;
import project.repositories.OPDShalakyatantraRepo;
import project.repositories.OPDShalyatantraRepo;
import project.repositories.OPDStrirogaRepo;
import project.repositories.OPDSwastharakshanamRepo;

@Component
public class DOPDLogic {
	
	@Autowired
	OPDKayachikitsaRepo opd_kaya_repos;
	@Autowired
	OPDPanchakarmaRepo opd_panch_repos;
	@Autowired
	OPDStrirogaRepo opd_striroga_repos;
	@Autowired
	OPDBalrogaRepo opd_balrog_repos;
	@Autowired
	OPDShalyatantraRepo opd_shalya_repos;
	@Autowired
	OPDShalakyatantraRepo opd_shalakya_repos;
	@Autowired
	OPDSwastharakshanamRepo opd_swastha_repos;
	@Autowired
	OPDAtyayikaRepo opd_atyayika_repos;
	
	
	//---------------------------------------- Kayachikitsa ------------------------------------------------------
	
	public List<OPDKayachikitsa> fetch_DOPD_Kayachikitsa(LocalDate d)
	{
		
		List<OPDKayachikitsa> list = opd_kaya_repos.get_kaya_DOPD_Patients(d);
		
		for(OPDKayachikitsa pt : list)
		{
			System.out.println(pt.getDopd_kaya_patient().getName()+"  "+pt.getDopd_kaya_patient().getAge()+"  "+pt.getDopd_kaya_patient().getAddress());
		}
		
	return list;
	
	}
	
	//---------------------------------------- Panchakarma ------------------------------------------------------
	
	
	public List<OPDPanchakarma> fetch_DOPD_Panchakarma(LocalDate d)
	{
		
		List<OPDPanchakarma> list = opd_panch_repos.get_panch_DOPD_Patients(d);
		
		for(OPDPanchakarma pt : list)
		{
			System.out.println(pt.getDopd_panch_patient().getName()+"  "+pt.getDopd_panch_patient().getAge()+"  "+pt.getDopd_panch_patient().getAddress());
		}
		
	return list;
	
	}

	//---------------------------------------- Striroga ------------------------------------------------------
	
	public List<OPDStriroga> fetch_DOPD_striroga(LocalDate d)
	{
		
		List<OPDStriroga> list = opd_striroga_repos.get_panch_striroga_Patients(d);
		
		for(OPDStriroga pt : list)
		{
			System.out.println(pt.getDopd_stri_patient().getName()+"  "+pt.getDopd_stri_patient().getAge()+"  "+pt.getDopd_stri_patient().getAddress());
		}
		
	return list;
	
	}

	//---------------------------------------- Balroga ------------------------------------------------------
	
	public List<OPDBalroga> fetch_DOPD_balroga(LocalDate d)
	{
		
		List<OPDBalroga> list = opd_balrog_repos.get_balroga_DOPD_Patients(d);
		
		for(OPDBalroga pt : list)
		{
			System.out.println(pt.getDopd_bal_patient().getName()+"  "+pt.getDopd_bal_patient().getAge()+"  "+pt.getDopd_bal_patient().getAddress());
		}
		
	return list;
	
	}

	//---------------------------------------- Shalyatantra ------------------------------------------------------
	
	
	public List<OPDShalyatantra> fetch_DOPD_shalya(LocalDate d)
	{
		
		List<OPDShalyatantra> list = opd_shalya_repos.get_shalya_DOPD_Patients(d);
		
		for(OPDShalyatantra pt : list)
		{
			System.out.println(pt.getDopd_shalya_patient().getName()+"  "+pt.getDopd_shalya_patient().getAge()+"  "+pt.getDopd_shalya_patient().getAddress());
		}
		
	return list;
	
	}
	
	//---------------------------------------- Shalakyatantra ------------------------------------------------------
	
	
	public List<OPDShalakyatantra> fetch_DOPD_shalakya(LocalDate d)
	{
		
		List<OPDShalakyatantra> list = opd_shalakya_repos.get_shalakya_DOPD_Patients(d);
		
		for(OPDShalakyatantra pt : list)
		{
			System.out.println(pt.getDopd_shalakya_patient().getName()+"  "+pt.getDopd_shalakya_patient().getAge()+"  "+pt.getDopd_shalakya_patient().getAddress());
		}
		
	return list;
	
	}
	
	//---------------------------------------- Swasthavrutta ------------------------------------------------------

	public List<OPDSwastharakshanam> fetch_DOPD_swasthavrutta(LocalDate d)
	{
		
		List<OPDSwastharakshanam> list = opd_swastha_repos.get_swastha_DOPD_Patients(d);
		
		for(OPDSwastharakshanam pt : list)
		{
			System.out.println(pt.getDopd_swastha_patient().getName()+"  "+pt.getDopd_swastha_patient().getAge()+"  "+pt.getDopd_swastha_patient().getAddress());
		}
		
	return list;
	
	}

	//---------------------------------------- Atyayika ------------------------------------------------------
	
	public List<OPDAtyayika> fetch_DOPD_atyayika(LocalDate d)
	{
		
		List<OPDAtyayika> list = opd_atyayika_repos.get_atyayika_DOPD_Patients(d);
		
		for(OPDAtyayika pt : list)
		{
			System.out.println(pt.getDopd_atya_patient().getName()+"  "+pt.getDopd_atya_patient().getAge()+"  "+pt.getDopd_atya_patient().getAddress());
		}
		
	return list;
	
	}
	
}
