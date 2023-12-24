package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.OPDAtyayika;
import project.Entity.OPDBalroga;
import project.Entity.OPDKayachikitsa;
import project.Entity.OPDPanchakarma;
import project.Entity.OPDShalakyatantra;
import project.Entity.OPDShalyatantra;
import project.Entity.OPDStriroga;
import project.Entity.OPDSwastharakshanam;
import project.Entity.Patient;
import project.EntityDAO.DOPDLogic;

@RestController
@CrossOrigin("*")
@RequestMapping("/ayurved")
public class DOPDController {

	@Autowired
	DOPDLogic dopd_logic;


	//---------------------------------------- Kayachikitsa ------------------------------------------------------
	
	
	@PostMapping("/dopd/kaya")
	@CrossOrigin("*")
	public List<OPDKayachikitsa> getKayachikisaDOPDlist(@RequestBody Patient p)
	{
		System.out.println("kayachikitsa DOPD "+p.getDate());

		LocalDate locald = p.getDate();

		System.out.println("Inside kaychikitsa DOPD");

		List<OPDKayachikitsa> list = dopd_logic.fetch_DOPD_Kayachikitsa(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;

	}

	
	//---------------------------------------- Panchakarma ------------------------------------------------------
	
	
	@PostMapping("/dopd/panch")
	@CrossOrigin("*")
	public List<OPDPanchakarma> getPanchakarmaDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Panchakarma DOPD"+p.getDate());
		LocalDate locald = p.getDate();

		System.out.println("Inside Panchakarma DOPD");

		List<OPDPanchakarma> list = dopd_logic.fetch_DOPD_Panchakarma(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
	}
	
	//---------------------------------------- Shalyatantra ------------------------------------------------------
	
	
	@PostMapping("/dopd/shalya")
	@CrossOrigin("*")
	public List<OPDShalyatantra> getShalyatantraDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Shalyatantra DOPD "+p.getDate());
		LocalDate locald = p.getDate();

		System.out.println("Inside Shalyatantra DOPD");

		List<OPDShalyatantra> list = dopd_logic.fetch_DOPD_shalya(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
	}

	//---------------------------------------- Shalakyatantra ------------------------------------------------------

	@PostMapping("/dopd/shalakya")
	@CrossOrigin("*")
	public List<OPDShalakyatantra> getShalakyatantraDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Shalakya DOPD "+p.getDate());
		LocalDate locald = p.getDate();

		System.out.println("Inside Shalakya DOPD");

		List<OPDShalakyatantra> list = dopd_logic.fetch_DOPD_shalakya(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
	}

	
	//---------------------------------------- Striroga ------------------------------------------------------
	

	@PostMapping("/dopd/strirog")
	@CrossOrigin("*")
	public List<OPDStriroga>  getStrirogDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Strirog DOPD "+p.getDate());
		
		LocalDate locald = p.getDate();

		System.out.println("Inside Strirog DOPD");

		List<OPDStriroga> list = dopd_logic.fetch_DOPD_striroga(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
		
	}

	
	//---------------------------------------- Balroga ------------------------------------------------------

	@PostMapping("/dopd/balrog")
	@CrossOrigin("*")
	public List<OPDBalroga> getBalrogDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Balrog DOPD "+p.getDate());
		
		LocalDate locald = p.getDate();

		System.out.println("Inside Balrog DOPD");

		List<OPDBalroga> list = dopd_logic.fetch_DOPD_balroga(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		for(OPDBalroga bal:list)
		{
			System.out.println(bal.getDopd_bal_sr_no()+" "+bal.getDopd_bal_patient().getName());
		}
		
		return list;

	}

	//---------------------------------------- Swasthavrutta ------------------------------------------------------
	
	@PostMapping("/dopd/swastha")
	@CrossOrigin("*")
	public List<OPDSwastharakshanam> getSwastharakshanamDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Swastharakshanam DOPD +p.getDate()");
		
		LocalDate locald = p.getDate();

		System.out.println("Inside Swastharakshanam DOPD");

		List<OPDSwastharakshanam> list = dopd_logic.fetch_DOPD_swasthavrutta(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;
	}

	
	//---------------------------------------- Atyayika ------------------------------------------------------
	
	
	@PostMapping("/dopd/atyayika")
	@CrossOrigin("*")
	public List<OPDAtyayika> getAtyayikaDOPDlist(@RequestBody Patient p)
	{
		System.out.println("Atyayika DOPD "+p.getDate());
		
		LocalDate locald = p.getDate();

		System.out.println("Inside Atyayika DOPD");

		List<OPDAtyayika> list = dopd_logic.fetch_DOPD_atyayika(locald);

		if(list!=null)
		{
			System.out.println("not null");
		}
		else
		{
			System.out.println("null");
		}

		return list;

	}

}
