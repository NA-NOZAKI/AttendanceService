package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/Attendance")
public class AttendanceCon {

	@Autowired
	private AttendanceService attendanceService;

	/**
	 * 勤怠情報を取得するエンドポイント
	 * ユーザータイプが "master" の場合はすべての勤怠情報を取得し、それ以外の場合はログインしているユーザーの勤怠情報のみを取得する
	 */
	@GetMapping("/")
	public ResponseEntity<List<Attendance>> getAllAttendances() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userType = authentication.getAuthorities().iterator().next().getAuthority();
		List<Attendance> attendances;
		if ("master".equals(userType)) {
			attendances = attendanceService.getAllAttendances();
		} else {
			String username = authentication.getName();
			attendances = attendanceService.getAttendancesByUsername(username);
		}
		return new ResponseEntity<>(attendances, HttpStatus.OK);
	}

	/**
	 * 指定されたIDの勤怠情報を取得するエンドポイント
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Attendance> getAttendanceById(@PathVariable("id") Long id) {
		Attendance attendance = attendanceService.getAttendanceById(id);
		return new ResponseEntity<>(attendance, HttpStatus.OK);
	}

	/**
	 * 新しい勤怠情報を作成するエンドポイント
	 */
	@PostMapping("/")
	public ResponseEntity<Attendance> createAttendance(@RequestBody Attendance attendance) {
		Attendance createdAttendance = attendanceService.createAttendance(attendance);
		return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
	}

	/**
	 * 指定されたIDの勤怠情報を更新するエンドポイント
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Attendance> updateAttendance(@PathVariable("id") Long id,
			@RequestBody Attendance attendanceDetails) {
		Attendance updatedAttendance = attendanceService.updateAttendance(id, attendanceDetails);
		return new ResponseEntity<>(updatedAttendance, HttpStatus.OK);
	}

	/**
	 * 指定されたIDの勤怠情報を削除するエンドポイント
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAttendance(@PathVariable("id") Long id) {
		attendanceService.deleteAttendance(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
