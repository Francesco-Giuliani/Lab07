package it.polito.tdp.poweroutages.model.bean;

public class Tag {
	
	private int id;
	private String value;

	public Tag(int id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public int getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return id + ", " + value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Tag other = (Tag) obj;
		if (id != other.id)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
