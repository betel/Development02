package golgol;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends PApplet {

	static final int W_WINDOW = 600; // ウィンドウサイズ
	static final int H_WINDOW = 400;
	static final int W_CELL = 10; // セルのサイズ

	Cell[][] cells;

	int col, row;

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

	public void draw() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				cells[i][j].drawCell();
			}
		}
	}

	public void mouseClicked() {

	}
}
