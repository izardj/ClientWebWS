package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import ws.BanqueService;
import ws.BanqueWS;
import ws.BanqueWSLocator;

/**
 * Servlet implementation class ConvertirEuroDollar
 */
@WebServlet("/ConvertirEuroDollar")
public class ConvertirEuroDollar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConvertirEuroDollar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 1- récupérer les paramètres
		double montantEuro = Double.parseDouble(request.getParameter("euro"));
		
		// 2- traitements avec le webservice
		BanqueService banqueService;
		try {
			banqueService = new BanqueWSLocator().getBanqueServicePort();
			double montantDollar = banqueService.conversionED(montantEuro);

			// 3- préparation envoi
			request.setAttribute("dollar", montantDollar);
			request.setAttribute("euro", montantEuro);
			// 4- envoi
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
