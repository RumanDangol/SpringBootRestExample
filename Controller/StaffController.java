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

import com.fusemachine.libraryManagement.entity.Staff;













@RestController 
//@RequestMapping(value)
public class StaffController {
	private static BigInteger staffId;
	private static Map< BigInteger, Staff> staffs;

	private static Staff save(Staff staff) {
		if (staffs == null) {
		staffs = new HashMap<BigInteger, Staff>();
		staffId = BigInteger.ONE;
			
		}
		/**
		 *  for staff update
		 */
		if(staff.getId() != null) {
			Staff oldStaff = staffs.get(staff.getId());
			if(oldStaff == null) {
				return null;
			}
			staffs.remove(staff.getId());
			staffs.put(staff.getId(), staff);
			return staff;
		}
		
		/**
		 *  create staff
		 */
	staff.setId(staffId);
	staffId = staffId.add(BigInteger.ONE);
	staffs.put(staff.getId(), staff);
	return staff;
	}
	private static boolean delete(BigInteger id) {
		Staff deletedStaff = staffs.remove(id);
		if(deletedStaff == null) {
			return false;
		}
		return true;
	}
	static {
		for(int i = 1; i<10 ; i++) {
		
	
			Staff sti = new Staff();
			sti.setName("staff"+i);
			sti.setPassword("staff"+i);
			save(sti);
			}
		
		
	}
	@RequestMapping(value = "/staffs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Staff>> getStaffs(){
		
		Collection<Staff> staffsAll = staffs.values();
		return new ResponseEntity<Collection<Staff>>(staffsAll, HttpStatus.OK);
	}
	@RequestMapping(value = "/staff/{id}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Staff> getStaff(@PathVariable("id") BigInteger id){

		
		Staff staff = staffs.get(id);
		if(staff == null) {
			return new ResponseEntity<Staff>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Staff>(staff, HttpStatus.OK);
		
	}
	@RequestMapping(value = "/staff/delete/{id}", method = RequestMethod.DELETE ,  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Staff> deleteStaff(@PathVariable("id") BigInteger id, @RequestBody Staff staff){
		
		Boolean deleted = delete(id);
		if(!deleted) {
			return new ResponseEntity<Staff>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/staff/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Staff> createStaff(@RequestBody Staff staff){
		
		Staff savedStaff = save(staff);
		
		return new ResponseEntity<Staff>(savedStaff, HttpStatus.CREATED);
		
	}
	@RequestMapping(value = "/staff/update/{id}", method = RequestMethod.PUT , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Staff> updateStaff(@RequestBody Staff staff){
		
		Staff updatedStaff = save(staff);
		if(updatedStaff == null) {
			return new ResponseEntity<Staff>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Staff>(updatedStaff, HttpStatus.OK);
		
	}
	
}
