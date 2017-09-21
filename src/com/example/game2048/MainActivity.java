package com.example.game2048;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	public MainActivity() {
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

	private TextView tvScore, tvBestScore;
	private static MainActivity mainActivity = null;
	private static int bestScore = 0;
	private int score;
	public static boolean btnflag = false;

	public static MainActivity getMainActivity() {
		return mainActivity;
	}

}
