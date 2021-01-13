package src.entity;

import src.common.DifficultyEnum;
import src.common.Timer;
import src.logic.BoardLogic;
import src.threads.BoardEventThread;
import src.view.GameView;

/**
 * Service class for game board
 * Works with Board draw logics and MouseEvents thread
 */
public class GameBoard implements InitComponent{
	/**
	 * game board logic obj
	 */
	private final BoardLogic logic;
	/**
	 * board mouse events thread
	 */
	private static BoardEventThread thread;
	/**
	 * timer thread field
	 */
	private static Timer timer = null;

	/**
	 * initializes logic class and mouse events thread
	 * @param view GameView obj to work with JFrame
	 */
	public GameBoard(GameView view, DifficultyEnum difficulty) {
		//initializing logic class with absolute coordinates for board
		logic = new BoardLogic(203,23,view,difficulty.getValue());
		//initializing thread to listen mouse events
		thread = new BoardEventThread("boardListener",view,logic);
		//initializing thread for timer
		timer = new Timer(view, 203, 23,logic);
	}

	/**
	 * starts thread and draw board
	 */
	public void init(){
		logic.newGame();
		logic.draw();
		timer.start();
		thread.start();
	}

	/**
	 * getter for board event thread
	 * @return {@link BoardEventThread static obj}
	 */
	public static BoardEventThread getThread() {
		return thread;
	}

	/**
	 * getter for timer obj
	 * @return {@link Timer static obj}
	 */
	public static Timer getTimer() {
		return timer;
	}
}
