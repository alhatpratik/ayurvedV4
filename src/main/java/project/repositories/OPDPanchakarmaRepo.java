package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDKayachikitsa;
import project.Entity.OPDPanchakarma;
import project.Entity.Patient;

@Repository
public interface OPDPanchakarmaRepo extends JpaRepository<project.Entity.OPDPanchakarma, Double> {

	@Query("select p from OPDPanchakarma p where p.dopd_panch_patient.date=:d")
	public List<OPDPanchakarma> get_panch_DOPD_Patients(LocalDate d);
	
	@Query("select p from OPDPanchakarma p where dopd_panch_yearly_no = (select max(dopd_panch_yearly_no) from OPDPanchakarma)")
	public Patient getLastRecord();
}
