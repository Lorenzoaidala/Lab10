package it.polito.tdp.rivers.model;

import it.polito.tdp.rivers.db.RiversDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Model {
	
	
	private RiversDAO dao;
	private List<River> rivers;
	private PriorityQueue<Flow> queue;
	
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
		if(!r.getFlows().isEmpty())
		return r.getFlows().get(0).getDay();
		return null;
	}
	
	public LocalDate getEndDate(River r) {
		if(!r.getFlows().isEmpty())
		return r.getFlows().get(r.getFlows().size()-1).getDay();
		return null;
	}
	
	public int getNumeroMisurazioni(River r) {
		return r.getFlows().size();
	}
	
	public SimulationResult simulate(River river, double k) {
		queue= new PriorityQueue<Flow>();
		this.queue.addAll(river.getFlows());
		
		double Q = k*30*convM3alSecToM3alGiorno(river.getFlowAvg());
		double C = Q/2;
		double f_out_min = convM3alSecToM3alGiorno(0,8*river.getFlowAvg());
		int giorniCritici = 0;
		
		System.out.println("Q = "+Q);
		Flow flow;
		while((flow=this.queue.poll())!=null) {
			double f_out = f_out_min;
			
			if(Math.random()>0.95) {
				f_out = 10*f_out_min;
			}
			C = convM3alSecToM3alGiorno(flow.getFlow());
			
			if(C>Q) {
				C=Q;
			}
			if(C<f_out) {
				giorniCritici++;
				C=0;
			} else {
				C-=f_out;
			}
			
		}
	}
	
	
}
