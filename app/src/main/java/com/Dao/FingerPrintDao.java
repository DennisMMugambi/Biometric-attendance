package com.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Model.Employee;

@Dao
public interface FingerPrintDao {
    @Query("Select * from Employee")
    List<Employee> getEmployeeList();
    @Insert
    void insertEmployee(Employee employee);
    @Update
    void updateEmployee(Employee employee);
    @Delete
    void deleteEmployee(Employee employee);
}
