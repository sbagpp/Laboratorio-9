package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {
	/**
	 * trova tutti gli stati presenti nel DB con confini registrrati fino all'anno passato come parametro
	 * @param idMap
	 * @param anno
	 */

	public void loadAllCountries(Map<Integer, Country> idMap, int anno) {

		String sql ="select ccode, StateAbb, StateNme "
				+ "from `country` as st, `contiguity` as conf "
				+ "where conf.`conttype`=1 and conf.`year`<=? and (conf.`state2no`=st.`CCode` or conf.`state1no`=st.`CCode`) "
				+ "group by ccode, StateAbb, StateNme";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				//int ccode, String stateAbb, String stateNme
				Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				idMap.put(rs.getInt("ccode"), c);
			}
			
			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		System.out.println("TODO -- BordersDAO -- getCountryPairs(int anno)");
		return new ArrayList<Border>();
	}
}
