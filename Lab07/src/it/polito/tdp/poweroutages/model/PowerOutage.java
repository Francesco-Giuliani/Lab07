package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import it.polito.tdp.poweroutages.model.bean.*;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class PowerOutage {
	
	private int id;
	private EventType eventType;
	private Tag tag;
	private Area area;
	private Nerc nerc;
	private Responsible responsible;
	private int costumersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventEnded;
	private int demandLoss;
	
	private int year;
	private long hours;
	private PowerOutageDAO powerOutageDAO = new PowerOutageDAO();	
	
	public PowerOutage(int id, EventType eventType, Tag tag, Area area, Nerc nerc, Responsible responsible,
			int costumersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventEnded, int demandLoss) {
		this.setId(id);
		this.eventType = eventType;
		this.tag = tag;
		this.area = area;
		this.nerc = nerc;
		this.responsible = responsible;
		this.costumersAffected = costumersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventEnded = dateEventEnded;
		this.demandLoss = demandLoss;
		
		Duration d= Duration.between(this.dateEventBegan, this.dateEventEnded) ;
		this.hours = d.toHours();
		this.year = this.dateEventBegan.getYear();
	}

	/*public PowerOutage(LocalDateTime powerOutStart, LocalDateTime powerOutEnd, int affectedPeople) {
		this.dateEventBegan = powerOutStart;
		this.dateEventEnded = powerOutEnd;
		this.affectedPeople = affectedPeople;
		this.year = this.dateEventBegan.getYear();
		Duration d= Duration.between(this.dateEventBegan, this.dateEventEnded) ;
		this.hours = d.toHours();
	}*/
	
	

	
	@Override
	public String toString() {
		return year+", " +dateEventBegan+", "+dateEventEnded+", "+ hours+ ", " +this.costumersAffected+"\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + getId();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAffectedCustomers() {
		return this.costumersAffected;
	}

	public long getHours() {
		return this.hours;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Tag getTag() {
		return tag;
	}

	public Area getArea() {
		return area;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public int getCostumersAffected() {
		return costumersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public LocalDateTime getDateEventEnded() {
		return dateEventEnded;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public int getYear() {
		return year;
	}

	public PowerOutageDAO getPowerOutageDAO() {
		return powerOutageDAO;
	}
	
	
	
	
	
}
