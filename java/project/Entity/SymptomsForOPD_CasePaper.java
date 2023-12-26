package project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class SymptomsForOPD_CasePaper {

	@Id
	@Column
	int sr_no;
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
	
	
	public int getSr_no() {
		return sr_no;
	}
	public void setSr_no(int sr_no) {
		this.sr_no = sr_no;
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
