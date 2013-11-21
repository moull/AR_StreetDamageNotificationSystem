package damage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import damage.model.bean.DamageBean;

@SuppressWarnings("serial")
public class SendCoordsServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			DamageBean damage = new DamageBean();
			double latitude = Double.valueOf(request.getParameter("latitude"));
			double longitude = Double.valueOf(request.getParameter("longitude"));
			damage.setLatitude(latitude);
			damage.setLongitude(longitude);
			request.setAttribute("damage", damage);
			if (request.getParameter("latitude") == null || request.getParameter("longitude") == null){
				request.setAttribute("error", true);
			}
		} catch (Throwable e) {
	 		e.printStackTrace();
	 		request.setAttribute("error", true);
	 	}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/uploadusinggps.jsp");
		dispatcher.forward(request, response);
	}

}
