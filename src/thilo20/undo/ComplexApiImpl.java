package thilo20.undo;
import java.util.HashMap;
import java.util.Map;

/**
 * Complex API which does not support an undo feature. Assumptions: Undo is hard
 * to implement as there are many internal operations with side effects
 * involved. Object state is hard to duplicate, requires a lot of memory etc.
 */
public class ComplexApiImpl implements ComplexApi {

	/** many private variables, maps and more.. */
	private int myVal; // will be used with increment/decrement for easy understanding of undo
	private Map<String, String> myMap; // arbitrary complex content

	public ComplexApiImpl() {
		myMap = new HashMap<>();
		myVal = 0;
	}

	/**
	 * copy c'tor creating duplication independent of other, e.g. deep copy.
	 * 
	 * @param other
	 */
	public ComplexApiImpl(ComplexApiImpl other) {
		myVal = other.myVal;
		myMap = new HashMap<>(other.myMap);

		delay(Config.DELAY_COPY_INSTANCE);
	}

	/* (non-Javadoc)
	 * @see thilo20.undo.ComplexApi#op1()
	 */
	@Override
	public void op1() {
		myVal++;

		delay(Config.DELAY_OP1);
	}

	/* (non-Javadoc)
	 * @see thilo20.undo.ComplexApi#op2(int)
	 */
	@Override
	public int op2(int val) {
		myVal++;

		delay(Config.DELAY_OP2);

		// anything supposed to be tricky
		String v = myMap.get(String.valueOf(val));
		if (v != null) {
			v += (char) ('a' + val);
			myMap.put("" + val, v);
		} else {
			myMap.put("" + val, "x");
		}
		return myMap.size() + val / 2;
	}

	@Override
	public String toString() {
		return "ComplexApi [myVal=" + myVal + ", myMap=" + myMap + "]";
	}

	/** Delays execution for given time in ms. */
	private void delay(long millis) {
		if (!Config.DELAY_ON)
			return;
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
