package thilo20.undo;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * Actual command for any operation, solved by reflection.
 * 
 * Uses Apache commons-lang.
 */
class CommandGeneric implements Command {
	/** target method name */
	String methodName;
	/** variable arguments */
	Object[] args;

	/** Constructor with method to call and given parameters. */
	public CommandGeneric(String methodName, Object[] args) {
		this.methodName = methodName;
		this.args = args;
	}

	@Override
	public void execute(ComplexApi target) {
		// dev note: this might help:
		// https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/MethodUtils.html
		try {
			// resolve and invoke method
			MethodUtils.invokeMethod(target, methodName, args);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public String toString() {
		if (args.length == 0) {
		return methodName;
		} else {
			return methodName + "(" + Arrays.toString(args) + ")";
		}
	}
}
