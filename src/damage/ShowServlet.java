package damage;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import damage.model.bean.OfflineBean;
import damage.model.dao.ShowDAO;

@SuppressWarnings("serial")
public class ShowServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LinkedList<OfflineBean> data = new LinkedList<OfflineBean>();
		ShowDAO dao = new ShowDAO();
		
		try {
		data = dao.select();
		request.setAttribute("data", data);
		} catch (Throwable e) {
	 		e.printStackTrace();
	 		request.setAttribute("error", true);
	 	}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/show.jsp");
			dispatcher.forward(request, response);
	}

}
