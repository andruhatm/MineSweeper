package src;

import java.awt.event.MouseEvent;

public class MenuMouseEventThread extends Thread{

	private final GameView view;
	private final MenuLogic logics;
	private MouseEvent lastEvent = null;
	private MouseEvent currentEvent = null;
	private boolean active = true;

	MenuMouseEventThread(String name, GameView view, MenuLogic logics){
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

			if(lastEvent != null)
				if(lastEvent != currentEvent){
					this.logics.menuClick(lastEvent);
					System.out.println("menuClick mthd");
				}

			currentEvent = lastEvent;

			System.out.println("menu thread is working...");

			try {
				Thread.sleep(50);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void disable(){
		System.out.println("menu thread disabled");
		currentThread().interrupt();
		setActive(false);
//		view.printCanvas();
	}

	private boolean isActive() {
		return active;
	}

	private void setActive(boolean active) {
		this.active = active;
	}
}
