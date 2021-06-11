package it.polito.tdp.rivers.db;

import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public void getAllRivers(Map<Integer,River> idMap) {
		
		final String sql = "SELECT id, name FROM river";

		//List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				idMap.put(res.getInt(("id"), rs.get)
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public double getAvgFlow(int river_id) {
		
		String sql="SELECT AVG(flow)AS media "
				+ "FROM flow "
				+ "WHERE river =?";
		
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river_id);
			ResultSet res = st.executeQuery();
			double result = res.getDouble("media");
			return result;
		}catch(SQLException e) {
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Flow> getFlowsForRIver(int river_id){
		String sql ="SELECT DAY,flow "
				+ "FROM flow "
				+ "WHERE river =?";
		
		List<Flow> result = new LinkedList<Flow>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river_id);
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				result.add(new Flow(res.get("DAY"), res.getDouble("flow"),river_id ));
			}
			
			
		}catch(SQLException e) {
			throw new RuntimeException("Error Connection Database");
		}
	}
}
