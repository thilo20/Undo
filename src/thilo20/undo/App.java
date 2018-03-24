package thilo20.undo;

/** App using ComplexApi, in need of undo support. */
public class App {

	/** Original code using original ComplexApi. */
	public static void orig() {
		ComplexApi ca = new ComplexApiImpl();
		ca.op1();
		ca.op1();
		ca.op1();
		ca.op2(42);
		// undo required but not available..
		// ca.undo(1);
	}

	/** Shows undo feature with hidden undo method. */
	public static void demo1() {
		ComplexApi ca = new ComplexApiWithUndo();
		ca.op1();
		ca.op1();
		ca.op1();
		ca.op2(42);
		// undo required..
		((ComplexApiWithUndo) ca).getUndoApi().undo(1);
	}

	/** Shows undo feature with public undo method. */
	public static void demo2() {
		ComplexApiWithUndo ca = new ComplexApiWithUndo();
		ca.op1();
		ca.op1();
		ca.op1();
		ca.op2(42);
		// undo required..
		ca.getUndoApi().undo(1);
	}

	public static void main(String[] args) {
		orig();
		demo1();
		demo2();
	}

}
