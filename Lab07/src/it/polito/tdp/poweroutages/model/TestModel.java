package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO pod = new PowerOutageDAO();
		
		//test PowerOutageDAO.getAllPowerOutages()
		for(PowerOutage po: pod.getAllPowerOutages())
			System.out.println(po.getId()+ " "+ po.toString());
	}
}
