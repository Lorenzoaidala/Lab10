package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	
	public List<River> getRivers(){
		return rivers;
	}
	
	public double getAvgFlows(River r) {
		double avg = 0;
		for(Flow f: r.getFlows()) {
			avg += f.getFlow();
		}
		avg = avg/r.getFlows().size();
		return avg;
	}

	public LocalDate getStartDate(River r) {
		return r.getFlows().get(0).getDay();
	}
	
	public LocalDate getEndDate(River r) {
		return r.getFlows().get(r.getFlows().size()-1).getDay();
	}
	
	
	
}
