package com.Database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.Dao.AttendanceDao;
import com.Dao.AttendanceDao_Impl;
import com.Dao.FingerPrintDao;
import com.Dao.FingerPrintDao_Impl;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FingerPrintsDatabase_Impl extends FingerPrintsDatabase {
  private volatile FingerPrintDao _fingerPrintDao;

  private volatile AttendanceDao _attendanceDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(7) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Employee` (`employee_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `rightThumb` BLOB, `rightIndex` BLOB, `leftThumb` BLOB, `leftIndex` BLOB, `employeeName` TEXT, `Age` INTEGER NOT NULL, `jobTitle` TEXT, `identification` TEXT, `attendance_score` REAL, `parent_contact` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Attendance` (`attendance_Id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `User_attendance_name` TEXT, `Date` TEXT, `time_in` TEXT, `time_out` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '14006ff66228343179c65d8cddc2ee1b')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Employee`");
        _db.execSQL("DROP TABLE IF EXISTS `Attendance`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsEmployee = new HashMap<String, TableInfo.Column>(11);
        _columnsEmployee.put("employee_id", new TableInfo.Column("employee_id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("rightThumb", new TableInfo.Column("rightThumb", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("rightIndex", new TableInfo.Column("rightIndex", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("leftThumb", new TableInfo.Column("leftThumb", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("leftIndex", new TableInfo.Column("leftIndex", "BLOB", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("employeeName", new TableInfo.Column("employeeName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("Age", new TableInfo.Column("Age", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("jobTitle", new TableInfo.Column("jobTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("identification", new TableInfo.Column("identification", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("attendance_score", new TableInfo.Column("attendance_score", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEmployee.put("parent_contact", new TableInfo.Column("parent_contact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEmployee = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEmployee = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEmployee = new TableInfo("Employee", _columnsEmployee, _foreignKeysEmployee, _indicesEmployee);
        final TableInfo _existingEmployee = TableInfo.read(_db, "Employee");
        if (! _infoEmployee.equals(_existingEmployee)) {
          return new RoomOpenHelper.ValidationResult(false, "Employee(Model.Employee).\n"
                  + " Expected:\n" + _infoEmployee + "\n"
                  + " Found:\n" + _existingEmployee);
        }
        final HashMap<String, TableInfo.Column> _columnsAttendance = new HashMap<String, TableInfo.Column>(5);
        _columnsAttendance.put("attendance_Id", new TableInfo.Column("attendance_Id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("User_attendance_name", new TableInfo.Column("User_attendance_name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("Date", new TableInfo.Column("Date", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("time_in", new TableInfo.Column("time_in", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAttendance.put("time_out", new TableInfo.Column("time_out", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttendance = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttendance = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttendance = new TableInfo("Attendance", _columnsAttendance, _foreignKeysAttendance, _indicesAttendance);
        final TableInfo _existingAttendance = TableInfo.read(_db, "Attendance");
        if (! _infoAttendance.equals(_existingAttendance)) {
          return new RoomOpenHelper.ValidationResult(false, "Attendance(Model.Attendance).\n"
                  + " Expected:\n" + _infoAttendance + "\n"
                  + " Found:\n" + _existingAttendance);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "14006ff66228343179c65d8cddc2ee1b", "846e32dd6f90559b3c87c204c9df3b38");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Employee","Attendance");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Employee`");
      _db.execSQL("DELETE FROM `Attendance`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FingerPrintDao fingerPrintDao() {
    if (_fingerPrintDao != null) {
      return _fingerPrintDao;
    } else {
      synchronized(this) {
        if(_fingerPrintDao == null) {
          _fingerPrintDao = new FingerPrintDao_Impl(this);
        }
        return _fingerPrintDao;
      }
    }
  }

  @Override
  public AttendanceDao attendanceDao() {
    if (_attendanceDao != null) {
      return _attendanceDao;
    } else {
      synchronized(this) {
        if(_attendanceDao == null) {
          _attendanceDao = new AttendanceDao_Impl(this);
        }
        return _attendanceDao;
      }
    }
  }
}
