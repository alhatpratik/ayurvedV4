package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.IPDBalroga;
import project.Entity.IPDKayachikitsa;
import project.Entity.IPDPanchakarma;
import project.Entity.IPDShalakyatantra;
import project.Entity.IPDShalyatantra;
import project.Entity.IPDStriroga;
import project.repositories.IPDBalrogaRepo;
import project.repositories.IPDKayachikitsaRepo;
import project.repositories.IPDPanchakarmaRepo;
import project.repositories.IPDShalakyatantraRepo;
import project.repositories.IPDShalyatantraRepo;
import project.repositories.IPDStrirogaRepo;

@Component
public class DIPDLogic {

	@Autowired
	IPDKayachikitsaRepo ipd_kaya_repos;
	@Autowired
	IPDPanchakarmaRepo ipd_panch_repos;
	@Autowired
	IPDStrirogaRepo ipd_stri_repos;
	@Autowired
	IPDBalrogaRepo ipd_bal_repos;
	@Autowired
	IPDShalyatantraRepo ipd_shalya_repos;
	@Autowired
	IPDShalakyatantraRepo ipd_shalakya_repos;
	
	
	//---------------------------------------- Kayachikitsa ------------------------------------------------------
	
		public List<IPDKayachikitsa> fetch_DIPD_Kayachikitsa(LocalDate d)
		{
			
			List<IPDKayachikitsa> list = ipd_kaya_repos.get_kaya_DIPD_Patients(d);
			
			for(IPDKayachikitsa pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}
		
		//---------------------------------------- Panchakarma ------------------------------------------------------
		
		
		public List<IPDPanchakarma> fetch_DIPD_Panchakarma(LocalDate d)
		{
			
			List<IPDPanchakarma> list = ipd_panch_repos.get_panch_DIPD_Patients(d);
			
			for(IPDPanchakarma pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}

		//---------------------------------------- Striroga ------------------------------------------------------
		
		public List<IPDStriroga> fetch_DIPD_striroga(LocalDate d)
		{
			
			List<IPDStriroga> list = ipd_stri_repos.get_striroga_DIPD_Patients(d);
			
			for(IPDStriroga pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}

		//---------------------------------------- Balroga ------------------------------------------------------
		
		public List<IPDBalroga> fetch_DIPD_balroga(LocalDate d)
		{
			
			List<IPDBalroga> list = ipd_bal_repos.get_balroga_DIPD_Patients(d);
			
			for(IPDBalroga pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}

		//---------------------------------------- Shalyatantra ------------------------------------------------------
		
		
		public List<IPDShalyatantra> fetch_DIPD_shalya(LocalDate d)
		{
			
			List<IPDShalyatantra> list = ipd_shalya_repos.get_shalya_DIPD_Patients(d);
			
			for(IPDShalyatantra pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}
		
		//---------------------------------------- Shalakyatantra ------------------------------------------------------
		
		
		public List<IPDShalakyatantra> fetch_DIPD_shalakya(LocalDate d)
		{
			
			List<IPDShalakyatantra> list = ipd_shalakya_repos.get_shalakya_DIPD_Patients(d);
			
			for(IPDShalakyatantra pt : list)
			{
				System.out.println(pt.getName()+"  "+pt.getAge()+"  "+pt.getAddress());
			}
			
		return list;
		
		}
		
}
