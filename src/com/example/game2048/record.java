package com.example.game2048;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class record extends ListActivity {

	// private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_record);

		// 显示“排行榜”
		ImageView paihangbang = (ImageView) findViewById(R.id.paihangbang);
		paihangbang.setImageResource(R.drawable.imagepai);

		// 显示数据列表
		setListAdapter(new NewAdapter(this));
	}
	// private SimpleCursorAdapter adapter;
	// private EditText etName, etSex;
	// private Button btnAdd;
	// private Db db;
	// private SQLiteDatabase dbRead, dbwrite;
	// private OnClickListener btnAddListener = new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// ContentValues cv = new ContentValues();
	// cv.put("name", etName.getText().toString());
	// cv.put("sex", etSex.getText().toString());
	//
	// dbwrite.insert("user", null, cv);
	//
	// refreshListView();
	// }
	// };
	//
	// private OnItemLongClickListener ListViewItemLongClickListener = new
	// OnItemLongClickListener() {
	//
	// @Override
	// public boolean onItemLongClick(AdapterView<?> parent, View view,
	// final int position, long id) {
	//
	// Cursor c = adapter.getCursor();
	// c.moveToPosition(position);
	//
	// int itemId = c.getInt(c.getColumnIndex("_id"));
	// dbwrite.delete("user", "_id=?", new String[] { itemId + "" });
	// refreshListView();
	// return true;
	// }
	//
	// };
	//
	// @Override
	// protected void onCreate(Bundle savedInstanceState) {
	// // TODO Auto-generated method stub
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_record);
	//
	// etName = (EditText) findViewById(R.id.etName);
	// etSex = (EditText) findViewById(R.id.etSex);
	// btnAdd = (Button) findViewById(R.id.btnAdd);
	// btnAdd.setOnClickListener(btnAddListener);
	//
	// db = new Db(this);
	//
	// dbwrite = db.getWritableDatabase();
	// dbRead = db.getReadableDatabase();
	//
	// adapter = new SimpleCursorAdapter(this, R.layout.list_cell, null,
	// new String[] { "name", "sex" }, new int[] { R.id.tvName,
	// R.id.tvSex });
	//
	// setListAdapter(adapter);
	// refreshListView();
	//
	// getListView().setOnItemLongClickListener(ListViewItemLongClickListener);
	// }
	//
	// private void refreshListView() {
	// Cursor c = dbRead.query("user", null, null, null, null, null,
	// "score desc");
	// ((CursorAdapter) adapter).changeCursor(c);
	// }
}
