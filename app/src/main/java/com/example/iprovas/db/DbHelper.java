package com.example.iprovas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "iProvas.db";
  private static final int DATABASE_VERSION = 1;
  private final String CREATE_TABLE_UA = "CREATE TABLE UA (ID VARCHAR(100) PRIMARY KEY, name VARCHAR(100));";
  private final String CREATE_TABLE_EVENT = "CREATE TABLE Event (ID VARCHAR(100) PRIMARY KEY, name VARCHAR(100), uaId VARCHAR(100), FOREIGN KEY (uaId) REFERENCES UA(ID));";

  public DbHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_TABLE_UA);
    db.execSQL(CREATE_TABLE_EVENT);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

  }
}