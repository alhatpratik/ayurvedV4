package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.BedOccupancy;

@Repository
public interface BedOccupancyRepo extends JpaRepository<BedOccupancy, Double>{

	@Query("select p from BedOccupancy p where p.date=:d")
	public List<BedOccupancy> getBedOccupancy_IPD_Patients(LocalDate d);
	
}
