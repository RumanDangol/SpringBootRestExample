package com.fusemachine.libraryManagement.entity;

import java.math.BigInteger;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan

public class Book {

	BigInteger id;
	
	String name;
	String publisher;
	Boolean isAvailable;
	int studentId;
	
	public Book() {
		super();
	}
	public Book(String name, String publisher, Boolean isAvailable, int studentId) {
		super();
		this.name = name;
		this.publisher = publisher;
		this.isAvailable = isAvailable;
		this.studentId = studentId;
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
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Boolean getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
