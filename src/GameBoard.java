package src;

public class GameBoard {

	/**
	 * game board logic obj
	 */
	static BoardLogic logic;
	/**
	 * board mouse events thread
	 */
	static BoardMouseEventThread thread;

	public GameBoard(GameView view) {

		//initializing logic class with absolute coordinates for board
		logic = new BoardLogic(203,23,view,19);

		//initializing thread to listen mouse events
		thread = new BoardMouseEventThread("boardListener",view,logic);
	}

	public void startGame(){
		logic.newGame();
		logic.drawBoard();
		thread.start();
	}

}
