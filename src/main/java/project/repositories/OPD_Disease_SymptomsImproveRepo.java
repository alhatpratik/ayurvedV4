package project.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.Entity.OPD_Disease_Symptoms_patient_improvement;

@Repository
public interface OPD_Disease_SymptomsImproveRepo extends JpaRepository<OPD_Disease_Symptoms_patient_improvement, Double>{

	@Query("select p from OPD_Disease_Symptoms_patient_improvement p where p.vyadhiName=:vyadhiName")
	public List<OPD_Disease_Symptoms_patient_improvement> getOpdCasePaperSymptomsDetails (String vyadhiName);
	
}

