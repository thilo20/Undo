package thilo20.undo;

import java.util.List;

public interface Undo {

	/** resets to the oldest restorable state, caOld. */
	void undoAll();

	/** re-applies commands to caNew. */
	void redo(int n);

	/**
	 * core feature: undo last commands applied to this ComplexApi.
	 *
	 * @param n
	 *            number of operations to undo, >0
	 */
	void undo(int n);

	/** Provides undo-able commands for GUI listing. */
	List<Command> getUndoCommands();

	/** Provides redo-able commands for GUI listing. */
	List<Command> getRedoCommands();

}