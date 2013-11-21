package damage;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import damage.model.bean.OfflineBean;
import damage.model.dao.DeleteDAO;
import damage.model.dao.DownloadDAO;
import damage.model.dao.OfflineDAO;


@SuppressWarnings("serial")
public class DownloadServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			LinkedList<OfflineBean> off = new LinkedList<OfflineBean>();
			DownloadDAO start = new DownloadDAO();
			off = start.select();
			OfflineBean ins = new OfflineBean();
			OfflineDAO end = new OfflineDAO();
			DeleteDAO del = new DeleteDAO();
			
			del.delete();
			
			for(int i=0; i<off.size(); i++){
				
				ins.setId(off.get(i).getId());
				ins.setLatitude(off.get(i).getLatitude());
				ins.setLongitude(off.get(i).getLongitude());
				ins.setType(off.get(i).getType());
				ins.setDate(off.get(i).getDate());
				ins.setDegree(off.get(i).getDegree());
				ins.setDescription(off.get(i).getDescription());
				
				end.insert(ins);
			}
			

		} catch (Throwable e) {
			e.printStackTrace();
			request.setAttribute("error", true);
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/downloadendpage.jsp");
		dispatcher.forward(request, response);
	}
}
