package src;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuLogic {

	private final GameView view;

	private final int absoluteX;

	private final int absoluteY;

	public MenuLogic(int absoluteX, int absoluteY, GameView view) {
		this.view = view;
		this.absoluteX = absoluteX;
		this.absoluteY = absoluteY;
	}

	public void menuClick(MouseEvent e) {
		int X = (e.getX()-absoluteX);
		int Y = (e.getY()-absoluteY);
		System.out.println("pressed X " + X + " pressed Y " + Y);

		if (X < 0 || Y < 0 || X > 200 || Y > 60){
			return;
		}

		 if (e.getButton() == MouseEvent.BUTTON1) {

			GameMenu.thread.disable();
			GameBoard board = new GameBoard(this.view);
			board.startGame();
		}
	}

	public void drawMenu() {
		view.addTextToCanvas("MineSweeper",absoluteX-80,absoluteY-150,34,Color.white);
		view.addTextToCanvas("Play",absoluteX+65,absoluteY+23,18,Color.white);
		view.addTextToCanvas("Minesweeper is a single-player puzzle video game. ",absoluteX-250,absoluteY+144,14,Color.white);
		view.addTextToCanvas("The objective of the game is to clear a rectangular ",absoluteX-250,absoluteY+164,14,Color.white);
		view.addTextToCanvas("board containing hidden mines detonating any of them,",absoluteX-250,absoluteY+184,14,Color.white);
		view.addTextToCanvas("with help info about the number of neighboring mines.",absoluteX-250,absoluteY+204,14,Color.white);

		view.addRectangleToCanvas(absoluteX,absoluteY,200,60,3,false, Color.white);
	}
}
