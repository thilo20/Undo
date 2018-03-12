package thilo20.undo;

/** actual command for op2 */
class Command2 implements Command {
	int val;

	public Command2(int param) {
		val = param;
	}

	@Override
	public void execute(ComplexApi target) {
		target.op2(val);
	}

	@Override
	public String toString() {
		return "op2(" + val + ")";
	}
}