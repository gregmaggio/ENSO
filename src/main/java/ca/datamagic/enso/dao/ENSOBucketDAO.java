/**
 * 
 */
package ca.datamagic.enso.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.datamagic.enso.dto.ENSOBucketDTO;
import ca.datamagic.enso.dto.ENSORangeDTO;
import ca.datamagic.enso.dto.ONIDTO;
import ca.datamagic.enso.inject.MemoryCache;

/**
 * @author Greg
 *
 */
public class ENSOBucketDAO {
	public ENSOBucketDAO() {
	}

	@MemoryCache
	public List<ENSOBucketDTO> getAll() throws SQLException {
		List<ENSOBucketDTO> all = new ArrayList<ENSOBucketDTO>();
		ONIDAO dao = new ONIDAO();
		List<Integer> years = dao.getYears();
		for (int ii = 0; ii < years.size(); ii++) {
			Integer year = years.get(ii);
			List<ENSOBucketDTO> buckets = ENSOBucketDTO.getBuckets(year);
			List<ONIDTO> onis = dao.getAll(year);
			for (int jj = 0; jj < buckets.size(); jj++) {
				ENSOBucketDTO bucket = buckets.get(jj);
				ONIDTO oni1 = null;
				ONIDTO oni2 = null;
				ONIDTO oni3 = null;
				for (int kk = 0; kk < onis.size(); kk++) {
					ONIDTO oni = onis.get(kk); 
					int startCompare = bucket.getStart().compareTo(oni.getCalendar());
					int endCompare = bucket.getEnd().compareTo(oni.getCalendar());
					if ((startCompare <= 0) && (endCompare >= 0)) {
						if (oni1 == null) {
							oni1 = oni;
						} else if (oni2 == null) {
							oni2 = oni;
						} else if (oni3 == null) {
							oni3 = oni;
						} else {
							break;
						}
					}
				}
				double anom = 0.0;
				int count = 0;
				if ((oni1 != null) && (oni1.getAnom() != null)) {
					anom += oni1.getAnom().doubleValue();
					count++;
				}
				if ((oni2 != null) && (oni2.getAnom() != null)) {
					anom += oni2.getAnom().doubleValue();
					count++;
				}
				if ((oni3 != null) && (oni3.getAnom() != null)) {
					anom += oni3.getAnom().doubleValue();
					count++;
				}
				if (count > 0) {
					bucket.setAnom(anom / count);
				}
			}
			all.addAll(buckets);
		}
		return all;
	}
	
	@MemoryCache
	public List<ENSOBucketDTO> getAll(Integer year) throws SQLException {
		List<ENSOBucketDTO> buckets = ENSOBucketDTO.getBuckets(year);
		ONIDAO dao = new ONIDAO();
		List<ONIDTO> onis = dao.getAll(year);
		for (int jj = 0; jj < buckets.size(); jj++) {
			ENSOBucketDTO bucket = buckets.get(jj);
			ONIDTO oni1 = null;
			ONIDTO oni2 = null;
			ONIDTO oni3 = null;
			for (int kk = 0; kk < onis.size(); kk++) {
				ONIDTO oni = onis.get(kk); 
				int startCompare = bucket.getStart().compareTo(oni.getCalendar());
				int endCompare = bucket.getEnd().compareTo(oni.getCalendar());
				if ((startCompare <= 0) && (endCompare >= 0)) {
					if (oni1 == null) {
						oni1 = oni;
					} else if (oni2 == null) {
						oni2 = oni;
					} else if (oni3 == null) {
						oni3 = oni;
					} else {
						break;
					}
				}
			}
			double anom = 0.0;
			int count = 0;
			if ((oni1 != null) && (oni1.getAnom() != null)) {
				anom += oni1.getAnom().doubleValue();
				count++;
			}
			if ((oni2 != null) && (oni2.getAnom() != null)) {
				anom += oni2.getAnom().doubleValue();
				count++;
			}
			if ((oni3 != null) && (oni3.getAnom() != null)) {
				anom += oni3.getAnom().doubleValue();
				count++;
			}
			if (count > 0) {
				bucket.setAnom(anom / count);
			}
		}
		return buckets;
	}
	
	@MemoryCache
	public List<Integer> getYears() throws SQLException {
		ONIDAO dao = new ONIDAO();
		return dao.getYears();
	}
	
	@MemoryCache
	public List<ENSORangeDTO> getRanges() throws SQLException {
		List<ENSORangeDTO> ranges = new ArrayList<ENSORangeDTO>();
		List<ENSOBucketDTO> buckets = getAll();
		ENSORangeDTO current = null;
		for (int ii = 0; ii < buckets.size(); ii++) {
			if (current != null) {
				if (current.isElNino() && buckets.get(ii).isElNino()) {
					current.setEndYear(buckets.get(ii).getEndYear());
					current.setEndMonth(buckets.get(ii).getEndMonth());
				} else if (current.isLaNina() && buckets.get(ii).isLaNina()) {
					current.setEndYear(buckets.get(ii).getEndYear());
					current.setEndMonth(buckets.get(ii).getEndMonth());
				} else {
					if (current != null) {
						ranges.add(current);
						current = null;
					}
					if (buckets.get(ii).isElNino() || buckets.get(ii).isLaNina()) {
						current = new ENSORangeDTO(buckets.get(ii));
					}
				}
			} else {
				if (buckets.get(ii).isElNino() || buckets.get(ii).isLaNina()) {
					current = new ENSORangeDTO(buckets.get(ii));
				}
			}
		}
		return ranges;
	}
}
