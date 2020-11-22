package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameLogics {

	GameView view;

	//number of images
//	private static final int NUM_IMAGES = 13;
	//cell pixel size
	private static final int CELL_SIZE = 32;

	//picture codes
//	private final int MINE_IMG = 9;
//	private final int COVER_IMG = 10;
//	private final int MARK_IMG = 11;
//	private final int WRONG_MARK_IMG = 12;

	private static int absoluteX;
	private static int absoluteY;

	private final int totalMines = 30;
	private int remainderMines;

	private int rows = 16, columns = 16;

	private Cell[][] cells;

	//private Image[] img;

	private boolean inGame = true;

	public GameLogics(int absoluteX,int absoluteY,GameView view) {
		this.view = view;
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
		initCells();
	}

	public GameLogics() {
		initCells();
	}

	public void newGame() {
		Random random = new Random();
		this.inGame = true;
		this.remainderMines = totalMines;

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
		MouseEventsThread thread = new MouseEventsThread("mouseListener", this.view);

		System.out.println("ingame in new game "+inGame);
		//starting thread for catching mouse click events
		thread.start();

	}

	public void blockPressed(MouseEvent e){
		System.out.println("ingame in blockpressed "+inGame);

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

		pressedCell = this.cells[pressedRow][pressedCol];

		if (e.getButton() == MouseEvent.BUTTON3) {
			doRepaint = true;

			if (!pressedCell.isCovered()) {
				return;
			}

			String str;
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

	public void drawBoard(){

		int coveredCells = 0;

		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				Cell tile = this.cells[i][j];
				//System.out.println(tile);
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
				//g.drawImage(img[imageType], xPosition, yPosition, this);
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

	public void initCells() {
		this.cells = new Cell[rows][columns];

		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				this.cells[i][j] = new Cell(absoluteX,absoluteY);
			}
		}
	}

	public void setMinesCount() {
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

	private boolean checkEmpty(Cell cell) {
		if (!cell.isChecked()) {
			if (cell.isEmpty()) {
				return true;
			}
		}

		return false;
	}

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

				Cell cell = this.cells[xIndex][yIndex];
				if (cell.isCovered() && !cell.isEmpty()) {
					cell.uncover();
				}
			}
		}
	}

	private void clearAllCells() {
		for (int i = 0; i < this.rows; ++i) {
			for (int j = 0; j < this.columns; ++j) {
				this.cells[i][j].clearChecked();
			}
		}
	}

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

				Cell cell = this.cells[xIndex][yIndex];
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

	private TilesEnum decideImageType(Cell cell) {
		TilesEnum imageType = TilesEnum.COVER_IMG;

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

//	public class MineBoard extends JPanel {
//		@Override
//		public void paint(Graphics g) {
//			int coveredCells = 0;
//
//			for (int i = 0; i < getRows(); i++) {
//				for (int j = 0; j < getColumns(); j++) {
//					Cell cell = getCell(i,j);
//					int imageType;
//					int xPosition, yPosition;
//
//					if (cell.isCovered()) {
//						coveredCells++;
//					}
//
//					if (inGame) {
//						if (cell.isMine() && !cell.isCovered()) {
//							inGame = false;
//						}
//					}
//
//					imageType = decideImageType(cell);
//
//					xPosition = (j * CELL_SIZE);
//					yPosition = (i * CELL_SIZE);
//
//					g.drawImage(img[imageType], xPosition, yPosition, this);
//				}
//			}
//
//			if (coveredCells == 0 && inGame) {
//				inGame = false;
//				Main.view.setStatusText("Game Won");
//			} else if (!inGame) {
//				Main.view.setStatusText("Game Lost");
//			}
//		}
//	}

	//class MinesAdapter extends MouseAdapter {
//		public void mousePressed(MouseEvent e) {
//			System.out.println("mouse pressed");
//			int pressedCol = (e.getX() / CELL_SIZE) + 200;
//			int pressedRow = (e.getY() / CELL_SIZE) + 20;
//
//			System.out.println("X:"+ pressedCol +"Y: "+pressedRow);
//
//			boolean doRepaint = false;
//			Cell pressedCell;
//
//			if (!inGame) {
//				newGame();
//				mineBoard.repaint();
//			}
//
//			if ((pressedCol < 0 || pressedCol >= columns)
//							|| (pressedRow < 0 || pressedRow >= rows)) {
//				return;
//			}
//
//			pressedCell = cells[pressedRow][pressedCol];
//
//			if (e.getButton() == MouseEvent.BUTTON3) {
//				doRepaint = true;
//
//				if (!pressedCell.isCovered()) {
//					return;
//				}
//
//				//String str;
//				if (!pressedCell.isMarked()) {
//					pressedCell.setMark(true);
//					remainderMines--;
//				} else {
//					pressedCell.setMark(false);
//					remainderMines++;
//				}
//
//				Main.view.setStatusText(Integer.toString(remainderMines));
//			} else {
//				if (pressedCell.isMarked() || !pressedCell.isCovered()) {
//					return;
//				}
//
//				doRepaint = true;
//
//				pressedCell.uncover();
//				if (pressedCell.isMine()) {
//					inGame = false;
//				} else if (pressedCell.isEmpty()) {
//					findEmptyCells(pressedRow, pressedCol, 0);
//				}
//			}
//
//			if (doRepaint) {
//				mineBoard.repaint();
//			}
//		}
//	}

}
