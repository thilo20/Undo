package thilo20.undo;

/**
 * undo support for unmodified complex API. This version uses reflection to
 * invoke commands, see {@link CommandGeneric}.
 */
public class ComplexApiWithUndoGeneric extends ComplexApiWithUndo {

	@Override
	public void op1() {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new CommandGeneric("op1", new Object[] {}));
		// apply command to caNew
		caNew.op1();
	}

	@Override
	public int op2(int val) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new CommandGeneric(val));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op2(val);
	}

	@Override
	public String op3(String name) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new CommandGeneric(name));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op3(name);
	}

	@Override
	public double op4(int val, Double val2) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new CommandGeneric(val, val2));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op4(val, val2);
	}

}
