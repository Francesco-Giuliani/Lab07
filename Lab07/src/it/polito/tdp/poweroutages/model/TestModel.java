package it.polito.tdp.poweroutages.model;

import it.polito.tdp.poweroutages.model.javabean.Nerc;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		
		Nerc nerc = new Nerc(13, "MAIN");
		
		PowerOutagesCombination worst = model.findWorstCase(nerc, 4, 200);
		
		System.out.println(worst.toString());
	}
}
