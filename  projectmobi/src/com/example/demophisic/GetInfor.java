package com.example.demophisic;

import android.database.Cursor;
import android.util.Log;

public class GetInfor {
	public GetInfor()
	{
		helper =  new AcessHelper();
	}
	private AcessHelper helper;
	public int Getquan()
	{
		int quan =3;
		try{
			Cursor mycursor= helper.ExecuteQuery("select quan from thongtin where _id ='1'");
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("quan");
				mycursor.moveToNext();
				quan = Integer.valueOf( mycursor.getString(macot));
				Log.v("getquan",String.valueOf(quan));
			}
			}catch(Exception e){
				Log.v("getquan",e.toString());
			}
		return quan;
	}
	public boolean nhapquan(int quan){
		String insert = "update  thongtin SET quan = '"+String.valueOf(quan)+"' WHERE _id = '1'";

		return helper.ExecutenonQuery(insert);
	}
	public boolean nhaplevel(int quan){
		String insert = "update  thongtin SET level = '"+String.valueOf(quan)+"' WHERE _id = '1'";

		return helper.ExecutenonQuery(insert);
	}
	public boolean nhapthoigian(int quan){
		String insert = "update  thongtin SET thoigian = '"+String.valueOf(quan)+"' WHERE _id = '1'";

		return helper.ExecutenonQuery(insert);
	}
	public boolean nhapdiemcao(int quan){
		String insert = "update  thongtin SET diemcao = '"+String.valueOf(quan)+"' WHERE _id = '1'";

		return helper.ExecutenonQuery(insert);
	}
	public int Getlevel()
	{
		int level = 1;
		try{
		Cursor mycursor= helper.ExecuteQuery("select level from thongtin where _id ='1'");
		if (mycursor != null) {
			int macot = mycursor.getColumnIndex("level");
			mycursor.moveToNext();
			level = Integer.valueOf( mycursor.getString(macot));
		}
		}catch(Exception e){
			Log.v("getlevel",e.toString());
		}
		
		return level;
	}
	public int Getdiemcao()
	{
		int level = 1;
		try{
		Cursor mycursor= helper.ExecuteQuery("select diemcao from thongtin where _id ='1'");
		if (mycursor != null) {
			int macot = mycursor.getColumnIndex("diemcao");
			mycursor.moveToNext();
			level = Integer.valueOf( mycursor.getString(macot));
		}
		}catch(Exception e){
			Log.v("getdiemcao",e.toString());
		}
		
		return level;
	}
	public int Getthoigian()
	{
		int thoigian=20;
		try{
			Cursor mycursor= helper.ExecuteQuery("select thoigian from thongtin where _id ='1'");
			if (mycursor != null) {
				int macot = mycursor.getColumnIndex("thoigian");
				mycursor.moveToNext();
				thoigian = Integer.valueOf( mycursor.getString(macot));
			}
			}catch(Exception e){
				Log.v("getthoigian",e.toString());
			}
		return thoigian;
	}
}
