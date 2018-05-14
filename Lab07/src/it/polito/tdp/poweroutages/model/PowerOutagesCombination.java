package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
		this.powOutCombination = new LinkedList<>();
		this.maxYearSpan = maxYearSpan;
		this.maxHours = maxHours;
		this.customersAffected =0;
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
		this.customersAffected =0;
		for(PowerOutage po: this.powOutCombination)
			this.customersAffected += po.getAffectedCustomers();
		
		return customersAffected;
	}



	public long sumHours() {
		long sum =0;
		for(PowerOutage po:  this.powOutCombination)
			sum += po.getHours();
		return sum;
	}



	public boolean canInsertYears(PowerOutage po) {
		if(this.powOutCombination.size()==0)
			return true;
		if(this.powOutCombination.size()==1) {
			if(Math.abs(this.powOutCombination.get(0).getDateEventBegan().getYear() - po.getDateEventBegan().getYear())<=this.maxYearSpan) {
					return true;
				}else {
					return false;
				}
		}else {
		
			Collections.sort(this.powOutCombination, new DateComparator());
			LocalDateTime first = this.powOutCombination.get(0).getDateEventBegan();
			LocalDateTime last = this.powOutCombination.get(this.powOutCombination.size()-1).getDateEventBegan();
			LocalDateTime current = po.getDateEventBegan();
			
			if(current.isAfter(first) && current.isBefore(last))
				return true;
			if(current.isAfter(first) && (current.getYear()-first.getYear())<=this.maxYearSpan )
				return true;
			if(current.isBefore(last) && (last.getYear()-current.getYear())<=this.maxYearSpan)
				return true;
			}
		
		return false;
	}



	public void add(PowerOutage po) {
		this.powOutCombination.add(po);
	}



	public void remove(PowerOutage po) {
		this.powOutCombination.remove(po);
		
	}



	@Override
	public String toString() {
		return powOutCombination.toString() +"\n AFFECTED: " + this.getCustomersAffected()+"\n";
	}
	
	
	
	
	
	
}
