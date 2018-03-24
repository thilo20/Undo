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

	/** Shows undo feature with simple command pattern implementation. */
	public static void demo1() {
		ComplexApiWithUndo ca = new ComplexApiWithUndoSimple();
		ca.op1();
		ca.op1();
		ca.op1();
		ca.op2(42);
		ca.op1();
		// debug here to see first command applied:
		ca.op1();
		// undo required..
		ca.getUndoApi().undo(1);
	}

	/** Shows undo feature with generic command pattern implementation. */
	public static void demo2() {
		ComplexApiWithUndo ca = new ComplexApiWithUndoGeneric();
		ca.op1();
		ca.op1();
		ca.op1();
		ca.op2(42);
		ca.op1();
		// debug here to see first command applied:
		ca.op1();
		// undo required..
		ca.getUndoApi().undo(1);
	}

	public static void main(String[] args) {
		orig();
		demo1();
		demo2();
	}

}
