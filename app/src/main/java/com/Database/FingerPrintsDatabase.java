package com.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.Converters.Converters;
import com.Dao.AttendanceDao;
import com.Dao.FingerPrintDao;

import Model.Attendance;
import Model.Employee;

@Database(entities = {Employee.class, Attendance.class}, exportSchema = false, version = 7)
@TypeConverters({Converters.class})
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

    public abstract FingerPrintDao fingerPrintDao();

    public abstract AttendanceDao attendanceDao();
}
