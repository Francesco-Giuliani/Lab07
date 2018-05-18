package it.polito.tdp.poweroutages.model.javabean;

import java.util.ArrayList;
import java.util.List;

public class EventType {

	private int id;
	private String value;
	private List<PowerOutage> powerOutages;
	private boolean lazy;
	
	public EventType(int id, String value) {
		super();
		this.id = id;
		this.value = value;
		this.lazy = false;
		this.powerOutages = new ArrayList<>();
	}
	public EventType(int int1, boolean b) {
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
		EventType other = (EventType) obj;
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
	
	public boolean isLazy() {
		return this.lazy;
	}
	
}
