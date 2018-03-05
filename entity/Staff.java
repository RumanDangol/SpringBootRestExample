package com.fusemachine.libraryManagement.entity;

import java.math.BigInteger;




public class Staff {
	BigInteger id;
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}

	String name;
	String password;

	
	
	public Staff() {
		super();
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
	public Staff(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	@Override
	public String toString() {
		return "Staff [name=" + name + ", password=" + password + "]";
	}
}
