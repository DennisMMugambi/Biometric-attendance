package com.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import Model.Employee;
import Model.UserAndAttendance;

@Dao
public interface FingerPrintDao {
    @Query("Select * from Employee")
    List<Employee> getEmployeeList();
    @Query("Select * from Employee WHERE employeeName LIKE '%' || :employee_name || '%'")
    List<Employee> getSearchedEmployees(String employee_name);
    @Insert
    void insertEmployee(Employee employee);
    @Update
    void updateEmployee(Employee employee);
    @Delete
    void deleteEmployee(Employee employee);
    @Transaction
    @Query("SELECT * FROM Employee")
    public List<UserAndAttendance> getUsersAndAttendance();

}
