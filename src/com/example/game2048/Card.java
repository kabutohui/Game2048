package com.example.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	public Card(Context context) {
		super(context);

		lable = new TextView(getContext());
		lable.setTextSize(30);
		lable.setBackgroundColor(0x33ffffff);
		lable.setGravity(Gravity.CENTER);
		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(5, 5, 5, 5);
		addView(lable, lp);

		setNum(0);
	}

	private int num = 0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		if (num <= 0)
			lable.setText("");
		else {
			if (num <= 4) {
				lable.setTextColor(0xff000000);
			} else {
				lable.setTextColor(0xffffffff);
			}
			lable.setTextSize(30);
			lable.setText(num + "");
		}
	}

	public boolean equals(Card o) {
		return getNum() == o.getNum();
	}

	private TextView lable;
}
