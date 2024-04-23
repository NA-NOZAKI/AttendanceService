package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AttendanceDAO; // DAOクラスのインポートを追加
import com.example.model.Attendance;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceDAO attendanceDAO; // DAOクラスのインスタンスを注入

    // 全ての勤怠情報を取得する
    public List<Attendance> getAllAttendances() {
        return attendanceDAO.getAllAttendances(); // DAOクラスのメソッドを呼び出す
    }

    // 指定されたIDの勤怠情報を取得する
    public Attendance getAttendanceById(Long userId) {
        // DAOクラスのメソッドを呼び出す
        return attendanceDAO.getAttendanceByUserIdAndDate(userId, LocalDate.now());
    }

    // 新しい勤怠情報を作成する
    public Attendance createAttendance(Attendance attendance) {
        // DAOクラスのメソッドを呼び出す
        attendanceDAO.saveAttendance(attendance);
        return attendance;
    }

    // 指定されたIDの勤怠情報を更新する
    public Attendance updateAttendance(Long id, Attendance attendanceDetails) {
        // DAOクラスのメソッドを呼び出す
        attendanceDAO.updateAttendance(id, attendanceDetails);
        return attendanceDetails;
    }

    // 指定されたIDの勤怠情報を削除する
    public void deleteAttendance(Long id) {
        // DAOクラスのメソッドを呼び出す
        attendanceDAO.deleteAttendance(id);
    }

    // 特定のユーザーの勤怠情報を取得する
    public List<Attendance> getAttendancesByUsername(String username) {
        // DAOクラスのメソッドを呼び出す
        return attendanceDAO.getAttendancesByUserId(1L); // ここにユーザーIDを渡す必要があります
    }

    // 特定の日付範囲内の勤怠情報を取得する
    public List<Attendance> getAttendancesByDateRange(LocalDate startDate, LocalDate endDate) {
        // DAOクラスのメソッドを呼び出す
        return attendanceDAO.getAttendancesByDateRange(startDate, endDate);
    }

    // 特定のユーザーの特定の日付範囲内の勤怠情報を取得する
    public List<Attendance> getAttendancesByUsernameAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        // DAOクラスのメソッドを呼び出す
        return attendanceDAO.getAttendancesByUserIdAndDateRange(userId, startDate, endDate);
    }

    // 特定のユーザーの特定の日付の勤怠情報を取得する
    public Optional<Attendance> getAttendanceByUsernameAndDate(Long id, LocalDate date) {
        // DAOクラスのメソッドを呼び出す
        return Optional.ofNullable(attendanceDAO.getAttendanceByUserIdAndDate(id, date));
    }

}
