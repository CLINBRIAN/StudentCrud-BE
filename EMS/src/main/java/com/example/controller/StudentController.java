package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.repo.StudentRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	public Student addStudent(@RequestBody Student student) {
		
		return studentRepository.save(student);
		
	}
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/student/{sId}")
	public Student getStudentById(@PathVariable("sId") Integer  sId) {
		return studentRepository.findById(sId).get();
		
	} 
	
	@PutMapping("/student/update")
	public Student updateStudent(@RequestBody Student student) {
		studentRepository.findById(student.getsId()).orElseThrow();
		
		return studentRepository.save(student);
	}
	
	@DeleteMapping("/delete/{sId}")
	public AddResponse deleteStudent( @PathVariable("sId") Integer  sId) {
		 studentRepository.deleteById(sId);
		 AddResponse response = new AddResponse();
		 response.setMessage("delete success");
		 response.setId(sId);
		 
		 return response;
		
	}

}
