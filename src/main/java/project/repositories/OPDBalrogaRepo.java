package project.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.Entity.OPDBalroga;
import project.Entity.Patient;

@Repository
public interface OPDBalrogaRepo extends JpaRepository<project.Entity.OPDBalroga, Double> {

	@Query("select p from OPDBalroga p where p.dopd_bal_patient.date=:d")
	public List<OPDBalroga> get_balroga_DOPD_Patients(LocalDate d);
	
	@Query("select p from OPDBalroga p where dopd_bal_yearly_no = (select max(dopd_bal_yearly_no) from OPDBalroga)")
	public Patient getLastRecord();
}