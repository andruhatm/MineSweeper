package src.common;

import src.logic.BoardLogic;
import src.view.GameView;
import java.awt.*;

/**
 * Thread for count time
 */
public class Timer extends Thread {
	/**
	 * view field to work with JFrame mthds
	 */
	private final GameView view;
	/**
	 * millisecounds field
	 */
	private int millisecounds = 0;
	/**
	 * secounds field
	 */
	private int secounds = 0;
	/**
	 * minutes field
	 */
	private int minutes = 0;
	/**
	 * refresh millisecounds counter
	 */
	private int r_millis;
	/**
	 * bool variable to set thread activity
	 */
	private boolean exit = false;
	/**
	 * constant of X as starting draw coordinate
	 */
	private final int ABSOLUTE_X;
	/**
	 * constant of Y as starting draw coordinate
	 */
	private final int ABSOLUTE_Y;
	/**
	 * board logic class field
	 */
	private final BoardLogic logics;

	/**
	 * Timer constructor
	 * @param view GameView obj to work with JFrame
	 * @param absoluteX starting X coordinate to draw
	 * @param absoluteY starting Y coordinate to draw
	 * @param logics BoardLogics obj to call draw mthd
	 */
	public Timer(GameView view, int absoluteX, int absoluteY, BoardLogic logics) {
		this.view = view;
		r_millis = 0;
		this.ABSOLUTE_X = absoluteX;
		this.ABSOLUTE_Y = absoluteY;
		this.logics = logics;
	}

	/**
	 * starts a thread and call logic mthd to draw board and timer
	 */
	public void run() {
		while(!exit) {
			try {
				Thread.sleep(1L);
				if (millisecounds > 380 ) {
					millisecounds = 0;
					++secounds;
				}

				if (secounds > 59) {
					secounds = 0;
					++minutes;
				}

				if (r_millis == 50){
					view.addTextToCanvas(getTime(),ABSOLUTE_X + 530, ABSOLUTE_Y+50,22, Color.white);
					logics.draw();
					r_millis = 0;
				}

				++millisecounds;
				++r_millis;
			} catch (Exception exception) {
				exception.getMessage();
			}
		}
	}

	/**
	 * getter for secounds
	 * @return secounds counter in String
	 */
	public String getSecounds() {
		return String.valueOf(secounds);
	}

	/**
	 * getter for minutes
	 * @return minutes counter in String
	 */
	public String getMinutes() {
		return String.valueOf(minutes);
	}

	/**
	 * setter for exit field
	 * @param exit bool value to set
	 */
	public void setExit(boolean exit) {
		this.exit = exit;
	}

	/**
	 * getter for timer
	 * @return value of timer
	 */
	public String getTime(){
		final StringBuilder sb = new StringBuilder("Time: ");
			sb.append(getMinutes());
			sb.append(":").append(getSecounds());
			return sb.toString();
	}
}
