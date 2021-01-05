package src;

public class GameMenu {

	/**
	 * menu logic obj
	 */
	static MenuLogic logic;
	/**
	 * menu mouse events thread
	 */
	static MenuMouseEventThread thread;

	public GameMenu(GameView view) {
		view.useMouse(true);
		//initializing logic class with absolute coordinates for menu
		logic = new MenuLogic(380,250,view);

		//initializing thread to listen mouse clicks
		thread = new MenuMouseEventThread("menuListener",view,logic);
	}

	public void openMenu(){
		logic.drawMenu();
		thread.start();
	}

}
