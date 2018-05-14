package it.polito.tdp.poweoutages.comparators;

import java.util.Comparator;

import it.polito.tdp.poweroutages.model.PowerOutagesCombination;

public class CombByAffectedCustomersComparator implements Comparator<PowerOutagesCombination>{

	@Override
	public int compare(PowerOutagesCombination o1, PowerOutagesCombination o2) {
		int diff = o1.getCustomersAffected()-o2.getCustomersAffected();
		if(diff<0)
			return -1;
		else if(diff ==0)
			return 0;
		else
			return 1;
	}

	
	
}
