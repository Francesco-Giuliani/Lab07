package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class PowerOutagesCombination {

	private List<PowerOutage> powOutCombination;
	private long maxYearSpan;
	private long maxHours;
	private int customersAffected; 
	
	public PowerOutagesCombination(long maxYearSpan, long maxHours) {
		super();
		this.powOutCombination = new ArrayList<>();
		this.maxYearSpan = maxYearSpan;
		this.maxHours = maxHours;
	}
	
	
	
	
	
	
}
