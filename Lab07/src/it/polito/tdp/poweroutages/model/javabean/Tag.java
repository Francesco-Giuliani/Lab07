package it.polito.tdp.poweroutages.model.javabean;

import java.util.ArrayList;
import java.util.List;

public class Tag {

	private int id;
	private String value;
	private List<PowerOutage> powerOutages;
	private boolean lazy;
	
	public Tag(int id, String value) {
		super();
		this.id = id;
		this.lazy = false;
		this.value = value;
		this.powerOutages = new ArrayList<>();
	}
	public Tag(int int1, boolean b) {
		this.id = int1;
		this.lazy = b;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<PowerOutage> getPowerOutages() {
		return powerOutages;
	}
	public void setPowerOutages(List<PowerOutage> powerOutages) {
		this.powerOutages = powerOutages;
	}
	
}
