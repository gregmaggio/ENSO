/**
 * 
 */
package ca.datamagic.enso.importer;

import java.text.MessageFormat;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author Greg
 *
 */
public class ONI {
	private static Logger _logger = LogManager.getLogger(ONI.class);
	private Integer _year = null;
	private Integer _month = null;
	private Double _total = null;
	private Double _cimateAdjust = null;
	private Double _anom = null;
	
	public static ONI getONI(String text) {
		try {
			ONI oni = new ONI();
			oni._year = new Integer(text.substring(0, 4).trim());
			oni._month = new Integer(text.substring(4, 11).trim());
			oni._total = new Double(text.substring(11, 16).trim());
			oni._cimateAdjust = new Double(text.substring(19, 24).trim());
			oni._anom = new Double(text.substring(27).trim());
			return oni;
		} catch (Throwable t) {
			_logger.warn("Exception", t);
		}
		return null;
	}
	
	public Integer getYear() {
		return _year;
	}
	
	public Integer getMonth() {
		return _month;
	}
	
	public Double getTotal() {
		return _total;
	}
	
	public Double getCimateAdjust() {
		return _cimateAdjust;
	}
	
	public Double getAnom() {
		return _anom;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("{0},{1},{2},{3},{4}", new Object[] {
				Integer.toString(_year.intValue()),
				Integer.toString(_month.intValue()),
				Double.toString(_total.intValue()),
				Double.toString(_cimateAdjust.intValue()),
				Double.toString(_anom.intValue())
			});
	}
}
