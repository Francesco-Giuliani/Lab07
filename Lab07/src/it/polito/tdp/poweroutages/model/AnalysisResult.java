package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class AnalysisResult {
	
	private long totAffectedCustomers;
	private long totOutageHours;
	private List<PowerOutage> powerOutagesList;
	
	public AnalysisResult(List<PowerOutage> powerOutagesCombination) {
		this.powerOutagesList = new ArrayList<>(powerOutagesCombination);
		
		int customerSum =0, hoursSum =0; 
		
		for(PowerOutage po : this.powerOutagesList) {
			customerSum += po.getAffectedCustomers();
			hoursSum += po.getHours();
		}
		
		this.totAffectedCustomers = customerSum;
		this.totOutageHours = hoursSum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tot people affected: "+this.totAffectedCustomers+"\n");
		builder.append("Tot hours of outage: "+this.totOutageHours);
		for(PowerOutage po: this.powerOutagesList)
			builder.append(po+"\n");
		
		return builder.toString();
	}
	 
	

}
