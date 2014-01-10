package com.example.demophisic;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class AcessHelper {

	final String DB_Name = "thongtin";
	final String Path = "data/data/com.example.demophisic/";
	SQLiteDatabase db;

	public void close() {
		db.close();
	}

	public AcessHelper() {
		String myDbPath = Path + DB_Name;
		try {

			db = SQLiteDatabase.openDatabase(myDbPath, null,
					SQLiteDatabase.CREATE_IF_NECESSARY);
			// Tao bang trong co so du lieu

			try {
				db.execSQL("CREATE TABLE IF NOT EXISTS [thongtin] ([_id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, [quan] TEXT  ,[level] TEXT  NULL,[thoigian] TEXT  NULL,[diemcao] TEXT  NULL)");

				db.execSQL("insert into thongtin(quan, level,thoigian,diemcao) values ('3', '1','30', '0') ");
				Log.v("<Database insert>", "thanh cong");
			} catch (Exception e) {
				Log.v("<Database insert>", e.getMessage());
			}

			// db.close();
			Log.v("<Database create>", "All done!");
		} catch (SQLiteException e) {
			Log.v("<Database create>", e.getMessage());
			// Toast.makeText(getApplicationContext(), "",
			// Toast.LENGTH_SHORT).show();
		}
		// db.close();
	}

	// DataBaseHelper myDbHelper;

	public void open() {

		String myDbPath = Path + DB_Name;
		try {
			db = SQLiteDatabase.openDatabase(myDbPath, null,
					SQLiteDatabase.OPEN_READWRITE);

			Log.v("<Database create>", "All done!");
		} catch (SQLiteException e) {
			Log.v("<Database create>", e.getMessage() + "    " + myDbPath);

		}
	}

	public int Getquan() {
		int quan = 3;
		try {
			Cursor mycursor = db.rawQuery(
					"select quan from thongtin where _id ='1'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("quan");
				mycursor.moveToNext();
				quan = Integer.valueOf(mycursor.getString(macot));
				Log.v("getquan", String.valueOf(quan));
			}
		} catch (Exception e) {
			Log.v("getquan", e.toString());
		}
		return quan;
	}

	public void nhapquan(int quan) {
		db.execSQL("update  thongtin SET quan = '" + String.valueOf(quan)
				+ "' WHERE _id = '1'");

	}

	public void nhaplevel(int quan) {
		db.execSQL("update  thongtin SET level = '" + String.valueOf(quan)
				+ "' WHERE _id = '1'");

	}

	public void nhapthoigian(int quan) {
		db.execSQL("update  thongtin SET thoigian = '" + String.valueOf(quan)
				+ "' WHERE _id = '1'");

	}

	public void nhapdiemcao(int quan) {
		db.execSQL("update  thongtin SET diemcao = '" + String.valueOf(quan)
				+ "' WHERE _id = '1'");

	}

	public int Getlevel() {
		int level = 1;
		try {
			Cursor mycursor = db.rawQuery(
					"select level from thongtin where _id ='1'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("level");
				mycursor.moveToNext();
				level = Integer.valueOf(mycursor.getString(macot));
			}
		} catch (Exception e) {
			Log.v("getlevel", e.toString());
		}

		return level;
	}

	public int Getdiemcao() {
		int level = 1;
		try {
			Cursor mycursor = db.rawQuery(
					"select diemcao from thongtin where _id ='1'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("diemcao");
				mycursor.moveToNext();
				level = Integer.valueOf(mycursor.getString(macot));
			}
		} catch (Exception e) {
			Log.v("getdiemcao", e.toString());
		}

		return level;
	}

	public int Getthoigian() {
		int thoigian = 20;
		try {
			Cursor mycursor = db.rawQuery(
					"select thoigian from thongtin where _id ='1'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("thoigian");
				mycursor.moveToNext();
				thoigian = Integer.valueOf(mycursor.getString(macot));
			}
		} catch (Exception e) {
			Log.v("getthoigian", e.toString());
		}
		return thoigian;
	}

	public boolean deleteTitle(String name, String DATABASE_TABLE) {
		try {
			db.delete(DATABASE_TABLE, "_id='" + name + "'", null);
			return true;
		} catch (SQLiteException e) {
			Log.v("<deleteTitle>", e.getMessage());

			return false;
		}
	}

	public Cursor ExecuteQuery(String command) {
		try {

			Cursor c1 = db.rawQuery(command, null);
			return c1;
		} catch (Exception e) {
			Log.v("<<ExecuteQuery>>", "can`t get data");
			return null;
		}

	}

	public boolean ExecutenonQuery(String command) {
		try {
			db.execSQL(command);
			return true;
		} catch (Exception e) {
			Log.v("<<ExecutenonQuery>>", "can`t do");

		}
		return false;
	}

}
