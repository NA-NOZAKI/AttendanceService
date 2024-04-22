package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Attendance;
import com.example.service.AttendanceService;

@RestController
@RequestMapping("/api")
public class AttendanceCon {

	@Autowired
	private AttendanceService attendanceService;

	@GetMapping("/attendances")
	public List<Attendance> getAllAttendances() {
		return attendanceService.getAllAttendances();
	}

	@GetMapping("/attendances/{id}")
	public Attendance getAttendanceById(@PathVariable Long id) {
		return attendanceService.getAttendanceById(id);
	}

	@PostMapping("/attendances")
	public Attendance createAttendance(@RequestBody Attendance attendance) {
		return attendanceService.createAttendance(attendance);
	}

	@PutMapping("/attendances/{id}")
	public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance attendance) {
		return attendanceService.updateAttendance(id, attendance);
	}

	@DeleteMapping("/attendances/{id}")
	public void deleteAttendance(@PathVariable Long id) {
		attendanceService.deleteAttendance(id);
	}
}
