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
        return "INSERT OR ABORT INTO `Employee`(`id`,`fingerprints`,`Name`,`Age`,`jobTitle`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getId());
        if (value.getFingerprints() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getFingerprints());
        }
        if (value.getEmployeeName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmployeeName());
        }
        stmt.bindLong(4, value.getAge());
        if (value.getJobTitle() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getJobTitle());
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
        return "UPDATE OR ABORT `Employee` SET `id` = ?,`fingerprints` = ?,`Name` = ?,`Age` = ?,`jobTitle` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Employee value) {
        stmt.bindLong(1, value.getId());
        if (value.getFingerprints() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getFingerprints());
        }
        if (value.getEmployeeName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getEmployeeName());
        }
        stmt.bindLong(4, value.getAge());
        if (value.getJobTitle() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getJobTitle());
        }
        stmt.bindLong(6, value.getId());
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
      final int _cursorIndexOfFingerprints = _cursor.getColumnIndexOrThrow("fingerprints");
      final int _cursorIndexOfEmployeeName = _cursor.getColumnIndexOrThrow("Name");
      final int _cursorIndexOfAge = _cursor.getColumnIndexOrThrow("Age");
      final int _cursorIndexOfJobTitle = _cursor.getColumnIndexOrThrow("jobTitle");
      final List<Employee> _result = new ArrayList<Employee>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Employee _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final byte[] _tmpFingerprints;
        _tmpFingerprints = _cursor.getBlob(_cursorIndexOfFingerprints);
        final String _tmpEmployeeName;
        _tmpEmployeeName = _cursor.getString(_cursorIndexOfEmployeeName);
        final int _tmpAge;
        _tmpAge = _cursor.getInt(_cursorIndexOfAge);
        final String _tmpJobTitle;
        _tmpJobTitle = _cursor.getString(_cursorIndexOfJobTitle);
        _item = new Employee(_tmpId,_tmpFingerprints,_tmpEmployeeName,_tmpAge,_tmpJobTitle);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
