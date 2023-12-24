package project.repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.Pathological_Tests;

@Repository
public interface Pathological_TestsRepo extends JpaRepository<Pathological_Tests, Double>{

	@Query("select p from Pathological_Tests p where p.pathology_patient.date=:d and x_ray is not null")
	public List<Pathological_Tests> getAll_X_Ray_Tests(LocalDate d);
	
	@Query("select p from Pathological_Tests p where p.pathology_patient.date=:d and ecg is not null")
	public List<Pathological_Tests> getAll_ECG_Tests(LocalDate d);
	
}
