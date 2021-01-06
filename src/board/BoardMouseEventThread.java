package src.board;

import src.view.GameView;

import java.awt.event.MouseEvent;

/**
 * Thread for checking new mouse events on board
 */
public class BoardMouseEventThread extends Thread{

	/**
	 * GameView obj to work with JFrame
	 */
	private final GameView view;
	/**
	 * board logic class field
	 */
	private final BoardLogic logics;
	/**
	 * last mouse event to compare with
	 */
	private MouseEvent lastEvent = null;
	/**
	 * potential new mouse event to compare with last event
	 */
	private MouseEvent currentEvent = null;
	/**
	 * bool variable to set thread activity
	 */
	private boolean active = true;

	/**
	 * thread constructor
	 * @param name name of the thread
	 * @param view GameView obj to work with JFrame mthds
	 * @param logics logic obj to send new events
	 */
	BoardMouseEventThread(String name, GameView view, BoardLogic logics){
		super(name);
		this.view = view;
		this.logics = logics;
	}

	/**
	 * starts a thread and call logic mthd when new click occurs
	 */
	@Override
	public void run() {
		while (active) {
			for(MouseEvent event: view.pollMouseEvents()){
				if(event.getID()==MouseEvent.MOUSE_CLICKED){
					lastEvent = event;
				}
			}
			if(lastEvent != null)
				if(!lastEvent.equals(currentEvent)){
					this.logics.blockPressed(lastEvent);
				}
			currentEvent = lastEvent;
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * interrupt thread while closing board
	 */
	public void disable(){
		setActive(false);
	}

	/**
	 * setter for active field
	 * @param active bool value to set
	 */
	private void setActive(boolean active) {
		this.active = active;
	}
}
