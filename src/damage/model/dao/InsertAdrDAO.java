package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import damage.model.bean.LocationBean;

public class InsertAdrDAO extends AbstractDAO {

	public void insertLocation(LocationBean location)
			throws LocationFailedException {

		PreparedStatement prep;
		Connection connection;

		String query = new StringBuilder()
				.append("insert into location (id, city, street, num) values ")
				.append("(default,?,?,?);").toString();

		try {

			connection = getConnection();
			prep = connection.prepareStatement(query);
			prep.setString(1, location.getCity());
			prep.setString(2, location.getStreet());
			prep.setString(3, location.getNum());

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
