package it.polito.tdp.poweoutages.comparators;

import java.util.Comparator;

import it.polito.tdp.poweroutages.model.PowerOutage;

public class DateComparator implements Comparator<PowerOutage>{

	@Override
	public int compare(PowerOutage po1, PowerOutage po2) {
		return po1.getDateEventBegan().compareTo(po2.getDateEventBegan());
	}

	
	
}
