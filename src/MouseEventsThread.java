package src;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class MouseEventsThread extends Thread{

	GameView view;
	MouseEvent lastEvent = null;
	MouseEvent currentEvent = null;

	MouseEventsThread(String name,GameView view){
		super(name);
		this.view = view;
		//event holder
		//events.add(new MouseEvent(new JPanel(),1,2, InputEvent.SHIFT_MASK,2,3,1,false));
	}

	@Override
	public void run() {
		System.out.println("mouseThread started");
		while (true) {

//			clickEvents.clear();
//			for(MouseEvent event: view.pollMouseEvents()){
//				if(event.getID()==MouseEvent.MOUSE_CLICKED){
//					clickEvents.add(event);
//				}
//			}
//
//			if(lastEvent != null) {
//				newClickEvents.clear();
//				for(MouseEvent event: view.pollMouseEvents()){
//					if(event.getID()==MouseEvent.MOUSE_CLICKED){
//						newClickEvents.add(event);
//					}
//				}
//			}

			for(MouseEvent event: view.pollMouseEvents()){
				if(event.getID()==MouseEvent.MOUSE_CLICKED){
					lastEvent = event;
				}
			}
			if(lastEvent != null)
				if(lastEvent != currentEvent){
					System.out.println("X: " + lastEvent.getX() + " Y: " + lastEvent.getY());
					GameLogics logics = new GameLogics();
					logics.blockPressed(lastEvent);
				}


			currentEvent = lastEvent;

			System.out.println("working...");
			try {
				Thread.sleep(1000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
