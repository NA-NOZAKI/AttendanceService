package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Attendance;

public class AttendanceDAO {

    private String databaseUrl;

    public AttendanceDAO(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    // 全ての勤怠情報を取得する
    public List<Attendance> getAllAttendances() {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "SELECT * FROM Attendance";
            try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Attendance attendance = new Attendance();
                    attendance.setId(rs.getLong("id"));
                    attendance.setDate(rs.getDate("date").toLocalDate());
                    attendance.setStartTime(rs.getTime("startTime").toLocalTime());
                    attendance.setEndTime(rs.getTime("endTime").toLocalTime());
                    attendance.setBreakTime(rs.getInt("breakTime"));
                    attendance.setNote(rs.getString("note"));
                    attendances.add(attendance);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // 特定のユーザーの勤怠情報を取得する
    public List<Attendance> getAttendancesByUserId(Long userId) {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "SELECT * FROM Attendance WHERE user_id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, userId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Attendance attendance = new Attendance();
                        attendance.setId(rs.getLong("id"));
                        attendance.setDate(rs.getDate("date").toLocalDate());
                        attendance.setStartTime(rs.getTime("startTime").toLocalTime());
                        attendance.setEndTime(rs.getTime("endTime").toLocalTime());
                        attendance.setBreakTime(rs.getInt("breakTime"));
                        attendance.setNote(rs.getString("note"));
                        attendances.add(attendance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // 特定の日付範囲内の勤怠情報を取得する
    public List<Attendance> getAttendancesByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "SELECT * FROM Attendance WHERE date BETWEEN ? AND ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, java.sql.Date.valueOf(startDate));
                pstmt.setDate(2, java.sql.Date.valueOf(endDate));
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Attendance attendance = new Attendance();
                        attendance.setId(rs.getLong("id"));
                        attendance.setDate(rs.getDate("date").toLocalDate());
                        attendance.setStartTime(rs.getTime("startTime").toLocalTime());
                        attendance.setEndTime(rs.getTime("endTime").toLocalTime());
                        attendance.setBreakTime(rs.getInt("breakTime"));
                        attendance.setNote(rs.getString("note"));
                        attendances.add(attendance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // 特定のユーザーの特定の日付範囲内の勤怠情報を取得する
    public List<Attendance> getAttendancesByUserIdAndDateRange(Long userId, LocalDate startDate, LocalDate endDate) {
        List<Attendance> attendances = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "SELECT * FROM Attendance WHERE user_id=? AND date BETWEEN ? AND ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, userId);
                pstmt.setDate(2, java.sql.Date.valueOf(startDate));
                pstmt.setDate(3, java.sql.Date.valueOf(endDate));
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Attendance attendance = new Attendance();
                        attendance.setId(rs.getLong("id"));
                        attendance.setDate(rs.getDate("date").toLocalDate());
                        attendance.setStartTime(rs.getTime("startTime").toLocalTime());
                        attendance.setEndTime(rs.getTime("endTime").toLocalTime());
                        attendance.setBreakTime(rs.getInt("breakTime"));
                        attendance.setNote(rs.getString("note"));
                        attendances.add(attendance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    // 特定のユーザーの特定の日付の勤怠情報を取得する
    public Attendance getAttendanceByUserIdAndDate(Long userId, LocalDate date) {
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "SELECT * FROM Attendance WHERE user_id=? AND date=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, userId);
                pstmt.setDate(2, java.sql.Date.valueOf(date));
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        Attendance attendance = new Attendance();
                        attendance.setId(rs.getLong("id"));
                        attendance.setDate(rs.getDate("date").toLocalDate());
                        attendance.setStartTime(rs.getTime("startTime").toLocalTime());
                        attendance.setEndTime(rs.getTime("endTime").toLocalTime());
                        attendance.setBreakTime(rs.getInt("breakTime"));
                        attendance.setNote(rs.getString("note"));
                        return attendance;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 新しい勤怠情報を保存する
    public void saveAttendance(Attendance attendance) {
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "INSERT INTO Attendance (date, startTime, endTime, breakTime, note) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, java.sql.Date.valueOf(attendance.getDate()));
                pstmt.setTime(2, Time.valueOf(attendance.getStartTime()));
                pstmt.setTime(3, Time.valueOf(attendance.getEndTime()));
                pstmt.setInt(4, attendance.getBreakTime());
                pstmt.setString(5, attendance.getNote());
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 勤怠情報を更新する
    public void updateAttendance(Long id, Attendance attendance) {
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "UPDATE Attendance SET date=?, startTime=?, endTime=?, breakTime=?, note=? WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, java.sql.Date.valueOf(attendance.getDate()));
                pstmt.setTime(2, Time.valueOf(attendance.getStartTime()));
                pstmt.setTime(3, Time.valueOf(attendance.getEndTime()));
                pstmt.setInt(4, attendance.getBreakTime());
                pstmt.setString(5, attendance.getNote());
                pstmt.setLong(6, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 勤怠情報を削除する
    public void deleteAttendance(Long id) {
        try (Connection conn = DriverManager.getConnection(databaseUrl)) {
            String sql = "DELETE FROM Attendance WHERE id=?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setLong(1, id);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
	