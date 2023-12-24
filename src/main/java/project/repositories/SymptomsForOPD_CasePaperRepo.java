package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.Entity.SymptomsForOPD_CasePaper;

@Repository
public interface SymptomsForOPD_CasePaperRepo extends JpaRepository<SymptomsForOPD_CasePaper, Integer> {

	
	@Query("select p from SymptomsForOPD_CasePaper p")
	public SymptomsForOPD_CasePaper get_symptomsfor_OPD_Case_Paper(); 
	
}
