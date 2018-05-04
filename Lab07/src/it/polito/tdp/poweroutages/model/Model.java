package it.polito.tdp.poweroutages.model;

import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.bean.Nerc;

public class Model {

	private PowerOutageDAO podao;
	
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

}
