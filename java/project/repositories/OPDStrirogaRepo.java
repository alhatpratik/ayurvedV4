package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDPanchakarma;
import project.Entity.OPDStriroga;

@Repository
public interface OPDStrirogaRepo extends JpaRepository<project.Entity.OPDStriroga, Double> {

	@Query("select p from OPDStriroga p where p.dopd_stri_patient.date=:d")
	public List<OPDStriroga> get_panch_striroga_Patients(LocalDate d);
	
}
