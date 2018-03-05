package com.fusemachine.libraryManagement.Controller;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.fusemachine.libraryManagement.entity.Student;


@RestController 
public class StudentController {
	private static BigInteger studentId;
	private static Map< BigInteger, Student> students;
	
	private static Student save(Student student) {
		if (students == null) {
		students = new HashMap<BigInteger, Student>();
		studentId = BigInteger.ONE;
			
		}
		/**
		 *  for book update
		 */
		if(student.getId() != null) {
			Student oldGreeting = students.get(student.getId());
			if(oldGreeting == null) {
				return null;
			}
			students.remove(student.getId());
			students.put(student.getId(), student);
			return student;
		}
		
		/**
		 *  create student
		 */
		student.setId(studentId);
	studentId = studentId.add(BigInteger.ONE);
	students.put(student.getId(), student);
	return student;
	}
	private static boolean delete(BigInteger id) {
		Student deletedStudent = students.remove(id);
		if(deletedStudent == null) {
			return false;
		}
		return true;
	}
	static {
		for(int i = 1; i<10 ; i++) {
		Student sti = new Student();
		sti.setName("student"+i);
		sti.setPassword("student"+i);
		save(sti);
		}
		
		
		
	}
	@RequestMapping(value = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Student>> getStudents(){
		
		Collection<Student> studentsAll = students.values();
		return new ResponseEntity<Collection<Student>>(studentsAll, HttpStatus.OK);
	}
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudent(@PathVariable("id") BigInteger id){

		
		Student student = students.get(id);
		if(student == null) {
			return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE ,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") BigInteger id, @RequestBody Student student){
		
		Boolean deleted = delete(id);
		if(!deleted) {
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/student/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		
		Student savedStudent = save(student);
		
		return new ResponseEntity<Student>(savedStudent, HttpStatus.CREATED);
		
	}
	@RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> updateStudent(@RequestBody Student student){
		
		Student updatedStudent = save(student);
		if(updatedStudent == null) {
			return new ResponseEntity<Student>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Student>(updatedStudent, HttpStatus.OK);
		
	}
}
