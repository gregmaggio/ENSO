package ca.datamagic.enso.dto;

public class ENSORangeDTO {
	private Integer _startYear = null;
	private Integer _startMonth = null;
	private Integer _endYear = null;
	private Integer _endMonth = null;
	private Boolean _elNino = false;
	private Boolean _laNina = false;
	
	public ENSORangeDTO() {
	}
	
	public ENSORangeDTO(ENSOBucketDTO bucket) {
		_startYear = bucket.getStartYear();
		_startMonth = bucket.getStartMonth();
		_endYear = bucket.getEndYear();
		_endMonth = bucket.getEndMonth();
		_elNino = bucket.isElNino();
		_laNina = bucket.isLaNina();
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
	
	public Boolean isElNino() {
		return _elNino;
	}
	
	public Boolean isLaNina() {
		return _laNina;
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
	
	public void setElNino(Boolean newVal) {
		_elNino = newVal;
	}
	
	public void setLaNina(Boolean newVal) {
		_laNina = newVal;
	}
}
