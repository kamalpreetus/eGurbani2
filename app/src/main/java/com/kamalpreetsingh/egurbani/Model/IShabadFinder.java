package com.kamalpreetsingh.egurbani.Model;

import android.database.sqlite.SQLiteDatabase;

public interface IShabadFinder {
  FullShabad GetShabad(SQLiteDatabase paramSQLiteDatabase, String paramString);
}
