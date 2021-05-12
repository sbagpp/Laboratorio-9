package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private Map <Integer, Country> idMap;
	private BordersDAO dao;
	
	public Model() {
		this.dao = new BordersDAO();
		this.idMap = new HashMap<>();
	}
	
	public void creaGrafo(int anno) {
		this.dao.loadAllCountries(this.idMap, anno);
		for(Country c : this.idMap.values()) {
			System.out.println(c);
		}
		System.out.println(this.idMap.values().size());
	
	}

}
