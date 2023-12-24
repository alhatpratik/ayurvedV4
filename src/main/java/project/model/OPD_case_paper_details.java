package project.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import project.Entity.OPD_Disease_Medicine_CasePaper;
import project.Entity.OPD_Disease_Symptoms_patient_improvement;
import project.Entity.Patient;

@Component
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class OPD_case_paper_details {
	
	Patient p;
	OPD_Disease_Medicine_CasePaper opd_dis_med_paper;
	OPD_Disease_Symptoms_patient_improvement opd_sym_pat_impro;
	public Patient getP() {
		return p;
	}
	public void setP(Patient p) {
		this.p = p;
	}
	public OPD_Disease_Medicine_CasePaper getOpd_dis_med_paper() {
		return opd_dis_med_paper;
	}
	public void setOpd_dis_med_paper(OPD_Disease_Medicine_CasePaper opd_dis_med_paper) {
		this.opd_dis_med_paper = opd_dis_med_paper;
	}
	public OPD_Disease_Symptoms_patient_improvement getOpd_sym_pat_impro() {
		return opd_sym_pat_impro;
	}
	public void setOpd_sym_pat_impro(OPD_Disease_Symptoms_patient_improvement opd_sym_pat_impro) {
		this.opd_sym_pat_impro = opd_sym_pat_impro;
	}
	
}
