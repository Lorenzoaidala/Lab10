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

	public List<River> getAllRivers() {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				rivers.add(new River(res.getInt("id"), res.getString("name")));
			}

			conn.close();
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	/*public double getAvgFlow(int river_id) {
		
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
	}*/
	
	public List<Flow> getFlowsForRIver(River river){
		String sql ="SELECT DAY,flow "
				+ "FROM flow "
				+ "WHERE river =?";
		
		List<Flow> result = new LinkedList<Flow>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				result.add(new Flow(res.getDate("DAY").toLocalDate(), res.getDouble("flow"),river));
			}
			conn.close();
			return result;
			
		}catch(SQLException e) {
			throw new RuntimeException("Error Connection Database");
		}
	}
}
