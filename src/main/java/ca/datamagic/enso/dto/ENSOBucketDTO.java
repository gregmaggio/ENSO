/**
 * 
 */
package ca.datamagic.enso.dto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Greg
 *
 */
public class ENSOBucketDTO {
	private String _text = null;
	private Integer _startYear = null;
	private Integer _startMonth = null;
	private Integer _endYear = null;
	private Integer _endMonth = null;
	private Double _anom = null;
	private Boolean _elNino = false;
	private Boolean _laNina = false;
	
	public ENSOBucketDTO() {
	}

	public static List<ENSOBucketDTO> getBuckets(int year) {
		List<ENSOBucketDTO> buckets = new ArrayList<ENSOBucketDTO>();
		for (int ii = 0; ii < 12; ii++) {
			buckets.add(getBucket(year, ii));
		}
		return buckets;
	}
	
	public static ENSOBucketDTO getBucket(int year, int index) {
		ENSOBucketDTO bucket = null;
		switch (index) {
		case 0:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year - 1);
			bucket.setStartMonth(12);
			bucket.setEndYear(year);
			bucket.setEndMonth(2);
			bucket.setText("DJF");
			break;
		case 1:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(1);
			bucket.setEndYear(year);
			bucket.setEndMonth(3);
			bucket.setText("JFM");
			break;
		case 2:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(2);
			bucket.setEndYear(year);
			bucket.setEndMonth(4);
			bucket.setText("FMA");
			break;
		case 3:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(3);
			bucket.setEndYear(year);
			bucket.setEndMonth(5);
			bucket.setText("MAM");
			break;
		case 4:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(4);
			bucket.setEndYear(year);
			bucket.setEndMonth(6);
			bucket.setText("AMJ");
			break;
		case 5:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(5);
			bucket.setEndYear(year);
			bucket.setEndMonth(7);
			bucket.setText("MJJ");
			break;
		case 6:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(6);
			bucket.setEndYear(year);
			bucket.setEndMonth(8);
			bucket.setText("JJA");
			break;
		case 7:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(7);
			bucket.setEndYear(year);
			bucket.setEndMonth(9);
			bucket.setText("JAS");
			break;
		case 8:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(8);
			bucket.setEndYear(year);
			bucket.setEndMonth(10);
			bucket.setText("ASO");
			break;
		case 9:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(9);
			bucket.setEndYear(year);
			bucket.setEndMonth(11);
			bucket.setText("SON");
			break;
		case 10:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(10);
			bucket.setEndYear(year);
			bucket.setEndMonth(12);
			bucket.setText("OND");
			break;
		case 11:
			bucket = new ENSOBucketDTO();
			bucket.setStartYear(year);
			bucket.setStartMonth(11);
			bucket.setEndYear(year + 1);
			bucket.setEndMonth(1);
			bucket.setText("NDJ");
			break;
		}
		return bucket;
	}
	
	public String getText() {
		return _text;
	}
	
	public Integer getStartYear() {
		return _startYear;
	}

	public Integer getStartMonth() {
		return _startMonth;
	}
	
	public Integer getEndYear() {
		return _endYear;
	}

	public Integer getEndMonth() {
		return _endMonth;
	}
	
	public Double getAnom() {
		return _anom;
	}
	
	public Boolean isElNino() {
		return _elNino;
	}
	
	public Boolean isLaNina() {
		return _laNina;
	}
	
	public void setText(String newVal) {
		_text = newVal;
	}
	
	public void setStartYear(Integer newVal) {
		_startYear = newVal;
	}
	
	public void setStartMonth(Integer newVal) {
		_startMonth = newVal;
	}
	
	public void setEndYear(Integer newVal) {
		_endYear = newVal;
	}
	
	public void setEndMonth(Integer newVal) {
		_endMonth = newVal;
	}
	
	public void setAnom(Double newVal) {
		_anom = newVal;
		if (_anom != null) {
			if (_anom.doubleValue() > +0.5) {
				_elNino = Boolean.TRUE;
				_laNina = Boolean.FALSE;
			} else if (_anom.doubleValue() < -0.5) {
				_elNino = Boolean.FALSE;
				_laNina = Boolean.TRUE;
			}
		} else {
			_elNino = Boolean.FALSE;
			_laNina = Boolean.FALSE;
		}
	}
	
	public void setElNino(Boolean newVal) {
		_elNino = newVal;
	}
	
	public void setLaNina(Boolean newVal) {
		_laNina = newVal;
	}
	
	@JsonIgnore
	public Calendar getStart() {
		if ((getStartYear() != null) && (getStartMonth() != null)) {
			Calendar start = Calendar.getInstance();
			start.set(Calendar.YEAR, getStartYear().intValue());
			start.set(Calendar.MONTH, getStartMonth().intValue() - 1);
			start.set(Calendar.DATE, 1);
			start.set(Calendar.HOUR_OF_DAY, 0);
			start.set(Calendar.MINUTE, 0);
			start.set(Calendar.SECOND, 0);
			start.set(Calendar.MILLISECOND, 0);
			return start;
		}
		return null;
	}
	
	@JsonIgnore
	public Calendar getEnd() {
		if ((getEndYear() != null) && (getEndMonth() != null)) {
			Calendar end = Calendar.getInstance();
			end.set(Calendar.YEAR, getEndYear().intValue());
			end.set(Calendar.MONTH, getEndMonth().intValue() - 1);
			end.set(Calendar.DATE, 1);
			end.set(Calendar.HOUR_OF_DAY, 0);
			end.set(Calendar.MINUTE, 0);
			end.set(Calendar.SECOND, 0);
			end.set(Calendar.MILLISECOND, 0);
			return end;
		}
		return null;
	}
}
