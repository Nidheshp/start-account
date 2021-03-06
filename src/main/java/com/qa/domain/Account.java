package com.qa.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(length = 50)
	private String firstName;
	@Column(length = 50)
	private String secondName;
	@Column(length = 4)
	private String accountNumber;

	public Account(Long id, String firstName, String secondName, String accountNumber) {
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.accountNumber = accountNumber;
	}
	
	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

}
