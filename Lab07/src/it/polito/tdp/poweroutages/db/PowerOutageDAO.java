package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.PowerOutage;
import it.polito.tdp.poweroutages.model.bean.Nerc;

public class PowerOutageDAO {
	
	/*TODO
	 * public List<PowerOutage> getAllPowerOutagesc() {

		
		//String sql = "SELECT id, value FROM nerc";
		//List<PowerOutage> powOutList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				
				PowerOutage po = new PowerOutage();
				powOutList.add(po);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powOutList;
	}*/


	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public String getNercById(int id){
		String res = null;
		String sql = "SELECT value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("value");
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		
		return res;
	}

	public String getAreaById(int id){
		String res = null;
		String sql = "SELECT value FROM area WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("value");
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		
		return res;
	}
	
	public String getResponsibleById(int id){
		String res = null;
		String sql = "SELECT value FROM responsible WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("value");
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		
		return res;
	}
	
	public String getTagById(int id){
		String res = null;
		String sql = "SELECT value FROM tag WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("value");
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		
		return res;
	}
	
	public String getEventTypeById(int id){
		String res = null;
		String sql = "SELECT value FROM eventtype WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = rs.getString("value");
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		
		return res;
	}

}
