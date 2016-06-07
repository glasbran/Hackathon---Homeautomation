package wasdev.sample.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.ApplicationConnector;
import logic.MyEventCallback;


/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static ApplicationConnector appConn_1;
    public static MyEventCallback evCallBack_1;
    private static ApplicationConnector appConn_2;
    public static MyEventCallback evCallBack_2;
    
	public void init() throws ServletException {
		System.out.println("Init Servlet");
		//Init Application Client with event und status subscription
		evCallBack_1 = new MyEventCallback("Belkin_Switch_1");
		appConn_1 = new ApplicationConnector(evCallBack_1,"awwf5o","Belkin_Switch","Belkin_Switch_1","a-awwf5o-bapcimuww5","2?!QM2yFZTD1@YiA8V");
		
		evCallBack_2 = new MyEventCallback("Belkin_Switch_2");
		appConn_2 = new ApplicationConnector(evCallBack_2,"awwf5o","Belkin_Switch","Belkin_Switch_2","a-awwf5o-bapcimuww5","2?!QM2yFZTD1@YiA8V");
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	//Check which button was pressed
    	String act = request.getParameter("switch_1");
    	if (act == null) {
    		System.out.println("null");
    	} else if (act.equals("Toggle")) {
    		System.out.println("on");

    		//Toggle the Light State
    		appConn_1.changeState("toggle_state");
    	} else {
    		System.out.println("just an update");
    		//request.setAttribute("message", "no information right now");;
    		appConn_1.changeState("get_state");
    	}
    	
    	String act_2 = request.getParameter("switch_2");
    	if (act_2 == null) {
    		System.out.println("null");
    	} else if (act_2.equals("Toggle")) {
    		System.out.println("on");

    		//Toggle the Light State
    		appConn_2.changeState("toggle_state");
    	} else {
    		System.out.println("just an update");
    		//request.setAttribute("message", "no information right now");;
    		appConn_2.changeState("get_state");
    	}
    	
    	//update Status
    	request.setAttribute("message", evCallBack_1.getLightStatusMessage());
    	request.setAttribute("message_2", evCallBack_2.getLightStatusMessage());
    	
    	request.getRequestDispatcher("controlcenter.jsp").forward(request, response);
    }

}
