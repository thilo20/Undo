package thilo20.undo;

/** command interface according to GoF. */
public interface Command {

	/** Executes command on target. */
	public void execute(ComplexApi target);
}
