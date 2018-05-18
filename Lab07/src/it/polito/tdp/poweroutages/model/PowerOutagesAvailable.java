package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.javabean.PowerOutage;

public class PowerOutagesAvailable {
	
	private List<PowerOutage> available;
	private long maxHours;

	public PowerOutagesAvailable(List<PowerOutage> available, long maxHours) {
		this.available = available;
		this.maxHours = maxHours;
	}
	public PowerOutagesAvailable(PowerOutagesAvailable altro) {
		this.available = new ArrayList<>(altro.available);
		this.maxHours = altro.maxHours;
	}
	
	public PowerOutagesAvailable(long maxHours) {
		this.available = new ArrayList<>();
		this.maxHours = maxHours;
	}
	
	public List<PowerOutage> getPowerOutagesAddableToCombination(PowerOutagesCombination combination){
		List<PowerOutage> addables = new ArrayList<>();
		for(PowerOutage po: this.available)
			if(combination.getTotHours()+po.getHoursDuration()<=this.maxHours && !combination.contains(po))
				addables.add(po);
		return addables;
	}
	
	public boolean add(PowerOutage p) {
		return this.available.add(p);
	}
	public boolean remove(PowerOutage p) {
		return this.available.remove(p);
	}
	@Override
	public String toString() {
		return available + ", " + maxHours + "]";
	}
	
	
}
