package Calculator;

public interface Calculator {
	public static final String DIVISION_BY_ZERO = "Division by zero";
	public static final String BAD_FORMAT = "Bad formatted input";
	public static final String END_LIST_UP = "End of the list up";
	public static final String END_LIST_DOWN = "End of the list down";

	/*
	 * Take input from user
	 */
	public void Input(String s);

	/*
	 * return the current formula
	 */
	public String current_formula();

	/*
	 * Return the result of the current operations or error message one of
	 * (Division by zero - bad formatted input)
	 */
	public String getResults();

	/*
	 * Save in file the last 5 done Operations
	 */

	public void save();

	/*
	 * Load from file the saved operations
	 */

	public void load();

	/*
	 * return the last operation in String format
	 */
	public String prev();

	/*
	 * return the next operation in String format
	 */
	public String next();
}

