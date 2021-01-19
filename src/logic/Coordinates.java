package src.logic;

/**
 * Base class for coordinates
 * @version 1.0
 */
public class Coordinates {
	/**
	 * constant of X as starting draw coordinate
	 */
	protected final int ABSOLUTE_X;
	/**
	 * constant of Y as starting draw coordinate
	 */
	protected final int ABSOLUTE_Y;

	/**
	 * constructor for base logic class
	 * @param ABSOLUTE_X x starting draw coordinate
	 * @param ABSOLUTE_Y y starting draw coordinate
	 */
	public Coordinates(int ABSOLUTE_X, int ABSOLUTE_Y) {
		this.ABSOLUTE_X = ABSOLUTE_X;
		this.ABSOLUTE_Y = ABSOLUTE_Y;
	}
}
