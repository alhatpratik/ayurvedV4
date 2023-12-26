package project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class OPD_Disease_Symptoms_patient_improvement {

	@Id
	double sr_no;
	@Column
	String symptoms;
	@Column
	String c_o;
	@Column
	String h_o;
	@Column
	String blood_pressure;
	@Column
	int pulse;
	@Column
	String nadi;
	@Column
	String ur;
	@Column
	String cvs;
	@Column
	String udar;
	@Column
	String netra;
	@Column
	String jiva;
	@Column
	String kshudha;
	@Column
	String ahaar;
	@Column
	String mal;
	@Column
	String mutra;
	@Column
	String nidra;
	@Column
	String temp;
	@Column
	String vyadhiName;
	@Column
	String department;
	
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}

	public String getVyadhiName() {
		return vyadhiName;
	}
	public void setVyadhiName(String vyadhiName) {
		this.vyadhiName = vyadhiName;
	}	
	public double getSr_no() {
		return sr_no;
	}
	public void setSr_no(double sr_no) {
		this.sr_no = sr_no;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getC_o() {
		return c_o;
	}
	public void setC_o(String c_o) {
		this.c_o = c_o;
	}
	public String getH_o() {
		return h_o;
	}
	public void setH_o(String h_o) {
		this.h_o = h_o;
	}
	public String getBlood_pressure() {
		return blood_pressure;
	}
	public void setBlood_pressure(String blood_pressure) {
		this.blood_pressure = blood_pressure;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public String getNadi() {
		return nadi;
	}
	public void setNadi(String nadi) {
		this.nadi = nadi;
	}
	public String getUr() {
		return ur;
	}
	public void setUr(String ur) {
		this.ur = ur;
	}
	public String getCvs() {
		return cvs;
	}
	public void setCvs(String cvs) {
		this.cvs = cvs;
	}
	public String getUdar() {
		return udar;
	}
	public void setUdar(String udar) {
		this.udar = udar;
	}
	public String getNetra() {
		return netra;
	}
	public void setNetra(String netra) {
		this.netra = netra;
	}
	public String getJiva() {
		return jiva;
	}
	public void setJiva(String jiva) {
		this.jiva = jiva;
	}
	public String getKshudha() {
		return kshudha;
	}
	public void setKshudha(String kshudha) {
		this.kshudha = kshudha;
	}
	public String getAhaar() {
		return ahaar;
	}
	public void setAhaar(String ahaar) {
		this.ahaar = ahaar;
	}
	public String getMal() {
		return mal;
	}
	public void setMal(String mal) {
		this.mal = mal;
	}
	public String getMutra() {
		return mutra;
	}
	public void setMutra(String mutra) {
		this.mutra = mutra;
	}
	public String getNidra() {
		return nidra;
	}
	public void setNidra(String nidra) {
		this.nidra = nidra;
	}

}
