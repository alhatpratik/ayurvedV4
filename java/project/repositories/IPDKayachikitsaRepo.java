package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.IPDKayachikitsa;

@Repository
public interface IPDKayachikitsaRepo extends JpaRepository<project.Entity.IPDKayachikitsa, Double>{

	@Query("select p from IPDKayachikitsa p where p.date=:d")
	public List<IPDKayachikitsa> get_kaya_DIPD_Patients(LocalDate d);
	
}
