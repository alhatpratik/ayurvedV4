package project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Component
@JsonIgnoreProperties(value= {"handler","hibernateLazyInitializer","FieldHandler"})
public class OPD_Disease_Medicine_CasePaper {

	@Id
	double sr_no;
	@Column
	String snehan;
	@Column
	String swedan;
	@Column
	String virechan;
	@Column
	String basti;
	@Column
	String nasya;
	@Column
	String raktmokshan;
	@Column
	String shirodhara;
	@Column
	String other;
	@Column
	String ashan1;
	@Column
	String ashan2;
	@Column
	String hematological;
	@Column
	String serological;
	@Column
	String microbiological;
	@Column
	String x_ray;
	@Column
	String ecg;
	@Column
	String usg;
	@Column
	String vyadhiName;
	
	@Column
	String department;
	@Column
	String rx1;
	@Column
	String rx2;
	@Column
	String rx3;
	@Column
	String biochemical;
	
	
	
	public String getBiochemical() {
		return biochemical;
	}
	public void setBiochemical(String biochemical) {
		this.biochemical = biochemical;
	}
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
	public String getSnehan() {
		return snehan;
	}
	public void setSnehan(String snehan) {
		this.snehan = snehan;
	}
	public String getSwedan() {
		return swedan;
	}
	public void setSwedan(String swedan) {
		this.swedan = swedan;
	}
	public String getVirechan() {
		return virechan;
	}
	public void setVirechan(String virechan) {
		this.virechan = virechan;
	}
	public String getBasti() {
		return basti;
	}
	public void setBasti(String basti) {
		this.basti = basti;
	}
	public String getNasya() {
		return nasya;
	}
	public void setNasya(String nasya) {
		this.nasya = nasya;
	}
	public String getRaktmokshan() {
		return raktmokshan;
	}
	public void setRaktmokshan(String raktmokshan) {
		this.raktmokshan = raktmokshan;
	}
	public String getShirodhara() {
		return shirodhara;
	}
	public void setShirodhara(String shirodhara) {
		this.shirodhara = shirodhara;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public String getAshan1() {
		return ashan1;
	}
	public void setAshan1(String ashan1) {
		this.ashan1 = ashan1;
	}
	public String getAshan2() {
		return ashan2;
	}
	public void setAshan2(String ashan2) {
		this.ashan2 = ashan2;
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
	public String getRx1() {
		return rx1;
	}
	public void setRx1(String rx1) {
		this.rx1 = rx1;
	}
	public String getRx2() {
		return rx2;
	}
	public void setRx2(String rx2) {
		this.rx2 = rx2;
	}
	public String getRx3() {
		return rx3;
	}
	public void setRx3(String rx3) {
		this.rx3 = rx3;
	}
	
}
