package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDShalyatantra;

@Repository
public interface IPDShalyatantraRepo extends JpaRepository<project.Entity.IPDShalyatantra, Double> {

	
	@Query("select p from IPDShalyatantra p where p.date=:d")
	public List<IPDShalyatantra> get_shalya_DIPD_Patients(LocalDate d);
	
}
