package com.example.service;

import java.time.LocalDate;
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

	// 全ての勤怠情報を取得する
	public List<Attendance> getAllAttendances() {
		return attendanceRepository.findAll();
	}

	// 指定されたIDの勤怠情報を取得する
	public Attendance getAttendanceById(Long userId) {
		Optional<Attendance> optionalAttendance = attendanceRepository.findById(userId);
		return optionalAttendance.orElse(null);
	}

	// 新しい勤怠情報を作成する
	public Attendance createAttendance(Attendance attendance) {
		return attendanceRepository.save(attendance);
	}

	// 指定されたIDの勤怠情報を更新する
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

	// 指定されたIDの勤怠情報を削除する
	public void deleteAttendance(Long id) {
		Attendance attendance = attendanceRepository.findById(id)
				.orElse(null);
		if (attendance != null) {
			attendanceRepository.delete(attendance);
		} else {
			// 勤怠情報が見つからない場合の処理
		}
	}

	// 特定のユーザーの勤怠情報を取得する
	public List<Attendance> getAttendancesByUsername(String username) {
	    // ユーザー名を使用して勤怠情報を取得するロジックを実装
	    return attendanceRepository.findByUserName(username);
	}

	// 特定の日付範囲内の勤怠情報を取得する
	public List<Attendance> getAttendancesByDateRange(LocalDate startDate, LocalDate endDate) {
	    // 日付範囲を使用して勤怠情報を取得するロジックを実装
	    return attendanceRepository.findByDateBetween(startDate, endDate);
	}

	// 特定のユーザーの特定の日付範囲内の勤怠情報を取得する
	public List<Attendance> getAttendancesByUsernameAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
	    // ユーザー名と日付範囲を使用して勤怠情報を取得するロジックを実装
	    return attendanceRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
	}

	// 特定のユーザーの特定の日付の勤怠情報を取得する
	public Optional<Attendance> getAttendanceByUsernameAndDate(Long Id, LocalDate date) {
	    // ユーザー名と日付を使用して勤怠情報を取得するロジックを実装
	    return attendanceRepository.findByUserIdAndDate(Id, date);
	}

}
