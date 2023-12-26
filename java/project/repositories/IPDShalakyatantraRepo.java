package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDShalakyatantra;


@Repository
public interface IPDShalakyatantraRepo extends JpaRepository<project.Entity.IPDShalakyatantra, Double> {

	@Query("select p from IPDShalakyatantra p where p.date=:d")
	public List<IPDShalakyatantra> get_shalakya_DIPD_Patients(LocalDate d);
	
}
