package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.IPDBalroga;
import project.Entity.IPDKayachikitsa;
import project.Entity.IPDPanchakarma;
import project.Entity.IPDShalakyatantra;
import project.Entity.IPDShalyatantra;
import project.Entity.IPDStriroga;
import project.Entity.OPDAtyayika;
import project.Entity.OPDBalroga;
import project.Entity.OPDKayachikitsa;
import project.Entity.OPDPanchakarma;
import project.Entity.OPDShalakyatantra;
import project.Entity.OPDShalyatantra;
import project.Entity.OPDStriroga;
import project.Entity.OPDSwastharakshanam;
import project.Entity.Patient;
import project.EntityDAO.DIPDLogic;

@RestController
@RequestMapping("/ayurved")
@CrossOrigin("*")
public class DIPDController {

	@Autowired
	DIPDLogic dipd_logic;
	
	//---------------------------------------- Kayachikitsa ------------------------------------------------------
	
	
		@PostMapping("/dipd/kaya")
		@CrossOrigin("*")
		public List<IPDKayachikitsa> getKayachikisaDIPDlist(@RequestBody Patient p)
		{
			System.out.println("kayachikitsa DIPD "+p.getDate());

			LocalDate locald = p.getDate();

			System.out.println("Inside kaychikitsa DIPD");

			List<IPDKayachikitsa> list = dipd_logic.fetch_DIPD_Kayachikitsa(locald);

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
		
		
		@PostMapping("/dipd/panch")
		@CrossOrigin("*")
		public List<IPDPanchakarma> getPanchakarmaDIPDlist(@RequestBody Patient p)
		{
			System.out.println("Panchakarma DIPD"+p.getDate());
			LocalDate locald = p.getDate();

			System.out.println("Inside Panchakarma DIPD");

			List<IPDPanchakarma> list = dipd_logic.fetch_DIPD_Panchakarma(locald);

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
		
		
		@PostMapping("/dipd/shalya")
		@CrossOrigin("*")
		public List<IPDShalyatantra> getShalyatantraDIPDlist(@RequestBody Patient p)
		{
			System.out.println("Shalyatantra DIPD "+p.getDate());
			LocalDate locald = p.getDate();

			System.out.println("Inside Shalyatantra DIPD");

			List<IPDShalyatantra> list = dipd_logic.fetch_DIPD_shalya(locald);

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

		@PostMapping("/dipd/shalakya")
		@CrossOrigin("*")
		public List<IPDShalakyatantra> getShalakyatantraDIPDlist(@RequestBody Patient p)
		{
			System.out.println("Shalakya DOPD "+p.getDate());
			LocalDate locald = p.getDate();

			System.out.println("Inside Shalakya DOPD");

			List<IPDShalakyatantra> list = dipd_logic.fetch_DIPD_shalakya(locald);

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
		

		@PostMapping("/dipd/striroga")
		@CrossOrigin("*")
		public List<IPDStriroga>  getStrirogDIPDlist(@RequestBody Patient p)
		{
			System.out.println("Strirog DOPD "+p.getDate());
			
			LocalDate locald = p.getDate();

			System.out.println("Inside Strirog DOPD");

			List<IPDStriroga> list = dipd_logic.fetch_DIPD_striroga(locald);

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

		@PostMapping("/dipd/balroga")
		@CrossOrigin("*")
		public List<IPDBalroga> getBalrogDIPDlist(@RequestBody Patient p)
		{
			System.out.println("Balrog DOPD "+p.getDate());
			
			LocalDate locald = p.getDate();

			System.out.println("Inside Balrog DOPD");

			List<IPDBalroga> list = dipd_logic.fetch_DIPD_balroga(locald);

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
