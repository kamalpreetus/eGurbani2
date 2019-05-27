package com.kamalpreetsingh.egurbani.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;

public class ShabadFinder implements IShabadFinder {
  private static final int NUM_OF_TRANSLATION_SOURCES = 6;
  
  private static final String TAG = "ShabadFinder";
  
  private Cursor cursorr;
  
  private void generateShabadLine(TranslationSource paramTranslationSource, ShabadLine paramShabadLine) { setTranslation(paramTranslationSource, paramShabadLine, "translation", "gurmukhi"); }
  
  private TranslationSource getTranslationSrcToAdd(int paramInt) {
    switch (paramInt) {
      case 6:
        Log.d("ShabadFinder", "6 : YOU SHOULD NEVER SEE THIS");
        break;
      case 5:
        return TranslationSource.FAREEDKOT_PUNJABI;
      case 4:
        return TranslationSource.SIKHNET_SPANISH;
      case 3:
        return TranslationSource.MANMOHAN_SINGH_PUNJABI;
      case 2:
        return TranslationSource.MANMOHAN_SINGH_ENGLISH;
      case 1:
        return TranslationSource.SANT_SINGH_ENGLISH;
      case 0:
        return TranslationSource.PROF_SAHIB_SINGH_PUNJABI;
    } 
    Log.d("ShabadFinder", "DEFAULT : YOU SHOULD NEVER SEE THIS");
    return TranslationSource.INVALID;
  }
  
  private void setTranslation(TranslationSource paramTranslationSource, ShabadLine paramShabadLine, String paramString1, String paramString2) {
    paramString1 = this.cursorr.getString(this.cursorr.getColumnIndex(paramString1));
    switch (paramTranslationSource) {
      default:
        return;
      case PROF_SAHIB_SINGH_PUNJABI:
        paramShabadLine.setProfSahibSinghPunjabi(paramString1);
        return;
      case FAREEDKOT_PUNJABI:
        paramShabadLine.setFareedkotPunjabi(paramString1);
        return;
      case SIKHNET_SPANISH:
        paramShabadLine.setSikhnetSpanish(paramString1);
        return;
      case MANMOHAN_SINGH_PUNJABI:
        paramShabadLine.setManmohamSinghPunjabi(paramString1);
        return;
      case MANMOHAN_SINGH_ENGLISH:
        paramShabadLine.setManmohamSinghEnglish(paramString1);
        return;
      case SANT_SINGH_ENGLISH:
        break;
    } 
    paramShabadLine.setGurmukhi_shabad(this.cursorr.getString(this.cursorr.getColumnIndex(paramString2)));
    paramShabadLine.setSantSinghEnglish(paramString1);
  }
  
  private boolean shouldNewShabadBeAdded(int paramInt) { return (paramInt % 6 == 0); }
  
  public FullShabad GetShabad(SQLiteDatabase paramSQLiteDatabase, String paramString) {
    this.cursorr = paramSQLiteDatabase.rawQuery(String.format("SELECT * FROM lines JOIN shabads ON shabads.id = lines.shabad_id JOIN translations ON lines.id = translations.line_id WHERE shabad_id = '%s' ORDER BY lines.order_id", new Object[] { paramString }), new String[0]);
    byte b = 1;
    FullShabad fullShabad = new FullShabad();
    ArrayList arrayList = new ArrayList();
    for (ShabadLine shabadLine = new ShabadLine(); this.cursorr.moveToNext(); shabadLine = shabadLine1) {
      generateShabadLine(getTranslationSrcToAdd(b % 6), shabadLine);
      ShabadLine shabadLine1 = shabadLine;
      if (shouldNewShabadBeAdded(b)) {
        arrayList.add(shabadLine);
        shabadLine1 = new ShabadLine();
      } 
      b++;
    } 
    fullShabad.setShabadLines(arrayList);
    return fullShabad;
  }
}
