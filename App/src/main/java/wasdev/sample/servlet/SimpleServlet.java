package wasdev.sample.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.ibm.iotf.client.app.ApplicationClient;

import logic.LightChanger;
import logic.MyEventCallback;
import logic.Subscriber;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    
	public void init() throws ServletException {
		System.out.println("Init Servlet");
		Subscriber light_one_s = new Subscriber();
		light_one_s.clientConnect();	
	}
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	String act = request.getParameter("light_1");
    	if (act == null) {
    		System.out.println("null");
    		//request.setAttribute("message", "null");
    	} else if (act.equals("on")) {
    		System.out.println("on");
    		//request.setAttribute("message", "on");
    		
    		//Turn the Light ON
    		LightChanger.changeState("light_on");
    	} else if (act.equals("off")) {
    		System.out.println("off");
    		//request.setAttribute("message", "off");
    		//Turn The Light Off
    		LightChanger.changeState("light_off");
    	} else {
    		System.out.println("just an update");
    		//request.setAttribute("message", "no information right now");;
    	}
    	
    	//update Status
    	request.setAttribute("message", MyEventCallback.getLightStatusMessage());
    	
    	request.getRequestDispatcher("controlcenter.jsp").forward(request, response);
    }

}
