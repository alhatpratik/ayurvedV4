package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDShalyatantra;

@Repository
public interface OPDShalyatantraRepo extends JpaRepository<project.Entity.OPDShalyatantra, Double>{

	@Query("select p from OPDShalyatantra p where p.dopd_shalya_patient.date=:d")
	public List<OPDShalyatantra> get_shalya_DOPD_Patients(LocalDate d);
	
}
