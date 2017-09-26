package com.example.game2048;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		gameView = this;
		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		gameView = this;
		initGameView();
	}

	public GameView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		gameView = this;
		initGameView();
	}

	private void initGameView() {
		setColumnCount(4);
		setBackgroundColor(0xffbbada0);
		setOnTouchListener(new View.OnTouchListener() {
			private float startX, startY, offsetX, offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_UP:
					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}
					colorChanged();
					break;
				default:
					break;
				}
				return true;
			}
		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 10) / 4;

		addCards(cardWidth, cardWidth);
		startGame();
	}

	private void addCards(int cardWidth, int cardHeight) {
		Card c;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(0);

				addView(c, cardWidth, cardHeight);

				cardMap[x][y] = c;
			}
		}
	}

	public void startGame() {

		MainActivity.getMainActivity().clearScore();
		clearAll();
		addRandomNum();
		addRandomNum();
		colorChanged();
	}

	private void clearAll() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardMap[x][y].setNum(0);
			}
		}
		colorChanged();
	}

	private void addRandomNum() {

		emptyPoints.clear();

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() <= 0) {
					emptyPoints.add(new Point(x, y));
				}
			}
		}
		Point p = emptyPoints
				.remove((int) (Math.random() * emptyPoints.size()));
		cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	private void swipeLeft() {

		boolean merge = false;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cardMap[x1][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							merge = true;
							x--;

						} else if (cardMap[x][y].equals(cardMap[x1][y])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x1][y].setNum(0);

							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}

		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeRight() {

		boolean merge = false;

		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardMap[x1][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x1][y].getNum());
							cardMap[x1][y].setNum(0);
							x++;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x1][y])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x1][y].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeUp() {

		boolean merge = false;

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardMap[x][y1].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							y--;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void swipeDown() {

		boolean merge = false;

		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardMap[x][y1].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x][y1].getNum());
							cardMap[x][y1].setNum(0);
							y++;
							merge = true;
						} else if (cardMap[x][y].equals(cardMap[x][y1])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x][y1].setNum(0);
							MainActivity.getMainActivity().addScore(
									cardMap[x][y].getNum());
							merge = true;
						}
						break;
					}
				}
			}
		}
		if (merge) {
			addRandomNum();
			checkComplete();
		}
	}

	private void checkComplete() {

		boolean failcomplete = true, successcomplete = false;

		ALL: for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() == 0
						|| (x > 0 && cardMap[x][y].equals(cardMap[x - 1][y]))
						|| (x < 3 && cardMap[x][y].equals(cardMap[x + 1][y]))
						|| (y > 0 && cardMap[x][y].equals(cardMap[x][y - 1]))
						|| (y < 3 && cardMap[x][y].equals(cardMap[x][y + 1]))) {
					failcomplete = false;
					break ALL;
				}
			}
		}

		if (failcomplete) {

			db = new Db(MainActivity.getMainActivity());
			dbwrite = db.getWritableDatabase();
			cv = new ContentValues();

			SimpleDateFormat sDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String date = sDateFormat.format(new java.util.Date());

			cv.put("date", date);
			cv.put("score", MainActivity.getMainActivity().getScore());
			cv.put("maxnum", getMaxnum());

			AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
			builder.setTitle("游戏结束，请英雄留下您的称谓");
			// 通过LayoutInflater来加载一个xml的布局文件作为一个View对象
			View view = LayoutInflater.from(getContext()).inflate(
					R.layout.dialog, null);
			// 设置我们自己定义的布局文件作为弹出框的Content
			builder.setView(view);

			final EditText username = (EditText) view
					.findViewById(R.id.edUsername);

			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							String name = username.getText().toString();
							// 写入数据库
							cv.put("name", name);
							dbwrite.insert("user", null, cv);
							dbwrite.close();
						}
					});

			builder.setNegativeButton("算了",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(getContext(), "告辞！",
									Toast.LENGTH_SHORT).show();
						}
					});
			builder.show();
			// new AlertDialog.Builder(getContext())
			// .setTitle("你好")
			// .setMessage("游戏已结束")
			// .setPositiveButton("重来一次",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// // TODO Auto-generated method stub
			// startGame();
			// }
			// })
			// .setNegativeButton("退出",
			// new DialogInterface.OnClickListener() {
			//
			// @Override
			// public void onClick(DialogInterface dialog,
			// int which) {
			// // TODO Auto-generated method stub
			// MainActivity.getMainActivity().finish();
			// }
			// }).show();
		}

		ALL: for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() == 2048) {
					successcomplete = true;
					count++;
					break ALL;
				}
			}
		}
		if (successcomplete && (count == 1)) {
			new AlertDialog.Builder(getContext())
					.setTitle("恭喜")
					.setMessage(
							"你已完成2048，得分为："
									+ MainActivity.getMainActivity().getScore()
									+ ",游戏结束")
					.setPositiveButton("再来一次",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									startGame();
								}
							})
					.setNegativeButton("继续游戏",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

								}
							}).show();
		}
	}

	private int getMaxnum() {
		int max = 0;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() > max)
					max = cardMap[x][y].getNum();
			}
		}
		return max;
	}

	private void colorChanged() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				cardMap[x][y].setBackgroundColor(Num2color(cardMap[x][y]
						.getNum()));
			}
		}
	}

	private int Num2color(int num) {
		int color = 0;
		switch (num) {
		case 2:
			color = 0xffeee4da;
			break;
		case 4:
			color = 0xffede0c8;
			break;
		case 8:
			color = 0xfff2b179;
			break;
		case 16:
			color = 0xfff59563;
			break;
		case 32:
			color = 0xfff67c5f;
			break;
		case 64:
			color = 0xfff65e3b;
			break;
		case 128:
			color = 0xffedcf72;
			break;
		case 256:
			color = 0xffffff00;
			break;
		case 512:
			color = 0xffccff00;
			break;
		case 1024:
			color = 0xff99ff00;
			break;
		case 2048:
			color = 0xff66ff00;
			break;
		case 4096:
			color = 0xff663333;
			break;
		case 8192:
			color = 0xff0000ff;
			break;
		default:
			color = 0x33ffffff;
			break;
		}
		return color;
	}

	public static GameView getGameView() {
		return gameView;
	}

	private Card[][] cardMap = new Card[4][4];
	private List<Point> emptyPoints = new ArrayList<Point>();
	private static int count = 0;
	public static GameView gameView = null;
	private Db db;
	private SQLiteDatabase dbwrite;
	private ContentValues cv;
}
