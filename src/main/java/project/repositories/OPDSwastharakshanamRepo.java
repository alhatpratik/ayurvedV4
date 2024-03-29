package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.Entity.OPDSwastharakshanam;
import project.Entity.Patient;


@Repository
public interface OPDSwastharakshanamRepo extends JpaRepository<project.Entity.OPDSwastharakshanam, Double> {

	@Query("select p from OPDSwastharakshanam p where p.dopd_swastha_patient.date=:d")
	public List<OPDSwastharakshanam> get_swastha_DOPD_Patients(LocalDate d);
	
	@Query("select p from OPDSwastharakshanam p where dopd_swastha_yearly_no = (select max(dopd_swastha_yearly_no) from OPDSwastharakshanam)")
	public Patient getLastRecord();
}
