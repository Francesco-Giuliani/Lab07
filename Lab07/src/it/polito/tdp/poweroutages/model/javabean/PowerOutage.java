package it.polito.tdp.poweroutages.model.javabean;

import java.time.*;

public class PowerOutage implements Comparable<PowerOutage>{

	private int id;
	private EventType eventType;
	private Tag tag;
	private Area area;
	private Nerc nerc;
	private Responsible responsible;
	private int customersAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventFinished;
	private int demandLoss;
	private long hoursDuration;
	
	public PowerOutage(int id, EventType eventType, Tag tag, Area area, Nerc nerc, Responsible responsible,
			int customersAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventFinished, int demandLoss) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.tag = tag;
		this.area = area;
		this.nerc = nerc;
		this.responsible = responsible;
		this.customersAffected = customersAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventFinished = dateEventFinished;
		this.demandLoss = demandLoss;
		this.hoursDuration = Duration.between(this.dateEventBegan, this.dateEventFinished).toHours();
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		if (id != other.id)
			return false;
		return true;
	}
	
	
	//GETTER E  SETTERS
	
	

	public int getId() {
		return id;
	}

	public long getHoursDuration() {
		return hoursDuration;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}

	public Responsible getResponsible() {
		return responsible;
	}

	public void setResponsible(Responsible responsible) {
		this.responsible = responsible;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	public void setCustomersAffected(int customersAffected) {
		this.customersAffected = customersAffected;
	}

	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}

	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}

	public LocalDateTime getDateEventFinished() {
		return dateEventFinished;
	}

	public void setDateEventFinished(LocalDateTime dateEventFinished) {
		this.dateEventFinished = dateEventFinished;
	}

	public int getDemandLoss() {
		return demandLoss;
	}

	public void setDemandLoss(int demandLoss) {
		this.demandLoss = demandLoss;
	}

	@Override
	public int compareTo(PowerOutage other) {
		return this.dateEventBegan.compareTo(other.getDateEventBegan());
	}



	@Override
	public String toString() {
		return id + ", " + nerc + ", " + customersAffected + ", " + dateEventBegan + ", " + dateEventFinished + ", HOURS DURATION: "
				+ hoursDuration +"\n";
	}
	
	
	
	
}