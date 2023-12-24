package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPD_Disease_Medicine_CasePaper;

@Repository
public interface OPD_DiseaseMedicineCasePaperRepo extends JpaRepository<OPD_Disease_Medicine_CasePaper, Double> {

	@Query("select p from OPD_Disease_Medicine_CasePaper p where p.vyadhiName=:vyadhiName")
	public List<OPD_Disease_Medicine_CasePaper> getOpdCasePaperMedicineDetails (String vyadhiName);
	
	
}

