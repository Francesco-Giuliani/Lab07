package it.polito.tdp.poweroutages.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.PowerOutage;
import it.polito.tdp.poweroutages.model.bean.Area;
import it.polito.tdp.poweroutages.model.bean.EventType;
import it.polito.tdp.poweroutages.model.bean.Nerc;
import it.polito.tdp.poweroutages.model.bean.Responsible;
import it.polito.tdp.poweroutages.model.bean.Tag;

public class PowerOutageDAO {
	
	 public List<PowerOutage> getAllPowerOutages() {
		 
		String sql = "SELECT * FROM poweroutages";
		
		List<PowerOutage> powOutList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int id = rs.getInt("id");
				EventType et = this.getEventTypeById(rs.getInt("event_type_id"));
				Tag tag = this.getTagById(rs.getInt("tag_id"));
				Area a = this.getAreaById(rs.getInt("area_id"));
				Nerc n = this.getNercById(rs.getInt("nerc_id"));
				Responsible r = this.getResponsibleById(rs.getInt("responsible_id"));
				int ca = rs.getInt("customers_affected");
				Timestamp dateStart = rs.getTimestamp("date_event_began");
				Timestamp dateEnd = rs.getTimestamp("date_event_finished");
				int dl = rs.getInt("demand_loss");
				
				LocalDateTime dateEventStarted = dateStart.toLocalDateTime();
				LocalDateTime dateEventEnded = dateEnd.toLocalDateTime();
				
				PowerOutage po = new PowerOutage(id, et, tag, a, n, r, ca, dateEventStarted, dateEventEnded, dl);
				powOutList.add(po);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return powOutList;
	}


	 public List<PowerOutage> getPowerOutagesByNerc(Nerc nerc) {
		 String sql = "SELECT * FROM poweroutages WHERE nerc_id = ? ";
			
			List<PowerOutage> powOutList = new ArrayList<>();

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				st.setInt(1, nerc.getId());
				ResultSet rs = st.executeQuery();

				while (rs.next()) {
					
					int id = rs.getInt("id");
					EventType et = this.getEventTypeById(rs.getInt("event_type_id"));
					Tag tag = this.getTagById(rs.getInt("tag_id"));
					Area a = this.getAreaById(rs.getInt("area_id"));
					Nerc n = this.getNercById(rs.getInt("nerc_id")); //MIGLIORABILE? metto copie del nerc appena usato
					Responsible r = this.getResponsibleById(rs.getInt("responsible_id"));
					int ca = rs.getInt("customers_affected");
					Timestamp dateStart = rs.getTimestamp("date_event_began");
					Timestamp dateEnd = rs.getTimestamp("date_event_finished");
					int dl = rs.getInt("demand_loss");
					
					LocalDateTime dateEventStarted = dateStart.toLocalDateTime();
					LocalDateTime dateEventEnded = dateEnd.toLocalDateTime();
					
					PowerOutage po = new PowerOutage(id, et, tag, a, n, r, ca, dateEventStarted, dateEventEnded, dl);
					powOutList.add(po);
				}

				conn.close();

			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

			return powOutList;
	}

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
	
	public Nerc getNercById(int id){
		Nerc res = null;
		String sql = "SELECT id, value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = new Nerc(rs.getInt("id"), rs.getString("value"));
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		return res;
	}

	public Area getAreaById(int id){
		Area res = null;
		String sql = "SELECT id, value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = new Area(rs.getInt("id"), rs.getString("value"));
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		return res;
		}
	
	public Responsible getResponsibleById(int id){
		Responsible res = null;
		String sql = "SELECT id, value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = new Responsible(rs.getInt("id"), rs.getString("value"));
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		return res;
	}
	
	public Tag getTagById(int id){
		Tag res = null;
		String sql = "SELECT id, value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = new Tag(rs.getInt("id"), rs.getString("value"));
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		return res;
	}
	
	public EventType getEventTypeById(int id){
		EventType res = null;
		String sql = "SELECT id, value FROM nerc WHERE id = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				res = new EventType(rs.getInt("id"), rs.getString("value"));
			}
			
			conn.close();			
		}catch(SQLException sqle) {
			System.out.println("Errore DB");
			throw new RuntimeException(sqle);
		}
		return res;
	}



	
	
}
