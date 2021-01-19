package src.logic;

import src.common.DifficultyEnum;
import src.entity.GameBoard;
import src.entity.GameMenu;
import src.threads.MenuEventThread;
import src.view.GameView;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Draw logic class for Menu
 */
public class MenuLogic extends Coordinates implements ScreenLogic {
	/**
	 * view field to work with JFrame mthds
	 */
	private final GameView view;
	/**
	 * level of difficulty
	 */
	private DifficultyEnum difficulty = DifficultyEnum.EASY;

	/**
	 * Menu logic constructor
	 * @param absoluteX X coordinate to draw from
	 * @param absoluteY Y coordinate to draw from
	 * @param view GameView obj to work with JFrame
	 */
	public MenuLogic(int absoluteX, int absoluteY, GameView view) {
		super(absoluteX,absoluteY);
		this.view = view;
	}
	/**
	 * opens game board onclick of play button
	 * @param event mouse event from {@link MenuEventThread}
	 */
	public void click(InputEvent event) {
		try {
			KeyEvent keyEvent = (KeyEvent) event;
			if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER){
				openBoard();
			}
		}catch (ClassCastException exception) {
			MouseEvent e = (MouseEvent) event;
			int X = (e.getX() - ABSOLUTE_X);
			int Y = (e.getY() - ABSOLUTE_Y);
			System.out.println("pressed X " + X + " pressed Y " + Y);
			if (X > -220 && Y > 80 && X < -70 && Y < 135 && e.getButton() == MouseEvent.BUTTON1) {
				setDifficulty(DifficultyEnum.EASY);
				view.playSound("click.wav",false);
			}
			if (X > 25 && Y > 80 && X < 175 && Y < 135 && e.getButton() == MouseEvent.BUTTON1) {
				setDifficulty(DifficultyEnum.MEDIUM);
				view.playSound("click.wav",false);
			}
			if (X > 260 && Y > 80 && X < 410 && Y < 135 && e.getButton() == MouseEvent.BUTTON1) {
				setDifficulty(DifficultyEnum.HARD);
				view.playSound("click.wav",false);
			}
			if (X > 0 && Y > -25 && X < 200 && Y < 35 && e.getButton() == MouseEvent.BUTTON1){
				openBoard();
				view.playSound("click.wav",false);
			}
		}
	}

	/**
	 * initializes game board and disables menu mouse event thread
	 */
	public void openBoard(){
		GameMenu.getThread().disable();
		GameBoard board = new GameBoard(this.view,getDifficulty());
		board.init();
	}

	/**
	 * draw menu components
	 */
	public void draw() {
		view.addTextToCanvas("MineSweeper",ABSOLUTE_X-80,ABSOLUTE_Y-150,34,Color.white);
		view.addTextToCanvas("Play",ABSOLUTE_X+65,ABSOLUTE_Y-2,18,Color.white);
		view.addTextToCanvas("Easy",ABSOLUTE_X-180,ABSOLUTE_Y+100,18,Color.white);
		view.addTextToCanvas("Medium",ABSOLUTE_X+50,ABSOLUTE_Y+100,18,Color.white);
		view.addTextToCanvas("Hard",ABSOLUTE_X+300,ABSOLUTE_Y+100,18,Color.white);
		view.addTextToCanvas("Minesweeper is  a single - player puzzle video game. ",ABSOLUTE_X-250,ABSOLUTE_Y+144,14,Color.white);
		view.addTextToCanvas("The objective of the  game is to clear a rectangular ",ABSOLUTE_X-250,ABSOLUTE_Y+164,14,Color.white);
		view.addTextToCanvas("board containing hidden mines detonating any of them,",ABSOLUTE_X-250,ABSOLUTE_Y+184,14,Color.white);
		view.addTextToCanvas("with help info about the number of neighboring mines.",ABSOLUTE_X-250,ABSOLUTE_Y+204,14,Color.white);
		view.addTextToCanvas("LMB - open tile        RMB - flag tile as mine",ABSOLUTE_X-250,ABSOLUTE_Y+234,16,Color.white);
		view.addRectangleToCanvas(ABSOLUTE_X-220,ABSOLUTE_Y+80,150,50,3,false, Color.white);
		view.addRectangleToCanvas(ABSOLUTE_X+25,ABSOLUTE_Y+80,150,50,3,false, Color.white);
		view.addRectangleToCanvas(ABSOLUTE_X+260,ABSOLUTE_Y+80,150,50,3,false, Color.white);
		view.addRectangleToCanvas(ABSOLUTE_X,ABSOLUTE_Y-25,200,60,3,false, Color.white);
	}

	/**
	 * getter for difficulty level
	 * @return {@link DifficultyEnum obj representing difficulty}
	 */
	public DifficultyEnum getDifficulty() {
		return difficulty;
	}

	/**
	 * seeter for difficulty level
	 * @param difficulty {@link DifficultyEnum obj represents level of difficulty}
	 */
	public void setDifficulty(DifficultyEnum difficulty) {
		this.difficulty = difficulty;
	}
}
