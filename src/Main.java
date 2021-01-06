package src;

import src.menu.GameMenu;
import src.view.GameView;

import java.awt.*;
import java.util.HashMap;

/**
 * Main class of application
 * @version 1.0
 */
public final class Main {

	/**
	 * entrypoint of the application
	 * @param args additional params
	 */
	public static void main(String[] args) {

		//initializing view provider object
		GameView view = new GameView(GameView.WINDOWSIZE_SMALL);

		//initializing Menu object
		GameMenu menu = new GameMenu(view);

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
		view.printCanvas();
	}

}
