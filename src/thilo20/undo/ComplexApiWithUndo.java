package thilo20.undo;

import java.util.ArrayList;
import java.util.List;

/** undo support for unmodified complex API. */
public class ComplexApiWithUndo implements ComplexApi {

	/** original api with all operations directly applied. */
	ComplexApiImpl caNew;
	/** original api with delayed operations, n operations behind caNew. */
	ComplexApiImpl caOld;

	/**
	 * undo-able commands. These are the last commands directly applied to caNew but
	 * not yet to caOld.
	 */
	List<Command> commandsUndo;

	/** commands previously undone, may be re-applied to caNew. */
	List<Command> commandsRedo;

	/** instance of inner class. */
	protected UndoImpl myUndoFeatures = new UndoImpl();

	/** Constructor initializing the inner 2 ComplexApi instances. */
	public ComplexApiWithUndo() {
		caNew = new ComplexApiImpl();
		caOld = new ComplexApiImpl(caNew);
		commandsUndo = new ArrayList<>();
		commandsRedo = new ArrayList<>();
		System.out.println("Command history, maximum number of operations: " + Config.MAX_COMMANDS);
	}

	/**
	 * Adds a new command to command list and executes oldest command if list is
	 * exceeds size.
	 * 
	 * @param comm
	 *            new command
	 */
	protected void addCommand(Command comm) {
		// store new command
		commandsUndo.add(comm);

		if (commandsUndo.size() > Config.MAX_COMMANDS) {
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

	/** Provides the undo functionality. */
	public Undo getUndoApi() {
		return myUndoFeatures;
	}

	/** Internal undo implementation. */
	class UndoImpl implements Undo {

		/*
		 * (non-Javadoc)
		 * 
		 * @see thilo20.undo.Undo#undoAll()
		 */
		@Override
		public void undoAll() {
			System.out.println("reset to old state.");
			// reset caNew to caOld
			caNew = new ComplexApiImpl(caOld);

			// populate redo
			commandsUndo.addAll(commandsRedo);
			commandsRedo.clear();
			commandsRedo.addAll(commandsUndo);
			// commandsRedo.addAll(0, commandsUndo);
			// clear undo
			commandsUndo.clear();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see thilo20.undo.Undo#redo(int)
		 */
		@Override
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see thilo20.undo.Undo#undo(int)
		 */
		@Override
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

		/*
		 * (non-Javadoc)
		 * 
		 * @see thilo20.undo.Undo#getUndoCommands()
		 */
		@Override
		public List<Command> getUndoCommands() {
			return commandsUndo;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see thilo20.undo.Undo#getRedoCommands()
		 */
		@Override
		public List<Command> getRedoCommands() {
			return commandsRedo;
		}
	}

	@Override
	public String toString() {
		// pretend to be normal ComplexApi
		return caNew.toString();
	}

	/** Helper to understand what's going on. */
	public void printState() {
		System.out.println("ComplexApiWithUndo: undo=" + commandsUndo.size() + " redo=" + commandsRedo.size()
				+ " toString: " + toString());
	}

}
