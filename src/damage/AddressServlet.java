package damage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import damage.model.bean.DamageBean;
import damage.model.bean.LocationBean;
import damage.model.dao.AddressDAO;
import damage.model.dao.CheckAddressDAO;
import damage.model.dao.InsertAdrDAO;
import damage.model.dao.InsertLocDAO;

@SuppressWarnings("serial")
public class AddressServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {

			String city = request.getParameter("city");
			String street = request.getParameter("street");
			String number = request.getParameter("number");
			String type = request.getParameter("type");
			int degree = Integer.parseInt(request.getParameter("degree"));
			String description = request.getParameter("description");

			// does location exist?
			LocationBean location = new LocationBean();
			CheckAddressDAO check = new CheckAddressDAO();
			location.setStreet(street);
			location.setCity(city);
			location.setNum(number);
			boolean insert = check.findLocation(location);

			if(insert == true){
				 //location must be inserted
				InsertAdrDAO lid = new InsertAdrDAO();
				 lid.insertLocation(location);
				 request.setAttribute("bool", insert);
			 }
			
			// insert damage
			AddressDAO dao = new AddressDAO();
			DamageBean damage = new DamageBean();
			damage.setCity(city);
			damage.setStreet(street);
			damage.setNumber(number);
			damage.setType(type);
			damage.setDegreeofdamage(degree);
			damage.setDescription(description);
			dao.insertDamage(damage);

		} catch (Throwable e) {
			e.printStackTrace();
			request.setAttribute("error", true);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/insertsuccessful.jsp");
		dispatcher.forward(request, response);

	}
}