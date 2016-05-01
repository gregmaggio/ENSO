package ca.datamagic.enso.dto;

import java.util.Comparator;

public class ENSORangeComparator implements Comparator<ENSORangeDTO> {
	@Override
	public int compare(ENSORangeDTO dto1, ENSORangeDTO dto2) {
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
