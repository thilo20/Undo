package thilo20.undo;

/** actual command for op2 */
class Command4 implements Command {
	int val;
	Double val2;

	public Command4(int param, Double param2) {
		val = param;
		val2 = param2;
	}

	@Override
	public void execute(ComplexApi target) {
		target.op4(val, val2);
	}

	@Override
	public String toString() {
		return "op4(" + val + ", " + val2 + ")";
	}
}