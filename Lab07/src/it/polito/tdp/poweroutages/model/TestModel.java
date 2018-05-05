package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.bean.Nerc;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		PowerOutageDAO pod = new PowerOutageDAO();
		
		
		//test PowerOutageDAO.getAllPowerOutages()
		/*for(PowerOutage po: pod.getAllPowerOutages())
			System.out.println(po.getId()+ " "+ po.toString());*/
		
		//test PowerOutageDAO.getPowerOutagesByNerc(Nerc nerc)
		for(PowerOutage p : pod.getPowerOutagesByNerc(new Nerc(6, "SSP")))
			System.out.println(p.getId()+ " "+ p.toString());
	}
}
