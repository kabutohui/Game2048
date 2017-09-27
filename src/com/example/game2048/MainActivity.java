package com.example.game2048;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends Activity {

	public MainActivity() {
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.activity_main);

		tvScore = (TextView) findViewById(R.id.tvScore);
		tvBestScore = (TextView) findViewById(R.id.tvBestScore);

		findViewById(R.id.Restart).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {

						GameView.getGameView().startGame();
					}
				});

		findViewById(R.id.Record).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(MainActivity.this,
								record.class));

					}
				});
	}

	public void clearScore() {
		score = 0;
		showScore();
	}

	public void showScore() {
		tvScore.setText(score + "");
		bestScore = getBestScore();
		if (bestScore < score) {
			bestScore = score;
		}
		tvBestScore.setText(bestScore + "");
	}

	public void addScore(int s) {
		score += s;
		showScore();
	}

	public int getScore() {
		return score;
	}

	public static int getBestScore() {

		Db db = new Db(MainActivity.getMainActivity());
		SQLiteDatabase dbRead = db.getReadableDatabase();
		Cursor c = dbRead.query("user", null, null, null, null, null,
				"score desc");
		while (c.moveToNext()) {
			bestScore = Integer
					.parseInt((c.getString(c.getColumnIndex("score"))));
			break;
		}
		return bestScore;
	}

	private TextView tvScore, tvBestScore;
	private static MainActivity mainActivity = null;
	public static int bestScore = 0;
	private int score;
	public static boolean btnflag = false;

	public static MainActivity getMainActivity() {
		return mainActivity;
	}

}
