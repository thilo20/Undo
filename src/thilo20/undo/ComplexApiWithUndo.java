package thilo20.undo;

import java.util.ArrayList;
import java.util.List;

/** undo support for unmodified complex API. */
public class ComplexApiWithUndo extends ComplexApi {

	/** original api with all operations directly applied. */
	ComplexApi caNew;
	/** original api with delayed operations, n operations behind caNew. */
	ComplexApi caOld;

	/** max undo operations, max delay between caNew and caOld. */
	final int MAX_COMMANDS = 5;
	/**
	 * undo-able commands. These are the last commands directly applied to caNew but
	 * not yet to caOld.
	 */
	List<Command> commandsUndo;

	/** commands previously undone, may be re-applied to caNew. */
	List<Command> commandsRedo;

	public ComplexApiWithUndo() {
		caNew = new ComplexApi();
		caOld = new ComplexApi(caNew);
		commandsUndo = new ArrayList<>();
		commandsRedo = new ArrayList<>();
	}

	/** Adds a new command to command list and executes oldest command if list is exceeds size.
	 * 
	 *  @param comm new command
	 */
	private void addCommand(Command comm) {
		// store new command
		commandsUndo.add(comm);
		
		if (commandsUndo.size() > MAX_COMMANDS) {
			System.out.println("undo limit exceeded, updating caOld.");
			// remove and execute oldest command
			commandsUndo.remove(0).execute(caOld);
		}
	}

	@Override
	public void op1() {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new Command1());
		// apply command to caNew
		caNew.op1();
	}

	@Override
	public int op2(int val) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new Command2(val));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op2(val);
	}

	/** resets to the oldest restorable state, caOld. */
	public void undoAll() {
		// reset caNew to caOld
		caNew = new ComplexApi(caOld);

		// populate redo
		commandsUndo.addAll(commandsRedo);
		commandsRedo.clear();
		commandsRedo.addAll(commandsUndo);
		// commandsRedo.addAll(0, commandsUndo);
		// clear undo
		commandsUndo.clear();
	}

	/** re-applies commands to caNew. */
	public void redo(int n) {
		if (n < 1)
			return;
		if (n > commandsRedo.size()) {
			n = commandsRedo.size();
		}
		// apply first commands again, populating undo
		for (int i = 0; i < n; i++) {
			addCommand(commandsRedo.get(i));
			commandsRedo.get(i).execute(caNew);
		}
		// delete them
		commandsRedo = commandsRedo.subList(n, commandsRedo.size());
	}

	/**
	 * core feature: undo last commands applied to this ComplexApi.
	 *
	 * @param n
	 *            number of operations to undo, >0
	 */
	public void undo(int n) {
		if (n < 1)
			return;
		if (commandsUndo.isEmpty())
			return;

		// how many commands to re-apply for intended state?
		int comm = commandsUndo.size() - n;

		// we have no reverse operations for ComplexApi.op1() and ComplexApi.op2(int)
		// - must reset to backup state, caOld
		undoAll();

		// undo is actually solved by redo of commands.
		// no reverse commands are required!
		redo(comm);
	}



	@Override
	public String toString() {
		// pretend to be normal ComplexApi
		return caNew.toString();
	}

	public void printState() {
		System.out.println("ComplexApiWithUndo: undo=" + commandsUndo.size() + " redo=" + commandsRedo.size()
				+ " toString: " + toString());
	}

	/** Provides undo-able commands for GUI listing. */
	public List<Command> getUndoCommands() {
		return commandsUndo;
	}

	/** Provides redo-able commands for GUI listing. */
	public List<Command> getRedoCommands() {
		return commandsRedo;
	}
}
