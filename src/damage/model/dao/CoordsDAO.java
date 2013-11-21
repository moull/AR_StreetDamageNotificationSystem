package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import damage.model.bean.DamageBean;

public class CoordsDAO extends AbstractDAO {
	
public int insertDamage(DamageBean damage) throws InsertingFailedException {
		
		String query = new StringBuilder()
		.append("insert into damage ")
		.append("values(default, ") //id
		.append("(select min(id) as locid from location where longitude = ? and latitude = ?), ") //locid
		.append("(select id as typeid from typeofdamage where type = ?), ") //typeid
		.append("?, ") //degree
		.append("?, ") //description
		.append("default, ") //status
		.append("(SELECT CURRENT_DATE));") //date, today
		.toString();
		
		try  {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setDouble(1, damage.getLongitude());
			preparedStatement.setDouble(2, damage.getLatitude());
			preparedStatement.setString(3, damage.getType());
			preparedStatement.setInt(4, damage.getDegreeofdamage());
			preparedStatement.setString(5, damage.getDescription());
			
			preparedStatement.executeUpdate();
			
			ResultSet keys = preparedStatement.getGeneratedKeys();
			if(keys.next()) {
				int id = keys.getInt(1);
				return id;
			} else {
				throw new InsertingFailedException();
			}
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
