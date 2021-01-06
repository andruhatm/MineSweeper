package src.menu;

import src.board.GameBoard;
import src.view.GameView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Draw logic class for Menu
 */
public class MenuLogic {
	/**
	 * view field to work with JFrame mthds
	 */
	private final GameView view;
	/**
	 * constant of X as starting draw coordinate
	 */
	private final int ABSOLUTE_X;
	/**
	 * constant of Y as starting draw coordinate
	 */
	private final int ABSOLUTE_Y;

	/**
	 * Menu logic constructor
	 * @param absoluteX X coordinate to draw from
	 * @param absoluteY Y coordinate to draw from
	 * @param view GameView obj to work with JFrame
	 */
	public MenuLogic(int absoluteX, int absoluteY, GameView view) {
		this.view = view;
		this.ABSOLUTE_X = absoluteX;
		this.ABSOLUTE_Y = absoluteY;
	}

	public void menuPressed(){
		GameMenu.thread.disable();
		GameBoard board = new GameBoard(this.view);
		board.startGame();
	}

	/**
	 * opens game board onclick of play button
	 * @param e mouse event from {@link MenuMouseEventThread}
	 */
	public void menuClick(MouseEvent e) {
		int X = (e.getX()-ABSOLUTE_X);
		int Y = (e.getY()-ABSOLUTE_Y);
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

	/**
	 * draw menu components
	 */
	public void drawMenu() {
		view.addTextToCanvas("MineSweeper",ABSOLUTE_X-80,ABSOLUTE_Y-150,34,Color.white);
		view.addTextToCanvas("Play",ABSOLUTE_X+65,ABSOLUTE_Y+23,18,Color.white);
		view.addTextToCanvas("Minesweeper is  a single - player puzzle video game. ",ABSOLUTE_X-250,ABSOLUTE_Y+144,14,Color.white);
		view.addTextToCanvas("The objective of the  game is to clear a rectangular ",ABSOLUTE_X-250,ABSOLUTE_Y+164,14,Color.white);
		view.addTextToCanvas("board containing hidden mines detonating any of them,",ABSOLUTE_X-250,ABSOLUTE_Y+184,14,Color.white);
		view.addTextToCanvas("with help info about the number of neighboring mines.",ABSOLUTE_X-250,ABSOLUTE_Y+204,14,Color.white);
		view.addTextToCanvas("LMB - open tile        RMB - flag tile as mine",ABSOLUTE_X-250,ABSOLUTE_Y+234,16,Color.white);
		view.addRectangleToCanvas(ABSOLUTE_X,ABSOLUTE_Y,200,60,3,false, Color.white);
	}
}
