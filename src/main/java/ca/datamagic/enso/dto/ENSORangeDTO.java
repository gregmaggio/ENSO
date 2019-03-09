package ca.datamagic.enso.dto;

public class ENSORangeDTO {
	private Integer startYear = null;
	private Integer startMonth = null;
	private Integer endYear = null;
	private Integer endMonth = null;
	private Boolean elNino = false;
	private Boolean laNina = false;
	
	public ENSORangeDTO() {
	}
	
	public ENSORangeDTO(ENSOBucketDTO bucket) {
		this.startYear = bucket.getStartYear();
		this.startMonth = bucket.getStartMonth();
		this.endYear = bucket.getEndYear();
		this.endMonth = bucket.getEndMonth();
		this.elNino = bucket.isElNino();
		this.laNina = bucket.isLaNina();
	}
	
	public Integer getStartYear() {
		return this.startYear;
	}

	public Integer getStartMonth() {
		return this.startMonth;
	}
	
	public Integer getEndYear() {
		return this.endYear;
	}

	public Integer getEndMonth() {
		return this.endMonth;
	}
	
	public Boolean isElNino() {
		return this.elNino;
	}
	
	public Boolean isLaNina() {
		return this.laNina;
	}
	
	public void setStartYear(Integer newVal) {
		this.startYear = newVal;
	}
	
	public void setStartMonth(Integer newVal) {
		this.startMonth = newVal;
	}
	
	public void setEndYear(Integer newVal) {
		this.endYear = newVal;
	}
	
	public void setEndMonth(Integer newVal) {
		this.endMonth = newVal;
	}
	
	public void setElNino(Boolean newVal) {
		this.elNino = newVal;
	}
	
	public void setLaNina(Boolean newVal) {
		this.laNina = newVal;
	}
}
