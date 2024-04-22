package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Attendance;
import com.example.repository.AttendanceRepository;

@Service
public class AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	public Attendance getAttendanceById(Long id) {
		Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
		return optionalAttendance.orElse(null);
	}

	public Attendance createAttendance(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	public Attendance updateAttendance(Long id, Attendance attendanceDetails) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElse(null);
		if (attendance != null) {
			attendance.setDate(attendanceDetails.getDate());
			attendance.setStartTime(attendanceDetails.getStartTime());
			attendance.setEndTime(attendanceDetails.getEndTime());
			attendance.setBreakTime(attendanceDetails.getBreakTime());
			return attendanceRepository.save(attendance);
		} else {
			return null;
		}
	}

	public void deleteAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElse(null);
		if (attendance != null) {
			attendanceRepository.delete(attendance);
		} else {

		}
	}

	public List<Attendance> getAttendancesByUsername(String username) {
		return null;
	}
}
