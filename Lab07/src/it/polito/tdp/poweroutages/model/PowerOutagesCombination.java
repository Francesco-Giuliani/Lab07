package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweoutages.comparators.DateComparator;

public class PowerOutagesCombination {

	private List<PowerOutage> powOutCombination;
	private long maxYearSpan;
	private long maxHours;
	private int customersAffected; 
	
	public PowerOutagesCombination(long maxYearSpan, long maxHours) {
		super();
		this.powOutCombination = new LinkedList<>();
		this.maxYearSpan = maxYearSpan;
		this.maxHours = maxHours;
	}

	
	
	public PowerOutagesCombination(PowerOutagesCombination partial) {
		this.powOutCombination = new LinkedList<>(partial.getPowOutCombination());
		this.maxHours = partial.getMaxHours();
		this.maxYearSpan = partial.getMaxYearSpan();
		this.customersAffected = partial.getCustomersAffected();
	}



	public List<PowerOutage> getPowOutCombination() {
		return powOutCombination;
	}

	public long getMaxYearSpan() {
		return maxYearSpan;
	}

	public long getMaxHours() {
		return maxHours;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}



	public long sumHours() {
		long sum =0;
		for(PowerOutage po:  this.powOutCombination)
			sum += po.getHours();
		return sum;
	}



	public boolean canInsertYears(PowerOutage po) {
		Collections.sort(this.powOutCombination, new DateComparator());
		PowerOutage first = this.powOutCombination.get(0);
		PowerOutage last = this.powOutCombination.get(this.powOutCombination.size()-1);
		
		if(po.getDateEventBegan().compareTo())
		
		return false;
	}
	

	
	
	
	
}
