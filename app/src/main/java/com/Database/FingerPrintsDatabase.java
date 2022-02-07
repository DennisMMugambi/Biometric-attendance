package com.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.Dao.FingerPrintDao;

import Model.Employee;

@Database(entities = {Employee.class}, exportSchema = false, version = 5)
public abstract class FingerPrintsDatabase extends RoomDatabase {
    private static final String DB_NAME = "fingerprints_db";
    private static FingerPrintsDatabase instance;

    public static synchronized FingerPrintsDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FingerPrintsDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    //public  IncomeDao incomeDao;

    public abstract FingerPrintDao fingerPrintDao();
}
