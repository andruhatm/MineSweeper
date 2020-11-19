package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Main {

	static GameView view;

	static GameLogics logic;

	public static void main(String[] args) {
		view = new GameView(GameView.WINDOWSIZE_SMALL);
		logic = new GameLogics();

		view.useMouse(true);
		view.setWindowIcon("icon/mineIcon.jpg");
		view.setWindowTitle("MineSweeper");
		view.setStatusText("status text");

		int x = 0;
		int y = 0;


		view.addRectangleToCanvas(200,20,517,517,5,false,Color.white);

		view.printCanvas();

		logic.newGame();

		while(true){

			MouseEvent[] mouseEvents = view.pollMouseEvents();
			for (MouseEvent mouseEvent : mouseEvents) {
				if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
					x = mouseEvent.getX();
					y = mouseEvent.getY();
				}
			}
			System.out.println("X=" + x + " Y=" + y);
			view.printCanvas();
		}

	}

}
