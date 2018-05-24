package thilo20.undo;

/**
 * Complex API (example impl) which does not support an undo feature.
 * 
 * These are the exposed operations that shall be undo-able.
 */
public interface ComplexApi {

	/** complex operation modifying internal state */
	void op1();

	/** another complex operation with different signature and return type */
	int op2(int val);

	/** another complex operation with different signature and return type */
	String op3(String name);

	/** another complex operation with different signature and return type */
	double op4(int val, Double val2);
}