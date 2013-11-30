package golgol;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends PApplet {

	static final int W_WINDOW = 600; // ウィンドウサイズ
	static final int H_WINDOW = 400;
	static final int W_CELL = 20; // セルのサイズ

	Cell[][] cells;

	int col, row;

	// 初めに実行する部分
	public void setup() {
		size(W_WINDOW, H_WINDOW); // ウィンドウサイズを指定
		col = W_WINDOW / W_CELL; // ウィンドウに収まる分だけ配列を用意
		row = H_WINDOW / W_CELL;
		cells = new Cell[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				cells[i][j] = new Cell(this, i * W_CELL, j * W_CELL, W_CELL);
			}
		}
	}

	// ループ部分
	public void draw() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				cells[i][j].drawCell();
			}
		}
	}

	// クリック時のマウスの位置からセルを指定する
	public void mouseClicked() {
		int locX = mouseX / W_CELL;
		int locY = mouseY / W_CELL;
		Cell theCell = cells[locX][locY]; // マウスの位置から特定されたセル
		boolean life = theCell.getBool(); // セルの生死判定を得る

		// 生死の切り替え
		if (life) {
			theCell.setBool(false);
		} else {
			theCell.setBool(true);
		}
	}

	// キーをおした時の動作
	public void keyPressed() {
		if (key == 'c') {
			clearCell();
		}
	}

	// 描画部分
	void drawField() {

	}

	// セルのクリア
	void clearCell() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				cells[i][j].setBool(false);
			}
		}
	}

	// ロジック部分
	public void judge(int i, int j) {
		boolean life = cells[i][j].getBool();
		if (i < col - 1 && j < row - 1) {
			if (life) {
				setBoolAt(i + 1, j, true);
				setBoolAt(i, j + 1, true);
			}
		}
	}

	// 生死の設定を見やすくするためだけのメソッド
	private void setBoolAt(int a, int b, boolean bool) {
		cells[a][b].setBool(bool);
	}
}
