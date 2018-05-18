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
import java.util.Map;

import it.polito.tdp.poweroutages.model.javabean.Area;
import it.polito.tdp.poweroutages.model.javabean.EventType;
import it.polito.tdp.poweroutages.model.javabean.Nerc;
import it.polito.tdp.poweroutages.model.javabean.PowerOutage;
import it.polito.tdp.poweroutages.model.javabean.Responsible;
import it.polito.tdp.poweroutages.model.javabean.Tag;

public class PowerOutageDAO {

	private Map<Integer, PowerOutage> idMapPowerOutages;
	private Map<Integer, Area> idMapAreas;
	private Map<Integer, EventType> idMapEventTypes;
	private Map<Integer, Nerc> idMapNercs;
	private Map<Integer, Responsible> idMapResponsibles;
	private Map<Integer, Tag> idMapTags;
	
	public PowerOutageDAO(Map<Integer, PowerOutage> idMapPowerOutages, Map<Integer, Area> idMapAreas,
			Map<Integer, EventType> idMapEventTypes, Map<Integer, Nerc> idMapNercs,
			Map<Integer, Responsible> idMapResponsibles, Map<Integer, Tag> idMapTags) {
		
		this.idMapPowerOutages = idMapPowerOutages;
		this.idMapAreas = idMapAreas;
		this.idMapEventTypes = idMapEventTypes;
		this.idMapNercs = idMapNercs;
		this.idMapResponsibles = idMapResponsibles;
		this.idMapTags = idMapTags;
	}

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = this.idMapNercs.get(res.getObject("id"));
				if(n== null) {
					n=new Nerc(res.getInt("id"), res.getString("value"));
					this.idMapNercs.put(n.getId(), n);
				}
				nercList.add(n);
				}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutage> getPowerOutagesByNerc(Nerc nerc) {
		
		String sql = "SELECT * FROM PowerOutages WHERE nerc_id = ?";
		List<PowerOutage> powOutList = new ArrayList<>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id"); //LAZY LOADING
				EventType et = new EventType(rs.getInt("event_type_id"), true);
				Tag t= new Tag(rs.getInt("tag_id"), true);
				Area a = new Area(rs.getInt("area_id"), true);
				Nerc n = nerc;
				Responsible r = new Responsible(rs.getInt("responsible_id"), true);
				int custAff = rs.getInt("customers_affected");
				Timestamp startSQL = rs.getTimestamp("date_event_began");
				LocalDateTime dateBegan = startSQL.toLocalDateTime();
				Timestamp finishSQL = rs.getTimestamp("date_event_finished");
				LocalDateTime dateFinish = finishSQL.toLocalDateTime();
				int demandLoss = rs.getInt("demand_loss");
				
				PowerOutage po = this.idMapPowerOutages.get(id);
				
				if(po==null) {
					po = new PowerOutage(id, et, t, a, n, r, custAff, dateBegan, dateFinish, demandLoss);
					this.idMapPowerOutages.put(po.getId(), po);
				}
				
				powOutList.add(po);
			}
			
			conn.close();
		}catch(SQLException sqle) {
			System.out.println("Errore DB!");
			sqle.printStackTrace();
		}
		return powOutList;
	}

}
