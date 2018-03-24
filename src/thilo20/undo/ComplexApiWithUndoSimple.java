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


}
