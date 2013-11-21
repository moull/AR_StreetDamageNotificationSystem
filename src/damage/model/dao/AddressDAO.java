package damage.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import damage.model.bean.DamageBean;

public class AddressDAO extends AbstractDAO {
	
	public void insertDamage(DamageBean damage) throws InsertingFailedException {
		
		String query = new StringBuilder()
		.append("insert into damage ")
		.append("values(default, ") //id
		.append("(select id as locid from location where street = ? and num = ? and city = ?), ") //locid
		.append("(select id as typeid from typeofdamage where type = ?), ") //typeid
		.append("?, ") //degree
		.append("?, ") //description
		.append("default, ") //status
		.append("(SELECT CURRENT_DATE));") //date, today
		.toString();
		
		try  {
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, damage.getStreet());
			preparedStatement.setString(2, damage.getNumber());
			preparedStatement.setString(3, damage.getCity());
			preparedStatement.setString(4, damage.getType());
			preparedStatement.setInt(5, damage.getDegreeofdamage());
			preparedStatement.setString(6, damage.getDescription());
			
			preparedStatement.executeUpdate();
			
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
