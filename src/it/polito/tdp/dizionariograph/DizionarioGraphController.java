package it.polito.tdp.dizionariograph;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtNumLettere;

    @FXML
    private TextField txtParola;

    @FXML
    private TextArea txtArea;
    
    @FXML
    private Button btnGrado; 

	private Model model;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	
    	txtArea.clear();
    	btnGrado.setDisable(false);
    	
       	int num=0; 	
    	
    	try {
    		num = Integer.parseInt(txtNumLettere.getText().trim());
    	} catch(NumberFormatException e) {
    		txtArea.setText("Devi inserire un numero intero!\n");
    		return ;
    	}

    	model.createGraph(num);
    	
    	txtArea.appendText("Grafo di "+model.getGrafo().vertexSet().size()+"vertici e "+model.getGrafo().edgeSet().size()+" archi: \n"+ model.getGrafo());
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	txtArea.clear();
    	txtNumLettere.clear();
    	txtParola.clear();
    	btnGrado.setDisable(true);

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	
    	txtArea.clear();
    	int grado=model.findMaxDegree();
    	txtArea.appendText("Il grado maggiore che si può trovare nel grafo è: "+Integer.toString(grado));
    	
    }
    	
    	
 

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	
    	txtArea.clear();
    	
    	if(txtParola.getText().equals("")) {
    		txtArea.setText("Non è stata inserita la parola");
    	}else {
    		List<String> lista=model.displayNeighbours(txtParola.getText());
    		txtArea.appendText("Le parole che differiscono di una sola lettera dalla parola "+txtParola.getText()+" sono \n");
    		for(String s: lista) {
    			txtArea.appendText(s+"\n");
    		}
    	}
    	

    }

    @FXML
    void initialize() {
        assert txtNumLettere != null : "fx:id=\"txtNumLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
    }
    
    
    public void setModel(Model model) {
    	this.model=model;
    }
    
    
}

