package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;
import java.util.*;

public class Model {
	
	
	RiversDAO dao;
	List<River> rivers;
	
	public Model() {
		dao = new RiversDAO();
		rivers = dao.getAllRivers();
		for(River r : rivers) {
			dao.getFlowsForRIver(r);
		}
	}

	
	
	
}
