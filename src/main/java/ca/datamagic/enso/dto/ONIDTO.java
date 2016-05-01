/**
 * 
 */
package ca.datamagic.enso.dto;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Greg
 *
 */
public class ONIDTO {
	private Integer _year = null;
	private Integer _month = null;
	private Double _anom = null;
	
	public ONIDTO() {
		
	}
	
	public ONIDTO(Integer year, Integer month, Double anom) {
		_year = year;
		_month = month;
		_anom = anom;
	}

	public Integer getYear() {
		return _year;
	}

	public Integer getMonth() {
		return _month;
	}
	
	public Double getAnom() {
		return _anom;
	}
	
	@JsonIgnore
	public Calendar getCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getYear().intValue());
		calendar.set(Calendar.MONTH, getMonth().intValue() - 1);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
	
	public void setYear(Integer newVal) {
		_year = newVal;
	}
	
	public void setMonth(Integer newVal) {
		_month = newVal;
	}
	
	public void setAnom(Double newVal) {
		_anom = newVal;
	}
}
