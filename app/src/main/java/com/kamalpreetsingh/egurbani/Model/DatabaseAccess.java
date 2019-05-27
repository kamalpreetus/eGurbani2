package com.kamalpreetsingh.egurbani.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.NonNull;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
  private static DatabaseAccess instance;
  
  private Cursor c = null;
  
  private Cursor c2 = null;
  
  private SQLiteDatabase db;
  
  private SQLiteOpenHelper openHelper;
  
  private DatabaseAccess(Context paramContext) { this.openHelper = new DatabaseOpenHelper(paramContext); }
  
  @NonNull
  private QueryResult getFirstLetterAnywhereQueryResult() {
    String str1 = this.c.getString(this.c.getColumnIndex("gurmukhi"));
    String str2 = this.c.getString(this.c.getColumnIndex("shabad_id"));
    int i = this.c.getInt(this.c.getColumnIndex("source_page"));
    QueryResult queryResult = new QueryResult();
    queryResult.setGurmukhi(str1);
    queryResult.setShabadID(str2);
    queryResult.setSourcePage(i);
    return queryResult;
  }
  
  public static DatabaseAccess getInstance(Context paramContext) {
    if (instance == null)
      instance = new DatabaseAccess(paramContext); 
    return instance;
  }
  
  public void close() {
    if (this.db != null)
      this.db.close(); 
  }
  
  public List<QueryResult> getFirstLetterAnywhere(String paramString1, String paramString2) {
    paramString1 = String.format("SELECT * FROM lines WHERE first_letters LIKE '%%%s%%' ORDER BY order_id", new Object[] { paramString1 });
    this.c = this.db.rawQuery(paramString1, new String[0]);
    ArrayList arrayList = new ArrayList();
    while (this.c.moveToNext()) {
      QueryResult queryResult = getFirstLetterAnywhereQueryResult();
      arrayList.add(queryResult);
      Log.d("DatabaseAccess", queryResult.getGurmukhi());
    } 
    return arrayList;
  }
  
  public FullShabad getShabadByID(String paramString) { return (new ShabadFinder()).GetShabad(this.db, paramString); }
  
  public void open() { this.db = this.openHelper.getWritableDatabase(); }
}


/* Location:              /Volumes/Jetdrive/Safari Downloads/app-debug_decoded_by_apktool/classes-dex2jar.jar!/com/example/kamalpreetsingh/gurbanikhoj/Model/DatabaseAccess.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.2
 */