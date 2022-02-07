package com.Database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.Dao.FingerPrintDao;
import com.Dao.FingerPrintDao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class FingerPrintsDatabase_Impl extends FingerPrintsDatabase {
  private volatile FingerPrintDao _fingerPrintDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(5) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Employee` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `rightThumb` BLOB, `rightIndex` BLOB, `leftThumb` BLOB, `leftIndex` BLOB, `Name` TEXT, `Age` INTEGER NOT NULL, `jobTitle` TEXT, `identification` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"37aee363a338a3e709ad259443e3bb30\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Employee`");
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
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsEmployee = new HashMap<String, TableInfo.Column>(9);
        _columnsEmployee.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsEmployee.put("rightThumb", new TableInfo.Column("rightThumb", "BLOB", false, 0));
        _columnsEmployee.put("rightIndex", new TableInfo.Column("rightIndex", "BLOB", false, 0));
        _columnsEmployee.put("leftThumb", new TableInfo.Column("leftThumb", "BLOB", false, 0));
        _columnsEmployee.put("leftIndex", new TableInfo.Column("leftIndex", "BLOB", false, 0));
        _columnsEmployee.put("Name", new TableInfo.Column("Name", "TEXT", false, 0));
        _columnsEmployee.put("Age", new TableInfo.Column("Age", "INTEGER", true, 0));
        _columnsEmployee.put("jobTitle", new TableInfo.Column("jobTitle", "TEXT", false, 0));
        _columnsEmployee.put("identification", new TableInfo.Column("identification", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEmployee = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEmployee = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEmployee = new TableInfo("Employee", _columnsEmployee, _foreignKeysEmployee, _indicesEmployee);
        final TableInfo _existingEmployee = TableInfo.read(_db, "Employee");
        if (! _infoEmployee.equals(_existingEmployee)) {
          throw new IllegalStateException("Migration didn't properly handle Employee(Model.Employee).\n"
                  + " Expected:\n" + _infoEmployee + "\n"
                  + " Found:\n" + _existingEmployee);
        }
      }
    }, "37aee363a338a3e709ad259443e3bb30", "e20374727950c273922239867a4ec4b2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Employee");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Employee`");
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
}
