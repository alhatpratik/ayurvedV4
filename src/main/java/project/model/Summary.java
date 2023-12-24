package project.model;

import org.springframework.stereotype.Component;

@Component
public class Summary {
	
	String department;
	char sex;
	long count_d ;
	int year_d;
	int month_d;
	
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public long getCount_d() {
		return count_d;
	}
	public void setCount_d(long count_d) {
		this.count_d = count_d;
	}
	public int getYear_d() {
		return year_d;
	}
	public void setYear_d(int sum) {
		this.year_d = sum;
	}
	public int getMonth_d() {
		return month_d;
	}
	public void setMonth_d(int month_d) {
		this.month_d = month_d;
	}
	
}
