package src.logic;

import java.awt.event.InputEvent;

/**
 * Logic interface
 * @version 1.0
 */
public interface ScreenLogic {

	/**
	 * onClick mthd
	 * @param event event from thread
	 */
	void click(InputEvent event);

	/**
	 * draw components mthd
	 */
	void draw();

}
