package golgol;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends PApplet {

	static final int W_WINDOW = 600; // ウィンドウサイズ
	static final int H_WINDOW = 450;
	static final int W_CELL = 15; // セルのサイズ
	static final int EDIT_MODE = 0; // modeがこの値の時に編集モードにする
	static final int ANIMATION_MODE = 1;// modeがこの値の時にアニメーションモードにする
	static final int FPS = 10; // フレームレート

	Cell[][] currentCells, nextCells;
	CellControl ctrl;

	static final int COL = W_WINDOW / W_CELL; // 列、行の数
	static final int ROW = H_WINDOW / W_CELL;
	int mode; // モード切替用変数

	// 初めに実行する部分
	public void setup() {
		size(W_WINDOW, H_WINDOW); // ウィンドウサイズを指定
		frameRate(FPS); // フレームレートの設定

		currentCells = new Cell[COL][ROW];
		nextCells = new Cell[COL][ROW];

		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < ROW; j++) {
				currentCells[i][j] = new Cell(this, i * W_CELL, j * W_CELL,
						W_CELL);
				nextCells[i][j] = new Cell(this, i * W_CELL, j * W_CELL, W_CELL);
			}
		}

		ctrl = new CellControl(currentCells, nextCells);

		mode = EDIT_MODE;
	}

	// ループ部分
	public void draw() {
		if (mode == ANIMATION_MODE) {
			ctrl.nextGeneration();
			ctrl.updateCells();
		}
		ctrl.drawField();
	}

	// クリック時のマウスの位置からセルを指定する
	public void mouseClicked() {
		if (mode == EDIT_MODE) {
			int locX = mouseX / W_CELL;
			int locY = mouseY / W_CELL;
			Cell theCell = currentCells[locX][locY]; // マウスの位置から特定されたセル
			boolean life = theCell.getBool(); // セルの生死判定を得る

			// 生死の切り替え
			if (life) {
				theCell.setBool(false);
			} else {
				theCell.setBool(true);
			}
		}
	}

	// キーをおした時の動作
	public void keyPressed() {
		if (key == 'c') {
			ctrl.clearCells();
		}
		if (key == ' ') {
			if (mode == ANIMATION_MODE) {
				mode = EDIT_MODE;
			} else if (mode == EDIT_MODE) {
				mode = ANIMATION_MODE;
			}
		}
		if (key == 'q') {
			exit();
		}
	}
}
