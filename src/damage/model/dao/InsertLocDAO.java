package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import damage.model.bean.LocationBean;

public class InsertLocDAO extends AbstractDAO{
	
	public void insertLocation(LocationBean location) throws LocationFailedException {
		
		PreparedStatement prep;
		Connection connection;
		
		String query = new StringBuilder()
		.append("insert into location (id, longitude, latitude) values ")
		.append("(default,?,?);")
		.toString();
		
		try{
			
			connection = getConnection();
			prep = connection.prepareStatement(query);
			prep.setDouble(1, location.getLongitude());
			prep.setDouble(2, location.getLatitude());
			
			prep.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LocationFailedException();
		}
		
	}
	
	public static class LocationFailedException extends Throwable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8431050056370316907L;
		
	}

}
