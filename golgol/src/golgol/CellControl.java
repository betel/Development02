package golgol;

import processing.core.PApplet;

public class CellControl {

	PApplet p;
	Cell[][] currentCells, nextCells;
	int col, row;

	public CellControl(Cell[][] current, Cell[][] next) {
		col = Main.COL;
		row = Main.ROW;
		currentCells = current;
		nextCells = next;
	}

	// Clear all cells on the field
	void clearCells() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].setBool(false);
				nextCells[i][j].setBool(false);
			}
		}
	}

	// Draw all cells
	void drawField() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].drawCell();
			}
		}
	}

	// Copy next generation to current one
	void updateCells() {
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				currentCells[i][j].setBool(nextCells[i][j].getBool());
			}
		}
	}

	// Determine cell'life and assign it to nextCells[][]
	void nextGeneration() {
		for (int i = 1; i < col - 1; i++) {
			for (int j = 1; j < row - 1; j++) {
				boolean life = currentCells[i][j].getBool();
				int count = countAliveCell(i, j);

				if (!life) { // if the cell is dead
					if (count == 3) {
						nextCells[i][j].setBool(true); // cell borns
					}
				} else { // if the cell is alive
					if (count >= 4) {
						nextCells[i][j].setBool(false); // die
					} else if (count >= 2) {
						nextCells[i][j].setBool(true); // alive
					} else {
						nextCells[i][j].setBool(false); // die
					}
				}
			}
		}
	}

	// Count surrounding alive cells and return it
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
