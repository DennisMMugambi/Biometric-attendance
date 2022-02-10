package com.Dao;

import Model.Attendance;
import Model.Employee;
import Model.UserAndAttendance;
import android.database.Cursor;
import androidx.collection.LongSparseArray;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.Converters.Converters;
import java.lang.Float;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FingerPrintDao_Impl implements FingerPrintDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Employee> __insertionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __deletionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter<Employee> __updateAdapterOfEmployee;

  public FingerPrintDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmployee = new EntityInsertionAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Employee` (`employee_id`,`rightThumb`,`rightIndex`,`leftThumb`,`leftIndex`,`employeeName`,`Age`,`jobTitle`,`identification`,`attendance_score`,`parent_contact`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getEmployee_id());
        if (value.getRightThumb() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getRightThumb());
        }
        if (value.getRightIndex() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindBlob(3, value.getRightIndex());
        }
        if (value.getLeftThumb() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindBlob(4, value.getLeftThumb());
        }
        if (value.getLeftIndex() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindBlob(5, value.getLeftIndex());
        }
        if (value.getEmployeeName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmployeeName());
        }
        stmt.bindLong(7, value.getAge());
        if (value.getJobTitle() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getJobTitle());
        }
        if (value.getIdentification() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getIdentification());
        }
        if (value.getAttendanceScore() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getAttendanceScore());
        }
        if (value.getPhone_number() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getPhone_number());
        }
      }
    };
    this.__deletionAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Employee` WHERE `employee_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getEmployee_id());
      }
    };
    this.__updateAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Employee` SET `employee_id` = ?,`rightThumb` = ?,`rightIndex` = ?,`leftThumb` = ?,`leftIndex` = ?,`employeeName` = ?,`Age` = ?,`jobTitle` = ?,`identification` = ?,`attendance_score` = ?,`parent_contact` = ? WHERE `employee_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getEmployee_id());
        if (value.getRightThumb() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getRightThumb());
        }
        if (value.getRightIndex() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindBlob(3, value.getRightIndex());
        }
        if (value.getLeftThumb() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindBlob(4, value.getLeftThumb());
        }
        if (value.getLeftIndex() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindBlob(5, value.getLeftIndex());
        }
        if (value.getEmployeeName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmployeeName());
        }
        stmt.bindLong(7, value.getAge());
        if (value.getJobTitle() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getJobTitle());
        }
        if (value.getIdentification() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getIdentification());
        }
        if (value.getAttendanceScore() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindDouble(10, value.getAttendanceScore());
        }
        if (value.getPhone_number() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getPhone_number());
        }
        stmt.bindLong(12, value.getEmployee_id());
      }
    };
  }

  @Override
  public void insertEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEmployee.insert(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEmployee(final Employee employee) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Employee> getEmployeeList() {
    final String _sql = "Select * from Employee";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employee_id");
      final int _cursorIndexOfRightThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "rightThumb");
      final int _cursorIndexOfRightIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "rightIndex");
      final int _cursorIndexOfLeftThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "leftThumb");
      final int _cursorIndexOfLeftIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "leftIndex");
      final int _cursorIndexOfEmployeeName = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeName");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfJobTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitle");
      final int _cursorIndexOfIdentification = CursorUtil.getColumnIndexOrThrow(_cursor, "identification");
      final int _cursorIndexOfAttendanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "attendance_score");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_contact");
      final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Employee _item;
        final byte[] _tmpRightThumb;
        _tmpRightThumb = _cursor.getBlob(_cursorIndexOfRightThumb);
        final byte[] _tmpRightIndex;
        _tmpRightIndex = _cursor.getBlob(_cursorIndexOfRightIndex);
        final byte[] _tmpLeftThumb;
        _tmpLeftThumb = _cursor.getBlob(_cursorIndexOfLeftThumb);
        final byte[] _tmpLeftIndex;
        _tmpLeftIndex = _cursor.getBlob(_cursorIndexOfLeftIndex);
        final String _tmpEmployeeName;
        _tmpEmployeeName = _cursor.getString(_cursorIndexOfEmployeeName);
        final int _tmpAge;
        _tmpAge = _cursor.getInt(_cursorIndexOfAge);
        final String _tmpJobTitle;
        _tmpJobTitle = _cursor.getString(_cursorIndexOfJobTitle);
        final String _tmpIdentification;
        _tmpIdentification = _cursor.getString(_cursorIndexOfIdentification);
        final Float _tmpAttendanceScore;
        if (_cursor.isNull(_cursorIndexOfAttendanceScore)) {
          _tmpAttendanceScore = null;
        } else {
          _tmpAttendanceScore = _cursor.getFloat(_cursorIndexOfAttendanceScore);
        }
        final String _tmpPhone_number;
        _tmpPhone_number = _cursor.getString(_cursorIndexOfPhoneNumber);
        _item = new Employee(_tmpRightThumb,_tmpRightIndex,_tmpLeftThumb,_tmpLeftIndex,_tmpEmployeeName,_tmpAge,_tmpJobTitle,_tmpIdentification,_tmpAttendanceScore,_tmpPhone_number);
        final int _tmpEmployee_id;
        _tmpEmployee_id = _cursor.getInt(_cursorIndexOfEmployeeId);
        _item.setEmployee_id(_tmpEmployee_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Employee> getSearchedEmployees(final String employee_name) {
    final String _sql = "Select * from Employee WHERE employeeName LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (employee_name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, employee_name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employee_id");
      final int _cursorIndexOfRightThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "rightThumb");
      final int _cursorIndexOfRightIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "rightIndex");
      final int _cursorIndexOfLeftThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "leftThumb");
      final int _cursorIndexOfLeftIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "leftIndex");
      final int _cursorIndexOfEmployeeName = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeName");
      final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
      final int _cursorIndexOfJobTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitle");
      final int _cursorIndexOfIdentification = CursorUtil.getColumnIndexOrThrow(_cursor, "identification");
      final int _cursorIndexOfAttendanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "attendance_score");
      final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_contact");
      final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Employee _item;
        final byte[] _tmpRightThumb;
        _tmpRightThumb = _cursor.getBlob(_cursorIndexOfRightThumb);
        final byte[] _tmpRightIndex;
        _tmpRightIndex = _cursor.getBlob(_cursorIndexOfRightIndex);
        final byte[] _tmpLeftThumb;
        _tmpLeftThumb = _cursor.getBlob(_cursorIndexOfLeftThumb);
        final byte[] _tmpLeftIndex;
        _tmpLeftIndex = _cursor.getBlob(_cursorIndexOfLeftIndex);
        final String _tmpEmployeeName;
        _tmpEmployeeName = _cursor.getString(_cursorIndexOfEmployeeName);
        final int _tmpAge;
        _tmpAge = _cursor.getInt(_cursorIndexOfAge);
        final String _tmpJobTitle;
        _tmpJobTitle = _cursor.getString(_cursorIndexOfJobTitle);
        final String _tmpIdentification;
        _tmpIdentification = _cursor.getString(_cursorIndexOfIdentification);
        final Float _tmpAttendanceScore;
        if (_cursor.isNull(_cursorIndexOfAttendanceScore)) {
          _tmpAttendanceScore = null;
        } else {
          _tmpAttendanceScore = _cursor.getFloat(_cursorIndexOfAttendanceScore);
        }
        final String _tmpPhone_number;
        _tmpPhone_number = _cursor.getString(_cursorIndexOfPhoneNumber);
        _item = new Employee(_tmpRightThumb,_tmpRightIndex,_tmpLeftThumb,_tmpLeftIndex,_tmpEmployeeName,_tmpAge,_tmpJobTitle,_tmpIdentification,_tmpAttendanceScore,_tmpPhone_number);
        final int _tmpEmployee_id;
        _tmpEmployee_id = _cursor.getInt(_cursorIndexOfEmployeeId);
        _item.setEmployee_id(_tmpEmployee_id);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<UserAndAttendance> getUsersAndAttendance() {
    final String _sql = "SELECT * FROM Employee";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
      try {
        final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employee_id");
        final int _cursorIndexOfRightThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "rightThumb");
        final int _cursorIndexOfRightIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "rightIndex");
        final int _cursorIndexOfLeftThumb = CursorUtil.getColumnIndexOrThrow(_cursor, "leftThumb");
        final int _cursorIndexOfLeftIndex = CursorUtil.getColumnIndexOrThrow(_cursor, "leftIndex");
        final int _cursorIndexOfEmployeeName = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeName");
        final int _cursorIndexOfAge = CursorUtil.getColumnIndexOrThrow(_cursor, "Age");
        final int _cursorIndexOfJobTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "jobTitle");
        final int _cursorIndexOfIdentification = CursorUtil.getColumnIndexOrThrow(_cursor, "identification");
        final int _cursorIndexOfAttendanceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "attendance_score");
        final int _cursorIndexOfPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "parent_contact");
        final LongSparseArray<Attendance> _collectionAttendance = new LongSparseArray<Attendance>();
        while (_cursor.moveToNext()) {
          if (!_cursor.isNull(_cursorIndexOfEmployeeId)) {
            final long _tmpKey = _cursor.getLong(_cursorIndexOfEmployeeId);
            _collectionAttendance.put(_tmpKey, null);
          }
        }
        _cursor.moveToPosition(-1);
        __fetchRelationshipAttendanceAsModelAttendance(_collectionAttendance);
        final List<UserAndAttendance> _result = new ArrayList<UserAndAttendance>(_cursor.getCount());
        while(_cursor.moveToNext()) {
          final UserAndAttendance _item;
          final Employee _tmpEmployee;
          if (! (_cursor.isNull(_cursorIndexOfEmployeeId) && _cursor.isNull(_cursorIndexOfRightThumb) && _cursor.isNull(_cursorIndexOfRightIndex) && _cursor.isNull(_cursorIndexOfLeftThumb) && _cursor.isNull(_cursorIndexOfLeftIndex) && _cursor.isNull(_cursorIndexOfEmployeeName) && _cursor.isNull(_cursorIndexOfAge) && _cursor.isNull(_cursorIndexOfJobTitle) && _cursor.isNull(_cursorIndexOfIdentification) && _cursor.isNull(_cursorIndexOfAttendanceScore) && _cursor.isNull(_cursorIndexOfPhoneNumber))) {
            final byte[] _tmpRightThumb;
            _tmpRightThumb = _cursor.getBlob(_cursorIndexOfRightThumb);
            final byte[] _tmpRightIndex;
            _tmpRightIndex = _cursor.getBlob(_cursorIndexOfRightIndex);
            final byte[] _tmpLeftThumb;
            _tmpLeftThumb = _cursor.getBlob(_cursorIndexOfLeftThumb);
            final byte[] _tmpLeftIndex;
            _tmpLeftIndex = _cursor.getBlob(_cursorIndexOfLeftIndex);
            final String _tmpEmployeeName;
            _tmpEmployeeName = _cursor.getString(_cursorIndexOfEmployeeName);
            final int _tmpAge;
            _tmpAge = _cursor.getInt(_cursorIndexOfAge);
            final String _tmpJobTitle;
            _tmpJobTitle = _cursor.getString(_cursorIndexOfJobTitle);
            final String _tmpIdentification;
            _tmpIdentification = _cursor.getString(_cursorIndexOfIdentification);
            final Float _tmpAttendanceScore;
            if (_cursor.isNull(_cursorIndexOfAttendanceScore)) {
              _tmpAttendanceScore = null;
            } else {
              _tmpAttendanceScore = _cursor.getFloat(_cursorIndexOfAttendanceScore);
            }
            final String _tmpPhone_number;
            _tmpPhone_number = _cursor.getString(_cursorIndexOfPhoneNumber);
            _tmpEmployee = new Employee(_tmpRightThumb,_tmpRightIndex,_tmpLeftThumb,_tmpLeftIndex,_tmpEmployeeName,_tmpAge,_tmpJobTitle,_tmpIdentification,_tmpAttendanceScore,_tmpPhone_number);
            final int _tmpEmployee_id;
            _tmpEmployee_id = _cursor.getInt(_cursorIndexOfEmployeeId);
            _tmpEmployee.setEmployee_id(_tmpEmployee_id);
          }  else  {
            _tmpEmployee = null;
          }
          Attendance _tmpAttendance = null;
          if (!_cursor.isNull(_cursorIndexOfEmployeeId)) {
            final long _tmpKey_1 = _cursor.getLong(_cursorIndexOfEmployeeId);
            _tmpAttendance = _collectionAttendance.get(_tmpKey_1);
          }
          _item = new UserAndAttendance();
          _item.employee = _tmpEmployee;
          _item.attendance = _tmpAttendance;
          _result.add(_item);
        }
        __db.setTransactionSuccessful();
        return _result;
      } finally {
        _cursor.close();
        _statement.release();
      }
    } finally {
      __db.endTransaction();
    }
  }

  private void __fetchRelationshipAttendanceAsModelAttendance(final LongSparseArray<Attendance> _map) {
    if (_map.isEmpty()) {
      return;
    }
    // check if the size is too big, if so divide;
    if(_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      LongSparseArray<Attendance> _tmpInnerMap = new LongSparseArray<Attendance>(androidx.room.RoomDatabase.MAX_BIND_PARAMETER_CNT);
      int _tmpIndex = 0;
      int _mapIndex = 0;
      final int _limit = _map.size();
      while(_mapIndex < _limit) {
        _tmpInnerMap.put(_map.keyAt(_mapIndex), _map.valueAt(_mapIndex));
        _mapIndex++;
        _tmpIndex++;
        if(_tmpIndex == RoomDatabase.MAX_BIND_PARAMETER_CNT) {
          __fetchRelationshipAttendanceAsModelAttendance(_tmpInnerMap);
          _tmpInnerMap = new LongSparseArray<Attendance>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
          _tmpIndex = 0;
        }
      }
      if(_tmpIndex > 0) {
        __fetchRelationshipAttendanceAsModelAttendance(_tmpInnerMap);
      }
      return;
    }
    StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `attendance_Id`,`User_attendance_name`,`Date`,`time_in`,`time_out` FROM `Attendance` WHERE `attendance_Id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex ++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "attendance_Id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfAttendanceId = CursorUtil.getColumnIndexOrThrow(_cursor, "attendance_Id");
      final int _cursorIndexOfUserAttendanceName = CursorUtil.getColumnIndexOrThrow(_cursor, "User_attendance_name");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "Date");
      final int _cursorIndexOfTimeIn = CursorUtil.getColumnIndexOrThrow(_cursor, "time_in");
      final int _cursorIndexOfTimeOut = CursorUtil.getColumnIndexOrThrow(_cursor, "time_out");
      while(_cursor.moveToNext()) {
        if (!_cursor.isNull(_itemKeyIndex)) {
          final long _tmpKey = _cursor.getLong(_itemKeyIndex);
          if (_map.containsKey(_tmpKey)) {
            final Attendance _item_1;
            _item_1 = new Attendance();
            final int _tmpAttendance_Id;
            _tmpAttendance_Id = _cursor.getInt(_cursorIndexOfAttendanceId);
            _item_1.setAttendance_Id(_tmpAttendance_Id);
            final String _tmpUser_attendance_name;
            _tmpUser_attendance_name = _cursor.getString(_cursorIndexOfUserAttendanceName);
            _item_1.setUser_attendance_name(_tmpUser_attendance_name);
            final ArrayList<String> _tmpDate;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfDate);
            _tmpDate = Converters.fromString(_tmp);
            _item_1.setDate(_tmpDate);
            final ArrayList<String> _tmpTime_in;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfTimeIn);
            _tmpTime_in = Converters.fromString(_tmp_1);
            _item_1.setTime_in(_tmpTime_in);
            final ArrayList<String> _tmpTime_out;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfTimeOut);
            _tmpTime_out = Converters.fromString(_tmp_2);
            _item_1.setTime_out(_tmpTime_out);
            _map.put(_tmpKey, _item_1);
          }
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
