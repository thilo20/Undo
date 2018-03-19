package thilo20.undo;

/** Configuration of undo setup. */
public class Config {

	/** max undo operations, max delay between caNew and caOld. */
	final static int MAX_COMMANDS = 50;

	/**
	 * performance simulation, optional. How much time does undo cost you? is it
	 * user-acceptable? Use values derived from your own complex API.
	 */
	final static boolean DELAY_ON = false;
	final static long DELAY_COPY_INSTANCE = 500l;
	final static long DELAY_OP1 = 100l;
	final static long DELAY_OP2 = 100l;

}
