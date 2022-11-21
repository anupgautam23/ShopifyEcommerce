package com.masai.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.UserException;
import com.masai.Model.Admin;
import com.masai.Model.AdminDTO;
import com.masai.Service.AdminServiceImpl;

@RestController("/Admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminService;
	
	@PostMapping("/SignUp")
	private ResponseEntity<Admin>  registerAdmin(@Valid @RequestBody Admin admin) throws UserException{
		
		Admin regAdmin =adminService.registerAdmin(admin);
		
		return new ResponseEntity<Admin>(regAdmin,HttpStatus.ACCEPTED);		
		
	}
	
	@PostMapping("/login")
	private ResponseEntity<String>  loginAdmin(@Valid @RequestBody AdminDTO admindto) throws UserException{
		
		String regAdmin  =adminService.loginAdmin(admindto);
		
		return new ResponseEntity<String>(regAdmin,HttpStatus.ACCEPTED);		
		
	}
	
}
