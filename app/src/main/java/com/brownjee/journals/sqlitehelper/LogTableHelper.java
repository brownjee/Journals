package com.brownjee.journals.sqlitehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LogTableHelper extends SQLiteOpenHelper{


	private static final String DATABASE_NAME="log.db";
	private static final int DATABASE_VERSION=1;
	
	public static final String TABLE_LOG="log";
	public static final String LOG_ID="_id";
	public static final String LOG_TITLE="title";
	public static final String LOG_DATA="summary";
	public static final String LOG_DATE="date";
	
	
	
	public LogTableHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	
	@Override
	public void onCreate(SQLiteDatabase db) {
		SqliteHelper.onCreate(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		SqliteHelper.onUpgrade(db, oldVersion, newVersion);
	}

	public boolean insertLog(String logtitle,String logsummary,String date) {
		
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues contentValues=new ContentValues();
		
		  contentValues.put("title",logtitle);
	      contentValues.put("summary",logsummary);
	      contentValues.put("date",date);
	     
	      db.insert("log",null,contentValues);
		return true;
	}
	
	public boolean updateLog(String id,String ulogtitle,String ulogsummary)
	{
		/*
		 * UPDATE table_name
         *  SET column1 = value1, column2 = value2...., columnN = valueN
         *  WHERE [condition];
		 */
		
		SQLiteDatabase db=this.getWritableDatabase();
	
		ContentValues contentValues=new ContentValues();
		
		contentValues.put("title",ulogtitle);
		contentValues.put("summary", ulogsummary);
		
		db.update("log",contentValues,"_id="+id,null);
		return true;
		
	}
	
	public Cursor fetchParticular(String id)
	{
		String sql="Select * from log where _id="+id;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cr=db.rawQuery(sql,null);
		return cr;
	}
	
	public Cursor fetchReport() {
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor mCursor = db.query(TABLE_LOG, new String[] {LOG_ID,
			    LOG_TITLE, LOG_DATA,LOG_DATE}, 
			    null, null, null, null, null);
			 
			  if (mCursor != null) {
			   mCursor.moveToFirst();
			  }
			  return mCursor;
	}
	
	public Cursor fetchAll()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cr=db.rawQuery("select * from log",null);
		return cr;
	}
	
	public Boolean Delete(String id)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_LOG, LOG_ID + "=" + id, null);
		return true;
	}
	

}
