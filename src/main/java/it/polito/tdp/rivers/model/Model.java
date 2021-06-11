package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;
import java.util.*;

public class Model {
	
	
	RiversDAO dao;
	
	public Model() {
		dao = new RiversDAO();
	}

	public List<River> getAllRivers(){
		return dao.getAllRivers();
	}
	
	public 
}
