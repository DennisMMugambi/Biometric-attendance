package com.Dao;

import Model.FingerPrint;
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

  private final EntityInsertionAdapter __insertionAdapterOfFingerPrint;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFingerPrint;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfFingerPrint;

  public FingerPrintDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFingerPrint = new EntityInsertionAdapter<FingerPrint>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `FingerPrint`(`id`,`fingerprints`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FingerPrint value) {
        stmt.bindLong(1, value.getId());
        if (value.getRefdata() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getRefdata());
        }
      }
    };
    this.__deletionAdapterOfFingerPrint = new EntityDeletionOrUpdateAdapter<FingerPrint>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `FingerPrint` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FingerPrint value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfFingerPrint = new EntityDeletionOrUpdateAdapter<FingerPrint>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `FingerPrint` SET `id` = ?,`fingerprints` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, FingerPrint value) {
        stmt.bindLong(1, value.getId());
        if (value.getRefdata() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindBlob(2, value.getRefdata());
        }
        stmt.bindLong(3, value.getId());
      }
    };
  }

  @Override
  public void insertFingerPrint(FingerPrint fingerPrint) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFingerPrint.insert(fingerPrint);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteFingerPrint(FingerPrint fingerPrint) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFingerPrint.handle(fingerPrint);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFingerPrint(FingerPrint fingerPrint) {
    __db.beginTransaction();
    try {
      __updateAdapterOfFingerPrint.handle(fingerPrint);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<FingerPrint> getFingerPrintList() {
    final String _sql = "Select * from FingerPrint";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfRefdata = _cursor.getColumnIndexOrThrow("fingerprints");
      final List<FingerPrint> _result = new ArrayList<FingerPrint>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final FingerPrint _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final byte[] _tmpRefdata;
        _tmpRefdata = _cursor.getBlob(_cursorIndexOfRefdata);
        _item = new FingerPrint(_tmpId,_tmpRefdata);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
