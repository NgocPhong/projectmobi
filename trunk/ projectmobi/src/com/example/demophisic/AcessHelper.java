package com.example.demophisic;

/////////////////////////////////////////////////////////////////////////////////
//////////////////// 1112219 - cu ngoc phong/////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////

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
				db.execSQL("CREATE TABLE IF NOT EXISTS [thongtin] ([_id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, [quan] TEXT  ,[level] TEXT  NULL,[thoigian] TEXT  NULL,[Tien] TEXT  NULL)");
				db.execSQL("CREATE TABLE IF NOT EXISTS [Items] ([_id] INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, [Ten] TEXT  ,[GiaTien] TEXT  NULL)");

				db.execSQL("insert into thongtin(_id,quan, level,thoigian,Tien) values ('1','3', '1','30', '3') ");
				// insert table Items
				db.execSQL("insert into Items(_id, Ten,GiaTien) values ('1','ThoiGian', '3') ");
				db.execSQL("insert into Items(_id, Ten,GiaTien) values ('2','Quan', '3') ");
			
				Log.v("<Database insert>", "thanh cong");
			} catch (Exception e) {
				Log.v("<Database insert>", e.getMessage());
			}

			
			Log.v("<Database create>", "All done!");
		} catch (SQLiteException e) {
			Log.v("<Database create>", e.getMessage());
			
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
				mycursor.close();
			}
		} catch (Exception e) {
			Log.v("getquan", e.toString());
		}
	
		return quan;
	}
	public int GetItemsCost(int id) {
		int tien = 0;
		try {
			Cursor mycursor = db.rawQuery(
					"select GiaTien from Items where _id ='"+id+"'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("GiaTien");
				mycursor.moveToNext();
				tien = Integer.valueOf(mycursor.getString(macot));
				Log.v("giatien", String.valueOf(tien));
				mycursor.close();
			}
		} catch (Exception e) {
			Log.v("giatien", e.toString());
		}
	
		return tien;
	}
	public void nhapGiaItem(int id,int cost) {
		db.execSQL("update  Items SET GiaTien = '" + String.valueOf(cost)
				+ "' WHERE _id = '"+id+"'");

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

	public void nhapTien(int quan) {
		db.execSQL("update  thongtin SET Tien = '" + String.valueOf(quan)
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
				mycursor.close();
			}
		} catch (Exception e) {
			Log.v("getlevel", e.toString());
		}

		return level;
	}

	public int GetTien() {
		int Tien = 1;
		try {
			Cursor mycursor = db.rawQuery(
					"select Tien from thongtin where _id ='1'", null);
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("Tien");
				mycursor.moveToNext();
				Tien = Integer.valueOf(mycursor.getString(macot));
				Log.v("getTien", mycursor.getString(macot));
				mycursor.close();
			}
		} catch (Exception e) {
			Log.v("getTien", e.toString());
		}

		return Tien;
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
				mycursor.close();
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
