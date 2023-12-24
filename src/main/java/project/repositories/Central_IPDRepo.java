package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import project.Entity.Central_IPD;

public interface Central_IPDRepo extends JpaRepository<Central_IPD, Double>{

	@Query("select p from Central_IPD p where p.date=:d")
	public List<Central_IPD> get_Central_Admit_Discharge_IPD_Patients(LocalDate d);
	
}
