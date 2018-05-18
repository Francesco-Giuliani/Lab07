package it.polito.tdp.poweroutages;

import it.polito.tdp.poweroutages.exceptions.EmptyFieldsException;
import it.polito.tdp.poweroutages.model.*;
import it.polito.tdp.poweroutages.model.javabean.Nerc;

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
    private ChoiceBox<Nerc> chcBxNERC;

    @FXML
    private TextField txtMaxYears;

    @FXML
    private TextField txtHours;

    @FXML
    private Button btnWorstCaseAnalysis;

    @FXML
    private TextArea txtResult;

    @FXML
    void HandleWorstcaseAnalysis(ActionEvent event) {
    	
    	Nerc nerc = this.chcBxNERC.getValue();
    	String years = this.txtMaxYears.getText();
    	String hours = this.txtHours.getText();
    	
    	try {
			this.inputIsValid(nerc, years, hours);
				
			
			int maxYearSpan = Integer.parseInt(years);
			int maxHours = Integer.parseInt(hours);
			
			PowerOutagesCombination worst = this.model.findWorstCase(nerc, maxYearSpan, maxHours);
			this.txtResult.appendText(worst+"\n");
			
		} catch (EmptyFieldsException e) {
			this.txtResult.appendText("Inserire nuovamente i valori richiesti.");
			e.printStackTrace();
			return;
		} catch (NumberFormatException nfe) {
			this.txtResult.appendText("Inserire degli interi in yeas e hours");
			nfe.printStackTrace();
			return;
		}

    }

    private boolean inputIsValid(Nerc nerc, String years, String hours) throws EmptyFieldsException {
		if(nerc == null || years ==null || years.isEmpty() || hours==null || hours.isEmpty()) {
			throw new EmptyFieldsException();
		}
    	return true;
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
    	this.chcBxNERC.getItems().setAll(this.model.getNercList());
    }
}
