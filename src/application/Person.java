package application;

import java.time.LocalDate;

public class Person {
	
	 private int id;
	 private String last_name;
	 private String first_name;
	 private LocalDate start_date ;
	 private double start_salary;
	 private char contract_signed;
	 private long ssn;
	 private LocalDate birth_date;
	 private long phone_number;
	 private String emg_contact_name;
	 private long emg_contact_number;
	 
	 public Person()
	 {
		 //this.id = id;
		 //this.last_name = last_name;
		 //this.first_name = first_name;
		// this.start_date = start_date ;
		// this.start_salary = 0;
		 //this.contract_signed=contract_signed;
		 //this.ssn = ssn;
		 //this.birth_date = birth_date;
		 //this.phone_number = phone_number;
		 //this.emg_contact_name =emg_contact_name;
		 //this.emg_contact_number = emg_contact_number;
	 }
	 
	 public Person(int id, String last_name, String first_name,
			       LocalDate start_date , double start_salary, char contract_signed 
			       , int ssn , LocalDate birth_date , int phone_number , 
			       String emg_contact_name ,  int emg_contact_number)
	 {
		 this.id = id;
		 this.last_name = last_name;
		 this.first_name = first_name;
		 this.start_date = start_date ;
		 this.start_salary = start_salary;
		 this.contract_signed=contract_signed;
		 this.ssn = ssn;
		 this.birth_date = birth_date;
		 this.phone_number = phone_number;
		 this.emg_contact_name =emg_contact_name;
		 this.emg_contact_number = emg_contact_number;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public double getStart_salary() {
	    return start_salary;
	}

	public void setStart_salary(double start_salary) {
	    this.start_salary = start_salary;
	}

	public char getContract_signed() {
	    return contract_signed;
	}

	public void setContract_signed(char contract_signed) {
	    this.contract_signed = contract_signed;
	}

	public long getSsn() {
	    return ssn;
	}

	public void setSsn(long ssn) {
	    this.ssn = ssn;
	}

	public LocalDate getBirth_date() {
	    return birth_date;
	}

	public void setBirth_date(LocalDate birth_date) {
	    this.birth_date = birth_date;
	}

	public long getPhone_number() {
	    return phone_number;
	}

	public void setPhone_number(long phone_number) {
	    this.phone_number = phone_number;
	}

	public String getEmg_contact_name() {
	    return emg_contact_name;
	}

	public void setEmg_contact_name(String emg_contact_name) {
	    this.emg_contact_name = emg_contact_name;
	}

	public long getEmg_contact_number() {
	    return emg_contact_number;
	}

	public void setEmg_contact_number(long emg_contact_number) {
	    this.emg_contact_number = emg_contact_number;
	}
}
