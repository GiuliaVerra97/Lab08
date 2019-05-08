package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;


import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {

	private Graph<String, DefaultEdge> grafo;
	private List<String> listaParole;	
	
	public void createGraph(int numeroLettere) {
		
		grafo=new SimpleGraph<>(DefaultEdge.class);
		WordDAO dao=new WordDAO();
		this.listaParole=dao.getAllWordsFixedLength(numeroLettere);
		
		Graphs.addAllVertices(this.grafo, this.listaParole);
		
		int conta;
		
		for(String partenza:this.grafo.vertexSet()) {
			
			for(String arrivo: this.grafo.vertexSet()) {
				conta=0;
				
				for(int i=0; i<partenza.length();i++) {
					if(partenza.charAt(i)!=arrivo.charAt(i))
						conta++;
				}
				
				if(conta==1)
					this.grafo.addEdge(partenza, arrivo);
					
			}
			
		}

	}

	
	
	
	public List<String> displayNeighbours(String parolaInserita) {
		
		List<String> result=new ArrayList<String>();
		WordDAO dao=new WordDAO();
		this.listaParole=dao.getAllWordsFixedLength(parolaInserita.length());
		
		for(String arrivo: listaParole) {
			int conta=0;
			for(int i=0; i<parolaInserita.length();i++) {
				if(parolaInserita.charAt(i)!=arrivo.charAt(i))
					conta++;
			}
			if(conta==1)
				result.add(arrivo);
		}
		
		return result;
		
	}

	
	
	
	
	public int findMaxDegree() {
		
		int  max=0;
		
		for(String parola: this.grafo.vertexSet()) {
			int grado=grafo.degreeOf(parola);			//calcola i gradi di una parola, ovvero il numero di vertici a cui è connesso direttamente 
			
			if(grado>max) {
				max=grado;
			}
			
		}
		
		return max;
	}

	
	
	
	
	public Graph<String, DefaultEdge> getGrafo() {
		return grafo;
	}

	public void setGrafo(Graph<String, DefaultEdge> grafo) {
		this.grafo = grafo;
	}

	public List<String> getListaParole() {
		return listaParole;
	}

	public void setListaParole(List<String> listaParole) {
		this.listaParole = listaParole;
	}


	
	
	
	
	
	
	
	
}
