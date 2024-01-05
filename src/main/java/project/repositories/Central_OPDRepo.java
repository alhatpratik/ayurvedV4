package project.repositories;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import project.Entity.Login;
import project.Entity.Patient;
import project.model.Summary;

public interface Central_OPDRepo extends JpaRepository<Patient, Double> {
	
	
	@Query("select p from Patient p where p.date=:d")
	public List<Patient> getOPDPatients(LocalDate d);
	
	@Query("select p from Patient p where yearly_no = (select max(yearly_no) from Patient)")
	public Patient getLastRecord();
	
//	@Query("select department,sex, count(department) AS count_d, year(date) AS year_d, month(date) AS month_d from Patient group by department,sex, year(date), month(date)")
//	public List<Object[]> getDOPD_genderwise_Monthly_SummaryRecord();
	
	@Query("select department,sex, count(department) AS count_d, year(date) AS year_d, month(date) AS month_d from Patient where year(date)=:yearValue group by department,sex, year(date), month(date)")
	public List<Object[]> getDOPD_genderwise_Monthly_SummaryRecord(int yearValue);
	
	@Query("select department,sex, count(department) AS count_d, year(date) AS year_d, month(date) AS month_d from Central_Admit where year(date)=:yearValue group by department,sex, year(date), month(date)")
	public List<Object[]> get_Admit_genderwise_Monthly_SummaryRecord(int yearValue);
	
//	@Query("select department,sex, count(department) AS count_d, year(date) AS year_d, month(date) AS month_d from Central_Admit group by department,sex, year(date), month(date)")
//	public List<Object[]> getAdmit_genderwise_Monthly_SummaryRecord();
	
	@Transactional
	@Modifying
	@Query( value = "update Patient set weight =:weight, new_no=:new_no, old_no=:old_no, name=:name, address=:address, age=:age, sex=:sex, department=:department, diagnosis=:diagnosis, date=:date where record_no=:record_no" )
	void updateRecord(double weight, double new_no,double old_no,String name, String address, int age, char sex, String department, String diagnosis, LocalDate date, double record_no );

	
}

