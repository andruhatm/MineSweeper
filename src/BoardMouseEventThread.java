package src;

import java.awt.event.MouseEvent;

/**
 * Thread for checking new mouse click events
 */
public class BoardMouseEventThread extends Thread{

	GameView view;
	BoardLogic logics;
	MouseEvent lastEvent = null;
	MouseEvent currentEvent = null;

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
		System.out.println("mouseThread started");
		while (true) {

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
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
