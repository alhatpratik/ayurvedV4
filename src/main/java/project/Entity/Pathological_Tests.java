package project.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Pathological_Tests {

	@Id
	double pathology_record_no;

	@OneToOne
	@JoinColumn(name = "patient_id", referencedColumnName = "record_no")
	Patient pathology_patient;
	
	@Column
	String hematological;
	@Column
	String serological;
	@Column
	String biochemical;
	@Column
	String microbiological;
	@Column
	String x_ray;
	@Column
	String ecg;
	@Column
	String usg;
	public double getPathology_record_no() {
		return pathology_record_no;
	}
	public void setPathology_record_no(double pathology_record_no) {
		this.pathology_record_no = pathology_record_no;
	}
	public Patient getPathology_patient() {
		return pathology_patient;
	}
	public void setPathology_patient(Patient pathology_patient) {
		this.pathology_patient = pathology_patient;
	}
	public String getHematological() {
		return hematological;
	}
	public void setHematological(String hematological) {
		this.hematological = hematological;
	}
	public String getSerological() {
		return serological;
	}
	public void setSerological(String serological) {
		this.serological = serological;
	}
	public String getBiochemical() {
		return biochemical;
	}
	public void setBiochemical(String biochemical) {
		this.biochemical = biochemical;
	}
	public String getMicrobiological() {
		return microbiological;
	}
	public void setMicrobiological(String microbiological) {
		this.microbiological = microbiological;
	}
	public String getX_ray() {
		return x_ray;
	}
	public void setX_ray(String x_ray) {
		this.x_ray = x_ray;
	}
	public String getEcg() {
		return ecg;
	}
	public void setEcg(String ecg) {
		this.ecg = ecg;
	}
	public String getUsg() {
		return usg;
	}
	public void setUsg(String usg) {
		this.usg = usg;
	}
	
}
