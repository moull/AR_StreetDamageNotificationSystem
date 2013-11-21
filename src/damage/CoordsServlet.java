package damage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import damage.model.bean.DamageBean;
import damage.model.bean.LocationBean;
import damage.model.dao.CoordsDAO;
import damage.model.dao.InsertLocDAO;
import damage.model.dao.LocDAO;

@SuppressWarnings("serial")
public class CoordsServlet extends HttpServlet {

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 try {
		 double latitude = Double.valueOf(request.getParameter("latitude"));
		 double longitude = Double.valueOf(request.getParameter("longitude"));
		 String type = request.getParameter("type");
		 int degree = Integer.parseInt(request.getParameter("degree"));
		 String description = request.getParameter("description");
		 
		 //does location already exist in the database?
		 LocationBean location = new LocationBean();
		 LocDAO ld = new LocDAO();
		  
		 location.setLatitude(latitude);
		 location.setLongitude(longitude);
		 boolean insert = ld.findLocation(location);
		 
		 if(insert == true){
			 //location must be inserted
			 InsertLocDAO lid = new InsertLocDAO();
			 lid.insertLocation(location);
			 request.setAttribute("bool", insert);
		 }
		 
	 //insert damage
		 CoordsDAO dao = new CoordsDAO();
		 DamageBean damage = new DamageBean();
		 damage.setLatitude(latitude);
		 damage.setLongitude(longitude);
		 damage.setType(type);
		 damage.setDegreeofdamage(degree);
		 damage.setDescription(description);
		 dao.insertDamage(damage);
		 
	 } catch (Throwable e) {
 		e.printStackTrace();
 		request.setAttribute("error", true);
 	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/insertsuccessful.jsp");
		dispatcher.forward(request, response);
	 
}
}