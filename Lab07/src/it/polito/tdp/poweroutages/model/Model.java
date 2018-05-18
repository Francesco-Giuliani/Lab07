package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import it.polito.tdp.poweroutages.model.javabean.*;
import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.javabean.Nerc;

public class Model {

	private PowerOutageDAO podao;
	private Map<Integer, PowerOutage> idMapPowerOutages;
	private Map<Integer, Area> idMapAreas;
	private Map<Integer, EventType> idMapEventTypes;
	private Map<Integer, Nerc> idMapNercs;
	private Map<Integer, Responsible> idMapResponsibles;
	private Map<Integer, Tag> idMapTags;
	
	//PER RECURSIVE
	private PowerOutagesCombination worst;
	
	public Model() {
		
		this.idMapPowerOutages = new HashMap<>();
		this.idMapAreas = new HashMap<>();
		this.idMapEventTypes = new HashMap<>();
		this.idMapNercs = new HashMap<>();
		this.idMapResponsibles = new HashMap<>();
		this.idMapTags = new HashMap<>();
		podao = new PowerOutageDAO(this.idMapPowerOutages, this.idMapAreas, this.idMapEventTypes,
				this.idMapNercs, this.idMapResponsibles, this.idMapTags);
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public PowerOutagesCombination findWorstCase(Nerc nerc, int maxYearSpan, int totHours) {
		
		PowerOutagesCombination parziale = new PowerOutagesCombination(maxYearSpan);
		
		this.worst = new PowerOutagesCombination(parziale);
		
		PowerOutagesAvailable disponibili = new PowerOutagesAvailable(this.podao.getPowerOutagesByNerc(nerc), totHours);
		
		this.recursive(totHours, parziale, disponibili);	
		
		return this.worst;
	}
	
	public void recursive(int totHours, PowerOutagesCombination parziale, PowerOutagesAvailable disponibili) {
		
		List<PowerOutage> addable = new ArrayList<>( disponibili.getPowerOutagesAddableToCombination(parziale));
		
		//CASO TERMINALE
		if(addable.isEmpty()) {
			if(worst.getTotCustomers()<parziale.getTotCustomers() && parziale.getTotHours()<=totHours)
				worst = new PowerOutagesCombination(parziale);
			return;
		}
			
		for(PowerOutage po:addable) {
				parziale.add(po);
				disponibili.remove(po);
				this.recursive(totHours, parziale, disponibili);
				parziale.remove(po);
				disponibili.add(po);
		}
	}

	public PowerOutagesCombination getWorst() {
		return worst;
	}

}
