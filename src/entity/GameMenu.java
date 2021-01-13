package src.entity;

import src.logic.MenuLogic;
import src.threads.MenuEventThread;
import src.view.GameView;

/**
 * Service class for Menu
 * Works with Menu draw logics and MouseEvents thread
 */
public class GameMenu implements InitComponent{

	/**
	 * menu logic obj
	 */
	private final MenuLogic logic;
	/**
	 * menu mouse events thread
	 */
	private static MenuEventThread thread;

	/**
	 * initializes logic class and mouse events thread
	 * @param view GameView obj to work with JFrame
	 */
	public GameMenu(GameView view) {
		view.useMouse(true);
		//initializing logic class with absolute coordinates for menu
		logic = new MenuLogic(380,250,view);
		//initializing thread to listen mouse clicks
		thread = new MenuEventThread("menuListener",view,logic);
	}

	/**
	 * starts thread and draw menu
	 */
	public void init(){
		logic.draw();
		thread.start();
	}

	/**
	 * getter for menu event thread
	 * @return {@link MenuEventThread static obj}
	 */
	public static MenuEventThread getThread() {
		return thread;
	}
}
