package src;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameLogics {

	//number of images
	private static final int NUM_IMAGES = 13;
	//cell pixel size
	private static final int CELL_SIZE  = 15;

	//picture codes
	private final int MINE_IMG       = 9;
	private final int COVER_IMG      = 10;
	private final int MARK_IMG       = 11;
	private final int WRONG_MARK_IMG = 12;

	private final int totalMines = 30;
	private int remainderMines;

	private int rows = 16, columns = 16;

	private Cell[][] cells;

	private Image[] img;

	private boolean inGame;

	public GameLogics() {
		initImg();

	}

	public void newGame(){
		Random random = new Random();
		this.inGame = true;
		this.remainderMines = totalMines;

		this.initCells();
		Main.view.setStatusText("Mines lasts: " + Integer.toString(remainderMines));

		int remainder = totalMines;
		while (remainder >= 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = this.cells[randX][randY];
			if (!cell.isMine()) {
				cell.setMine(true);
				remainder--;
			}
		}
		this.setMinesCount();
	}

	public void initCells(){
		this.cells = new Cell[rows][columns];

		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				this.cells[i][j] = new Cell();
			}
		}
	}

	public void initImg(){
		img = new Image[NUM_IMAGES];
		for (int i = 0; i < NUM_IMAGES; i++) {
			String path = "src/resources/icon2/" + i + ".png";
			img[i] = new ImageIcon(path).getImage();
		}
	}

	public void setMinesCount(){
		for (int i = 0; i < this.columns; ++i) {
			for (int j = 0; j < this.rows; ++j) {
				Cell cell = this.cells[i][j];

				if (!cell.isMine()) {
					int count = countMinesAround(i, j);
					cell.setAroundMines(count);
				}
			}
		}
	}

	private int countMinesAround(int x, int y) {
		int count = 0;

		for (int i = -1; i <= 1; ++i) {
			int xIndex = x + i;
			if (xIndex < 0 || xIndex >= this.rows) {
				continue;
			}

			for (int j = -1; j <= 1; ++j) {
				int yIndex = y + j;
				if (yIndex < 0 || yIndex >= this.columns) {
					continue;
				}

				if (i == 0 && j == 0) {
					continue;
				}

				if (this.cells[xIndex][yIndex].isMine()) {
					count++;
				}
			}
		}

		return count;
	}

}
