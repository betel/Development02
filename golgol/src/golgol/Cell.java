package golgol;

import processing.core.PApplet;

public class Cell {
	PApplet p;

	final int GREEN;
	final int RED; // テスト用に、死んだセルは赤で表現する

	int x, y; // セルの位置
	int w; // セルの一辺の長さ
	boolean life; // セルの生死 (false=dead,true=alive)

	// コンストラクタ
	Cell(PApplet p, int x, int y, int w) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.w = w;
		GREEN = p.color(0, 255, 0);
		RED = p.color(255, 0, 0);
	}

	// セルの描画
	void drawCell() {
		// translate()は使わない
		if (life) { // セルの生死によって色を分ける
			p.fill(GREEN);
		} else {
			p.fill(RED);
		}

		p.stroke(0); // グリッド線は黒色
		p.rect(x, y, w, w); // 四角形を描く
	}

	// セルの生死を設定する
	void setBool(boolean life) {
		this.life = life;
	}

	// セルの生死を返す
	boolean getBool() {
		return life;
	}
}
