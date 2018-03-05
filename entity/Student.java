package com.fusemachine.libraryManagement.entity;

import java.math.BigInteger;

public class Student {
	BigInteger id;
	
	String name;
	String password;
	public Student(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Student() {
		super();
	}
	
}
