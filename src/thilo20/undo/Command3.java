package thilo20.undo;

/** actual command for op3 */
class Command3 implements Command {
	String name;

	public Command3(String name) {
		this.name = name;
	}

	@Override
	public void execute(ComplexApi target) {
		target.op3(name);
	}

	@Override
	public String toString() {
		return "op3(" + name + ")";
	}
}