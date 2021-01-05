package src;

import java.awt.event.MouseEvent;

/**
 * Thread for checking new mouse click events
 */
public class BoardMouseEventThread extends Thread{

	private final GameView view;
	private final BoardLogic logics;
	private MouseEvent lastEvent = null;
	private MouseEvent currentEvent = null;
	private boolean active = true;

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
		System.out.println("board thread started");
		while (active) {

			for(MouseEvent event: view.pollMouseEvents()){
				if(event.getID()==MouseEvent.MOUSE_CLICKED){
					lastEvent = event;
				}
			}
			if(lastEvent != null)
				if(lastEvent != currentEvent){
					//System.out.println("X: " + lastEvent.getX() + " Y: " + lastEvent.getY());
					this.logics.blockPressed(lastEvent);
				}

			currentEvent = lastEvent;

			System.out.println("working...");
			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void disable(){
		System.out.println("board thread disabled");
		currentThread().interrupt();
		setActive(false);
	}

	private boolean isActive() {
		return active;
	}

	private void setActive(boolean active) {
		this.active = active;
	}
}