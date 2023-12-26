package project.EntityDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Central_Admit;
import project.Entity.Patient;
import project.repositories.Central_AdmitRepo;

@Component
public class CentralAdmitLogic {


	@Autowired
	Central_AdmitRepo central_ipd_repo;
	
	public List<Central_Admit> getCentral_IPD_Details_List(LocalDate d) 
	{
		return central_ipd_repo.getCentral_IPD_Patients(d);
	}
	
	
	
	
	
	
	
	
	
	
	
//	public List<Patient> getIPD(List<Patient> list) 
//	{
//		List<Patient> cipdList = new ArrayList<>();
//
//		int counter = 0;
//
//		for(Patient p : list)
//		{
//			System.out.println(p.getSr_no()+""+"*"+p.getDepartment()+"*  "+p.getAge()+"  *"+p.getDiagnosis()+"*");
//
//			//--------------------------------------kayachikitsa----------------------------------------------
//
//			if(p.getDepartment().equalsIgnoreCase("Kaychikitsa"))			
//			{					
//				if(	p.getDiagnosis().equalsIgnoreCase("aamlapitta") ||
//						p.getDiagnosis().equalsIgnoreCase("grahani")||
//						p.getDiagnosis().equalsIgnoreCase("shwas") ||
//						p.getDiagnosis().equalsIgnoreCase("nidranash") ||
//						p.getDiagnosis().equalsIgnoreCase("sthoulya") ||
//						p.getDiagnosis().equalsIgnoreCase("pandu") ||
//						p.getDiagnosis().equalsIgnoreCase("atisar") ||
//						p.getDiagnosis().equalsIgnoreCase("parshnirshul")
//						)
//				{
//					System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//					counter++;
//					cipdList.add(p);
//				}
//
//				if(p.getAge()>=40) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("Aamvat") || 
//							p.getDiagnosis().equalsIgnoreCase("Manyagatvat") ||
//							p.getDiagnosis().equalsIgnoreCase("sandhigatvat") ||
//							p.getDiagnosis().equalsIgnoreCase("Gradhrasi") ||
//							p.getDiagnosis().equalsIgnoreCase("Katigatvat"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//			}
//			else if(p.getDepartment().equalsIgnoreCase("panchakarma"))			
//			{
//
//				//----------------------------------------panchkarma--------------------------------------------
//				
//				if(	p.getDiagnosis().equalsIgnoreCase("nidranash") ||
//						p.getDiagnosis().equalsIgnoreCase("sthoullya")||
//						p.getDiagnosis().equalsIgnoreCase("khalitya") ||
//						p.getDiagnosis().equalsIgnoreCase("palitya") ||
//						p.getDiagnosis().equalsIgnoreCase("parshnishul")
//						)
//				{
//					System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//					counter++;
//					cipdList.add(p);
//				}
//
//				if(p.getAge()>=40) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("Aamvat") || 
//							p.getDiagnosis().equalsIgnoreCase("Manyagatvat") ||
//							p.getDiagnosis().equalsIgnoreCase("sandhigatvat") ||
//							p.getDiagnosis().equalsIgnoreCase("Gradhrasi") ||
//							p.getDiagnosis().equalsIgnoreCase("Katigatvat") ||
//							p.getDiagnosis().equalsIgnoreCase("malavshtambh"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//
//				}
//			}
//			else if(p.getDepartment().equalsIgnoreCase("shalya"))			
//			{
//				
//				//----------------------------------------shalya--------------------------------------------
//				
//				if((p.getAge()>=35) && (p.getAge()<=50)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("arsh") || 
//							p.getDiagnosis().equalsIgnoreCase("parikartika") ||
//							p.getDiagnosis().equalsIgnoreCase("bhagandar") ||
//							p.getDiagnosis().equalsIgnoreCase("gudbhransh") ||
//							p.getDiagnosis().equalsIgnoreCase("agantujravan") ||
//							p.getDiagnosis().equalsIgnoreCase("mutrashmari"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//			}
//			else if(p.getDepartment().equalsIgnoreCase("shalakyatantra"))
//			{
//
//				//----------------------------------------- shalakya-----------------------------------------------------
//				
//				if((p.getAge()>50) && (p.getAge()<=70)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("terizium`"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=25) && (p.getAge()<35))
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("netradah"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=35) && (p.getAge()<=50)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("pterigium") ||
//							p.getDiagnosis().equalsIgnoreCase("dacryocystits") ||
//							p.getDiagnosis().equalsIgnoreCase("lagan") ||
//							p.getDiagnosis().equalsIgnoreCase("netradah"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//			}
//			else if(p.getDepartment().equalsIgnoreCase("strirog-prasuti"))        
//			{
//				
//				 //------------------------------------- striroga-------------------------------------------
//				
//				if((p.getAge()>=18) && (p.getAge()<=35)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("stri vyandhyatva"))
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=14)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("puyayukt yonistrav") ||
//							p.getDiagnosis().equalsIgnoreCase("shwetpradar") ||
//							p.getDiagnosis().equalsIgnoreCase("yonikandu") ||
//							p.getDiagnosis().equalsIgnoreCase("yoni daurgandhya") ||
//							p.getDiagnosis().equalsIgnoreCase("yonidah")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//
//				if((p.getAge()>=14) && (p.getAge()<=45)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("granthibhut rajpravartan") ||
//							p.getDiagnosis().equalsIgnoreCase("raktpradar") ||
//							p.getDiagnosis().equalsIgnoreCase("kashtartav") ||
//							p.getDiagnosis().equalsIgnoreCase("aniyamit rajpravartan")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=14) && (p.getAge()<=35)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("anartav") ||
//							p.getDiagnosis().equalsIgnoreCase("PCOD")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=18) && (p.getAge()<=35)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("garbhini atisar") ||
//							p.getDiagnosis().equalsIgnoreCase("garbhini chhardi")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//			}
//			else if(p.getDepartment().equalsIgnoreCase("balrog"))			
//			{
//				
//				//-------------------------------------- balrog------------------------------------------------
//				
//				if((p.getAge()>=1) && (p.getAge()<=3)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("atisar") ||
//							p.getDiagnosis().equalsIgnoreCase("vataj atisar") ||
//							p.getDiagnosis().equalsIgnoreCase("pittaj atisar")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=1) && (p.getAge()<=5)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("krumi") ||
//							p.getDiagnosis().equalsIgnoreCase("visham jwar") ||
//							p.getDiagnosis().equalsIgnoreCase("bahupitta kamla")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=3) && (p.getAge()<=6)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("vishuchika") ||
//							p.getDiagnosis().equalsIgnoreCase("pandu")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//				if((p.getAge()>=5) && (p.getAge()<=8)) 
//				{
//					if(p.getDiagnosis().equalsIgnoreCase("sthaulya")
//							)
//					{
//						System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");
//						counter++;
//						cipdList.add(p);
//					}
//				}
//
//			}
//
//		}
//		System.out.println("total count: "+counter);
//		return cipdList;
//	}

}
