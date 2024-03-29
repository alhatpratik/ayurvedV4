package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDPanchakarma;
import project.Entity.OPDShalakyatantra;
import project.Entity.Patient;

@Repository
public interface OPDShalakyatantraRepo extends JpaRepository<OPDShalakyatantra, Double>{

	@Query("select p from OPDShalakyatantra p where p.dopd_shalakya_patient.date=:d")
	public List<OPDShalakyatantra> get_shalakya_DOPD_Patients(LocalDate d);
	
	@Query("select p from OPDShalakyatantra p where dopd_shalakya_yearly_no = (select max(dopd_shalakya_yearly_no) from OPDShalakyatantra)")
	public Patient getLastRecord();
}
