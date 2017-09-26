package com.example.game2048;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {

	public Db(Context context) {
		super(context, "db", null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE user("
				+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "date TEXT DEFAULT \"\"," + "score INTEGER \"\","
				+ "name INTEGER \"\"," + "maxnum TEXT DEFAULT \"\")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
