package src.threads;

import src.logic.MenuLogic;
import src.view.GameView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Thread for checking new mouse events from menu
 */
public class MenuEventThread extends Thread{

	/**
	 * GameView obj to work with JFrame
	 */
	private final GameView view;
	/**
	 * menu logic class field
	 */
	private final MenuLogic logics;
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
	 * last key event to compare with
	 */
	private KeyEvent lastKeyEvent;

	/**
	 * potential new key event to compare with last event
	 */
	private KeyEvent currentKeyEvent;

	/**
	 * thread constructor
	 * @param name name of the thread
	 * @param view GameView obj to work with JFrame mthds
	 * @param logics logic obj to send new events
	 */
	public MenuEventThread(String name, GameView view, MenuLogic logics){
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
				if(event.getID() == MouseEvent.MOUSE_CLICKED){
					lastEvent = event;
				}
			}
			for(KeyEvent event: view.pollKeyEvents()){
					lastKeyEvent = event;
			}
			if (lastKeyEvent != null)
				if(!lastKeyEvent.equals(currentKeyEvent))
					this.logics.click(lastKeyEvent);

			if(lastEvent != null)
				if(!lastEvent.equals(currentEvent)){
					this.logics.click(lastEvent);
				}
			currentEvent = lastEvent;
			currentKeyEvent = lastKeyEvent;
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * interrupt thread while closing menu
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
