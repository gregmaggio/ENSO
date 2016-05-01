/**
 * 
 */
package ca.datamagic.enso.dto;

import java.util.Comparator;

/**
 * @author Greg
 *
 */
public class YearComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer value1, Integer value2) {
		if ((value1 != null) && (value2 != null)) {
			if (value1.intValue() < value2.intValue()) {
				return -1;
			}
			if (value1.intValue() > value2.intValue()) {
				return +1;
			}
		}
		return 0;
	}
}
