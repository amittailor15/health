package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repository.AppointmentRepository;
import com.example.model.Appointment;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@PostMapping("/appointment")
	public String create(@RequestBody Appointment appointment)
	{
		appointmentRepository.save(appointment);
		return "Appointment created";
	}
	
	@GetMapping("/appointment")
	public ResponseEntity<List<Appointment>> getAllAppointments()
	{
		List <Appointment> appointme = new ArrayList<>(); 
		appointmentRepository.findAll().forEach(appointme::add); 
		return new ResponseEntity<List<Appointment>>(appointme, HttpStatus.OK);
	}
	
	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable long id)
	{ 
		Optional<Appointment> appointm = appointmentRepository.findById(id);
		if(appointm.isPresent()) 
		{
			return new ResponseEntity<Appointment>(appointm.get(), HttpStatus.FOUND);
		}
		else 
		{
			return new ResponseEntity<Appointment>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/appointments/{id}")
	public String deleteAppointmentByEmpId(@PathVariable long id) 
	{ 
		appointmentRepository.deleteById(id); 
		return "Appointment Deleted Successfully";
	}
	
	@DeleteMapping("/appointments")
	public String deleteAllAppointment()
	{ 
		appointmentRepository.deleteAll(); 
		return "Appointment deleted Successfully..";
	}
}
