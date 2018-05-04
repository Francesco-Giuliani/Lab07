package it.polito.tdp.poweroutages;

import it.polito.tdp.poweroutages.exceptions.EmptyFieldException;
import it.polito.tdp.poweroutages.model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<?> chcBxNERC;

    @FXML
    private TextField txtMaxYears;

    @FXML
    private Label txtHours;

    @FXML
    private Button btnWorstCaseAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void HandleWorstcaseAnalysis(ActionEvent event) {
    	
    	this.txtResult.clear();
    	
    	String yearSpanStr = this.txtMaxYears.getText() , maxHourDurationStr = this.txtHours.getText();
    	int yearSpan, maxHourDuration;
    	
    	try {
    		if(yearSpanStr == null || maxHourDurationStr == null)
    			throw new EmptyFieldException();
    		
    		yearSpan = Integer.parseInt(yearSpanStr);
    		maxHourDuration = Integer.parseInt(maxHourDurationStr);
    		
    	
    		
    	}catch(EmptyFieldException efe) {
    		efe.printStackTrace();
    		this.txtResult.appendText("Ci sono dei campi vuoti. Compilare correttamente tutti i campi richiesti. \n");
    		return;
    	}catch(NumberFormatException nfe) {
    		nfe.printStackTrace();
    		this.txtResult.appendText("Valori inseriti errati. Inserire interi nei campi ore e anni. \n");
    		return;
    	}
    		
    	
    }

    @FXML
    void initialize() {
        assert chcBxNERC != null : "fx:id=\"chcBxNERC\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtMaxYears != null : "fx:id=\"txtMaxYears\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtHours != null : "fx:id=\"txtHours\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnWorstCaseAnalysis != null : "fx:id=\"btnWorstCaseAnalysis\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
