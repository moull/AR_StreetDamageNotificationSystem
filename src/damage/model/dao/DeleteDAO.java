package damage.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DeleteDAO extends AbstractOfflineDAO{

	public void delete() throws DeleteFailedException {
		
		Statement stmt;
		
		String query = new StringBuilder()
		.append("Delete from damage")
		.toString();
		
		try  {
			Connection con = getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DeleteFailedException();
		}
	}
	
	public static class DeleteFailedException extends Throwable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -8431050056370316907L;
		
	}
}
