package com.example.game2048;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NewAdapter extends BaseAdapter {

	private Db db;
	private SQLiteDatabase dbRead, dbwrite;
	private Context context = null;
	private ListCellData[] data = new ListCellData[11];
	private int count = 0;
	private Cursor c = null;
	private int[] Icon = { R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, R.drawable.image8,
			R.drawable.image9, R.drawable.image10 };

	public NewAdapter(Context context) {
		this.context = context;
	}

	private boolean getdata() {
		db = new Db(MainActivity.getMainActivity());
		dbRead = db.getReadableDatabase();
		dbwrite = db.getWritableDatabase();
		c = dbRead.query("user", null, null, null, null, null, "score desc");
		if (c.getCount() == 0) {
			return false;
		}
		count = 0;
		while (c.moveToNext()) {

			data[count] = new ListCellData(
					c.getString(c.getColumnIndex("date")), c.getString(c
							.getColumnIndex("score")), c.getString(c
							.getColumnIndex("maxnum")), Icon[count],
					c.getString(c.getColumnIndex("name")));
			count++;
			if (count == 10) {
				break;
			}
		}
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LinearLayout ll = null;

		if (convertView != null) {
			ll = (LinearLayout) convertView;
		} else {
			ll = (LinearLayout) LayoutInflater.from(getContext()).inflate(
					R.layout.list_cell, null);
		}

		ImageView icon = (ImageView) ll.findViewById(R.id.icon);
		TextView date = (TextView) ll.findViewById(R.id.tvDate);
		TextView score = (TextView) ll.findViewById(R.id.tvScore);
		TextView maxnum = (TextView) ll.findViewById(R.id.tvMaxnum);
		TextView username = (TextView) ll.findViewById(R.id.tvUsername);

		icon.setImageResource(data[position].iconId);
		date.setText(data[position].date);
		score.setText(data[position].score);
		maxnum.setText(data[position].maxNum);
		username.setText(data[position].username);

		return ll;
	}

	private Context getContext() {
		// TODO Auto-generated method stub
		return context;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public ListCellData getItem(int position) {
		// TODO Auto-generated method stub
		return data[position];
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (getdata())
			return count >= 10 ? 10 : count;
		else
			return 0;
	}
}
