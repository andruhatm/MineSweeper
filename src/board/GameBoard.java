package src.board;

import src.view.GameView;

/**
 * Service class for game board
 * Works with Board draw logics and MouseEvents thread
 */
public class GameBoard {

	/**
	 * game board logic obj
	 */
	static BoardLogic logic;
	/**
	 * board mouse events thread
	 */
	static BoardMouseEventThread thread;

	/**
	 * initializes logic class and mouse events thread
	 * @param view GameView obj to work with JFrame
	 */
	public GameBoard(GameView view) {
		//initializing logic class with absolute coordinates for board
		logic = new BoardLogic(203,23,view,19);
		//initializing thread to listen mouse events
		thread = new BoardMouseEventThread("boardListener",view,logic);
	}

	/**
	 * starts thread and board draw logics
	 */
	public void startGame(){
		logic.newGame();
		logic.drawBoard();
		thread.start();
	}

}
