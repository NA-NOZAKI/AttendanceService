package com.example.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Attendance;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	// 特定のユーザーの勤怠履歴を取得する
	List<Attendance> findByUserId(Long userId);

	// 特定の日付範囲内の勤怠履歴を取得する
	List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);

	// 特定のユーザーの特定の日付範囲内の勤怠履歴を取得する
	List<Attendance> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

	// 特定のユーザーの特定の日付の勤怠情報を取得する
	Optional<Attendance> findByUserIdAndDate(Long userId, LocalDate date);

	List<Attendance> findByUserName(String username);
}