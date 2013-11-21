package damage.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import damage.model.bean.OfflineBean;
import damage.model.dao.DownloadDAO.SelectFailedException;

public class ShowDAO extends AbstractOfflineDAO{

public LinkedList<OfflineBean> select() throws SelectFailedException {
		
		LinkedList<OfflineBean> damage = new LinkedList<OfflineBean>();
		Statement stmt;
		String query = new StringBuilder()
		.append("select * from damage ")
		.append("where not (latitude = 0) ")
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
				ob.setDegree(rs.getInt("degreeofdamage"));
				ob.setDate(rs.getString("dat"));
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
