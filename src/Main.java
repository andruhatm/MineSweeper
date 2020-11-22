package src;

import java.awt.*;
import java.util.HashMap;

public class Main {

	/**
	 * game view obj
	 */
	static GameView view;
	/**
	 * game logic obj
	 */
	static GameLogics logic;
	/**
	 * mouse events thread
	 */
	static MouseEventsThread thread;

	public static void main(String[] args) {

		//initializing View provider class
		view = new GameView(GameView.WINDOWSIZE_SMALL);

		//initializing Logic class with absolute coordinates for board
		logic = new GameLogics(203,23,view);

		//initializing Thread to listen mouse clicks
		thread = new MouseEventsThread("mouseListener",view,logic);

		view.setWindowIcon("mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
		view.setStatusText("status text");

		//adding gray color to ColorMap
		HashMap<Character, Color>map = view.getColormap();
		map.put('A',Color.gray);
		map.put('v',Color.lightGray);
		view.setColormap(map);


		//allows user to use mouse
		view.useMouse(true);

		logic.newGame();

		logic.drawBoard();

		//runs mouse click listener thread
		thread.start();
	}

}
