package com.kamalpreetsingh.egurbani.Model;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
  private static final String DATABASE_NAME = "shabadOSdb.sqlite";
  
  private static final int DATABASE_VERSION = 1;
  
  public DatabaseOpenHelper(Context paramContext) { super(paramContext, "shabadOSdb.sqlite", null, 1); }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/Model/DatabaseOpenHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */