package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDPanchakarmaProcedureRegister;
import project.Entity.OPDPanchakarmaProcedureRegister;

@Repository
public interface IPDPanchakarmaProcedureRepo extends JpaRepository<IPDPanchakarmaProcedureRegister,Double>{

	@Query("select p from IPDPanchakarmaProcedureRegister p where p.date=:d")
	public List<IPDPanchakarmaProcedureRegister> get_IPD_Panchakarma_Procedure_Details(LocalDate d);
	
//	@Query("select department,sex, count(department) AS count_d, year(date) AS year_d, month(date) AS month_d from Central_Admit where year(date)=:yearValue group by sex, year(date), month(date)")
//	public List<Object[]> get_OPD_Panchakarma_Procedure_Yearly_Details(int yearValue);
	
}
