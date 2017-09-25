package com.example.game2048;

public class ListCellData {

	public ListCellData(String date, String score, String maxNum, int iconId) {
		this.date = date;
		this.score = score;
		this.maxNum = maxNum;
		this.iconId = iconId;
	}

	public String date = "";
	public String score = "";
	public String maxNum = "";
	public int iconId = 0;

	public void setDate(String date) {
		this.date = date;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public void setMaxNum(String maxNum) {
		this.maxNum = maxNum;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}

}
