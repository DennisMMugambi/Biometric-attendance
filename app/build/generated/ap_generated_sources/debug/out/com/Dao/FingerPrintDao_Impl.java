package com.Dao;

import Model.Employee;
import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class FingerPrintDao_Impl implements FingerPrintDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfEmployee;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfEmployee;

  public FingerPrintDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEmployee = new EntityInsertionAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Employee`(`id`,`rightThumb`,`rightIndex`,`leftThumb`,`leftIndex`,`Name`,`Age`,`jobTitle`,`identification`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getId());
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
      }
    };
    this.__deletionAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Employee` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfEmployee = new EntityDeletionOrUpdateAdapter<Employee>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Employee` SET `id` = ?,`rightThumb` = ?,`rightIndex` = ?,`leftThumb` = ?,`leftIndex` = ?,`Name` = ?,`Age` = ?,`jobTitle` = ?,`identification` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getId());
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
        stmt.bindLong(10, value.getId());
      }
    };
  }

  @Override
  public void insertEmployee(Employee employee) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfEmployee.insert(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteEmployee(Employee employee) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfEmployee.handle(employee);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateEmployee(Employee employee) {
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
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfRightThumb = _cursor.getColumnIndexOrThrow("rightThumb");
      final int _cursorIndexOfRightIndex = _cursor.getColumnIndexOrThrow("rightIndex");
      final int _cursorIndexOfLeftThumb = _cursor.getColumnIndexOrThrow("leftThumb");
      final int _cursorIndexOfLeftIndex = _cursor.getColumnIndexOrThrow("leftIndex");
      final int _cursorIndexOfEmployeeName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("Age");
      final int _cursorIndexOfJobTitle = _cursor.getColumnIndexOrThrow("jobTitle");
      final int _cursorIndexOfIdentification = _cursor.getColumnIndexOrThrow("identification");
      final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Employee _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
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
        _item = new Employee(_tmpId,_tmpRightThumb,_tmpRightIndex,_tmpLeftThumb,_tmpLeftIndex,_tmpEmployeeName,_tmpAge,_tmpJobTitle,_tmpIdentification);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
