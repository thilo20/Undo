package thilo20.undo;
/** actual command for op1 */
class Command1 implements Command {
	@Override
	public void execute(ComplexApi target) {
		target.op1();
	}

	@Override
	public String toString() {
		return "op1";
	}
}
