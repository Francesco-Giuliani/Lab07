package it.polito.tdp.poweoutages.comparators;

import java.util.Comparator;

import it.polito.tdp.poweroutages.model.PowerOutage;

public class DateComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		PowerOutage po1 = (PowerOutage)o1;
		PowerOutage po2 = (PowerOutage)o2;
		
		return po1.getDateEventBegan().compareTo(po2.getDateEventBegan());
	}

	
	
}
