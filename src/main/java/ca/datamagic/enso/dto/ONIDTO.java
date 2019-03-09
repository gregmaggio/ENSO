/**
 * 
 */
package ca.datamagic.enso.dto;

import java.util.Calendar;

/**
 * @author Greg
 *
 */
public class ONIDTO {
	private Integer year = null;
	private Integer month = null;
	private Double anom = null;
	
	public ONIDTO() {
		
	}
	
	public ONIDTO(Integer year, Integer month, Double anom) {
		this.year = year;
		this.month = month;
		this.anom = anom;
	}

	public Integer getYear() {
		return this.year;
	}

	public Integer getMonth() {
		return this.month;
	}
	
	public Double getAnom() {
		return this.anom;
	}
	
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
		this.year = newVal;
	}
	
	public void setMonth(Integer newVal) {
		this.month = newVal;
	}
	
	public void setAnom(Double newVal) {
		this.anom = newVal;
	}
}
