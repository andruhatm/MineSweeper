package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

public class Main {

	static GameView view;

	static GameLogics logic;

	public static void main(String[] args) {
		//initializing View provider class
		view = new GameView(GameView.WINDOWSIZE_SMALL);
		//initializing Logic class with absolute coordinates for board
		logic = new GameLogics(203,23);

		view.useMouse(true);
		view.setWindowIcon("icon/mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
		view.setStatusText("status text");

		//adding gray color to ColorMap
		HashMap<Character, Color>map =  view.getColormap();
		map.put('A',Color.gray);
		view.setColormap(map);

		//allows user to use mouse
		view.useMouse(true);

		//adding border for game board with tiles
		view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);

		//drawing board on screen
		logic.drawBoard(view);

		//TODO цифры в строчном виде TilesEnum
		//TODO получать координаты нажатия мыши и перерисовывать поле в соответствии с условиями
		/*
			public void mousePressed(MouseEvent e) {
            int pressedCol = e.getX() / CELL_SIZE;
            int pressedRow = e.getY() / CELL_SIZE;

            boolean doRepaint = false;
            Cell pressedCell;

            if (!inGame) {
                newGame();
                repaint();
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

                String str;
                if (!pressedCell.isMarked()) {
                    pressedCell.setMark(true);
                    remainderMines--;
                } else {
                    pressedCell.setMark(false);
                    remainderMines++;
                }

                statusBar.setText(Integer.toString(remainderMines));
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
                repaint();
            }
        }
		 */

		//view.addImageToCanvas(TilesEnum.one.getTitle(),203,23,1);

		view.printCanvas();

		logic.newGame();
		//поток вызывающий метод pollMouseEvents() и проверящий что новый клик был добавлен

	}

}
