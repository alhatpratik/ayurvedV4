package project.EntityDAO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.BedOccupancy;
import project.Entity.Central_IPD;
import project.Entity.Central_Admit;
import project.Entity.IPDBalroga;
import project.Entity.IPDKayachikitsa;
import project.Entity.IPDPanchakarma;
import project.Entity.IPDPanchakarmaProcedureRegister;
import project.Entity.IPDShalakyatantra;
import project.Entity.IPDShalyatantra;
import project.Entity.IPDStriroga;
import project.Entity.OPDAtyayika;
import project.Entity.OPDBalroga;
import project.Entity.OPDKayachikitsa;
import project.Entity.OPDPanchakarma;
import project.Entity.OPDPanchakarmaProcedureRegister;
import project.Entity.OPDShalakyatantra;
import project.Entity.OPDShalyatantra;
import project.Entity.OPDStriroga;
import project.Entity.OPDSwastharakshanam;
import project.Entity.OPD_Disease_Medicine_CasePaper;
import project.Entity.OPD_Disease_Symptoms_patient_improvement;
import project.Entity.Pathological_Tests;
import project.Entity.Patient;
import project.repositories.OPDAtyayikaRepo;
import project.repositories.OPDBalrogaRepo;
import project.repositories.BedOccupancyRepo;
import project.repositories.Central_IPDRepo;
import project.repositories.Central_AdmitRepo;
import project.repositories.Central_OPDRepo;
import project.repositories.IPDBalrogaRepo;
import project.repositories.IPDKayachikitsaRepo;
import project.repositories.IPDPanchakarmaProcedureRepo;
import project.repositories.IPDPanchakarmaRepo;
import project.repositories.IPDShalakyatantraRepo;
import project.repositories.IPDShalyatantraRepo;
import project.repositories.IPDStrirogaRepo;
import project.repositories.OPDKayachikitsaRepo;
import project.repositories.OPDPanchakarmaProcedureRepo;
import project.repositories.OPDPanchakarmaRepo;
import project.repositories.OPDShalakyatantraRepo;
import project.repositories.OPDShalyatantraRepo;
import project.repositories.OPDStrirogaRepo;
import project.repositories.OPDSwastharakshanamRepo;
import project.repositories.OPD_DiseaseMedicineCasePaperRepo;
import project.repositories.OPD_Disease_SymptomsImproveRepo;
import project.repositories.Pathological_TestsRepo;

@Component
public class RecordLogic {

	//--------------------------------- variables for Central OPD and Departmental OPD ---------------------

	@Autowired
	DateLogic dl;
	@Autowired
	Central_OPDRepo ari;
	@Autowired
	OPDKayachikitsaRepo opd_kayaRepo;
	@Autowired
	OPDPanchakarmaRepo opd_panchRepo;
	@Autowired
	OPDShalakyatantraRepo opd_shalakyaRepo;
	@Autowired
	OPDShalyatantraRepo opd_shalyaRepo;
	@Autowired
	OPDStrirogaRepo opd_striRepo;
	@Autowired
	OPDBalrogaRepo opd_balRepo;
	@Autowired
	OPDSwastharakshanamRepo opd_swasthaRepo;
	@Autowired
	OPDAtyayikaRepo opd_atyaRepo;

	@Autowired
	OPDKayachikitsa opd_kaya;
	@Autowired
	OPDPanchakarma opd_panch;
	@Autowired
	OPDStriroga opd_stri;
	@Autowired
	OPDBalroga opd_bal;
	@Autowired
	OPDShalyatantra opd_shalya;
	@Autowired
	OPDShalakyatantra opd_shalakya;
	@Autowired
	OPDAtyayika opd_atya;
	@Autowired
	OPDSwastharakshanam opd_swasth;

	LocalDate central_opd_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_kaya_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_panch_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_stri_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_bal_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_shalya_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_shalakya_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_swastha_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_atya_backup_date = LocalDate.of(1995, 10, 06);

	double central_opd_sr_no=0;
	double opd_kaya_sr_no=0;
	double opd_panch_sr_no=0;
	double opd_stri_sr_no=0;
	double opd_bal_sr_no=0;
	double opd_shalya_sr_no=0;
	double opd_shalakya_sr_no=0;
	double opd_atya_sr_no=0;
	double opd_swastha_sr_no=0;

	double central_opd_yr_no=0;
	double opd_kaya_yr_no =1;
	double opd_panch_yr_no =1;
	double opd_stri_yr_no=1;
	double opd_bal_yr_no=1;
	double opd_shalya_yr_no=1;
	double opd_shalakya_yr_no=1;
	double opd_atya_yr_no=1;
	double opd_swastha_yr_no=1;

	//--------------------------------- variables for Central Admit and Departmental IPD ---------------------

	@Autowired
	Central_AdmitRepo central_admitRepo;
	@Autowired
	IPDKayachikitsaRepo ipd_kayaRepo;
	@Autowired
	IPDPanchakarmaRepo ipd_panchRepo;
	@Autowired
	IPDStrirogaRepo ipd_striRepo;
	@Autowired
	IPDBalrogaRepo ipd_balRepo;
	@Autowired
	IPDShalyatantraRepo ipd_shalyaRepo;
	@Autowired
	IPDShalakyatantraRepo ipd_shalakyaRepo;

	@Autowired
	Central_Admit central_admit;
	@Autowired
	IPDKayachikitsa ipd_kaya;
	@Autowired
	IPDPanchakarma ipd_panch;
	@Autowired
	IPDStriroga ipd_stri;
	@Autowired
	IPDBalroga ipd_bal;
	@Autowired
	IPDShalyatantra ipd_shalya;
	@Autowired
	IPDShalakyatantra ipd_shalakya;


	LocalDate central_admit_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate ipd_kaya_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate ipd_panch_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate ipd_stri_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate ipd_bal_backup_date = LocalDate.of(1995, 10, 06);;
	LocalDate ipd_shalya_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate ipd_shalakya_backup_date = LocalDate.of(1995, 10, 06);

	double central_admit_yr_count = 1;
	double central_admit_Sr_count = 1;
	double ipd_kaya_sr_no=0;
	double ipd_panch_sr_no=0;
	double ipd_stri_sr_no=0;
	double ipd_bal_sr_no=0;
	double ipd_shalya_sr_no=0;
	double ipd_shalakya_sr_no=0;
	double ipd_atya_sr_no=0;
	double ipd_swastha_sr_no=0;


	double ipd_kaya_yr_no =1;
	double ipd_panch_yr_no =1;
	double ipd_stri_yr_no=1;
	double ipd_bal_yr_no=1;
	double ipd_shalya_yr_no=1;
	double ipd_shalakya_yr_no=1;
	double ipd_atya_yr_no=1;
	double ipd_swastha_yr_no=1;

	//------------------------------------------------------------------ variables for Bed Occupancy

	@Autowired
	BedOccupancyRepo bed_occupancy_repos;

	@Autowired
	BedOccupancy bed_occupancy;

	LocalDate bed_occupancy_backup_date = LocalDate.of(1995, 10, 06);

	double bed_occupancy_sr_no = 1;
	double bed_occupancy_yr_no = 1;
	double bed_occupancy_record_no = 1;

	//------------------------------------------------------------------ variables for Central Admit Discharge

	@Autowired
	Central_IPDRepo central_ipdRepos;

	@Autowired
	Central_IPD central_ipd;

	LocalDate central_ipd_backup_date = LocalDate.of(1995, 10, 06);

	double central_ipd_sr_no = 1;
	double central_ipd_yr_no = 1;

	//------------------------------------------------------------------ variables for Adding record to OPD Case Paper

	@Autowired
	OPD_DiseaseMedicineCasePaperRepo opd_disease_medicine_case_paper_Repos;

	@Autowired
	OPD_Disease_Medicine_CasePaper opd_disease_medicine_case_paper;

	@Autowired
	OPD_Disease_SymptomsImproveRepo opd_disease_symptoms_improve_Repos;

	@Autowired
	OPD_Disease_Symptoms_patient_improvement opd_disease_symptoms_improvement;

	// create objects and save them in your database tables

	//----------------------------- New Changes for new year----------------------------------


	LocalDate patient_backup_date = LocalDate.of(1995, 10, 06);

	double central_opd_record_no = 1;
	double central_ipd_record_no = 1;
	double central_admit_record_no = 1;

	double opd_kaya_record_no=1;
	double opd_panch_record_no=1;
	double opd_stri_record_no=1;
	double opd_bal_record_no=1;
	double opd_shalya_record_no=1;
	double opd_shalakya_record_no=1;
	double opd_atya_record_no=1;
	double opd_swastha_record_no=1;

	double ipd_kaya_record_no =1;
	double ipd_panch_record_no =1;
	double ipd_stri_record_no=1;
	double ipd_bal_record_no=1;
	double ipd_shalya_record_no=1;
	double ipd_shalakya_record_no=1;

	//----------------------------- Panchakarma Procedure Register Variables ----------------------------------

	@Autowired
	OPDPanchakarmaProcedureRepo opd_panch_procedure_register_repo;

	@Autowired
	IPDPanchakarmaProcedureRepo ipd_panch_procedure_register_repo;

	@Autowired
	OPDPanchakarmaProcedureRegister opd_panch_procedure;

	@Autowired
	IPDPanchakarmaProcedureRegister ipd_panch_procedure;

	double opd_panch_procedure_yearly_count= 1;
	double opd_panch_procedure_serial_count= 1;

	double ipd_panch_procedure_yearly_count= 1;
	double ipd_panch_procedure_serial_count= 1;
	double ipd_panch_procedure_record_count= 1;
	double opd_panch_procedure_record_count= 1;

	LocalDate ipd_panch_procedure_backup_date = LocalDate.of(1995, 10, 06);
	LocalDate opd_panch_procedure_backup_date = LocalDate.of(1995, 10, 06);

	//------------------------------------- VARIABLES ENDS -------------------------------------------------

	@Autowired
	Pathological_TestsRepo pathologyTestRepo; 

	double pathology_Test_record_no = 1;

	//-------------------------------------- CONSTANTS ----------------------------------------------------
	
	@Autowired
	Constant constant;
	
	//------------------------------------- VARIABLES ENDS -------------------------------------------------


	public String addRecords()
	{

		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			try {

				f_in = new FileInputStream("D:\\abc.xlsx");

			} catch (FileNotFoundException e) {

				return "File Not Found";
			}

			XSSFWorkbook workbook = null;

			try {

				workbook = new XSSFWorkbook(f_in);

			} catch (IOException e) {

				return "problem in workbook";
			}

			XSSFSheet sheet = workbook.getSheetAt(0);

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			Iterator<Row> rowIt = sheet.iterator();
			while(rowIt.hasNext())
			{
				Row row = rowIt.next();
				Iterator<Cell> cellIterator =row.cellIterator();

				String rowSplit = "";
				while(cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					rowSplit += cell.toString()+";";       // seperate each value of sheet column by ; semicolon 
				}											// and concat each row of sheet with ; semicolon
				System.out.println(rowSplit);				//	by which further we can split that in each column String
				String [] arr = rowSplit.split(";");        // split each string by ; semicolon

				Patient p = new Patient();
				System.out.println("arr[0] "+arr[0]);		// setting each column word in patient p 

				try{
					p.setSr_no(Double.parseDouble(arr[0]));
				}
				catch(Exception e) {
					return constant.sr_no_not_found;
				}
				
				try{
					System.out.println("arr[1] "+arr[1]);
					p.setYearly_no(Double.parseDouble(arr[1]));
				}
				catch(Exception e) {
					return constant.yr_no_not_found_or_invalid;
				}
				
				try{
					if(arr[2]!="")
					{
						System.out.println("arr[2] "+arr[2]);
						p.setNew_no(Double.parseDouble(arr[2]));
					}
				}
				catch(Exception e) {
					return constant.new_no_not_found_or_invalid;
				}
				
				try{
					if(arr[3]!="")
					{
						System.out.println("arr[3] "+arr[3]);
						p.setOld_no(Double.parseDouble(arr[3]));
					}
				}
				catch(Exception e) {
					return constant.old_no_not_found;
				}

				try{
					System.out.println("arr[4] "+arr[4]);
					p.setName((arr[4].toLowerCase()).trim());
				}
				catch(Exception e) {
					return constant.patient_name_not_found_or_invalid;
				}

				try{
					System.out.println("arr[5] "+arr[5]);
					p.setAddress((arr[5].toLowerCase()).trim());
				}
				catch(Exception e) {
					return constant.address_not_found_invalid;
				}
				
				try{
					System.out.println("arr[6] "+arr[6]);
					p.setAge((int)Double.parseDouble(arr[6]));
				}
				catch(Exception e) {
					return constant.age_not_found;
				}
				
				try{
					System.out.println("arr[7] "+arr[7]);
					p.setSex(arr[7].charAt(0));
				}
				catch(Exception e) {
					return constant.gender_not_found_invalid;
				}
				
				try{
					System.out.println("arr[8] "+arr[8]);
					p.setDiagnosis((arr[8].toLowerCase()).trim());
				}
				catch(Exception e) {
					return constant.diagnosis_not_found_or_invalid;
				}
				
				try{
					System.out.println("arr[9] "+arr[9]);
					p.setDepartment((arr[9].toLowerCase()).trim());
				}
				catch(Exception e) {
					return constant.department_not_found_invalid;
				}
				
				try{
					System.out.println("arr[10] "+arr[10]);
					LocalDate ld = dl.getDate(arr[10]);
					p.setDate(ld);
				}
				catch(Exception e) {
					return constant.admit_date_not_found_or_invalid;
				}
				
				try{
					System.out.println("arr[11] "+arr[11]);
					LocalDate discharge_date = dl.getDate(arr[11]);
					System.out.println("Discharge Date : "+discharge_date);
					p.setDischarge_date(discharge_date);
				}
				catch(Exception e) {
					return constant.discharge_date_not_found_or_invalid;
				}
				
				try{
					System.out.println("arr[12] Weight"+arr[12]);
					p.setWeight(Double.parseDouble(arr[12]));
				}
				catch(Exception e) {
					return constant.weight_not_found_or_invalid;
				}
				
				try{
					System.out.println("arr[13] "+arr[13]);
					p.setRecord_no(Double.parseDouble(arr[13]));
					System.out.println(p.getRecord_no()+" record Number");
				}
				catch(Exception e) {
					return constant.record_no_not_found_or_invalid;
				}
				
				LocalDate central_date = p.getDate();

				if(central_opd_backup_date.getYear()==central_date.getYear())
				{
					central_opd_yr_no++;
					p.setYearly_no(central_opd_yr_no);
				}
				else
				{
					central_opd_yr_no = 1;
					p.setYearly_no(central_opd_yr_no);
				}


				if(central_opd_backup_date.isEqual(central_date)) {
					central_opd_sr_no++;
					p.setSr_no(central_opd_sr_no);
				}
				else
				{
					central_opd_backup_date = central_date;
					central_opd_sr_no = 1;
					p.setSr_no(central_opd_sr_no);
				}

				ari.save(p);				// saving each object of p in COPD / Patient table

				System.out.println("Record saved in Central / Patient table");
				
				//-----------------------------------------------------------------------------------
				//----------- check each patient for X-Ray and Other Pathological Tests and save that record ------------------
				//-----------------------------------------------------------------------------------

				String DOPD_status = addRecordToDOPD(p);  // function call to add record in DOPD from COPD Excel Sheet
				System.out.println(DOPD_status);

			}

			System.out.println();

			try {
				workbook.close();
			} catch (IOException e) {
				System.out.println("workbook Closed");
			}
		} 
		catch(NullPointerException e)
		{
			return "Cannot perform Action";
		}

		//--------------------------------------------------------------------------------------------------------
		// Add Record to OPD_DiseaseMedicineCasePaperRepo OPD_Disease_Symptoms_patient_improvement
		//--------------------------------------------------------------------------------------------------------

		String opd_case_paper_status = addRecordToOPDCasePaper_Kayachiktsa_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Panchakarma_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Shalakyatantra_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Shalyatantra_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Strirog_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Balrog_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Atyayika_Department();
		System.out.println(opd_case_paper_status+"");

		opd_case_paper_status = addRecordToOPDCasePaper_Swastharakshanam_Department();
		System.out.println(opd_case_paper_status+"");

		System.out.println(opd_case_paper_status);

		return "Records Added Successfully";

	}

	//-------------------------------------------------------------------------------------------------------

	public String addRecordToDOPD(Patient p)
	{

		LocalDate ld = p.getDate();				

		if(p.getDepartment().equalsIgnoreCase(constant.kayachikitsa))
		{

			if(opd_kaya_backup_date.getYear()==ld.getYear())
			{
				opd_kaya_yr_no++;
				opd_kaya.setDopd_kaya_yearly_no(opd_kaya_yr_no);
			}
			else
			{
				opd_kaya_yr_no = 1;
				opd_kaya.setDopd_kaya_yearly_no(opd_kaya_yr_no);
			}


			if(opd_kaya_backup_date.isEqual(ld)) {				// checking each date to maintain sr_no
				opd_kaya_sr_no++;								// if new date comes then sr_no should start with 1 again
				opd_kaya.setDopd_kaya_sr_no(opd_kaya_sr_no);				// else sr_no must increase by 1;
			}
			else
			{
				opd_kaya_backup_date = ld;
				opd_kaya_sr_no = 1;
				opd_kaya.setDopd_kaya_sr_no(opd_kaya_sr_no);
			}

			opd_kaya.setDopd_kaya_record_no(opd_kaya_record_no);			// setting each field of opdKayachikitsa 
			opd_kaya_record_no++;								// and maintaining yearly number by increasing +1;
			opd_kaya.setDopd_kaya_patient(p);
			opd_kayaRepo.save(opd_kaya);					// adding patient in opdKayachikitsa Table in database;

			String ipdStatus = addRecordToCentralIPD_And_DIPD_kaya(p);
			System.out.println(ipdStatus);

			return "patient added in kayachikitsa";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.panchakarma))
		{

			if(opd_panch_backup_date.getYear()==ld.getYear())
			{
				opd_panch_yr_no++;
				opd_panch.setDopd_panch_yearly_no(opd_panch_yr_no);
			}
			else
			{
				opd_panch_yr_no = 1;
				opd_panch.setDopd_panch_yearly_no(opd_panch_yr_no);
			}

			if(opd_panch_backup_date.isEqual(ld)) {
				opd_panch_sr_no++;
				opd_panch.setDopd_panch_sr_no(opd_panch_sr_no);
			}
			else
			{
				opd_panch_backup_date = ld;
				opd_panch_sr_no = 1;
				opd_panch.setDopd_panch_sr_no(opd_panch_sr_no);
			}


			opd_panch.setDopd_panch_record_no(opd_panch_record_no);
			opd_panch_record_no++;
			opd_panch.setDopd_panch_patient(p);
			opd_panchRepo.save(opd_panch);

			String ipdStatus = addRecordToCentralIPD_And_DIPD_Panchkarma(p);
			System.out.println(ipdStatus);

			String panch_procedure_status = addRecordToOPDPanchakarmaProcedureAndIPDPanchakarmaProcedureRegister(p);
			System.out.println(panch_procedure_status);

			return "patient added in panchakarma";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.strirog))
		{
			if(opd_stri_backup_date.getYear()==ld.getYear())
			{
				opd_stri_yr_no++;
				opd_stri.setDopd_stri_yearly_no(opd_stri_yr_no);
			}
			else
			{
				opd_stri_yr_no = 1;
				opd_stri.setDopd_stri_yearly_no(opd_stri_yr_no);
			}


			if(opd_stri_backup_date.isEqual(ld)) {
				opd_stri_sr_no++;
				opd_stri.setDopd_stri_sr_no(opd_stri_sr_no);
			}
			else
			{
				opd_stri_backup_date = ld;
				opd_stri_sr_no = 1;
				opd_stri.setDopd_stri_sr_no(opd_stri_sr_no);
			}


			opd_stri.setDopd_stri_record_no(opd_stri_record_no);
			opd_stri_record_no++;
			opd_stri.setDopd_stri_patient(p);
			opd_striRepo.save(opd_stri);

			String ipdStatus = addRecordToCentralIPD_And_DIPD_Striroga(p);
			System.out.println(ipdStatus);

			return "patient added in strirog-prasuti";


		}
		else if(p.getDepartment().equalsIgnoreCase(constant.shalya))
		{

			if(opd_shalya_backup_date.getYear()==ld.getYear())
			{
				opd_shalya_yr_no++;
				opd_shalya.setDopd_shalya_yearly_no(opd_shalya_yr_no);
			}
			else
			{
				opd_shalya_yr_no = 1;
				opd_shalya.setDopd_shalya_yearly_no(opd_shalya_yr_no);
			}


			if(opd_shalya_backup_date.isEqual(ld)) {
				opd_shalya_sr_no++;
				opd_shalya.setDopd_shalya_sr_no(opd_shalya_sr_no);
			}
			else
			{
				opd_shalya_backup_date = ld;
				opd_shalya_sr_no = 1;
				opd_shalya.setDopd_shalya_sr_no(opd_shalya_sr_no);
			}

			opd_shalya.setDopd_shalya_record_no(opd_shalya_record_no);
			opd_shalya_record_no++;
			opd_shalya.setDopd_shalya_patient(p);
			opd_shalyaRepo.save(opd_shalya);

			String ipdStatus = addRecordToCentralIPD_And_DIPD_Shalyatantra(p);
			System.out.println(ipdStatus);

			return "patient added in shalya";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.balrog))
		{
			if(opd_bal_backup_date.getYear()==ld.getYear())
			{
				opd_bal_yr_no++;
				opd_bal.setDopd_bal_yearly_no(opd_bal_yr_no);
			}
			else
			{
				opd_bal_yr_no = 1;
				opd_bal.setDopd_bal_yearly_no(opd_bal_yr_no);
			}


			if(opd_bal_backup_date.isEqual(ld)) {
				opd_bal_sr_no++;
				opd_bal.setDopd_bal_sr_no(opd_bal_sr_no);
			}
			else
			{
				opd_bal_backup_date = ld;
				opd_bal_sr_no = 1;
				opd_bal.setDopd_bal_sr_no(opd_bal_sr_no);
			}

			opd_bal.setDopd_bal_record_no(opd_bal_record_no);
			opd_bal_record_no++;
			opd_bal.setDopd_bal_patient(p);
			opd_balRepo.save(opd_bal);

			String ipdStatus = addRecordToCentralIPD_And_DIPD_Balroga(p);
			System.out.println(ipdStatus);

			return "patient added in Balrog";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.shalakya))
		{

			if(opd_shalakya_backup_date.getYear()==ld.getYear())
			{
				opd_shalakya_yr_no++;
				opd_shalakya.setDopd_shalakya_yearly_no(opd_shalakya_yr_no);
			}
			else
			{
				opd_shalakya_yr_no = 1;
				opd_shalakya.setDopd_shalakya_yearly_no(opd_shalakya_yr_no);
			}


			if(opd_shalakya_backup_date.isEqual(ld)) {
				opd_shalakya_sr_no++;
				opd_shalakya.setDopd_shalakya_sr_no(opd_shalakya_sr_no);
			}
			else
			{
				opd_shalakya_backup_date = ld;
				opd_shalakya_sr_no = 1;
				opd_shalakya.setDopd_shalakya_sr_no(opd_shalakya_sr_no);
			}


			opd_shalakya.setDopd_shalakya_record_no(opd_shalakya_record_no);
			opd_shalakya_record_no++;
			opd_shalakya.setDopd_shalakya_patient(p);
			opd_shalakyaRepo.save(opd_shalakya);

			String ipdStatus = addRecordToCentralIPD_And_DIPD_Shalakyatantra(p);
			System.out.println(ipdStatus);

			return "patient added in Shalakya";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.atyayika))
		{

			if(opd_atya_backup_date.getYear()==ld.getYear())
			{
				opd_atya_yr_no++;
				opd_atya.setDopd_atya_yearly_no(opd_atya_yr_no);
			}
			else
			{
				opd_atya_yr_no = 1;
				opd_atya.setDopd_atya_yearly_no(opd_atya_yr_no);
			}

			if(opd_atya_backup_date.isEqual(ld)) {
				opd_atya_sr_no++;
				opd_atya.setDopd_atya_sr_no(opd_atya_sr_no);
			}
			else 
			{
				opd_shalakya_backup_date = ld;
				opd_atya_sr_no = 1;
				opd_atya.setDopd_atya_sr_no(opd_atya_sr_no);
			}

			opd_atya.setDopd_atya_record_no(opd_atya_record_no);
			opd_atya_yr_no++;
			opd_atya.setDopd_atya_patient(p);
			opd_atyaRepo.save(opd_atya);

			return "patient added in Atyayika";

		}
		else if(p.getDepartment().equalsIgnoreCase(constant.swasthavrutta))
		{

			if(opd_swastha_backup_date.getYear()==ld.getYear())
			{
				opd_swastha_yr_no++;
				opd_swasth.setDopd_swastha_yearly_no(opd_swastha_yr_no);
			}
			else
			{
				opd_swastha_yr_no = 1;
				opd_swasth.setDopd_swastha_yearly_no(opd_swastha_yr_no);
			}

			if(opd_swastha_backup_date.isEqual(ld)) {
				opd_swastha_sr_no++;
				opd_swasth.setDopd_swastha_sr_no(opd_swastha_sr_no);
			}
			else
			{
				opd_swastha_backup_date = ld;
				opd_swastha_sr_no = 1;
				opd_swasth.setDopd_swastha_sr_no(opd_swastha_sr_no);
			}

			opd_swasth.setDopd_swastha_record_no(opd_swastha_record_no);
			opd_swastha_record_no++;
			opd_swasth.setDopd_swastha_patient(p);
			opd_swasthaRepo.save(opd_swasth);

			return "patient added in swasthavrutta";
		}
		return "not found in all DOPD";
	}

	public String addRecordToCentralIPD_And_DIPD_kaya(Patient p)
	{

		if(	p.getDiagnosis().equalsIgnoreCase(constant.aamlapitta) ||
				p.getDiagnosis().equalsIgnoreCase(constant.grahani)||
				p.getDiagnosis().equalsIgnoreCase(constant.shwas) ||
				p.getDiagnosis().equalsIgnoreCase(constant.nidranash) ||
				p.getDiagnosis().equalsIgnoreCase(constant.sthoulya) ||
				p.getDiagnosis().equalsIgnoreCase(constant.pandu) ||
				p.getDiagnosis().equalsIgnoreCase(constant.atisar) ||
				p.getDiagnosis().equalsIgnoreCase(constant.vatajkas) ||
				p.getDiagnosis().equalsIgnoreCase(constant.parshnirshul)
				)
		{

			String kaya_ipd_status = addRecordToCentralIPD_And_DIPD_kaya_logic_part(p);
			System.out.println(kaya_ipd_status);

			return kaya_ipd_status;

		}

		if(p.getAge()>=40) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.aamvat) || 
					p.getDiagnosis().equalsIgnoreCase(constant.manyagatvat) ||
					p.getDiagnosis().equalsIgnoreCase(constant.sandhigatvat) ||
					p.getDiagnosis().equalsIgnoreCase(constant.gradhrasi) ||
					p.getDiagnosis().equalsIgnoreCase(constant.katigatvat))
			{

				String kaya_ipd_status = addRecordToCentralIPD_And_DIPD_kaya_logic_part(p);
				System.out.println(kaya_ipd_status);

				return kaya_ipd_status;

			}
		}

		return "No IPD Disease found for kaya IPD";
	}

	public String addRecordToCentralIPD_And_DIPD_Panchkarma(Patient p)
	{
		if(	p.getDiagnosis().equalsIgnoreCase(constant.nidranash) ||
				p.getDiagnosis().equalsIgnoreCase(constant.sthoulya)||
				p.getDiagnosis().equalsIgnoreCase(constant.parshnirshul)
				)
		{
			String panch_ipd_status = addRecordToCentralIPD_And_DIPD_Panchkarma_logic_part(p);
			System.out.println(panch_ipd_status);

			return panch_ipd_status;
		}

		if(p.getAge()>=40) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.aamvat) || 
					p.getDiagnosis().equalsIgnoreCase(constant.manyagatvat) ||
					p.getDiagnosis().equalsIgnoreCase(constant.sandhigatvat) ||
					p.getDiagnosis().equalsIgnoreCase(constant.gradhrasi) ||
					p.getDiagnosis().equalsIgnoreCase(constant.katigatvat) ||
					p.getDiagnosis().equalsIgnoreCase(constant.malavshtambha))
			{

				String panch_ipd_status = addRecordToCentralIPD_And_DIPD_Panchkarma_logic_part(p);
				System.out.println(panch_ipd_status);

				return panch_ipd_status;
			}

		}

		return "No IPD Disease Found";
	}

	public String addRecordToCentralIPD_And_DIPD_Striroga(Patient p)
	{
		if((p.getAge()>=18) && (p.getAge()<=35)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.stri_vyandhyatva))
			{
				String stri_ipd_status = addRecordToCentralIPD_And_DIPD_Striroga_logic_part(p);
				System.out.println(stri_ipd_status);

				return stri_ipd_status;
			}
		}

		if((p.getAge()>=14)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.puyayukt_yonistrav) ||
					p.getDiagnosis().equalsIgnoreCase(constant.shwetpradar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.yonikandu) ||
					p.getDiagnosis().equalsIgnoreCase(constant.yoni_daurgandhya) ||
					p.getDiagnosis().equalsIgnoreCase(constant.yonidah)
					)
			{
				String stri_ipd_status = addRecordToCentralIPD_And_DIPD_Striroga_logic_part(p);
				System.out.println(stri_ipd_status);

				return stri_ipd_status;
			}
		}


		if((p.getAge()>=14) && (p.getAge()<=45)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.granthibhut_rajpravartan) ||
					p.getDiagnosis().equalsIgnoreCase(constant.raktpradar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.kashtartav) ||
					p.getDiagnosis().equalsIgnoreCase(constant.aniyamit_rajpravartan)
					)
			{

				String stri_ipd_status = addRecordToCentralIPD_And_DIPD_Striroga_logic_part(p);
				System.out.println(stri_ipd_status);

				return stri_ipd_status;
			}
		}

		if((p.getAge()>=14) && (p.getAge()<=35)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.anartav) ||
					p.getDiagnosis().equalsIgnoreCase(constant.PCOD)
					)
			{


				String stri_ipd_status = addRecordToCentralIPD_And_DIPD_Striroga_logic_part(p);
				System.out.println(stri_ipd_status);

				return stri_ipd_status;
			}

		}

		if((p.getAge()>=18) && (p.getAge()<=35)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.garbhini_atisar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.garbhini_chhardi)
					)
			{

				String stri_ipd_status = addRecordToCentralIPD_And_DIPD_Striroga_logic_part(p);
				System.out.println(stri_ipd_status);

				return stri_ipd_status;
			}
		}

		return "NO IPD Disease found in Strirog";
	}

	public String addRecordToCentralIPD_And_DIPD_Balroga(Patient p)
	{
		if((p.getAge()>=1) && (p.getAge()<=3)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.atisar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.vataj_atisar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.pittaj_atisar)
					)
			{

				String bal_ipd_status = addRecordToCentralIPD_And_DIPD_Balroga_logic_part(p);
				System.out.println(bal_ipd_status);

				return bal_ipd_status;
			}
		}

		if((p.getAge()>=1) && (p.getAge()<=5)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.krumi) ||
					p.getDiagnosis().equalsIgnoreCase(constant.visham_jwar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.bahupitta_kamla)
					)
			{

				String bal_ipd_status = addRecordToCentralIPD_And_DIPD_Balroga_logic_part(p);
				System.out.println(bal_ipd_status);

				return bal_ipd_status;
			}
		}

		if((p.getAge()>=3) && (p.getAge()<=6)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.vishuchika) ||
					p.getDiagnosis().equalsIgnoreCase(constant.pandu)
					)
			{

				String bal_ipd_status = addRecordToCentralIPD_And_DIPD_Balroga_logic_part(p);
				System.out.println(bal_ipd_status);

				return bal_ipd_status;
			}
		}

		if((p.getAge()>=5) && (p.getAge()<=8)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.sthoulya)
					)
			{

				String bal_ipd_status = addRecordToCentralIPD_And_DIPD_Balroga_logic_part(p);
				System.out.println(bal_ipd_status);

				return bal_ipd_status;
			}
		}
		return "No IPD Disease found for Balrog IPD";
	}

	public String addRecordToCentralIPD_And_DIPD_Shalyatantra(Patient p) 
	{
		if((p.getAge()>=35) && (p.getAge()<=50)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.arsh) || 
					p.getDiagnosis().equalsIgnoreCase(constant.parikartika) ||
					p.getDiagnosis().equalsIgnoreCase(constant.bhagandar) ||
					p.getDiagnosis().equalsIgnoreCase(constant.gudbhransh) ||
					p.getDiagnosis().equalsIgnoreCase(constant.agantujravan) ||
					p.getDiagnosis().equalsIgnoreCase(constant.mutrashmari))
			{

				String shalya_ipd_status = addRecordToCentralIPD_And_DIPD_Shalyatantra_logic_part(p);

				System.out.println(shalya_ipd_status);

				return shalya_ipd_status;
			}
		}

		return "NO IPD Disease found for IPD Shalya";
	}

	public String addRecordToCentralIPD_And_DIPD_Shalakyatantra(Patient p)
	{
		if((p.getAge()>50) && (p.getAge()<=70)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.terizium))
			{

				String shalakya_ipd_status = addRecordToCentralIPD_And_DIPD_Shalakyatantra_logic_part(p);
				System.out.println(shalakya_ipd_status);

				return shalakya_ipd_status;
			}
		}

		if((p.getAge()>=25) && (p.getAge()<35))
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.netradah))
			{
			}
		}

		if((p.getAge()>=35) && (p.getAge()<=50)) 
		{
			if(p.getDiagnosis().equalsIgnoreCase(constant.pterigium) ||
					p.getDiagnosis().equalsIgnoreCase(constant.dacryocystits) ||
					p.getDiagnosis().equalsIgnoreCase(constant.lagan) ||
					p.getDiagnosis().equalsIgnoreCase(constant.netradah))
			{

			}
		}

		return "No IPD Disease found for Shalakyatantra";
	}

	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------

	//					LOGIC PART OF ADDING PATIENT TO BED OCCUPANCY DEPARTMENT REGISTER

	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------



	public String addRecordToBedOccupancyTable(Central_Admit p)
	{

		bed_occupancy.setSr_no(bed_occupancy_sr_no);
		bed_occupancy_sr_no++;
		bed_occupancy.setYearly_no(bed_occupancy_yr_no);
		bed_occupancy_yr_no++;
		bed_occupancy.setRecord_no(bed_occupancy_record_no);
		bed_occupancy_record_no++;
		bed_occupancy.setNew_no(p.getNew_no());
		bed_occupancy.setOld_no(p.getOld_no());
		bed_occupancy.setName(p.getName());
		bed_occupancy.setAge(p.getAge());
		bed_occupancy.setWeight(p.getWeight());
		bed_occupancy.setAddress(p.getAddress());
		bed_occupancy.setDepartment(p.getDepartment());
		bed_occupancy.setDate(p.getDate());
		bed_occupancy.setDiagnosis(p.getDiagnosis());
		bed_occupancy.setSex(p.getSex());
		bed_occupancy.setDischarge_date(p.getDischarge_date());
		bed_occupancy.setAdmit_date(bed_occupancy.getDate());


		central_ipd.setSr_no(central_ipd_sr_no);
		central_ipd_sr_no++;
		central_ipd.setYearly_no(central_ipd_yr_no);
		central_ipd_yr_no++;
		central_ipd.setRecord_no(central_ipd_record_no);
		central_ipd_record_no++;
		central_ipd.setNew_no(p.getNew_no());
		central_ipd.setOld_no(p.getOld_no());
		central_ipd.setName(p.getName());
		central_ipd.setAge(p.getAge());
		central_ipd.setWeight(p.getWeight());
		central_ipd.setAddress(p.getAddress());
		central_ipd.setDepartment(p.getDepartment());
		central_ipd.setDate(p.getDate());
		central_ipd.setDiagnosis(p.getDiagnosis());
		central_ipd.setSex(p.getSex());
		central_ipd.setDischarge_date(p.getDischarge_date());
		central_ipd.setAdmit_date(central_ipd.getDate());

		LocalDate changeable = p.getDate();
		LocalDate discharge_date_minus_one_backup = p.getDischarge_date().minusDays(1);

		if(discharge_date_minus_one_backup.isAfter(changeable))
		{
			while(true)
			{
				if(changeable.equals(discharge_date_minus_one_backup))
				{
					//--------------------------------------------------------------------------
					// here for Bed Occupancy Register patient record must remain a day before
					// his Discharge Date so as patient record comes to date discharge_date_minus_one 
					// we are setting discharge date and saving that record so that discharge date will be 
					// saved in database for a day before Discharge Date and from admit to discharge_date_minus_two
					// it will be null value

					bed_occupancy.setDischarge_date(discharge_date_minus_one_backup.plusDays(1));
					bed_occupancy_repos.save(bed_occupancy);


					//-----------------------------------------------------------------------	

					// here for Admit Discharge Register we need patient record till discharge date
					// and our loop is going till discharge_date_minus_one so we need to add record one more time
					// so for discharge_date_minus_one day we saved record here for once and then for 
					// final discharge date we are setting date, yearly no. and discharge date
					// so discharge date will be visible for final day only

					central_ipdRepos.save(central_ipd);

					changeable = changeable.plusDays(1);
					central_ipd.setDate(changeable);
					central_ipd.setDischarge_date(discharge_date_minus_one_backup.plusDays(1));
					central_ipd.setYearly_no(central_ipd_yr_no);
					central_ipd_yr_no++;
					central_ipd.setRecord_no(central_ipd_record_no);
					central_ipd_record_no++;
					central_ipdRepos.save(central_ipd);

					break;
				}
				else
				{
					bed_occupancy.setDischarge_date(null);

					bed_occupancy_repos.save(bed_occupancy);

					changeable = changeable.plusDays(1);

					bed_occupancy.setYearly_no(bed_occupancy_yr_no);
					bed_occupancy_yr_no++;
					bed_occupancy.setRecord_no(bed_occupancy_record_no);
					bed_occupancy_record_no++;

					bed_occupancy.setDate(changeable);

					//--------------------------------------------------------------------------

					central_ipd.setDischarge_date(null);
					central_ipdRepos.save(central_ipd);

					central_ipd.setYearly_no(central_ipd_yr_no);
					central_ipd_yr_no++;
					central_ipd.setRecord_no(central_ipd_record_no);
					central_ipd_record_no++;
					central_ipd.setDate(changeable);

					System.out.println("while Loop");
				}
			}

		}
		else
		{
			return null;
		}


		return null;
	}




	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------

	//					LOGIC PART OF ADDING PATIENT TO IPD DEPARTMENT AND CENTRAL ADMIT REGISTER

	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------


	public String addRecordToCentralIPD_And_DIPD_kaya_logic_part(Patient p)
	{
		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		LocalDate ld = p.getDate();


		//-------------------------------------------- Add record to Kayachikitsa Departmental IPD

		if(ipd_kaya_backup_date.getYear()==ld.getYear())
		{
			ipd_kaya_yr_no++;
			ipd_kaya.setYearly_no(ipd_kaya_yr_no);
		}
		else
		{
			ipd_kaya_yr_no = 1;
			ipd_kaya.setYearly_no(ipd_kaya_yr_no);
		}

		if(ipd_kaya_backup_date.isEqual(ld)) {				
			ipd_kaya_sr_no++;								
			ipd_kaya.setSr_no(ipd_kaya_sr_no);				
		}
		else
		{
			ipd_kaya_backup_date = ld;
			ipd_kaya_sr_no = 1;
			ipd_kaya.setSr_no(ipd_kaya_sr_no);
		}


		ipd_kaya.setRecord_no(ipd_kaya_record_no);			 
		ipd_kaya_record_no++;								

		ipd_kaya.setNew_no(p.getNew_no());
		ipd_kaya.setOld_no(p.getOld_no());

		ipd_kaya.setName(p.getName());
		ipd_kaya.setAge(p.getAge());

		ipd_kaya.setAddress(p.getAddress());
		ipd_kaya.setDepartment(p.getDepartment());

		ipd_kaya.setDate(p.getDate());
		ipd_kaya.setDiagnosis(p.getDiagnosis());

		ipd_kaya.setSex(p.getSex());
		ipd_kaya.setDischarge_date(p.getDischarge_date());

		ipd_kaya.setWeight(p.getWeight());

		ipd_kayaRepo.save(ipd_kaya);

		//------------------------------------------------------- Add record to Central IPD

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}


		if(central_admit_backup_date.isEqual(ld)) {				// checking each date to maintain sr_no
			central_admit_Sr_count++;								// if new date comes then sr_no should start with 1 again
			central_admit.setSr_no(central_admit_Sr_count);				// else sr_no must increase by 1;
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setRecord_no(central_admit_record_no);
		central_admit_record_no++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());
		central_admitRepo.save(central_admit);

		String bed_occupancy_status = addRecordToBedOccupancyTable(central_admit);
		System.out.println("Bed Occupancy Status : "+bed_occupancy_status);

		return "Record Added to CIPD and kaya IPD";
	}


	public String addRecordToCentralIPD_And_DIPD_Panchkarma_logic_part(Patient p)
	{
		LocalDate ld = p.getDate();

		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		//------------------------------------------------------- Add record to Panchakarma Departmental IPD

		if(ipd_panch_backup_date.getYear()==ld.getYear())
		{
			ipd_panch_yr_no++;
			ipd_panch.setYearly_no(ipd_panch_yr_no);
		}
		else
		{
			ipd_panch_yr_no = 1;
			ipd_panch.setYearly_no(ipd_panch_yr_no);
		}

		if(ipd_panch_backup_date.isEqual(ld)) {
			ipd_panch_sr_no++;
			ipd_panch.setSr_no(ipd_panch_sr_no);
		}
		else
		{
			ipd_panch_backup_date = ld;
			ipd_panch_sr_no = 1;
			ipd_panch.setSr_no(ipd_panch_sr_no);
		}


		ipd_panch.setRecord_no(ipd_panch_record_no);
		ipd_panch_record_no++;

		ipd_panch.setNew_no(p.getNew_no());
		ipd_panch.setOld_no(p.getOld_no());

		ipd_panch.setName(p.getName());
		ipd_panch.setAge(p.getAge());

		ipd_panch.setAddress(p.getAddress());
		ipd_panch.setDepartment(p.getDepartment());

		ipd_panch.setDate(p.getDate());
		ipd_panch.setDiagnosis(p.getDiagnosis());

		ipd_panch.setSex(p.getSex());
		ipd_panch.setDischarge_date(p.getDischarge_date());

		ipd_panch.setWeight(p.getWeight());

		ipd_panchRepo.save(ipd_panch);

		//------------------------------------------------------- Add record to Central IPD Admit

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}

		if(central_admit_backup_date.isEqual(ld)) {				
			central_admit_Sr_count++;								
			//			opd_kaya.setSr_no(central_admit_Sr_count);
			central_admit.setSr_no(central_admit_Sr_count);
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setRecord_no(central_admit_record_no);
		central_admit_record_no++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());
		central_admitRepo.save(central_admit);

		String bed_occupancy_status = addRecordToBedOccupancyTable(central_admit);
		System.out.println("Bed Occupancy Status : "+bed_occupancy_status);

		return "Record Added to CIPD and Panchkarma IPD";
	}


	public String addRecordToCentralIPD_And_DIPD_Striroga_logic_part(Patient p)

	{
		LocalDate ld =p.getDate();

		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		//------------------------------------------------------- Add record to Striroga Departmental IPD

		if(ipd_stri_backup_date.getYear()==ld.getYear())
		{
			ipd_stri_yr_no++;
			ipd_stri.setYearly_no(ipd_stri_yr_no);
		}
		else
		{
			ipd_stri_yr_no = 1;
			ipd_stri.setYearly_no(ipd_stri_yr_no);
		}

		if(ipd_stri_backup_date.isEqual(ld)) {
			ipd_stri_sr_no++;
			ipd_stri.setSr_no(ipd_stri_sr_no);
		}
		else
		{
			ipd_stri_backup_date = ld;
			ipd_stri_sr_no = 1;
			ipd_stri.setSr_no(ipd_stri_sr_no);
		}


		ipd_stri.setRecord_no(ipd_stri_record_no);
		ipd_stri_record_no++;

		ipd_stri.setNew_no(p.getNew_no());
		ipd_stri.setOld_no(p.getOld_no());

		ipd_stri.setName(p.getName());
		ipd_stri.setAge(p.getAge());

		ipd_stri.setAddress(p.getAddress());
		ipd_stri.setDepartment(p.getDepartment());

		ipd_stri.setDate(p.getDate());
		ipd_stri.setDiagnosis(p.getDiagnosis());

		ipd_stri.setSex(p.getSex());
		ipd_stri.setDischarge_date(p.getDischarge_date());

		ipd_stri.setWeight(p.getWeight());

		ipd_striRepo.save(ipd_stri);


		//------------------------------------------------------- Add record to Central IPD

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}


		if(central_admit_backup_date.isEqual(ld)) {				
			central_admit_Sr_count++;								
			central_admit.setSr_no(central_admit_Sr_count);				
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setRecord_no(central_admit_record_no);
		central_admit_record_no++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());
		central_admitRepo.save(central_admit);

		String bed_occupancy_status = addRecordToBedOccupancyTable(central_admit);
		System.out.println("Bed Occupancy Status : "+bed_occupancy_status);

		return "patient added in strirog-prasuti";
	}


	public String addRecordToCentralIPD_And_DIPD_Balroga_logic_part(Patient p)
	{
		LocalDate ld = p.getDate();

		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		//------------------------------------------------------- Add record to Balroga Departmental IPD

		if(ipd_bal_backup_date.getYear()==ld.getYear())
		{
			ipd_bal_yr_no++;
			ipd_bal.setYearly_no(ipd_bal_yr_no);
		}
		else
		{
			ipd_bal_yr_no = 1;
			ipd_bal.setYearly_no(ipd_bal_yr_no);
		}

		if(ipd_bal_backup_date.isEqual(ld)) {
			ipd_bal_sr_no++;
			ipd_bal.setSr_no(ipd_bal_sr_no);
		}
		else
		{
			ipd_bal_backup_date = ld;
			ipd_bal_sr_no = 1;
			ipd_bal.setSr_no(ipd_bal_sr_no);
		}

		ipd_bal.setRecord_no(ipd_bal_record_no);
		ipd_bal_record_no++;

		ipd_bal.setNew_no(p.getNew_no());
		ipd_bal.setOld_no(p.getOld_no());

		ipd_bal.setName(p.getName());
		ipd_bal.setAge(p.getAge());

		ipd_bal.setAddress(p.getAddress());
		ipd_bal.setDepartment(p.getDepartment());

		ipd_bal.setDate(p.getDate());
		ipd_bal.setDiagnosis(p.getDiagnosis());

		ipd_bal.setSex(p.getSex());
		ipd_bal.setDischarge_date(p.getDischarge_date());

		ipd_bal.setWeight(p.getWeight());

		ipd_balRepo.save(ipd_bal);


		//------------------------------------------------------- Add record to Central IPD

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}

		if(central_admit_backup_date.isEqual(ld)) {				
			central_admit_Sr_count++;								
			central_admit.setSr_no(central_admit_Sr_count);				
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setRecord_no(central_admit_record_no);
		central_admit_record_no++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());
		central_admitRepo.save(central_admit);

		String bed_occupancy_status = addRecordToBedOccupancyTable(central_admit);
		System.out.println("Bed Occupancy Status : "+bed_occupancy_status);

		return "patient added in Balrog IPD";

	}


	public String addRecordToCentralIPD_And_DIPD_Shalyatantra_logic_part(Patient p)
	{
		LocalDate ld = p.getDate();

		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		//------------------------------------------------------- Add record to Shalyatantra Departmental IPD

		if(ipd_shalya_backup_date.getYear()==ld.getYear())
		{
			ipd_shalya_yr_no++;
			ipd_shalya.setYearly_no(ipd_shalya_yr_no);
		}
		else
		{
			ipd_shalya_yr_no = 1;
			ipd_shalya.setYearly_no(ipd_shalya_yr_no);
		}

		if(ipd_shalya_backup_date.isEqual(ld)) {
			ipd_shalya_sr_no++;
			ipd_shalya.setSr_no(ipd_shalya_sr_no);
		}
		else
		{
			ipd_shalya_backup_date = ld;
			ipd_shalya_sr_no = 1;
			ipd_shalya.setSr_no(ipd_shalya_sr_no);
		}

		ipd_shalya.setRecord_no(ipd_shalya_record_no);
		ipd_shalya_record_no++;

		ipd_shalya.setNew_no(p.getNew_no());
		ipd_shalya.setOld_no(p.getOld_no());

		ipd_shalya.setName(p.getName());
		ipd_shalya.setAge(p.getAge());

		ipd_shalya.setAddress(p.getAddress());
		ipd_shalya.setDepartment(p.getDepartment());

		ipd_shalya.setDate(p.getDate());
		ipd_shalya.setDiagnosis(p.getDiagnosis());

		ipd_shalya.setSex(p.getSex());
		ipd_shalya.setDischarge_date(p.getDischarge_date());

		ipd_shalya.setWeight(p.getWeight());

		ipd_shalyaRepo.save(ipd_shalya);


		//------------------------------------------------------- Add record to Central IPD

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}

		if(central_admit_backup_date.isEqual(ld)) {				
			central_admit_Sr_count++;								
			central_admit.setSr_no(central_admit_Sr_count);				
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setRecord_no(central_admit_record_no);
		central_admit_record_no++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());

		central_admitRepo.save(central_admit);

		String bed_occupancy_status = addRecordToBedOccupancyTable(central_admit);
		System.out.println("Bed Occupancy Status : "+bed_occupancy_status);

		return "IPD patient added in shalya";

	}


	public String addRecordToCentralIPD_And_DIPD_Shalakyatantra_logic_part(Patient p)
	{
		LocalDate ld = p.getDate();

		System.out.println(p.getDepartment()+"  "+p.getAge()+"  "+p.getDiagnosis()+"      added");

		//------------------------------------------------------- Add record to Shalakya Departmental IPD

		if(ipd_shalakya_backup_date.getYear()==ld.getYear())
		{
			ipd_shalakya_yr_no++;
			ipd_shalakya.setYearly_no(ipd_shalakya_yr_no);
		}
		else
		{
			ipd_shalakya_yr_no = 1;
			ipd_shalakya.setYearly_no(ipd_shalakya_yr_no);
		}

		if(ipd_shalakya_backup_date.isEqual(ld)) {
			ipd_shalakya_sr_no++;
			ipd_shalakya.setSr_no(ipd_shalakya_sr_no);
		}
		else
		{
			ipd_shalakya_backup_date = ld;
			ipd_shalakya_sr_no = 1;
			ipd_shalakya.setSr_no(ipd_shalakya_sr_no);
		}


		ipd_shalakya.setRecord_no(ipd_shalakya_record_no);
		ipd_shalakya_record_no++;

		ipd_shalakya.setNew_no(p.getNew_no());
		ipd_shalakya.setOld_no(p.getOld_no());

		ipd_shalakya.setName(p.getName());
		ipd_shalakya.setAge(p.getAge());

		ipd_shalakya.setAddress(p.getAddress());
		ipd_shalakya.setDepartment(p.getDepartment());

		ipd_shalakya.setDate(p.getDate());
		ipd_shalakya.setDiagnosis(p.getDiagnosis());

		ipd_shalakya.setSex(p.getSex());
		ipd_shalakya.setDischarge_date(p.getDischarge_date());

		ipd_shalakya.setWeight(p.getWeight());

		ipd_shalakyaRepo.save(ipd_shalakya);



		//------------------------------------------------------- Add record to Central IPD

		if(central_admit_backup_date.getYear()==ld.getYear())
		{
			central_admit_yr_count++;
			central_admit.setYearly_no(central_admit_yr_count);
		}
		else
		{
			central_admit_yr_count = 1;
			central_admit.setYearly_no(central_admit_yr_count);
		}

		if(central_admit_backup_date.isEqual(ld)) {				
			central_admit_Sr_count++;								
			central_admit.setSr_no(central_admit_Sr_count);				
		}
		else
		{
			central_admit_backup_date = ld;
			central_admit_Sr_count = 1;
			central_admit.setSr_no(central_admit_Sr_count);
		}

		central_admit.setSr_no(central_admit_Sr_count);
		central_admit.setYearly_no(central_admit_yr_count);
		central_admit_yr_count++;
		central_admit.setNew_no(p.getNew_no());
		central_admit.setOld_no(p.getOld_no());
		central_admit.setName(p.getName());
		central_admit.setAge(p.getAge());
		central_admit.setWeight(p.getWeight());
		central_admit.setAddress(p.getAddress());
		central_admit.setDepartment(p.getDepartment());
		central_admit.setDate(p.getDate());
		central_admit.setDiagnosis(p.getDiagnosis());
		central_admit.setSex(p.getSex());
		central_admit.setDischarge_date(p.getDischarge_date());
		central_admitRepo.save(central_admit);

		return "patient added in Shalakya";

	}




	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------

	//					LOGIC PART OF ADDING RECORD FOR OPD CASE PAPER

	//	-----------------------------------------------------------------------------------------------------
	//	-----------------------------------------------------------------------------------------------------



	public String addRecordToOPDCasePaper_Kayachiktsa_Department()
	{

		// ---------------------------------------------------------- ATISAR

		opd_disease_medicine_case_paper.setSr_no(1);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.atisar);
		opd_disease_medicine_case_paper.setRx1(constant.SHANKH_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.KUTAJ_GHANA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.SHUNTHI_CHURN_1_2tp_1_2tp_x_5D);
		opd_disease_medicine_case_paper.setMicrobiological(constant.STOOL_R_M);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(1);
		opd_disease_symptoms_improvement.setSymptoms(constant.dravmal_Pravrutti_Dourballya);
		opd_disease_symptoms_improvement.setVyadhiName(constant.atisar);
		opd_disease_symptoms_improvement.setC_o(constant.ATISAR_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- JWAR

		opd_disease_medicine_case_paper.setSr_no(2);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.jwar);
		opd_disease_medicine_case_paper.setRx1(constant.SAMSHAMANI_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TRIBHUVAN_KIRTI_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setHematological(constant.CBC_ESR_M_P);
		opd_disease_medicine_case_paper.setMicrobiological(constant.URINE_R_M);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(2);
		opd_disease_symptoms_improvement.setSymptoms(constant.jwar);
		opd_disease_symptoms_improvement.setVyadhiName(constant.jwar);
		opd_disease_symptoms_improvement.setC_o(constant.JWAR_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- KASA

		opd_disease_medicine_case_paper.setSr_no(3);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.kasa);

		opd_disease_medicine_case_paper.setRx1(constant.SITOPALADI_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.LAVANGADI_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.YASHTIMADHU_CHURNA_1g_1g_x_5D);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(3);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.kasa);

		opd_disease_symptoms_improvement.setSymptoms(constant.kas_aani_kanfyuktashtivanam);
		opd_disease_symptoms_improvement.setC_o(constant.KASA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- SARVANGA KANDU

		opd_disease_medicine_case_paper.setSr_no(4);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SARVANGA_KANDU);
		opd_disease_medicine_case_paper.setRx1(constant.NISHOTAR_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.AROGYAVARDHINI_VATI_125mg_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.GANDHAK_RASAYAN_VATI_125mg_2_2_x_5D);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(4);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SARVANGA_KANDU);
		opd_disease_symptoms_improvement.setC_o(constant.SARVANGA_KANDU_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- gradhrasi

		opd_disease_medicine_case_paper.setSr_no(5);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.gradhrasi);

		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MAHAVATVIDHWANSA_RAS_2_2
				+ constant.x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.GANDHAK_RASAYAN_VATI_2_2
				+ constant.x_5D);
		opd_disease_medicine_case_paper.setX_ray(constant.X_RAY_L_S_SPINE);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(5);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.gradhrasi);

		opd_disease_symptoms_improvement.setSymptoms("िकती ते पादोगुली पय􀀰त संचारी वेदना, सकाळी च􀀆मण , िप􀁂􀁃लीका\r\n"
				+ "संचारण, किटशूल");
		opd_disease_symptoms_improvement.setC_o(constant.gradhrasi_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- SHWAS

		opd_disease_medicine_case_paper.setSr_no(6);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SHWAS);

		opd_disease_medicine_case_paper.setRx1(constant.SHWAS_KUTHAR_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MTRIBHUVAN_KIRTI_VATI_2_2_x_5D
				+ constant.x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.NEBULIZATION_Budacort_TDS_x_2_D);
		opd_disease_medicine_case_paper.setX_ray(constant.X_RAY_CHEST_PA_View);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(6);
		opd_disease_symptoms_improvement.setDepartment("constant.kayachikitsa");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SHWAS);

		opd_disease_symptoms_improvement.setSymptoms("􀁉ास 􀁊ायला 􀀨ास, चालतांना दम लागतो, छातीत दुखावा");
		opd_disease_symptoms_improvement.setC_o(constant.SHWAS_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- STHOULYA

		opd_disease_medicine_case_paper.setSr_no(7);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.sthoulya);

		opd_disease_medicine_case_paper.setRx1(constant.AROGYAVARDHINI_VATI_2_2x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.NISHOTTAR_CHURNA_0_3g_x_5D);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(7);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.sthoulya);

		opd_disease_symptoms_improvement.setSymptoms("􀁀थूलता, आितभर उ. जानुसंधीशूल, संक􀀪 ि􀀆या");
		opd_disease_symptoms_improvement.setC_o(constant.Sthoullya_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- SANDHIVAT

		opd_disease_medicine_case_paper.setSr_no(8);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SANDHIVAT);

		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(8);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.SANDHIVAT);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.SANDHIVAT_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- JANUSHUL

		opd_disease_medicine_case_paper.setSr_no(9);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.janushul);

		opd_disease_medicine_case_paper.setRx1(constant.TRAYODASHANGA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.ASHWAGANDA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.ULTRA_SOUND_EXERSIZE_PH);
		opd_disease_medicine_case_paper.setX_ray(constant.X_RAY_BIL_KNEE_Jt);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(9);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.janushul);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.janushul_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- AAMVAT

		opd_disease_medicine_case_paper.setSr_no(10);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.aamvat);

		opd_disease_medicine_case_paper.setRx1(constant.SIMHANAD_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.PUNARNAVADI_GUGGUL_250mg_2_2_x_5D);
		opd_disease_medicine_case_paper.setSerological(constant.RA_Test);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(10);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.aamvat);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.AAMVAT_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- VATRAKTA

		opd_disease_medicine_case_paper.setSr_no(11);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.vatrakta);

		opd_disease_medicine_case_paper.setRx1(constant.AMRUTA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GOKSHURADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.KAISHOR_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setSerological(constant.RA_Test);
		opd_disease_medicine_case_paper.setBiochemical(constant.S_Uric_Acid);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(11);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.vatrakta);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.VATRAKTA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- AJIRNA

		opd_disease_medicine_case_paper.setSr_no(12);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.Ajirna);

		opd_disease_medicine_case_paper.setRx1(constant.HINGVASHTAK_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx3("PIPPALI CHURNA  1g-----1g x 5D");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(12);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.Ajirna);

		opd_disease_symptoms_improvement.setSymptoms(constant.Aankujan_Dravmul_Pravrutti);
		opd_disease_symptoms_improvement.setC_o(constant.AJIRNA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- GRAHANI

		opd_disease_medicine_case_paper.setSr_no(13);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.grahani);

		opd_disease_medicine_case_paper.setRx1(constant.PRAVAL_PANCHAMRUT_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("KUTAJ GHANA VATI 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx3(constant.AMALAKI_CHURNA_1g_1g_x_5D);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(13);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.grahani);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.GRAHANI_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- PRAMEHA

		opd_disease_medicine_case_paper.setSr_no(14);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.PRAMEHA);

		opd_disease_medicine_case_paper.setRx1(constant.GUDMAR_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setSerological(constant.LIPID_PROFILE);
		opd_disease_medicine_case_paper.setBiochemical(constant.BSL_F_PP);
		opd_disease_medicine_case_paper.setMicrobiological(constant.URINE_R_M);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(14);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.PRAMEHA);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.PRAMEHA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- AMLAPITTA

		opd_disease_medicine_case_paper.setSr_no(15);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.aamlapitta);

		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.PRAVAL_PANCHAMRUT_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(15);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.aamlapitta);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.AMLAPITTA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- AGNIMANDYA

		opd_disease_medicine_case_paper.setSr_no(16);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.AGNIMANDYA);

		opd_disease_medicine_case_paper.setRx1(constant.HINGVASHTAK_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(16);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.AGNIMANDYA);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o(constant.AGNIMANDYA_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- MALAVASHTAMBHA

		opd_disease_medicine_case_paper.setSr_no(17);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.MALAVASHTAMBHA);

		opd_disease_medicine_case_paper.setRx1(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx2("AVIPATTIKAR CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3(constant.AMALAKI_CHURNA_2g_2g_x_5D);

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(17);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName(constant.MALAVASHTAMBHA);

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o("MALAVASHTAMBHA-KC");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- ANIDRA

		opd_disease_medicine_case_paper.setSr_no(18);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName("ANIDRA");

		opd_disease_medicine_case_paper.setRx1("SARPAGANDHA CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx2("BALA POWDER 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(18);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName("ANIDRA");

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o("ANIDRA-KC");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- BRAMA

		opd_disease_medicine_case_paper.setSr_no(19);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName("BRAMA");

		opd_disease_medicine_case_paper.setRx1("SUTSHEKAR RAS VATI 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx2("KHADIR CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//------------------------------------------------

		opd_disease_symptoms_improvement.setSr_no(19);
		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setVyadhiName("BRAMA");

		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setC_o("BRAMA-KC");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);

		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- parshnirshul

		opd_disease_medicine_case_paper.setSr_no(20);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.parshnirshul);
		opd_disease_medicine_case_paper.setRx1(constant.SHANKH_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.KUTAJ_GHANA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper.setX_ray("X-RAY-BIL-ANKLE Jt. A.P./LATERAL");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(20);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("parshnirshul");
		opd_disease_symptoms_improvement.setC_o("parshnirshul-KC");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- Pandu

		opd_disease_medicine_case_paper.setSr_no(20);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.pandu);
		opd_disease_medicine_case_paper.setRx1(constant.SYP_VITCOFOL_5ML);
		opd_disease_medicine_case_paper.setRx2(constant.TAAPYADI_LOH_250MG);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper.setHematological(constant.CBC_ESR);
		opd_disease_medicine_case_paper.setBiochemical("S.CREATININE");


		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(20);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.pandu);
		opd_disease_symptoms_improvement.setC_o(constant.pandu_kc);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- Katigatvat

		opd_disease_medicine_case_paper.setSr_no(21);
		opd_disease_medicine_case_paper.setDepartment(constant.kayachikitsa);
		opd_disease_medicine_case_paper.setVyadhiName(constant.katigatvat);
		opd_disease_medicine_case_paper.setRx1(constant.SYP_VITCOFOL_5ML);
		opd_disease_medicine_case_paper.setRx2(constant.TAAPYADI_LOH_250MG_X_15D);
		opd_disease_medicine_case_paper.setRx3("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.kayachikitsa);
		opd_disease_symptoms_improvement.setSr_no(21);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.katigatvat);
		opd_disease_symptoms_improvement.setC_o(constant.Katigatvat_KC);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		return "kayachikitsa record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Panchakarma_Department()
	{

		// ---------------------------------------------------------- JANUVAT

		opd_disease_medicine_case_paper.setSr_no(41);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.januvat);
		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MANJISHTHA_CHURNA_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.VACHA_CHURNA_10g_for_lepan_JANUSANDHI);
		opd_disease_medicine_case_paper.setOther(constant.JANU_BASTI_Mahanarayan_Tel);

		opd_disease_medicine_case_paper.setX_ray(constant.X_RAY_BIL_KNEE_Jt);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(41);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.januvat);
		opd_disease_symptoms_improvement.setC_o(constant.JANUVAT_PK);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- MALABANDHA

		opd_disease_medicine_case_paper.setSr_no(42);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.malabandha);
		opd_disease_medicine_case_paper.setRx1(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.AVIPATIKAR_CHURNA_1g_1g_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setBasti(constant.til_tail_basti);
		opd_disease_medicine_case_paper.setOther("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(42);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.malabandha);
		opd_disease_symptoms_improvement.setC_o(constant.MALABANDHA_PK);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- VATRAKTA

		opd_disease_medicine_case_paper.setSr_no(43);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.vatrakta);
		opd_disease_medicine_case_paper.setRx1(constant.AMRUTA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.DARUHARIDRA_CHURNA_2g_2g_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan(constant.SNEHAN_SARVANGA_BALA_TEL);
		opd_disease_medicine_case_paper.setSerological(constant.RA_Test);
		opd_disease_medicine_case_paper.setBiochemical(constant.S_Uric_Acid);
		opd_disease_medicine_case_paper.setRaktmokshan(constant.SIRAVEDH);
		opd_disease_medicine_case_paper.setSerological(constant.RA_Test);
		opd_disease_medicine_case_paper.setBiochemical(constant.S_Uric_Acid);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(43);
		opd_disease_symptoms_improvement.setSymptoms("vam pad shodh pad shul");
		opd_disease_symptoms_improvement.setVyadhiName(constant.vatrakta);
		opd_disease_symptoms_improvement.setC_o("VATRAKTA-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- SANDHIVAT

		opd_disease_medicine_case_paper.setSr_no(44);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SANDHIVAT);
		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MAHAVATVIDHWANSA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.ERANDAMOOL_CHURNA_3g_3g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(44);
		opd_disease_symptoms_improvement.setSymptoms("sandhishool kriyaalpata malavirodh 6-8 months");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SANDHIVAT);
		opd_disease_symptoms_improvement.setC_o(constant.SANDHIVAT_PK);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- STHOULYA

		opd_disease_medicine_case_paper.setSr_no(45);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.sthoulya);
		opd_disease_medicine_case_paper.setRx1("AROGYAVARDHINI 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx3("MEDOHAR VATI 2-----2 x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(45);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.sthoulya);
		opd_disease_symptoms_improvement.setC_o("STHOULYA-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- AAMVAT 

		opd_disease_medicine_case_paper.setSr_no(46);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.aamvat);
		opd_disease_medicine_case_paper.setRx1(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SIMHANAD_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("CHITRAK CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(46);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.aamvat);
		opd_disease_symptoms_improvement.setC_o("AAMVAT-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- ANIDRA 

		opd_disease_medicine_case_paper.setSr_no(47);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName("ANIDRA");
		opd_disease_medicine_case_paper.setRx1("SARPAGANDHA CHURNA 1g----1g x 5D");
		opd_disease_medicine_case_paper.setRx2("MANJISHTHA CHURNA 2g-----2g x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(47);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("ANIDRA");
		opd_disease_symptoms_improvement.setC_o("ANIDRA-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- PRUSHTHAVAT 

		opd_disease_medicine_case_paper.setSr_no(48);
		opd_disease_medicine_case_paper.setDepartment("akarma");
		opd_disease_medicine_case_paper.setVyadhiName("PRUSHTHAVAT");
		opd_disease_medicine_case_paper.setRx1(constant.DASHAMOOL_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MAHAVATVIDHWANSA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(48);
		opd_disease_symptoms_improvement.setSymptoms("Prushtha Shul sankashtha Kriya 2 - 3 months Pasun");
		opd_disease_symptoms_improvement.setVyadhiName("PRUSHTHAVAT");
		opd_disease_symptoms_improvement.setC_o("PRUSHTHAVAT-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- KATIVAT 

		opd_disease_medicine_case_paper.setSr_no(49);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName("KATIVAT");
		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MAHAVATVIDHWANSA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("KATI BASTI(Dhanwantar Tel)");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(49);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("KATIVAT");
		opd_disease_symptoms_improvement.setC_o("KATIVAT-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- KARSHYA 

		opd_disease_medicine_case_paper.setSr_no(50);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName("KARSHYA");
		opd_disease_medicine_case_paper.setRx1("SHATAVARI CHURNA 1g----1g x 5D");
		opd_disease_medicine_case_paper.setRx2("LAGHU MALINI VASANT 2-----2 x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("SNEHAN-SARVANGA( Bala Tel)");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(50);
		opd_disease_symptoms_improvement.setSymptoms("krushata angamard kriyaalpata");
		opd_disease_symptoms_improvement.setVyadhiName("KARSHYA");
		opd_disease_symptoms_improvement.setC_o("KARSHYA-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- SARVANGMARDA 

		opd_disease_medicine_case_paper.setSr_no(51);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName("SARVANGMARDA");
		opd_disease_medicine_case_paper.setRx1(constant.SIMHANAD_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(51);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("SARVANGMARDA");
		opd_disease_symptoms_improvement.setC_o("SARVANGMARDA-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- NIDRANASH 

		opd_disease_medicine_case_paper.setSr_no(52);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.NIDRANASH);
		opd_disease_medicine_case_paper.setRx1(constant.SARPAGANDA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2("BALA POWDER 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("LAGHU SUTSHEKAR VATI 2-----2 x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("SHIRODHARA(Brahmi Tel)"); 

		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(52);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.NIDRANASH);
		opd_disease_symptoms_improvement.setC_o("NIDRANASH-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- MANYAGATVAT 

		opd_disease_medicine_case_paper.setSr_no(53);
		opd_disease_medicine_case_paper.setDepartment(constant.panchakarma);
		opd_disease_medicine_case_paper.setVyadhiName(constant.manyagatvat);
		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("SARVANGA SNEHAN(Sahachar Tel)");
		opd_disease_medicine_case_paper.setSwedan("SARVANGA PETI SWEDAN");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti("KAL BASTI KRAM"); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.panchakarma);
		opd_disease_symptoms_improvement.setSr_no(53);
		opd_disease_symptoms_improvement.setSymptoms("Sakashtha Mal Pravrutti malavsthambh udarshool");
		opd_disease_symptoms_improvement.setVyadhiName(constant.manyagatvat);
		opd_disease_symptoms_improvement.setC_o("MANYAGATVAT-PK");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		return "Panchakarma record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Strirog_Department()
	{

		// ---------------------------------------------------------- YONIDAHA 

		opd_disease_medicine_case_paper.setSr_no(61);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("YONIDAH");
		opd_disease_medicine_case_paper.setRx1(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("CHOPCHINI CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("NAGA GUTI 2-----2 x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(61);
		opd_disease_symptoms_improvement.setSymptoms("Sakashtha Mal Pravrutti malavsthambh udarshool");
		opd_disease_symptoms_improvement.setVyadhiName("YONIDAH");
		opd_disease_symptoms_improvement.setC_o("YONIDAH-SR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- RAKTPRADAR 

		opd_disease_medicine_case_paper.setSr_no(62);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("RAKTPRADAR");
		opd_disease_medicine_case_paper.setRx1("");
		opd_disease_medicine_case_paper.setRx2("");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(62);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("RAKTPRADAR");
		opd_disease_symptoms_improvement.setC_o("RAKTPRADAR-SR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- GRANTYARTAV

		opd_disease_medicine_case_paper.setSr_no(63);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("GRANTYARTAV");
		opd_disease_medicine_case_paper.setRx1("TRIKATU CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx2("CHANDRAPRABHA 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(63);
		opd_disease_symptoms_improvement.setSymptoms("Sakashtha Mal Pravrutti malavsthambh udarshool");
		opd_disease_symptoms_improvement.setVyadhiName("GRANTYARTAV");
		opd_disease_symptoms_improvement.setC_o("GRANTYARTAV");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- PCOD

		opd_disease_medicine_case_paper.setSr_no(64);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName(constant.PCOD);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("AROGYAVARDHINI GUTI 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setHematological(constant.CBC_ESR);
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setMicrobiological(constant.URINE_R_M);
		opd_disease_medicine_case_paper.setBiochemical(constant.TESTOSTIRONE_SHBG_AMH_FSH_LH_ESTROGEN_THYROID_PROFILE);
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(64);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.PCOD);
		opd_disease_symptoms_improvement.setC_o(constant.PCOD);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- STANYAKSHYA

		opd_disease_medicine_case_paper.setSr_no(65);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("STANYAKSHYA");
		opd_disease_medicine_case_paper.setRx1("SHATAVARI CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx2("BALA POWDER 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(65);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("STANYAKSHYA");
		opd_disease_symptoms_improvement.setC_o(constant.PCOD);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- RAJONIVRUTTI

		opd_disease_medicine_case_paper.setSr_no(66);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("RAJONIVRUTTI");
		opd_disease_medicine_case_paper.setRx1(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TAPYADI_LOHA_GUTI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("PIPPALI CHURNA 500mg-----500mg x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(66);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("RAJONIVRUTTI");
		opd_disease_symptoms_improvement.setC_o("RAJONIVRUTTI-SR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- KASHTARTAV

		opd_disease_medicine_case_paper.setSr_no(67);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("KASHTARTAV");
		opd_disease_medicine_case_paper.setRx1(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.AROGYAVARDHINI_VATI_2_2x_5D);
		opd_disease_medicine_case_paper.setRx3("PIPPALI CHURNA 500mg-----500mg x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(67);
		opd_disease_symptoms_improvement.setSymptoms("sakashta rajpravrutti katishul udarshool u. hastpadshul");
		opd_disease_symptoms_improvement.setVyadhiName("KASHTARTAV");
		opd_disease_symptoms_improvement.setC_o("KASHTARTAV-SR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- YONIKANDU

		opd_disease_medicine_case_paper.setSr_no(68);
		opd_disease_medicine_case_paper.setDepartment("strirog");
		opd_disease_medicine_case_paper.setVyadhiName("YONIKANDU");
		opd_disease_medicine_case_paper.setRx1("GANDHAK RASAYAN 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx2("PATOLA CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("KUTAJ GHANA VATI 2-----2 x 5D");
		opd_disease_medicine_case_paper.setOther("YONIDHAVAN- TRIPHALA KWATH + YONIPICHU -NEEMTRIPHALA TEL");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment("strirog");
		opd_disease_symptoms_improvement.setSr_no(68);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("YONIKANDU");
		opd_disease_symptoms_improvement.setC_o("YONIKANDU-SR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		return "Strirog record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Balrog_Department()
	{

		// ---------------------------------------------------------- PRATISHYAYA

		opd_disease_medicine_case_paper.setSr_no(81);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("PRATISHYAYA");
		opd_disease_medicine_case_paper.setRx1("SITOPALADI CHURNA 1g------1g x 5D");
		opd_disease_medicine_case_paper.setRx2(constant.TRIBHUVAN_KIRTI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("VACHA CHURNA 10g (for Lepan on Forhead)");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(81);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("PRATISHYAYA");
		opd_disease_symptoms_improvement.setC_o("PRATISHYAYA-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- ATISAR

		opd_disease_medicine_case_paper.setSr_no(82);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("ATISAR");
		opd_disease_medicine_case_paper.setRx1(constant.SHANKHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("KUTAJ GHANA VATI 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx3("SUNTHI POWDER 125mg----125mg x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(82);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("ATISAR");
		opd_disease_symptoms_improvement.setC_o("ATISAR-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- SHAYYA MUTRA

		opd_disease_medicine_case_paper.setSr_no(83);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("SHAYYA MUTRA");
		opd_disease_medicine_case_paper.setRx1(constant.KRUMI_KUTHAR_RAS_1_1_x_5D);
		opd_disease_medicine_case_paper.setRx2("ASHWAGANDHA CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(83);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("SHAYYA MUTRA");
		opd_disease_symptoms_improvement.setC_o("SHAYYA MUTRA-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- KARSHYA

		opd_disease_medicine_case_paper.setSr_no(84);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("KARSHYA");
		opd_disease_medicine_case_paper.setRx1("LAGHU MALINI VASANT 2----2 x 5D");
		opd_disease_medicine_case_paper.setRx2("ASHWAGANDHA CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx3("YASHTIMADHU CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(84);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("KARSHYA");
		opd_disease_symptoms_improvement.setC_o("KARSHYA-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- VISUCHIKA

		opd_disease_medicine_case_paper.setSr_no(85);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("VISUCHIKA");
		opd_disease_medicine_case_paper.setRx1("SUTSHEKAR RAS VATI?2----2 x 5D");
		opd_disease_medicine_case_paper.setRx2("HINGVASHTAK CHURNA 1g----1g x 5D");
		opd_disease_medicine_case_paper.setRx3(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(85);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("VISUCHIKA");
		opd_disease_symptoms_improvement.setC_o("VISUCHIKA-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);


		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- PANDU

		opd_disease_medicine_case_paper.setSr_no(86);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName("PANDU");
		opd_disease_medicine_case_paper.setRx1("SYP. VITCOFOL 5ml");
		opd_disease_medicine_case_paper.setRx2("TAAPYAADI LOH 250mg ------- 250mg x 15D");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(86);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName("PANDU");
		opd_disease_symptoms_improvement.setC_o("PANDU-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- UDARSHOOL

		opd_disease_medicine_case_paper.setSr_no(87);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName(constant.UDARSHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.HINGVASHTAK_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.KRUMI_KUTHAR_RAS_1_1_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(87);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.UDARSHOOL);
		opd_disease_symptoms_improvement.setC_o("UDARSHOOL-BR");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- MALAVASHTAMBHA

		opd_disease_medicine_case_paper.setSr_no(88);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName(constant.MALAVASHTAMBHA);
		opd_disease_medicine_case_paper.setRx1(constant.HINGVASHTAK_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.KRUMI_KUTHAR_RAS_1_1_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(88);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.MALAVASHTAMBHA);
		opd_disease_symptoms_improvement.setC_o(constant.MALAVASHTAMBHA_BR);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- KRUMI

		opd_disease_medicine_case_paper.setSr_no(89);
		opd_disease_medicine_case_paper.setDepartment(constant.balrog);
		opd_disease_medicine_case_paper.setVyadhiName(constant.KRUMI);
		opd_disease_medicine_case_paper.setRx1(constant.KRUMI_KUTHAR_RAS_1_1_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.HINGVASHTAK_CHURNA_250mg_250mg_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.balrog);
		opd_disease_symptoms_improvement.setSr_no(89);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.KRUMI);
		opd_disease_symptoms_improvement.setC_o(constant.KRUMI_BR);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		return "Balrog record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Shalyatantra_Department()
	{

		// ---------------------------------------------------------- GUDABRAMSHA

		opd_disease_medicine_case_paper.setSr_no(101);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.GUDABRAMSHA);
		opd_disease_medicine_case_paper.setRx1(constant.ASHWAGANDHA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.GUDA_PICHU_LAJJALU_TEL);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(constant.MATRA_BASTI_MUSHAK_SIDDHA_TEL); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(101);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.GUDABRAMSHA);
		opd_disease_symptoms_improvement.setC_o(constant.GUDABRAMSHA_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- PARIKARTIKA

		opd_disease_medicine_case_paper.setSr_no(102);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.parikartika);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.PUNARNAVADI_GUGGUL);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(102);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.parikartika);
		opd_disease_symptoms_improvement.setC_o("PARIKARTIKA-ST");
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- CHIPPA

		opd_disease_medicine_case_paper.setSr_no(103);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.CHIPPA);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.VRANAKARMA);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(103);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.CHIPPA);
		opd_disease_symptoms_improvement.setC_o(constant.CHIPPA_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- ARSHA

		opd_disease_medicine_case_paper.setSr_no(104);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.ARSHA);
		opd_disease_medicine_case_paper.setRx1(constant.NAGKESHAR_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.ARSHAKUTHAR_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.VIDANGA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(104);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.ARSHA);
		opd_disease_symptoms_improvement.setC_o(constant.ARSHA_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- BHAGANDAR

		opd_disease_medicine_case_paper.setSr_no(105);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.BHAGANDAR);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.AROGYAVARDHINI_VATI_2_2x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.VRANA_DHAVAN_Triphala_Neem_Kwath);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(105);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.BHAGANDAR);
		opd_disease_symptoms_improvement.setC_o(constant.BHAGANDAR_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- KUNAKHA

		opd_disease_medicine_case_paper.setSr_no(106);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.KUNAKHA);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.VRANAKARMA);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara(""); 
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(106);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.KUNAKHA);
		opd_disease_symptoms_improvement.setC_o(constant.KUNAKHA_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- VRUSHANAVAT

		opd_disease_medicine_case_paper.setSr_no(107);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.VRUSHANAVAT);
		opd_disease_medicine_case_paper.setRx1(constant.YOGARAJ_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.ERANDAMOOL_CHURNA_3g_3g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setUsg(constant.USG_SCROTUM);
		opd_disease_medicine_case_paper.setBasti("MATRA BASTI(60mlErand Tel) x 5D"); 
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(107);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.VRUSHANAVAT);
		opd_disease_symptoms_improvement.setC_o(constant.VRUSHANAVAT_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- MUTRASHMARI

		opd_disease_medicine_case_paper.setSr_no(108);
		opd_disease_medicine_case_paper.setDepartment(constant.shalya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.mutrashmari);
		opd_disease_medicine_case_paper.setRx1("GOKSHUR CHURNA 1g-----1g x 5D");
		opd_disease_medicine_case_paper.setRx2(constant.PUNARNAVA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setUsg(constant.USG_KUB);
		opd_disease_medicine_case_paper.setMicrobiological(constant.URINE_R_M);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalya);
		opd_disease_symptoms_improvement.setSr_no(108);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.mutrashmari);
		opd_disease_symptoms_improvement.setC_o(constant.MUTRASHMARI_ST);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		return "Shalyatantra record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";

	}

	public String addRecordToOPDCasePaper_Shalakyatantra_Department()
	{

		// ---------------------------------------------------------- MUKHAPAK

		opd_disease_medicine_case_paper.setSr_no(121);
		opd_disease_medicine_case_paper.setDepartment("");
		opd_disease_medicine_case_paper.setVyadhiName(constant.MUKHAPAK);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.KHADIR_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(121);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.MUKHAPAK);
		opd_disease_symptoms_improvement.setC_o(constant.MUKHAPAK_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- PRATISHYAYA

		opd_disease_medicine_case_paper.setSr_no(122);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.MUKHAPAK);
		opd_disease_medicine_case_paper.setRx1(constant.TRIBHUVAN_KIRTI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SITOPALADI_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(122);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.MUKHAPAK);
		opd_disease_symptoms_improvement.setC_o(constant.MUKHAPAK_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- KARNASHOOL

		opd_disease_medicine_case_paper.setSr_no(123);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.KARNASHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MAHAVATVIDHWANSA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.AMALAKI_CHURNA_2g_2g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(123);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.KARNASHOOL);
		opd_disease_symptoms_improvement.setC_o(constant.KARNASHOOL_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- SURYAVARTA

		opd_disease_medicine_case_paper.setSr_no(124);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SURYAVARTA);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya(constant.YASHTIMADHU_TEL);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(124);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SURYAVARTA);
		opd_disease_symptoms_improvement.setC_o(constant.SURYAVARTA_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- NETRADAH

		opd_disease_medicine_case_paper.setSr_no(125);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.netradah);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SUKSHMA_TRIPHALA_GUTI_2g_2g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(125);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.netradah);
		opd_disease_symptoms_improvement.setC_o(constant.NETRADAH_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- KHALITYA

		opd_disease_medicine_case_paper.setSr_no(126);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.KHALITYA);
		opd_disease_medicine_case_paper.setRx1(constant.AROGYAVARDHINI_VATI_2_2x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.YASHTIMADHU_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(126);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.KHALITYA);
		opd_disease_symptoms_improvement.setC_o(constant.KHALITYA_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- GILAYU SHOPH

		opd_disease_medicine_case_paper.setSr_no(127);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.GILAYU_SHOPH);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_250mg_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.KHADIR_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.MAHAMAYUR_GHRUT_5ml_5ml_5ml_X_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(127);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.GILAYU_SHOPH);
		opd_disease_symptoms_improvement.setC_o(constant.GILAYU_SHOPH_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- PINAS

		opd_disease_medicine_case_paper.setSr_no(128);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.PINAS);
		opd_disease_medicine_case_paper.setRx1(constant.TRIBHUVAN_KIRTI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TRIKATU_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(128);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.PINAS);
		opd_disease_symptoms_improvement.setC_o(constant.PINAS_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- NETRABHISHYANDYA

		opd_disease_medicine_case_paper.setSr_no(129);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.NETRABHISHYANDYA);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SUKSHMA_TRIPHALA_GUTI_2g_2g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setSnehan("");
		opd_disease_medicine_case_paper.setSwedan("");
		opd_disease_medicine_case_paper.setSerological("");
		opd_disease_medicine_case_paper.setBiochemical("");
		opd_disease_medicine_case_paper.setShirodhara("");
		opd_disease_medicine_case_paper.setBasti(""); 
		opd_disease_medicine_case_paper.setMicrobiological("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(129);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.NETRABHISHYANDYA);
		opd_disease_symptoms_improvement.setC_o(constant.NETRABHISHYANDYA_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- DRUSHTIMANDYA

		opd_disease_medicine_case_paper.setSr_no(130);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.DRUSHTIMANDYA);
		opd_disease_medicine_case_paper.setRx1(constant.TRIPHALA_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.TAPYADI_LOHA_GUTI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.NETRA_TARPAN_Mahatraiphalyadi_Ghrut);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(130);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.DRUSHTIMANDYA);
		opd_disease_symptoms_improvement.setC_o(constant.DRUSHTIMANDYA_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- SHIRASHOOL

		opd_disease_medicine_case_paper.setSr_no(131);
		opd_disease_medicine_case_paper.setDepartment(constant.shalakya);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SHIRASHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.LAGHU_SUTSHEKHAR_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya(constant.YASHTIMADHU_TEL);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.shalakya);
		opd_disease_symptoms_improvement.setSr_no(131);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SHIRASHOOL);
		opd_disease_symptoms_improvement.setC_o(constant.SHIRASHOOL_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		return "Shalakyatantra record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Atyayika_Department()
	{

		// ---------------------------------------------------------- UDARSHOOL

		opd_disease_medicine_case_paper.setSr_no(151);
		opd_disease_medicine_case_paper.setDepartment(constant.atyayika);
		opd_disease_medicine_case_paper.setVyadhiName(constant.UDARSHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.CHANDRAPRABHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SHANKHA_VATI_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.atyayika);
		opd_disease_symptoms_improvement.setSr_no(151);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.UDARSHOOL);
		opd_disease_symptoms_improvement.setC_o(constant.UDARSHOOL_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- CHHARDI

		opd_disease_medicine_case_paper.setSr_no(152);
		opd_disease_medicine_case_paper.setDepartment(constant.atyayika);
		opd_disease_medicine_case_paper.setVyadhiName(constant.CHHARDI);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.ELA_MASHI_1g_1g_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.SARIVA_CHURNA_2g_2g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.atyayika);
		opd_disease_symptoms_improvement.setSr_no(152);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.CHHARDI);
		opd_disease_symptoms_improvement.setC_o(constant.UDARSHOOL_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- URSHOOL

		opd_disease_medicine_case_paper.setSr_no(153);
		opd_disease_medicine_case_paper.setDepartment(constant.atyayika);
		opd_disease_medicine_case_paper.setVyadhiName(constant.URSHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.KAMDUDHA_RAS_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SARIVA_CHURNA_2g_2g_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.ELA_MASHI_1g_1g_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.atyayika);
		opd_disease_symptoms_improvement.setSr_no(153);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.URSHOOL);
		opd_disease_symptoms_improvement.setC_o(constant.URSHOOL_SKT);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		return "Atyayika record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}

	public String addRecordToOPDCasePaper_Swastharakshanam_Department()
	{

		// ---------------------------------------------------------- SWASTHARAKSHANAM

		opd_disease_medicine_case_paper.setSr_no(171);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.KHALITYA);
		opd_disease_medicine_case_paper.setRx1(constant.MANJISHTHA_CHURNA_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.MANJISHTHA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(171);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.KHALITYA);
		opd_disease_symptoms_improvement.setC_o(constant.KHALITYA_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		// ---------------------------------------------------------- HRIDROG

		opd_disease_medicine_case_paper.setSr_no(172);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.hridrog);
		opd_disease_medicine_case_paper.setRx1(constant.DASHAMOOL_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.SIMHANAD_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3(constant.MEDOHAR_VATI_250mg_2_2_x_5D);
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(172);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.hridrog);
		opd_disease_symptoms_improvement.setC_o(constant.HRIDROG_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);



		// ---------------------------------------------------------- UDAVARTA

		opd_disease_medicine_case_paper.setSr_no(173);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.UDAVARTA);
		opd_disease_medicine_case_paper.setRx1(constant.HINGVASHTAK_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.AMALAKI_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.PAVANAMUKTASAN);
		opd_disease_medicine_case_paper.setAshan2(constant.HALASAN);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(173);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.UDAVARTA);
		opd_disease_symptoms_improvement.setC_o(constant.UDAVARTA_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);




		// ---------------------------------------------------------- GRUDRASI

		opd_disease_medicine_case_paper.setSr_no(174);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.GRUDRASI);
		opd_disease_medicine_case_paper.setRx1(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.NAGA_GUTI_62_5mg_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.GOMUKHASAN);
		opd_disease_medicine_case_paper.setAshan2(constant.MATSYENDRASAN);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(174);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.GRUDRASI);
		opd_disease_symptoms_improvement.setC_o(constant.GRUDRASI_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- PRAMEHA

		opd_disease_medicine_case_paper.setSr_no(175);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.PRAMEHA);
		opd_disease_medicine_case_paper.setRx1(constant.RASNADI_GUGGUL_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2("");
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1("");
		opd_disease_medicine_case_paper.setSerological(constant.LIPID_PROFILE);
		opd_disease_medicine_case_paper.setBiochemical(constant.BSL_F_PP);
		opd_disease_medicine_case_paper.setMicrobiological(constant.URINE_R_M);
		opd_disease_medicine_case_paper.setAshan2("");
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(175);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.PRAMEHA);
		opd_disease_symptoms_improvement.setC_o(constant.PRAMEHA_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- SHIRASHOOL

		opd_disease_medicine_case_paper.setSr_no(176);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.SHIRASHOOL);
		opd_disease_medicine_case_paper.setRx1(constant.LAGHU_SUTSHEKHAR_2_2_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.VACHA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.SHVANASAN);
		opd_disease_medicine_case_paper.setAshan2(constant.PASHCIMOTANASAN);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(176);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.SHIRASHOOL);
		opd_disease_symptoms_improvement.setC_o(constant.PRAMEHA_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- MALAVSHTAMBHA

		opd_disease_medicine_case_paper.setSr_no(177);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.malavshtambha);
		opd_disease_medicine_case_paper.setRx1(constant.GANDHARVAHAREETAKI_CHURNA_0_3g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.NISHOTTAR_CHURNA_0_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.PAVANAMUKTASAN);
		opd_disease_medicine_case_paper.setAshan2(constant.KATICHAKRASAN);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(177);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.malavshtambha);
		opd_disease_symptoms_improvement.setC_o(constant.MALAVSHTAMBHA_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);


		// ---------------------------------------------------------- NIDRANASH

		opd_disease_medicine_case_paper.setSr_no(178);
		opd_disease_medicine_case_paper.setDepartment(constant.swasthavrutta);
		opd_disease_medicine_case_paper.setVyadhiName(constant.NIDRANASH);
		opd_disease_medicine_case_paper.setRx1(constant.SARPAGANDA_CHURNA_1g_1g_x_5D);
		opd_disease_medicine_case_paper.setRx2(constant.NISHOTTAR_CHURNA_0_1g_x_5D);
		opd_disease_medicine_case_paper.setRx3("");
		opd_disease_medicine_case_paper.setOther("");
		opd_disease_medicine_case_paper.setNasya("");
		opd_disease_medicine_case_paper.setAshan1(constant.PAVANAMUKTASAN);
		opd_disease_medicine_case_paper.setAshan2(constant.KATICHAKRASAN);
		opd_disease_medicine_case_paper_Repos.save(opd_disease_medicine_case_paper);

		//--------------------------------------------------------------------------------------------------------------------

		opd_disease_symptoms_improvement.setDepartment(constant.swasthavrutta);
		opd_disease_symptoms_improvement.setSr_no(178);
		opd_disease_symptoms_improvement.setSymptoms("");
		opd_disease_symptoms_improvement.setVyadhiName(constant.NIDRANASH);
		opd_disease_symptoms_improvement.setC_o(constant.NIDRANASH_SW);
		opd_disease_symptoms_improvement.setH_o(constant.NAD);
		opd_disease_symptoms_improvement.setUr(constant.avishesh);
		opd_disease_symptoms_improvement.setUdar(constant.soft);
		opd_disease_symptoms_improvement.setCvs(constant.S1S2_N);

		opd_disease_symptoms_improve_Repos.save(opd_disease_symptoms_improvement);

		return "Swastharakshanam record Saved in opd_disease_symptoms_improvement and opd_disease_medicine_case_paper";
	}



	//------------------------------------ Panchakarma Procedure ---------------------------------------------------------------



	public String addRecordToOPDPanchakarmaProcedureAndIPDPanchakarmaProcedureRegister(Patient p)
	{
		if(p.getDiagnosis().equalsIgnoreCase(constant.manyagatvat))
		{
			LocalDate panch_procedure_admit_date = p.getDate();

			if(ipd_panch_procedure_backup_date.getYear() == panch_procedure_admit_date.getYear())
			{
				p.setYearly_no(ipd_panch_procedure_yearly_count);
				ipd_panch_procedure_yearly_count++;
			}
			else
			{
				ipd_panch_procedure_backup_date = panch_procedure_admit_date;
				ipd_panch_procedure_yearly_count = 1;
				p.setYearly_no(ipd_panch_procedure_yearly_count);
				ipd_panch_procedure_yearly_count++;
			}


			if(ipd_panch_procedure_backup_date.isEqual(panch_procedure_admit_date)) {

				p.setSr_no(ipd_panch_procedure_serial_count);
				ipd_panch_procedure_serial_count++;
			}
			else
			{
				ipd_panch_procedure_backup_date = panch_procedure_admit_date;
				ipd_panch_procedure_serial_count = 1;
				p.setSr_no(ipd_panch_procedure_serial_count);
				ipd_panch_procedure_serial_count++;
			}

			String IPDprocedureStatus = addRecordToIPDPanchakarmaProcedure(p);
			System.out.println(IPDprocedureStatus);

		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.januvat)||
				p.getDiagnosis().equalsIgnoreCase(constant.malabandha)||
				p.getDiagnosis().equalsIgnoreCase(constant.vatrakta))
		{

			LocalDate panch_procedure_admit_date = p.getDate();

			if(opd_panch_procedure_backup_date.getYear() == panch_procedure_admit_date.getYear())
			{
				p.setYearly_no(opd_panch_procedure_yearly_count);
				opd_panch_procedure_yearly_count++;
			}
			else
			{
				opd_panch_procedure_yearly_count = 1;
				p.setYearly_no(opd_panch_procedure_yearly_count);
			}


			if(opd_panch_procedure_backup_date.isEqual(panch_procedure_admit_date)) {

				p.setSr_no(opd_panch_procedure_serial_count);
				opd_panch_procedure_serial_count++;
			}
			else
			{
				opd_panch_procedure_backup_date = panch_procedure_admit_date;
				opd_panch_procedure_serial_count = 1;
				p.setSr_no(opd_panch_procedure_serial_count);
				opd_panch_procedure_serial_count++;
			}

			String OPDprocedureStatus = addRecordToOPDPanchakarmaProcedure(p);
			System.out.println(OPDprocedureStatus);

		}

		return "IPD record added to OPD Panchakarma Procedure";
	}



	public String addRecordToOPDPanchakarmaProcedure(Patient p)
	{

		if(p.getDiagnosis().equalsIgnoreCase(constant.januvat))
		{
			opd_panch_procedure.setRecord_no(opd_panch_procedure_record_count);
			opd_panch_procedure_record_count++;
			opd_panch_procedure.setSr_no(p.getSr_no());
			opd_panch_procedure.setYearly_no(p.getYearly_no());
			opd_panch_procedure.setAddress(p.getAddress());
			opd_panch_procedure.setAge(p.getAge());
			opd_panch_procedure.setDate(p.getDate());
			opd_panch_procedure.setDepartment(p.getDepartment());
			opd_panch_procedure.setDiagnosis(p.getDiagnosis());
			opd_panch_procedure.setName(p.getName());
			opd_panch_procedure.setNew_no(p.getNew_no());
			opd_panch_procedure.setOld_no(p.getOld_no());
			opd_panch_procedure.setSex(p.getSex());
			opd_panch_procedure.setOther(constant.JANU_BASTI_Mahanarayan_Tel);

			opd_panch_procedure_register_repo.save(opd_panch_procedure);

			return "OPD record added to OPD Panchakarma Procedure";
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.malabandha))
		{
			opd_panch_procedure.setRecord_no(opd_panch_procedure_record_count);
			opd_panch_procedure_record_count++;
			opd_panch_procedure.setSr_no(p.getSr_no());
			opd_panch_procedure.setYearly_no(p.getYearly_no());
			opd_panch_procedure.setAddress(p.getAddress());
			opd_panch_procedure.setAge(p.getAge());
			opd_panch_procedure.setDate(p.getDate());
			opd_panch_procedure.setDepartment(p.getDepartment());
			opd_panch_procedure.setDiagnosis(p.getDiagnosis());
			opd_panch_procedure.setName(p.getName());
			opd_panch_procedure.setNew_no(p.getNew_no());
			opd_panch_procedure.setOld_no(p.getOld_no());
			opd_panch_procedure.setSex(p.getSex());
			opd_panch_procedure.setBasti("Matra Basti");

			opd_panch_procedure_register_repo.save(opd_panch_procedure);

			return "OPD record added to OPD Panchakarma Procedure";
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.vatrakta))
		{
			opd_panch_procedure.setRecord_no(opd_panch_procedure_record_count);
			opd_panch_procedure_record_count++;
			opd_panch_procedure.setSr_no(p.getSr_no());
			opd_panch_procedure.setYearly_no(p.getYearly_no());
			opd_panch_procedure.setAddress(p.getAddress());
			opd_panch_procedure.setAge(p.getAge());
			opd_panch_procedure.setDate(p.getDate());
			opd_panch_procedure.setDepartment(p.getDepartment());
			opd_panch_procedure.setDiagnosis(p.getDiagnosis());
			opd_panch_procedure.setName(p.getName());
			opd_panch_procedure.setNew_no(p.getNew_no());
			opd_panch_procedure.setOld_no(p.getOld_no());
			opd_panch_procedure.setSex(p.getSex());
			opd_panch_procedure.setSnehan("SNEHAN-SARVANGA( Bala Tel)");
			opd_panch_procedure.setRaktMokshan("SIRAVEDH(1D)");

			opd_panch_procedure_register_repo.save(opd_panch_procedure);

			return "OPD record added to OPD Panchakarma Procedure";
		}

		return "No disease found for Panchakarma Procedure";
	}


	public String addRecordToIPDPanchakarmaProcedure(Patient p)
	{
		LocalDate changeable = p.getDate();
		LocalDate discharge_date = p.getDischarge_date();

		if(discharge_date.isAfter(changeable))
		{

			while(true)
			{

				if(changeable.equals(discharge_date))
				{
					break;
				}
				else
				{
					ipd_panch_procedure.setRecord_no(ipd_panch_procedure_record_count);
					ipd_panch_procedure_record_count++;
					ipd_panch_procedure.setSr_no(p.getSr_no());
					ipd_panch_procedure.setYearly_no(p.getYearly_no());
					ipd_panch_procedure.setAddress(p.getAddress());
					ipd_panch_procedure.setAge(p.getAge());
					ipd_panch_procedure.setDate(changeable);
					ipd_panch_procedure.setDepartment(p.getDepartment());
					ipd_panch_procedure.setDiagnosis(p.getDiagnosis());
					ipd_panch_procedure.setName(p.getName());
					ipd_panch_procedure.setNew_no(p.getNew_no());
					ipd_panch_procedure.setOld_no(p.getOld_no());
					ipd_panch_procedure.setSex(p.getSex());
					ipd_panch_procedure.setSnehan("SARVANGA SNEHAN(Sahachar Tel)");
					ipd_panch_procedure.setSwedan(" sarvanga peti swedan");
					ipd_panch_procedure.setBasti("KAL BASTI KRAM");

					ipd_panch_procedure_register_repo.save(ipd_panch_procedure);

					changeable = changeable.plusDays(1);

					System.out.println("while Loop in panchakarma procedure IPD");
				}
			}

			ipd_panch_procedure_record_count++;

			return "IPD record added to IPD Panchakarma Procedure";

		}
		else
		{
			return "Date Exceeds for Panchakarma Procedure";
		}

	}


	//----------------------------------------------------------------------------------------------------
	//-----------------------------addRecordTo_X-Ray_PathologicalTest_ECG_USG-----------------------------
	//----------------------------------------------------------------------------------------------------

	public String pathologicalTest_kaychikitsa_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase("atisar"))
		{
			pathologyTest.setMicrobiological("STOOL: R-M");
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);
			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.SHWAS))
		{
			pathologyTest.setHematological("CBC - ESR,.");
			pathologyTest.setX_ray(constant.X_RAY_CHEST_PA_View);
			pathologyTest.setEcg(constant.ECG);
			pathologyTest.setPathology_patient(p);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.jwar))
		{
			pathologyTest.setHematological("CBC - ESR,M.P.");
			pathologyTest.setMicrobiological(constant.URINE_R_M);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);
			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.aamvat))
		{
			pathologyTest.setSerological(constant.RA_Test);
			pathologyTest.setPathology_patient(p);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.vatrakta))
		{
			pathologyTest.setSerological(constant.RA_Test);
			pathologyTest.setBiochemical(constant.S_Uric_Acid);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.janushul))
		{
			pathologyTest.setX_ray(constant.X_RAY_BIL_KNEE_Jt);

			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.PRAMEHA))
		{
			pathologyTest.setSerological(constant.LIPID_PROFILE);
			pathologyTest.setMicrobiological(constant.URINE_R_M);
			pathologyTest.setBiochemical(constant.BSL_F_PP);
			pathologyTest.setPathology_patient(p);

			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase("pandu"))
		{
			pathologyTest.setHematological(constant.CBC_ESR);
			pathologyTest.setBiochemical("S.CREATININE");
			pathologyTest.setPathology_patient(p);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;

			pathologyTestRepo.save(pathologyTest);
		}

		pathologyTest.setPathology_patient(p);

		return "pathology record saved to Kayachikitsa Department";
	}

	public String pathologicalTest_panchakarma_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(constant.januvat))
		{
			pathologyTest.setX_ray(constant.X_RAY_BIL_KNEE_Jt);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.vatrakta))
		{
			pathologyTest.setSerological(constant.RA_Test);
			pathologyTest.setBiochemical(constant.S_Uric_Acid);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);

			return "Pathology Test record saved in panchakarma ";
		}

		return "No pathological treatment found";
	}

	public String pathologicalTest_strirog_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(constant.PCOD))
		{
			pathologyTest.setBiochemical(constant.TESTOSTIRONE_SHBG_AMH_FSH_LH_ESTROGEN_THYROID_PROFILE);
			pathologyTest.setMicrobiological(constant.URINE_R_M);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);

			return "Pathology Test record saved in strirog ";
		}	

		return "No pathological treatment found";
	}

	public String pathologicalTest_balrog_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(""))
		{
			pathologyTest.setPathology_patient(p);
		}

		return "No pathological treatment found";
	}

	public String pathologicalTest_shalya_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(constant.mutrashmari))
		{
			pathologyTest.setMicrobiological(constant.URINE_R_M);
			pathologyTest.setUsg(constant.USG_KUB);
			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);

			return "Pathology Test record saved in shalya ";
		}
		else if(p.getDiagnosis().equalsIgnoreCase(constant.vrushanvat))
		{
			//			pathologyTest.setMicrobiological("");
			pathologyTest.setUsg(constant.USG_SCROTUM);

			pathologyTest.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			pathologyTest.setPathology_patient(p);

			pathologyTestRepo.save(pathologyTest);

			return "Pathology Test record saved in shalya ";
		}

		return "No pathological treatment found";
	}

	public String pathologicalTest_shalakya_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(""))
		{
			pathologyTest.setPathology_patient(p);
		}

		return "No pathological treatment found";
	}


	public String pathologicalTest_swasthavrutta_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest_swasthavrutta = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(constant.hridrog))
		{
			pathologyTest_swasthavrutta.setEcg(constant.ECG);
			pathologyTest_swasthavrutta.setPathology_patient(p);
			pathologyTest_swasthavrutta.setPathology_record_no(pathology_Test_record_no);
			pathology_Test_record_no++;
			
			pathologyTestRepo.save(pathologyTest_swasthavrutta);

			return "ECG record saved in swasthavrutta ";
		}

		return "No pathological treatment found";
	}

	public String pathologicalTest_atyayika_department_logic(Patient p)
	{
		Pathological_Tests pathologyTest = new Pathological_Tests();

		if(p.getDiagnosis().equalsIgnoreCase(""))
		{
			pathologyTest.setPathology_patient(p);

			return "";
		}

		return "";
	}
	
	public String addDayWiseDateInExcelFileDateColumn(int minRecordCount,int maxRecordCount)
	{
		
		try {

			FileInputStream f_in = null;				// logic to fetch list of all patients from Excel File
			
			try {
				f_in = new FileInputStream("D:\\abc.xlsx");
			} catch (FileNotFoundException e) {

				return "File Not Found";
			}
			
			XSSFWorkbook workbook = null;

			try {
				workbook = new XSSFWorkbook(f_in);
			} catch (IOException e) {

				return "problem in workbook";
			}

			//----------------------------------------------------------------------ITERATOR LOGIC-------

			try {
				workbook.close();
			} catch (IOException e) {
				System.out.println("workbook Closed");
			}
		} 
		catch(NullPointerException e)
		{
			return "Cannot perform Action";
		}
		
		return null;
		
	}
	
	
}

