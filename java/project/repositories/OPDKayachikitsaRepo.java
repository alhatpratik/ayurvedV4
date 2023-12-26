package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDKayachikitsa;

@Repository
public interface OPDKayachikitsaRepo extends JpaRepository<project.Entity.OPDKayachikitsa, Double>{

	@Query("select p from OPDKayachikitsa p where p.dopd_kaya_patient.date=:d")
	public List<OPDKayachikitsa> get_kaya_DOPD_Patients(LocalDate d);
	
}
