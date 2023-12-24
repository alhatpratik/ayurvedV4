package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDBalroga;

@Repository
public interface IPDBalrogaRepo extends JpaRepository<project.Entity.IPDBalroga, Double>{

	@Query("select p from IPDBalroga p where p.date=:d")
	public List<IPDBalroga> get_balroga_DIPD_Patients(LocalDate d);
	
}
