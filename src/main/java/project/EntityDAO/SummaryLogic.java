package project.EntityDAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.OPDPanchakarmaProcedureRegister;
import project.Entity.Patient;
import project.model.Summary;
import project.repositories.Central_OPDRepo;
import project.repositories.OPDPanchakarmaProcedureRepo;

@Component
public class SummaryLogic {

	@Autowired
	Central_OPDRepo opdi;

	@Autowired
	OPDPanchakarmaProcedureRepo opd_panch_procedure_repo;
	
//	public List<Summary> getDOPD_Genderwise_Monthly_SummaryRecord()
//	{
//
//		List<Object[]> dops_repo_fetched_summary_list = opdi.getDOPD_genderwise_Monthly_SummaryRecord();
//
//		List<Summary> dopd_summary_list = new ArrayList<>();
//
//		for(Object[] sum : dops_repo_fetched_summary_list)
//		{
//			Summary summary = new Summary();
//
//			summary.setDepartment((String)sum[0]);
//			summary.setSex((char)sum[1]);
//			summary.setCount_d((long)sum[2]);
//			summary.setYear_d((int)sum[3]);
//			summary.setMonth_d((int)sum[4]);
//
//			if(summary!=null)
//			{
//				dopd_summary_list.add(summary);
//			}
//
//		}
//
//		for(Summary summ : dopd_summary_list)
//		{
//
//			System.out.println(summ.getDepartment()+" "+" "+summ.getMonth_d()+" "+summ.getYear_d()+" "+summ.getCount_d());
//		}
//
//		System.out.println(dopd_summary_list.size());
//		return dopd_summary_list;
//	}


	
	public List<Summary> getDOPD_Genderwise_Monthly_SummaryRecord(int date)
	{

		List<Object[]> dops_repo_fetched_summary_list = opdi.getDOPD_genderwise_Monthly_SummaryRecord(date);

		List<Summary> dopd_summary_list = new ArrayList<>();

		for(Object[] sum : dops_repo_fetched_summary_list)
		{
			Summary summary = new Summary();

			summary.setDepartment((String)sum[0]);
			summary.setSex((char)sum[1]);
			summary.setCount_d((long)sum[2]);
			summary.setYear_d((int)sum[3]);
			summary.setMonth_d((int)sum[4]);

			if(summary!=null)
			{
				dopd_summary_list.add(summary);
			}

		}

		for(Summary summ : dopd_summary_list)
		{

			System.out.println(summ.getDepartment()+" "+" "+summ.getMonth_d()+" "+summ.getYear_d()+" "+summ.getCount_d());
		}

		System.out.println(dopd_summary_list.size());
		return dopd_summary_list;
	}

	
	public List<Summary> get_admit_Genderwise_Monthly_SummaryRecord(int year)
	{

		List<Object[]> admit_repo_fetched_summary_list = opdi.get_Admit_genderwise_Monthly_SummaryRecord(year);

		List<Summary> admit_genderwise_summary_list = new ArrayList<>();

		for(Object[] sum : admit_repo_fetched_summary_list)
		{
			Summary summary = new Summary();

			summary.setDepartment((String)sum[0]);
			summary.setSex((char)sum[1]);
			summary.setCount_d((long)sum[2]);
			summary.setYear_d((int)sum[3]);
			summary.setMonth_d((int)sum[4]);

			if(summary!=null)
			{
				admit_genderwise_summary_list.add(summary);
			}

		}

		for(Summary summ : admit_genderwise_summary_list)
		{

			System.out.println(summ.getDepartment()+" "+" "+summ.getMonth_d()+" "+summ.getYear_d()+" "+summ.getCount_d());
		}

		System.out.println(admit_genderwise_summary_list.size());

		return admit_genderwise_summary_list;
	}

//	public List<Summary> get_admit_Genderwise_Monthly_SummaryRecord()
//	{
//
//		List<Object[]> admit_repo_fetched_summary_list = opdi.getAdmit_genderwise_Monthly_SummaryRecord();
//
//		List<Summary> admit_genderwise_summary_list = new ArrayList<>();
//
//		for(Object[] sum : admit_repo_fetched_summary_list)
//		{
//			Summary summary = new Summary();
//
//			summary.setDepartment((String)sum[0]);
//			summary.setSex((char)sum[1]);
//			summary.setCount_d((long)sum[2]);
//			summary.setYear_d((int)sum[3]);
//			summary.setMonth_d((int)sum[4]);
//
//			if(summary!=null)
//			{
//				admit_genderwise_summary_list.add(summary);
//			}
//
//		}
//
//		for(Summary summ : admit_genderwise_summary_list)
//		{
//
//			System.out.println(summ.getDepartment()+" "+" "+summ.getMonth_d()+" "+summ.getYear_d()+" "+summ.getCount_d());
//		}
//
//		System.out.println(admit_genderwise_summary_list.size());
//
//		return admit_genderwise_summary_list;
//	}

	public List<Summary> get_OPD_Panchakarma_Procedure_Yearly_Summary(int year)
	{

		List<Object[]> opd_panch_procedure_repo_fetched_summary_list = opd_panch_procedure_repo.get_OPD_Panchakarma_Procedure_Yearly_Details(year);

		List<Summary> OPD_panch_procedure_summary_list = new ArrayList<>();

		for(Object[] sum : opd_panch_procedure_repo_fetched_summary_list)
		{
			Summary summary = new Summary();

			summary.setDepartment((String)sum[0]);
			summary.setSex((char)sum[1]);
			summary.setCount_d((long)sum[2]);
			summary.setYear_d((int)sum[3]);
			summary.setMonth_d((int)sum[4]);

			if(summary!=null)
			{
				OPD_panch_procedure_summary_list.add(summary);
			}

		}

		for(Summary summ : OPD_panch_procedure_summary_list)
		{

			System.out.println(summ.getDepartment()+" "+" "+summ.getMonth_d()+" "+summ.getYear_d()+" "+summ.getCount_d());
		}

		System.out.println(OPD_panch_procedure_summary_list.size());

		return OPD_panch_procedure_summary_list;
	}
	
}
