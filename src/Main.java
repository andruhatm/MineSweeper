package src;

import java.awt.*;
import java.util.HashMap;

public final class Main {

	/**
	 * game view obj
	 */
	static GameView view;
	/**
	 * game logic obj
	 */
	static BoardLogic logic;
	/**
	 * mouse events thread
	 */
	static BoardMouseEventThread thread;

	public static void main(String[] args) {

		//initializing View provider class
		view = new GameView(GameView.WINDOWSIZE_SMALL);

		//initializing Logic class with absolute coordinates for board
		logic = new BoardLogic(203,23,view);

		//initializing Thread to listen mouse clicks
		thread = new BoardMouseEventThread("mouseListener",view,logic);

		//adding gray color to ColorMap
		HashMap<Character, Color>map = view.getColormap();
		map.put('A',Color.gray);
		map.put('v',Color.lightGray);
		map.put('T',new Color(31, 255, 23));
		map.put('K',new Color(3, 147, 0));
		map.put('D',new Color(0, 17, 161));
		map.put('I',new Color(170, 0, 0));
		view.setColormap(map);
		view.setWindowIcon("mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
//		view.setStatusText("status text");

		/**
		 * menu
		 */
//		this.view.addImageToCanvas(,xPosition+absoluteX,yPosition+absoluteY,1);
		//TODO кнопка rectangle(addRectangleToCanvas) play (addTextToCanvas)
		/**
		 * game board
		 */
		//allows user to use mouse
		view.useMouse(true);
		logic.newGame();
		logic.drawBoard();
		//runs mouse click listener thread
		thread.start();
		view.printCanvas();

	}

}
