package com.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Attendance;

@Dao
public interface AttendanceDao {
        @Query("Select * from Attendance")
        List<Attendance> getAttendanceList();
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAttendance(Attendance attendance);
        @Update
        void updateAttendance(Attendance attendance);
        @Delete
        void deleteAttendance(Attendance attendance);
        @Query("SELECT EXISTS (SELECT 1 FROM attendance WHERE User_attendance_name = :User_attendance_name)")
        boolean exists(String User_attendance_name);
}
