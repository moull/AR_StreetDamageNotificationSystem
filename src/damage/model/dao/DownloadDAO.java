package damage.model.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import damage.model.bean.OfflineBean;

public class DownloadDAO extends AbstractDAO{

	public LinkedList<OfflineBean> select() throws SelectFailedException {
		
		LinkedList<OfflineBean> damage = new LinkedList<OfflineBean>();
		Statement stmt;
		
		String query = new StringBuilder()
		.append("select d.id as id, l.longitude as longitude, l.latitude as latitude, t.type as type, d.degreeofdamage as degree, d.dat as date, d.description as description ")
		.append("from damage d, location l, typeofdamage t ")
		.append("where d.typeid = t.id ")
		.append("and d.locid = l.id ")
		.append("and not (l.latitude = 0) ")
		.append("and d.status = 'broken';")
		.toString();
		
		try  {
			Connection con = getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				OfflineBean ob = new OfflineBean();
				
				ob.setId(rs.getInt("id"));
				ob.setLatitude(rs.getDouble("latitude"));
				ob.setLongitude(rs.getDouble("longitude"));
				ob.setType(rs.getString("type"));
				ob.setDegree(rs.getInt("degree"));
				ob.setDate(rs.getString("date"));
				ob.setDescription(rs.getString("description"));
				
				damage.add(ob);
			}
			rs.close();
			return damage;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SelectFailedException();
		}
	}
	
	public static class SelectFailedException extends Throwable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8431050056370316907L;
		
	}
}
