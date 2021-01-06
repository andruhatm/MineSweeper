package src.menu;

import src.view.GameView;

/**
 * Service class for Menu
 * Works with Menu draw logics and MouseEvents thread
 */
public class GameMenu {

	/**
	 * menu logic obj
	 */
	static MenuLogic logic;
	/**
	 * menu mouse events thread
	 */
	static MenuMouseEventThread thread;

	/**
	 * initializes logic class and mouse events thread
	 * @param view GameView obj to work with JFrame
	 */
	public GameMenu(GameView view) {
		view.useMouse(true);
		//initializing logic class with absolute coordinates for menu
		logic = new MenuLogic(380,250,view);
		//initializing thread to listen mouse clicks
		thread = new MenuMouseEventThread("menuListener",view,logic);
	}

	/**
	 * starts thread and menu draw logics
	 */
	public void openMenu(){
		logic.drawMenu();
		thread.start();
	}

}
