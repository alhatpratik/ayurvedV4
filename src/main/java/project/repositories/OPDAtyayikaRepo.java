package project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.Entity.OPDAtyayika;
import project.Entity.OPDSwastharakshanam;
import project.Entity.Patient;


@Repository
public interface OPDAtyayikaRepo extends JpaRepository<project.Entity.OPDAtyayika, Double>{

	@Query("select p from OPDAtyayika p where p.dopd_atya_patient.date=:d")
	public List<OPDAtyayika> get_atyayika_DOPD_Patients(LocalDate d);
	
	@Query("select p from OPDAtyayika p where dopd_atya_yearly_no = (select max(dopd_atya_yearly_no) from OPDAtyayika)")
	public Patient getLastRecord();
}
