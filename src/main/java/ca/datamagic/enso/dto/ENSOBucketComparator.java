/**
 * 
 */
package ca.datamagic.enso.dto;

import java.util.Comparator;

/**
 * @author Greg
 *
 */
public class ENSOBucketComparator implements Comparator<ENSOBucketDTO> {
	@Override
	public int compare(ENSOBucketDTO dto1, ENSOBucketDTO dto2) {
		if (dto1.getStartYear().intValue() < dto2.getStartYear().intValue()) {
			return -1;
		}
		if (dto1.getStartYear().intValue() > dto2.getStartYear().intValue()) {
			return +1;
		}
		if (dto1.getStartMonth().intValue() < dto2.getStartMonth().intValue()) {
			return -1;
		}
		if (dto1.getStartMonth().intValue() > dto2.getStartMonth().intValue()) {
			return +1;
		}
		return 0;
	}
}
