package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import damage.model.bean.OfflineBean;

public class OfflineDAO extends AbstractOfflineDAO{

	public void insert(OfflineBean data) throws InsertingFailedException {
		
		PreparedStatement pstmt;
		
		String query = new StringBuilder()
		.append("insert into damage ")
		.append("values(?, ?, ?, ?, ?, to_date(?,'YYYY-MM-DD'), ?); ")
		.toString();
		
		try{
			Connection connection = getConnection();
			pstmt = connection.prepareStatement(query);
			
			pstmt.setInt(1, data.getId());
			pstmt.setDouble(2, data.getLongitude());
			pstmt.setDouble(3, data.getLatitude());
			pstmt.setString(4, data.getType());
			pstmt.setInt(5, data.getDegree());
			pstmt.setString(6, data.getDate());
			pstmt.setString(7, data.getDescription());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsertingFailedException();
		}
	}
	
	public static class InsertingFailedException extends Throwable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8431050056370316907L;
		
	}
	
}
