package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.bean.Nerc;

public class Model {

	private PowerOutageDAO powOutDAO;
	private List<PowerOutage> worstCaseResult;
	private List<PowerOutagesCombination> powOutCombinationList;
	private PowerOutagesCombination worst;
	
	public Model() {
		powOutDAO = new PowerOutageDAO();
		this.worstCaseResult = new ArrayList<>();
	}
	
	public List<Nerc> getNercList() {
		return powOutDAO.getNercList();
	}
	
	public AnalysisResult worstCaseAnalysis(Nerc nerc, int maxYears, int maxHours) {
		
		List<PowerOutage> available = new ArrayList<>(this.powOutDAO.getPowerOutagesByNerc(nerc));
		PowerOutagesCombination partial = new PowerOutagesCombination(maxYears, maxHours);
		int level = 0;
		int maxLevel = available.size();
		
		this.recursive(maxYears, maxHours, maxLevel, level, partial, available);
		
		
		AnalysisResult analysisResult = new AnalysisResult(this.worstCaseResult);
		return analysisResult;
	}

	private void recursive(int maxYears, int maxHours, int maxLevel, int level, PowerOutagesCombination partial,
			List<PowerOutage> available) {
		
		if(level >= maxLevel || partial.getTotHours()) //condizione diterminazione
		
	}

}
