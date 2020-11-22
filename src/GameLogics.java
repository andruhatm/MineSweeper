package src;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameLogics {

	GameView view;

	//cell pixel size
	private static final int CELL_SIZE = 32;

	private static int absoluteX;
	private static int absoluteY;

	private final int totalMines = 30;
	private int remainderMines;

	private final int rows = 16;
	private final int columns = 16;

	private static Cell[][] cells;

	private boolean inGame;

	public GameLogics(int absoluteX,int absoluteY,GameView view) {
		this.view = view;
		GameLogics.absoluteX = absoluteX;
		GameLogics.absoluteY = absoluteY;
		initCells();
	}

	/**
	 * initializes game board
	 */
	public void newGame() {
		System.out.println("new game");
		Random random = new Random();

		this.inGame = true;
		this.remainderMines = totalMines;

		view.setStatusText("Mines lasts: " + remainderMines);

		int remainder = totalMines;
		while (remainder >= 0) {
			int randX = random.nextInt(this.rows);
			int randY = random.nextInt(this.columns);

			Cell cell = cells[randX][randY];
			if (!cell.isMine()) {
				cell.setMine(true);
				remainder--;
			}
		}
		this.setMinesCount();
	}

	/**
	 * calculate's pressed cell and changes cells state, then call to repaint
	 * @param e last mouse click event from {@link MouseEventsThread}
	 */
	public void blockPressed(MouseEvent e){

		int pressedCol = (e.getX() - absoluteX) / CELL_SIZE;
		int pressedRow = (e.getY() - absoluteY) / CELL_SIZE;
		System.out.println("pressed column "+pressedCol+" pressed row "+pressedRow);

		boolean doRepaint = false;
		Cell pressedCell;

		if (!inGame) {
			newGame();
			drawBoard();
		}

		if ((pressedCol < 0 || pressedCol >= columns)
						|| (pressedRow < 0 || pressedRow >= rows)) {
			return;
		}

		pressedCell = cells[pressedRow][pressedCol];

		if (e.getButton() == MouseEvent.BUTTON3) {
			doRepaint = true;

			if (!pressedCell.isCovered()) {
				return;
			}

			if (!pressedCell.isMarked()) {
				pressedCell.setMark(true);
				remainderMines--;
			} else {
				pressedCell.setMark(false);
				remainderMines++;
			}

			view.setStatusText(Integer.toString(remainderMines));
		} else {
			if (pressedCell.isMarked() || !pressedCell.isCovered()) {
				return;
			}

			doRepaint = true;

			pressedCell.uncover();

			if (pressedCell.isMine()) {
				inGame = false;
			} else if (pressedCell.isEmpty()) {
				findEmptyCells(pressedRow, pressedCol, 0);
			}
		}

		if (doRepaint) {
			drawBoard();
		}
	}

	/**
	 * mthd for drawing/redrawing board by cells[][] array
	 * if all cells detected or user pressed bomb, sets new window status text
	 */
	public void drawBoard(){

		int coveredCells = 0;

		//adding border for game board with tiles
		view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);

		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				Cell tile = cells[i][j];

				TilesEnum imageType;
				int xPosition, yPosition;

				if (tile.isCovered()) {
					coveredCells++;
				}

				if (inGame) {
					if (tile.isMine() && !tile.isCovered()) {
						inGame = false;
					}
				}

				imageType = this.decideImageType(tile);

				xPosition = (j * CELL_SIZE);
				yPosition = (i * CELL_SIZE);

				this.view.addImageToCanvas(imageType.getTitle(),xPosition+absoluteX,yPosition+absoluteY,1);
			}
		}
		if (coveredCells == 0 && inGame) {
			inGame = false;
			view.setStatusText("Game Won");
		} else if (!inGame) {
			view.setStatusText("Game Lost");
		}
		view.printCanvas();
	}

	/**
	 * initializes array of cells
	 */
	public void initCells() {
		cells = new Cell[rows][columns];

		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				cells[i][j] = new Cell();
			}
		}
	}

	/**
	 * sets mines count of nearby mines in Cells array
	 */
	public void setMinesCount() {
		for (int i = 0; i < this.columns; ++i) {
			for (int j = 0; j < this.rows; ++j) {
				Cell cell = cells[i][j];

				if (!cell.isMine()) {
					int count = countMinesAround(i, j);
					cell.setAroundMines(count);
				}
			}
		}
	}

	/**
	 * calculates mines around and
	 * @param x x coordinate of cell in array
	 * @param y y coordinate of cell in array
	 * @return count of mines around
	 */
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

				if (cells[xIndex][yIndex].isMine()) {
					count++;
				}
			}
		}

		return count;
	}

	/**
	 * checks if cell is empty
	 * @param cell cell obj
	 * @return bool success result
	 */
	private boolean checkEmpty(Cell cell) {
		if (!cell.isChecked()) {
			return cell.isEmpty();
		}
		return false;
	}

	/**
	 * changes state of nearby cells (uncover them)
	 * @param x x coordinate of cell in array
	 * @param y y coordinate of cell in array
	 */
	private void uncoverAroundCell(int x, int y) {
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

				if (i == 0 || j == 0) {
					continue;
				}

				Cell cell = cells[xIndex][yIndex];
				if (cell.isCovered() && !cell.isEmpty()) {
					cell.uncover();
				}
			}
		}
	}

	/**
	 * clears all board when game ends
	 */
	private void clearAllCells() {
		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				cells[i][j].clearChecked();
			}
		}
	}

	/**
	 * uncover cells if no mines around
	 * @param x x coordinate of cell
	 * @param y y coordinate of cell
	 * @param depth depth of searching
	 */
	public void findEmptyCells(int x, int y, int depth) {

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

				if (!(i == 0 || j == 0)) {
					continue;
				}

				Cell cell = cells[xIndex][yIndex];
				if (checkEmpty(cell)) {
					cell.uncover();
					cell.checked();

					uncoverAroundCell(xIndex, yIndex);
					findEmptyCells(xIndex, yIndex, depth + 1);
				}
			}
		}

		if (depth == 0) {
			this.clearAllCells();
		}
	}

	/**
	 * choosing enum obj by Cell obj state
	 * @param cell Cell obj
	 * @return TilesEnum obj represents string of color chars to draw
	 */
	private TilesEnum decideImageType(Cell cell) {
		TilesEnum imageType;

		switch (cell.getValue()){
			case 0:
				imageType = TilesEnum.NULL;
				break;
			case 1:
				imageType = TilesEnum.one;
				break;
			case 2:
				imageType = TilesEnum.two;
				break;
			case 3:
				imageType = TilesEnum.three;
				break;
			case 4:
				imageType = TilesEnum.four;
				break;
			case 5:
				imageType = TilesEnum.five;
				break;
			case 6:
				imageType = TilesEnum.six;
			case 7:
				imageType = TilesEnum.seven;
				break;
			case 8:
				imageType = TilesEnum.eight;
				break;
			default:
				imageType = TilesEnum.COVER_IMG;
		}

		if (!inGame) {
			if (cell.isCovered() && cell.isMine()) {
				cell.uncover();
				imageType = TilesEnum.MINE_IMG;
			} else if (cell.isMarked()) {
				if (cell.isMine()) {
					imageType = TilesEnum.MARK_IMG;
				} else {
					imageType = TilesEnum.WRONG_MARK_IMG;
				}
			}
		} else {
			if (cell.isMarked()) {
				imageType = TilesEnum.MARK_IMG;
			} else if (cell.isCovered()) {
				imageType = TilesEnum.COVER_IMG;
			}
		}

		return imageType;
	}

}
