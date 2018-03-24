package thilo20.undo;

/**
 * undo support for unmodified complex API. This version uses one Command object
 * per method to invoke commands, see {@link Command1} and {@link Command2}.
 */
public class ComplexApiWithUndoSimple extends ComplexApiWithUndo {

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

	@Override
	public String op3(String name) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new Command3(name));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op3(name);
	}

	@Override
	public double op4(int val, Double val2) {
		// clear redo
		commandsRedo.clear();
		// store command for caOld
		addCommand(new Command4(val, val2));
		// apply command to caNew
		// note: this cannot be integrated in addCommand as return types might differ!
		return caNew.op4(val, val2);
	}

}
