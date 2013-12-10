package golgol;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class Main extends PApplet {

	static final int W_WINDOW = 600; // ウィンドウサイズ
	static final int H_WINDOW = 450;
	static final int W_CELL = 15; // セルのサイズ
	static final int EDIT_MODE = 0; // modeがこの値の時に編集モードにする
	static final int ANIMATION_MODE = 1;// modeがこの値の時にアニメーションモードにする
	static final int FPS = 20; // フレームレート

	Cell[][] currentCells, nextCells;

	int col, row; // 列、行の数
	int mode; // モード切替用変数

	// 初めに実行する部分
	public void setup() {
		size(W_WINDOW, H_WINDOW); // ウィンドウサイズを指定
		frameRate(FPS); // フレームレートの設定
		col = W_WINDOW / W_CELL; // ウィンドウに収まる分だけ配列を用意
		row = H_WINDOW / W_CELL;
		currentCells = new Cell[col][row];
		nextCells = new Cell[col][row];
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j] = new Cell(this, i * W_CELL, j * W_CELL,
						W_CELL);
				nextCells[i][j] = new Cell(this, i * W_CELL, j * W_CELL, W_CELL);
			}
		}
		mode = EDIT_MODE;
	}

	// ループ部分
	public void draw() {
		if (mode == ANIMATION_MODE) {
			nextGeneration();
			updateCells();
		}
		drawField();
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
			clearCell();
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

	// セルのクリア
	void clearCell() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].setBool(false);
				nextCells[i][j].setBool(false);
			}
		}
	}

	// 全てのセルを描画する
	void drawField() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].drawCell();
			}
		}
	}

	// 次世代の内容を現在のセルにコピー
	void updateCells() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].setBool(nextCells[i][j].getBool());
			}
		}
	}

	// ロジック部分(判定するのは外周を除く部分)
	// ルールに従って次世代を生成して、nextCells()に格納する
	void nextGeneration() {
		for (int i = 1; i < col - 1; i++) {
			for (int j = 1; j < row - 1; j++) {
				boolean life = currentCells[i][j].getBool();
				int count = countAliveCell(i, j);

				if (!life) { // セルが死んでいる時
					if (count == 3) {
						nextCells[i][j].setBool(true); // セルが生まれる
					}
				} else { // セルが生きている時
					if (count >= 4) {
						nextCells[i][j].setBool(false); // 過密により死滅
					} else if (count >= 2) {
						nextCells[i][j].setBool(true); // 生存
					} else {
						nextCells[i][j].setBool(false); // 過疎により死滅
					}
				}
			}
		}
	}

	// 周囲の生きているセルの数を返す
	int countAliveCell(int i, int j) {
		int count = 0;

		if (currentCells[i - 1][j - 1].getBool())
			count++;
		if (currentCells[i][j - 1].getBool())
			count++;
		if (currentCells[i + 1][j - 1].getBool())
			count++;
		if (currentCells[i - 1][j].getBool())
			count++;
		if (currentCells[i + 1][j].getBool())
			count++;
		if (currentCells[i - 1][j + 1].getBool())
			count++;
		if (currentCells[i][j + 1].getBool())
			count++;
		if (currentCells[i + 1][j + 1].getBool())
			count++;

		return count;
	}
}
