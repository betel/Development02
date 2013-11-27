package golgol;

import processing.core.PApplet;

public class Cell {
	PApplet p;
	int x, y; // セルの位置
	int w; // セルの一辺の長さ
	int color; // セルの色
	boolean life; // セルの生死

	Cell(PApplet p, int x, int y, int w) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.w = w;
		color = p.color(0, 200, 0); // 色は緑で初期化する

	}
}
