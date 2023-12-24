package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDPanchakarma;


@Repository
public interface IPDPanchakarmaRepo extends JpaRepository<project.Entity.IPDPanchakarma, Double> {

	@Query("select p from IPDPanchakarma p where p.date=:d")
	public List<IPDPanchakarma> get_panch_DIPD_Patients(LocalDate d);
	
}
