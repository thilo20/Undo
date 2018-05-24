package thilo20.undo;

import java.util.List;

/** Definition of provided undo functionality. */
public interface Undo {

	/**
	 * Revokes the last commands applied to the wrapped ComplexApi.
	 * (core feature of this whole project!)
	 * @param n number of operations to undo, >0
	 */
	void undo(int n);

	/**
	 * Resets to the oldest restorable state.
	 * Depends on configured history size {@link Config#MAX_COMMANDS}.
	 */
	void undoAll();

	/**
	 * Re-applies commands that have been undone right before.
	 * @param n number of operations to redo, >0
	 */
	void redo(int n);

	/** Provides undo-able commands for GUI listing. */
	List<Command> getUndoCommands();

	/** Provides redo-able commands for GUI listing. */
	List<Command> getRedoCommands();

}