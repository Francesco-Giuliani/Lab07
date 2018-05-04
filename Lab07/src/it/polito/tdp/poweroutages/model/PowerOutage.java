package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalAmount;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class PowerOutage {
	
	private LocalDateTime powerOutStart;
	private LocalDateTime powerOutEnd;
	private int affectedPeople;
	private int year;
	private long hours;
	private PowerOutageDAO powerOutageDAO = new PowerOutageDAO();
	
	public PowerOutage(LocalDateTime powerOutStart, LocalDateTime powerOutEnd, int affectedPeople) {
		this.powerOutStart = powerOutStart;
		this.powerOutEnd = powerOutEnd;
		this.affectedPeople = affectedPeople;
		this.year = this.powerOutStart.getYear();
		Duration d= Duration.between(this.powerOutStart, this.powerOutEnd) ;
		this.hours = d.toHours();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + affectedPeople;
		result = prime * result + ((powerOutEnd == null) ? 0 : powerOutEnd.hashCode());
		result = prime * result + ((powerOutStart == null) ? 0 : powerOutStart.hashCode());
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
		if (affectedPeople != other.affectedPeople)
			return false;
		if (powerOutEnd == null) {
			if (other.powerOutEnd != null)
				return false;
		} else if (!powerOutEnd.equals(other.powerOutEnd))
			return false;
		if (powerOutStart == null) {
			if (other.powerOutStart != null)
				return false;
		} else if (!powerOutStart.equals(other.powerOutStart))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return year+", " +powerOutStart+", "+powerOutEnd+", "+ hours+ ", " +affectedPeople;
	}
	
	
	
	
	
}
