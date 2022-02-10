package com.Dao;

import Model.Attendance;
import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.Converters.Converters;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AttendanceDao_Impl implements AttendanceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Attendance> __insertionAdapterOfAttendance;

  private final EntityDeletionOrUpdateAdapter<Attendance> __deletionAdapterOfAttendance;

  private final EntityDeletionOrUpdateAdapter<Attendance> __updateAdapterOfAttendance;

  public AttendanceDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttendance = new EntityInsertionAdapter<Attendance>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Attendance` (`attendance_Id`,`User_attendance_name`,`Date`,`time_in`,`time_out`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attendance value) {
        stmt.bindLong(1, value.getAttendance_Id());
        if (value.getUser_attendance_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUser_attendance_name());
        }
        final String _tmp;
        _tmp = Converters.fromArrayList(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = Converters.fromArrayList(value.getTime_in());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = Converters.fromArrayList(value.getTime_out());
        if (_tmp_2 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfAttendance = new EntityDeletionOrUpdateAdapter<Attendance>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Attendance` WHERE `attendance_Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attendance value) {
        stmt.bindLong(1, value.getAttendance_Id());
      }
    };
    this.__updateAdapterOfAttendance = new EntityDeletionOrUpdateAdapter<Attendance>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Attendance` SET `attendance_Id` = ?,`User_attendance_name` = ?,`Date` = ?,`time_in` = ?,`time_out` = ? WHERE `attendance_Id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attendance value) {
        stmt.bindLong(1, value.getAttendance_Id());
        if (value.getUser_attendance_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUser_attendance_name());
        }
        final String _tmp;
        _tmp = Converters.fromArrayList(value.getDate());
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = Converters.fromArrayList(value.getTime_in());
        if (_tmp_1 == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, _tmp_1);
        }
        final String _tmp_2;
        _tmp_2 = Converters.fromArrayList(value.getTime_out());
        if (_tmp_2 == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, _tmp_2);
        }
        stmt.bindLong(6, value.getAttendance_Id());
      }
    };
  }

  @Override
  public void insertAttendance(final Attendance attendance) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfAttendance.insert(attendance);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAttendance(final Attendance attendance) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfAttendance.handle(attendance);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAttendance(final Attendance attendance) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfAttendance.handle(attendance);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Attendance> getAttendanceList() {
    final String _sql = "Select * from Attendance";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfAttendanceId = CursorUtil.getColumnIndexOrThrow(_cursor, "attendance_Id");
      final int _cursorIndexOfUserAttendanceName = CursorUtil.getColumnIndexOrThrow(_cursor, "User_attendance_name");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "time_in");
      final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "time_out");
      final List<Attendance> _result = new ArrayList<Attendance>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Attendance _item;
        _item = new Attendance();
        final int _tmpAttendance_Id;
        _tmpAttendance_Id = _cursor.getInt(_cursorIndexOfAttendanceId);
        _item.setAttendance_Id(_tmpAttendance_Id);
        final String _tmpUser_attendance_name;
        _tmpUser_attendance_name = _cursor.getString(_cursorIndexOfUserAttendanceName);
        _item.setUser_attendance_name(_tmpUser_attendance_name);
        final ArrayList<String> _tmpDate;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfDate);
        _tmpDate = Converters.fromString(_tmp);
        _item.setDate(_tmpDate);
        final ArrayList<String> _tmpTime_in;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfTimeIn);
        _tmpTime_in = Converters.fromString(_tmp_1);
        _item.setTime_in(_tmpTime_in);
        final ArrayList<String> _tmpTime_out;
        final String _tmp_2;
        _tmp_2 = _cursor.getString(_cursorIndexOfTimeOut);
        _tmpTime_out = Converters.fromString(_tmp_2);
        _item.setTime_out(_tmpTime_out);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public boolean exists(final String User_attendance_name) {
    final String _sql = "SELECT EXISTS (SELECT 1 FROM attendance WHERE User_attendance_name = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (User_attendance_name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, User_attendance_name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
