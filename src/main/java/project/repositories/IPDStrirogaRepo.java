package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDStriroga;

@Repository
public interface IPDStrirogaRepo extends JpaRepository<project.Entity.IPDStriroga, Double> {

	@Query("select p from IPDStriroga p where p.date=:d")
	public List<IPDStriroga> get_striroga_DIPD_Patients(LocalDate d);
	
}
