package Servlet;

import Connector.AlchemyConnector;
import Connector.CloudantClientClass;
import java.io.*;
import java.net.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONValue;

@WebServlet(name = "IServlet", urlPatterns = {"/IServlet"})
public class IServlet extends HttpServlet {

	private String FACE_ENDPOINT_URL = "http://gateway-a.watsonplatform.net/calls/url/URLGetRankedImageFaceTags";

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
	
		AlchemyConnector connector = new AlchemyConnector();
		String input_url = (String) request.getParameter("gurl");
		StringBuilder sb = new StringBuilder();
		String line;
		
		URL face_url = new URL(FACE_ENDPOINT_URL+"?url="+input_url+"&apikey="+connector.getAPIKey()+"&outputMode=json");
		BufferedReader reader = new BufferedReader(new InputStreamReader(face_url.openStream()));
		while ((line = reader.readLine()) != null){
			sb.append(line);
		}
		String jsonString1 = sb.toString();
		//request.setAttribute("face",sb.toString());
		
		//start of cloudant connection
		try {
			CloudantClientClass cloudantdb = new CloudantClientClass();
			cloudantdb.addEntry(jsonString1);
		} catch(Exception ex){
			
		}
		//parse json string to get age and gender
		// "imageFaces": [ { "age": { "ageRange": "35-44", "score": "0.492583" }
		//"gender": { "gender": "FEMALE", "score": "0.995504" }
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonString1);
				
			JSONObject jsonObject = (JSONObject) obj;
			
			
			JSONArray jsonArray = (JSONArray) jsonObject.get("imageFaces");
			JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
			JSONObject objectage = (JSONObject) jsonObject1.get("age");
			String age = objectage.get("ageRange").toString();
			
			
			
			Object obj2 = parser.parse(jsonObject1.get("gender").toString());
			JSONObject genderobj1 = (JSONObject) obj2;
			String gender = genderobj1.get("gender").toString();
			request.setAttribute("age", age);
			request.setAttribute("gender", gender);
		} catch(Exception e){
			
		}
		//String age = jsonObject1.get("ageRange").toString();
		//String gender = genderobj1.get("gender").toString();
       /* JSONArray jsonArray = (JSONArray) jsonObject.get("translations");
        JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
        String translation = jsonObject1.get("translation").toString();
        String word_count = jsonObject.get("word_count").toString();
		*/
		
		
		//set output here
		

		response.setContentType("text/html");
        response.setStatus(200);
        request.getRequestDispatcher("viewinfo.jsp").forward(request, response);

	}

}

