package project.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class OPDPanchakarmaProcedureRegister {
	
	@Id
	double record_no;
	@Column
	double yearly_no;
	@Column
	double sr_no;
	@Column
	double new_no;
	@Column
	double old_no;
	@Column
	String name;
	@Column
	String address;
	@Column
	int age;
	@Column
	char sex;
	@Column
	String department;
	@Column
	String diagnosis;
	@Column
	LocalDate date;
	@Column
	LocalDate discharge_date;
	@Column
	double weight;
	@Column
	String snehan;
	@Column
	String swedan;
	@Column
	String vaman;
	@Column
	String virechan;
	@Column
	String basti;
	@Column
	String shirobasti;
	@Column
	String nasya;
	@Column
	String jalouka_vacharan;
	@Column
	String tarpan;
	@Column
	String lep;
	@Column
	String shirodhara;
	@Column
	String other;
	@Column
	String raktMokshan;
	
	
	
	public String getRaktMokshan() {
		return raktMokshan;
	}
	public void setRaktMokshan(String raktMokshan) {
		this.raktMokshan = raktMokshan;
	}
	public double getRecord_no() {
		return record_no;
	}
	public void setRecord_no(double record_no) {
		this.record_no = record_no;
	}
	public double getYearly_no() {
		return yearly_no;
	}
	public void setYearly_no(double yearly_no) {
		this.yearly_no = yearly_no;
	}
	public double getSr_no() {
		return sr_no;
	}
	public void setSr_no(double sr_no) {
		this.sr_no = sr_no;
	}
	public double getNew_no() {
		return new_no;
	}
	public void setNew_no(double new_no) {
		this.new_no = new_no;
	}
	public double getOld_no() {
		return old_no;
	}
	public void setOld_no(double old_no) {
		this.old_no = old_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDate getDischarge_date() {
		return discharge_date;
	}
	public void setDischarge_date(LocalDate discharge_date) {
		this.discharge_date = discharge_date;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
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
	public String getVaman() {
		return vaman;
	}
	public void setVaman(String vaman) {
		this.vaman = vaman;
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
	public String getShirobasti() {
		return shirobasti;
	}
	public void setShirobasti(String shirobasti) {
		this.shirobasti = shirobasti;
	}
	public String getNasya() {
		return nasya;
	}
	public void setNasya(String nasya) {
		this.nasya = nasya;
	}
	public String getJalouka_vacharan() {
		return jalouka_vacharan;
	}
	public void setJalouka_vacharan(String jalouka_vacharan) {
		this.jalouka_vacharan = jalouka_vacharan;
	}
	public String getTarpan() {
		return tarpan;
	}
	public void setTarpan(String tarpan) {
		this.tarpan = tarpan;
	}
	public String getLep() {
		return lep;
	}
	public void setLep(String lep) {
		this.lep = lep;
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

	
}
