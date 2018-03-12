package thilo20.undo;
import java.util.HashMap;
import java.util.Map;

/**
 * Complex API which does not support an undo feature. Assumptions: Undo is hard
 * to implement as there are many internal operations with side effects
 * involved. Object state is hard to duplicate, requires a lot of memory etc.
 */
public class ComplexApi {

	/** many private variables, maps and more.. */
	private int myVal;
	private Map<String, String> myMap;

	public ComplexApi() {
		myMap = new HashMap<>();
		myVal = 0;
	}

	/**
	 * copy c'tor creating duplication independent of other, e.g. deep copy.
	 * 
	 * @param other
	 */
	public ComplexApi(ComplexApi other) {
		myVal = other.myVal;
		myMap = new HashMap<>(other.myMap);
	}

	public void op1() {
		myVal++;
		// myVal = -myVal;
	}

	public int op2(int val) {
		// String v = myMap.get(String.valueOf(val));
		// if (v != null) {
		// v += val;
		// } else {
		// myMap.put("" + val, "");
		// }
		// myVal += val;
		// return val / 2;
		myVal++;
		return 0;
	}

	@Override
	public String toString() {
		return "ComplexApi [myVal=" + myVal + ", myMap=" + myMap + "]";
	}
}
