package thilo20.undo;

/**
 * Complex API which does not support an undo feature.
 * 
 * Assumptions: Undo is hard to implement as there are many internal operations
 * with side effects involved. Object state is hard to duplicate, requires a lot
 * of memory etc.
 */
public interface ComplexApi {

	/** complex operation modifying internal state */
	void op1();

	/** another complex operation with different signature and return type */
	int op2(int val);

}