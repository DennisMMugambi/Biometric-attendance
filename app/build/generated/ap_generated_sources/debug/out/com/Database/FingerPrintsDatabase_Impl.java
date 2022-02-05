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
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(4) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `FingerPrint` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fingerprints` BLOB)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"62c768b70e8837d731f6c3cf65ade786\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `FingerPrint`");
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
        final HashMap<String, TableInfo.Column> _columnsFingerPrint = new HashMap<String, TableInfo.Column>(2);
        _columnsFingerPrint.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFingerPrint.put("fingerprints", new TableInfo.Column("fingerprints", "BLOB", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFingerPrint = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFingerPrint = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFingerPrint = new TableInfo("FingerPrint", _columnsFingerPrint, _foreignKeysFingerPrint, _indicesFingerPrint);
        final TableInfo _existingFingerPrint = TableInfo.read(_db, "FingerPrint");
        if (! _infoFingerPrint.equals(_existingFingerPrint)) {
          throw new IllegalStateException("Migration didn't properly handle FingerPrint(Model.FingerPrint).\n"
                  + " Expected:\n" + _infoFingerPrint + "\n"
                  + " Found:\n" + _existingFingerPrint);
        }
      }
    }, "62c768b70e8837d731f6c3cf65ade786", "7036ae82b1d31a50050ccf19b34b0575");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "FingerPrint");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `FingerPrint`");
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
