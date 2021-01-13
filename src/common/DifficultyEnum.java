package src.common;

/**
 * Enum represents level of difficulty
 */
public enum  DifficultyEnum{
	EASY(10),
	MEDIUM(20),
	HARD(30);

	/**
	 * Difficulty field
	 */
	private final Integer difficulty;

	/**
	 * Difficulty constructor
	 * @param difficulty {@link Integer representation of difficulty}
	 */
	DifficultyEnum(Integer difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * getter for difficulty value
	 * @return difficulty Integer value
	 */
	public Integer getValue() {
		return difficulty;
	}

	@Override
	public String toString() {
		return "DifficultyEnum{" +
						"difficulty=" + difficulty +
						'}';
	}
}
