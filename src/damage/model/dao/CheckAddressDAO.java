package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import damage.model.bean.LocationBean;

public class CheckAddressDAO extends AbstractDAO{
	
	public boolean findLocation(LocationBean location) throws LocationFailedException {
		
		PreparedStatement prep;
		Connection connection;
		
		String query = new StringBuilder()
		.append("select * from location ")
		.append("where street = ? ")
		.append("and city = ? ")
		.append("and num = ? ")
		.toString();
		
		try  {
			connection = getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, location.getStreet());
			prep.setString(2, location.getCity());
			prep.setString(3, location.getNum());
			
			ResultSet rs  = prep.executeQuery();
			int empty = 0;
			
			while (rs.next()){
				empty++;
			}
			
			rs.close();
			
			if (empty == 0){
				return true;
			} else {
				return false;
			}
			
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
