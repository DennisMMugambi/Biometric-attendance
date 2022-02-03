package com.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.Database.FingerPrintsDatabase;

import java.util.List;

import Model.FingerPrint;

@Dao
public interface FingerPrintDao {
    @Query("Select * from FingerPrint")
    List<FingerPrint> getFingerPrintList();
    @Insert
    void insertFingerPrint(FingerPrint fingerPrint);
    @Update
    void updateFingerPrint(FingerPrint fingerPrint);
    @Delete
    void deleteFingerPrint(FingerPrint fingerPrint);
}
