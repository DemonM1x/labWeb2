package servlet;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@WebServlet(name = "Controller", value = "controller")
public class ControllerServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String queryString = request.getQueryString();
        String address = getServletContext().getContextPath();
        if (queryString != null){
            try {
                String[] parametersPairs = queryString.split("&");
                Map<String,String> parametersMap = new HashMap<String, String>();
                for (String parameterPair : parametersPairs){
                    String[] parameter = parameterPair.split("=");
                    parametersMap.put(parameter[0], parameter[1].length() > 0 ? parameter[1] : "");
                }
                String xVal = parametersMap.get("x");
                String yVal = parametersMap.get("y");
                String rVal = parametersMap.get("r");
                if (xVal != null && yVal != null && rVal != null){
                    address += "/areacheck?" + queryString;
                }
            }catch (Exception e){
                response.sendError(400, "Bad request");
                return;
            }
        }
        response.sendRedirect(address);
    }
}
