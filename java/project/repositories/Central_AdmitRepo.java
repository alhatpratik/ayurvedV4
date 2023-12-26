package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.Central_Admit;

@Repository
public interface Central_AdmitRepo extends JpaRepository<Central_Admit, Double> {
	
	@Query("select p from Central_Admit p where p.date=:d")
	public List<Central_Admit> getCentral_IPD_Patients(LocalDate d);
	
}
