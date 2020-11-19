package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main {

	static GameView view;

	static GameLogics logic;

	public static void main(String[] args) {

		logic = new GameLogics();

		logic.initCells();

		JPanel mines = logic.initMineBoard();

		view = new GameView(GameView.WINDOWSIZE_LARGE,mines);

		GameLogics.MinesAdapter listener = logic.initListener();

		view.addMouseListener(listener);

		logic.newGame();

		view.useMouse(true);

		//inits MinesAdapter listener and adds it to working frame

		view.setWindowIcon("icon/mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
		view.setStatusText("status text");

		//view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);
		//view.addPanel(logic.initMineBoard());

		view.printCanvas();
	}



}
