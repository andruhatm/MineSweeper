package src.board;

import src.view.GameView;
import src.common.TilesEnum;
import src.entity.Cell;
import src.menu.GameMenu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Draw logic class for Board
 */
public class BoardLogic {
	/**
	 * view field to work with JFrame mthds
	 */
	private final GameView view;
	/**
	 * constant of cell pixel size
	 */
	private final int CELL_SIZE = 32;
	/**
	 * constant of X as starting draw coordinate
	 */
	private final int ABSOLUTE_X;
	/**
	 * constant of Y as starting draw coordinate
	 */
	private final int ABSOLUTE_Y;
	/**
	 * count of mines to show in statusbar
	 */
	private int remainderMines;
	/**
	 * constant count of rows
	 */
	private final int ROWS = 16;
	/**
	 * constant count of columns
	 */
	private final int COLUMNS = 16;
	/**
	 * constant count of mines on board
	 */
	private final int MINES_COUNT;
	/**
	 * array of cells which represents the board
	 */
	private Cell[][] cells;
	/**
	 * count of uncovered cells of board
	 */
	private int uncoveredCells = 0;
	/**
	 * bool value to determine if player is in game or not
	 */
	private boolean inGame;

	/**
	 * BoardLogic constructor
	 * @param absoluteX starting X coordinate to draw
	 * @param absoluteY starting Y coordinate to draw
	 * @param view GameView obj to work with JFrame
	 * @param minesCount count of mines
	 */
	public BoardLogic(int absoluteX, int absoluteY, GameView view, int minesCount) {
		this.view = view;
		this.ABSOLUTE_X = absoluteX;
		this.ABSOLUTE_Y = absoluteY;
		this.MINES_COUNT = minesCount;
		initCells();
	}

	/**
	 * initializes game board
	 */
	public void newGame() {
		Random random = new Random();
		this.inGame = true;
		int totalMines = MINES_COUNT;
		this.remainderMines = totalMines+1;
		view.setStatusText("Mines lasts: " + remainderMines);
		int remainder = totalMines;
		while (remainder >= 0) {
			int randX = random.nextInt(this.ROWS);
			int randY = random.nextInt(this.COLUMNS);

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
	 * @param e last mouse click event from {@link BoardMouseEventThread}
	 */
	public void blockPressed(MouseEvent e){
		int pressedCol = (e.getX() - ABSOLUTE_X) / CELL_SIZE;
		int pressedRow = (e.getY() - ABSOLUTE_Y) / CELL_SIZE;
		Cell pressedCell;
		if (!inGame) {
			newGame();
			drawBoard();
		}
		if ((pressedCol < 0
				|| pressedCol >= COLUMNS)
				|| (pressedRow < 0 || pressedRow >= ROWS)
		){
			return;
		}
		pressedCell = cells[pressedRow][pressedCol];
		if (e.getButton() == MouseEvent.BUTTON3) {
			if (!pressedCell.isCovered()) {
				return;
			}
			if (!pressedCell.isMarked()) {
				pressedCell.setMark(true);
				System.out.println(pressedCell.toString());
				this.uncoveredCells--;
				this.remainderMines--;
			} else {
				pressedCell.setMark(false);
				this.uncoveredCells++;
				this.remainderMines++;
			}
			view.setStatusText(Integer.toString(remainderMines));
			view.playSound("flag.wav",false);

		} else {
			if (pressedCell.isMarked() || !pressedCell.isCovered()) {
				return;
			}
			pressedCell.uncover();
			if (pressedCell.isMine()) {
				view.playSound("boom.wav",false);
				inGame = false;
			} else if (pressedCell.isEmpty()) {
				findEmptyCells(pressedRow, pressedCol, 32);
				//TODO bugfix not open tiles
			}
			if(!pressedCell.isMine())
				view.playSound("shovel.wav",false);
		}
		drawBoard();
	}

	/**
	 * mthd for drawing/redrawing board by cells[][] array
	 * if all cells detected or user pressed bomb, sets new window status text
	 */
	public void drawBoard(){

		int coveredCells = uncoveredCells;

		//adding border for game board with tiles
		view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);

		for(int i=0;i<ROWS;i++){
			for(int j=0;j<COLUMNS;j++){
				Cell tile = cells[i][j];

				TilesEnum imageType;
				int xPosition, yPosition;

				if (tile.isCovered()) {
					coveredCells++;
				}

				imageType = this.decideImageType(tile);

				xPosition = (j * CELL_SIZE);
				yPosition = (i * CELL_SIZE);

				this.view.addImageToCanvas(imageType.getTitle(),xPosition+ABSOLUTE_X,yPosition+ABSOLUTE_Y,1);

				if (inGame) {
					if (tile.isMine() && !tile.isCovered()) {
						inGame = false;
					}
				}

			}
		}

		GameMenu menu;
		if (coveredCells == 0 && inGame) {
			menu = new GameMenu(this.view);
			GameBoard.thread.disable();
			inGame = false;
			view.setStatusText("Game Won. Window will close in 5 seconds");
			view.playSound("win.wav",false);
			try {
				TimeUnit.SECONDS.sleep(5);
			}catch (InterruptedException e){
				e.getCause();
			}
			menu.openMenu();
		} else if (!inGame) {
			menu = new GameMenu(this.view);
			GameBoard.thread.disable();
			view.playSound("lose.wav",false);
			view.setStatusText("Game Lost. Window will close in 5 seconds");
			view.printCanvas();
			try {
				TimeUnit.SECONDS.sleep(5);
			}catch (InterruptedException e){
				e.getCause();
			}
			menu.openMenu();
		}
		view.printCanvas();
	}

	/**
	 * initializes array of cells
	 */
	public void initCells() {
		cells = new Cell[ROWS][COLUMNS];
		for (int i = 0; i < this.ROWS; ++i) {
			for (int j = 0; j < this.COLUMNS; ++j) {
				cells[i][j] = new Cell();
			}
		}
	}

	/**
	 * sets mines count of nearby mines in Cells array
	 */
	public void setMinesCount() {
		for (int i = 0; i < this.COLUMNS; ++i) {
			for (int j = 0; j < this.ROWS; ++j) {
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
			if (xIndex < 0 || xIndex >= this.ROWS) {
				continue;
			}
			for (int j = -1; j <= 1; ++j) {
				int yIndex = y + j;
				if (yIndex < 0 || yIndex >= this.COLUMNS) {
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
			if (xIndex < 0 || xIndex >= this.ROWS) {
				continue;
			}
			for (int j = -1; j <= 1; ++j) {
				int yIndex = y + j;

				if (yIndex < 0 || yIndex >= this.COLUMNS) {
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
		for (int i = 0; i < this.ROWS; ++i) {
			for (int j = 0; j < this.COLUMNS; ++j) {
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
			if (xIndex < 0 || xIndex >= this.ROWS) {
				continue;
			}
			for (int j = -1; j <= 1; ++j) {
				int yIndex = y + j;

				if (yIndex < 0 || yIndex >= this.COLUMNS) {
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
		TilesEnum imageType = null;
		if (cell.isMine()) {
			imageType = TilesEnum.BLOW_IMG;
		}else if (cell.isMarked()){
			imageType = TilesEnum.MARK_IMG;
		}else {
			imageType = switch (cell.getValue()) {
				case 0 -> TilesEnum.NULL;
				case 1 -> TilesEnum.one;
				case 2 -> TilesEnum.two;
				case 3 -> TilesEnum.three;
				case 4 -> TilesEnum.four;
				case 5 -> TilesEnum.five;
				case 6 -> TilesEnum.six;
				case 7 -> TilesEnum.seven;
				case 8 -> TilesEnum.eight;
				default -> imageType;
			};
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
