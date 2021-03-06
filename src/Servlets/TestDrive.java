package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

import DAO.ITestDriveDAO;
import DAO.TestDriveDAO;

/**
 * Servlet implementation class TestDrive
 */
public class TestDrive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDrive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String model="";
		String lastname="";
		String middlename="";
		String data="";
		String telefon="";
		String name="";
		String userCaptchaResponse = request.getParameter("jcaptcha");
		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
		if(captchaPassed){
		// proceed to submit action
		model = new String(request.getParameter("auto_name").getBytes("ISO-8859-1"),
				"UTF-8");
		name = new String(request.getParameter("name")
				.getBytes("ISO-8859-1"), "UTF-8");

		lastname = new String(request.getParameter("last_name")
				.getBytes("ISO-8859-1"), "UTF-8");

		middlename = new String(request.getParameter("middle_name")
				.getBytes("ISO-8859-1"), "UTF-8");

		telefon = new String(request.getParameter("fon").getBytes("ISO-8859-1"),
				"UTF-8");
		data = new String(request.getParameter("data").getBytes("ISO-8859-1"),
				"UTF-8");
		
		ITestDriveDAO testDrive;
		testDrive = new TestDriveDAO();
		testDrive.addTestDrive(model, data, name, lastname, middlename, telefon);
		request.setAttribute("mes", "1");
		request.getRequestDispatcher("tehosmotr.jsp").forward(request,
				response);
		}else{
			request.setAttribute("mes1", "*����������� ��������� �������");
			request.getRequestDispatcher("testdrive.jsp").forward(request,
					response);
		}
	}

}
