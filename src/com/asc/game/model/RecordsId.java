package com.asc.game.model;

// Generated Feb 22, 2013 8:49:54 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RecordsId generated by hbm2java
 */
@SuppressWarnings("serial")
@Embeddable
public class RecordsId implements java.io.Serializable {

	private String name;
	private Integer wintime;

	public RecordsId() {
	}

	public RecordsId(String name, Integer wintime) {
		this.name = name;
		this.wintime = wintime;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "WINTIME")
	public Integer getWintime() {
		return this.wintime;
	}

	public void setWintime(Integer wintime) {
		this.wintime = wintime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RecordsId))
			return false;
		RecordsId castOther = (RecordsId) other;

		return ((this.getName() == castOther.getName()) || (this.getName() != null
				&& castOther.getName() != null && this.getName().equals(
				castOther.getName())))
				&& ((this.getWintime() == castOther.getWintime()) || (this
						.getWintime() != null && castOther.getWintime() != null && this
						.getWintime().equals(castOther.getWintime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getName() == null ? 0 : this.getName().hashCode());
		result = 37 * result
				+ (getWintime() == null ? 0 : this.getWintime().hashCode());
		return result;
	}

}
