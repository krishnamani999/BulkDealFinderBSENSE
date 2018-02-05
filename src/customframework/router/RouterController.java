package customframework.router;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customframework.controller.Controller;
import customframework.exception.FrameworkException;
import customframework.model.Model;

/**
 * Servlet implementation class RouterController
 */
@WebServlet("/router/*")
public class RouterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RouterParser routerParser;

    public RouterController() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String,RouterRecord> routerMap = routerParser.getRouterMap();
		RouterRecord record = routerMap.get(request.getParameter("controller")+"_"+request.getParameter("function"));
		Controller controller = record.getController();
		
		Model requestModel = record.getModel();
		requestModel.populate(request);
		
		RequestDispatcher dispatcher;
		try {
			Model responseModel = controller.handleRequest(requestModel);
			request.setAttribute("ResponseBean", responseModel);
			dispatcher = request.getServletContext().getRequestDispatcher(record.getSuccessJsp());
		} catch (FrameworkException e) {
			e.printStackTrace();
			dispatcher = request.getServletContext().getRequestDispatcher(record.getErrorJsp());
		}
		dispatcher.forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		if(routerParser == null){
			routerParser = new RouterParser();
			routerParser.parse();
		}
	}
}
