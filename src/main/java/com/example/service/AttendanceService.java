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

	// 全ての勤怠情報を取得するメソッド
	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	// 指定されたIDの勤怠情報を取得するメソッド
	public Attendance getAttendanceById(Long id) {
		Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
		return optionalAttendance.orElse(null);
	}

	// 新しい勤怠情報を作成するメソッド
	public Attendance createAttendance(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	// 指定されたIDの勤怠情報を更新するメソッド
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

	// 指定されたIDの勤怠情報を削除するメソッド
	public void deleteAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElse(null);
		if (attendance != null) {
			attendanceRepository.delete(attendance);
		} else {
			// 勤怠情報が見つからない場合の処理
		}
	}

	// 特定のユーザーの勤怠情報を取得するメソッド
	public List<Attendance> getAttendancesByUsername(String username) {
		return null;
	}
}
