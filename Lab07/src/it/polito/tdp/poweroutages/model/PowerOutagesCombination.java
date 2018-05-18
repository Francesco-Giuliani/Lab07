package it.polito.tdp.poweroutages.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.model.javabean.PowerOutage;

public class PowerOutagesCombination {
	
	private List<PowerOutage> combination;
	private PowerOutage first, last;
	private int maxYearSpan;
	
	public PowerOutagesCombination(int maxYearSpan) {
		
		this.combination = new ArrayList<>();
		this.maxYearSpan = maxYearSpan;
	}
	
	public PowerOutagesCombination(PowerOutagesCombination altra) {
		this.combination = new ArrayList<PowerOutage>(altra.combination);
		this.first = altra.first;
		this.last = altra.last;
		this.maxYearSpan = altra.maxYearSpan;
	}

	private int calculateYearSpan() {
		int size = this.combination.size();
		if(size>1) {
			this.last = Collections.max(this.combination);
			this.first = Collections.min(this.combination);
			return last.getDateEventBegan().getYear() - first.getDateEventBegan().getYear();
		} else if(size == 1) {
			this.first = this.combination.get(0);
			this.last = null;
			return 0;
		}
		else {
			this.last = null;
			this.first = null;
			return 0;
		}
	}

	private long sumHours() {
		long sum =0;
		for(PowerOutage po : this.combination) {
			sum += po.getHoursDuration();
		}
		return sum;
	}

	private long sumCustomers() {
		long sum =0;
		for(PowerOutage po : this.combination) {
			sum += po.getCustomersAffected();
		}
		return sum;
	}
	
	
	/**
	 * Aggiunge un elemento PowerOutage a this.combination (lista di oggetti PowerOutage).
	 * Somma le ore e i customers a quelli della combinazione
	 * @param toAdd
	 * @return true if the element is added to the combination (the event happened within the yearSpan)
	 * @return false if the element is not added (not in the max yearSpan)
	 */
	public boolean add(PowerOutage toAdd) {
		
		if(this.combination.contains(toAdd))
			return false;
		
		int size =this.combination.size();
		
		if(size==0){
			this.combination.add(toAdd);
			this.first = toAdd;
			this.last=null;
		}
		else if(size == 1) {
			int span = Math.abs(this.first.getDateEventBegan().getYear() -
					toAdd.getDateEventBegan().getYear()); 
			if(span <= this.maxYearSpan) {
				this.combination.add(toAdd);
				if(this.first.getDateEventBegan().isBefore(toAdd.getDateEventBegan()))
					this.last = toAdd;
				else {
					this.last = this.first;
					this.first = toAdd;
				}
			}else 
				return false;
		}else {
			if( ! this.isWithinYearSpan(toAdd))
				return false;		
			this.combination.add(toAdd);
			this.calculateYearSpan();
		}
		return true;
	}

	private boolean isWithinYearSpan(PowerOutage toAdd) {
		
		LocalDateTime toAddDate = toAdd.getDateEventBegan(), 
				firstDate= this.first.getDateEventBegan(),
				lastDate= this.last.getDateEventBegan();

		if(this.calculateYearSpan() == this.maxYearSpan) {
			if(toAddDate.isAfter(firstDate) &&
					toAddDate.isBefore(lastDate))
				return true;
			else 
				return false;
		
		}else {
			if(toAddDate.isAfter(lastDate))
				if(toAddDate.getYear()-firstDate.getYear()>this.maxYearSpan)
					return false;
				else 
					return true;
			if(toAddDate.isBefore(firstDate))
				if(lastDate.getYear()-toAddDate.getYear()>this.maxYearSpan)
					return false;
				else
					return true;
		}
		return true;
	}
	
	public void remove(PowerOutage toRemove) {
		if(this.combination.remove(toRemove)) {
			this.calculateYearSpan();
		}
	}

	@Override
	public String toString() {
		return "Tot people affected: " + this.getTotCustomers() + "\nTot hours of outage: " + this.getTotHours()+ "\n"
				+ this.stampaCombinazione();
	}

	private String stampaCombinazione() {

		StringBuilder sb = new StringBuilder();
		for(PowerOutage po: this.combination) {
			sb.append(po.getId()+ " "+po.getDateEventBegan().getYear()+ " "+po.getDateEventBegan()+ " "+
					po.getDateEventFinished()+" "+po.getHoursDuration()+" "+po.getCustomersAffected()+"\n");
		}
		return sb.toString();
	}

	public long getTotHours() {
		return this.sumHours();
	}

	public long getTotCustomers() {
		return this.sumCustomers();
	}


	public int getYearSpan() {
		return this.calculateYearSpan();
	}
	public boolean contains(PowerOutage po) {
		return this.combination.contains(po);
	}
	
}
