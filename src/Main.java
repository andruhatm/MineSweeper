package src;

import java.awt.*;
import java.util.HashMap;

public final class Main {

	/**
	 * game view obj
	 */
	static GameView view;

	public static void main(String[] args) {

		//initializing view provider object
		view = new GameView(GameView.WINDOWSIZE_SMALL);

		//initializing Board object
		//GameBoard board = new GameBoard(view);
		GameMenu menu = new GameMenu(view);

		view.useMouse(true);

		//adding colors to ColorMap
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

		// menu
		menu.openMenu();

		//TODO кнопка rectangle(addRectangleToCanvas) play (addTextToCanvas)

		view.printCanvas();
	}

}
