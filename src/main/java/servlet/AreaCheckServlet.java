package servlet;

import beans.UserData;
import beans.UserDataList;

import utils.ValuesCheck;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

//@WebServlet(name = "AreaCheckServlet", value = "/v1/areacheck")
public class AreaCheckServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String queryString = request.getQueryString();
        if(queryString != null){
            long nowTime = System.nanoTime();
            String xStr, yStr, rStr = "";
            try {


                String[] parametersPairs = queryString.split("&");
                Map<String, String> parametersMap = new HashMap<String, String>();
                for (String parametersPair : parametersPairs) {
                    String[] parameter = parametersPair.split("=");
                    parametersMap.put(parameter[0], parameter[1].length() > 0 ? parameter[1] : "");
                }
                xStr = parametersMap.get("x");
                yStr = parametersMap.get("y");
                rStr = parametersMap.get("r");
            } catch (ArrayIndexOutOfBoundsException | NullPointerException e){
                response.sendError(400, "Bad request");
                return;
            }
            ValuesCheck valuesCheckObj = new ValuesCheck();

            if (!valuesCheckObj.xyrCheck(xStr, yStr, rStr)) {
                response.sendError(400, "Parameters are not Valid, try again");
                return;
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            String contextPath = getServletContext().getContextPath();
            ServletContext servletContext = this.getServletContext();
            out.println(
                    """
                        <!DOCTYPE html>
                        <html>
                        <head>
                        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />                                            
                            """);
            out.println(" <link rel=\"stylesheet\" href=\""+contextPath+
                    "/css/tableStyle.css\" /></head>");
            out.println("""
                            <body>
                            <header>
                                <div class="box">
                                    <div class="fio">Podolskiy Vyacheslav Ilyich</div>
                                    <div class="group">Group: P3220</div>
                                    <div class="variant">Variant: 3007</div>
                                </div>
                            </header>
                            <main class="container">
                            <table class="table" border = '1'>
                                <thead>
                                <tr>
                                    <th>X</th>
                                    <th>Y</th>
                                    <th>R</th>
                                    <th>Result</th>
                                    <th>Event time</th>
                                    <th>Execution time</th>
                                </tr>
                                </thead>
                                <tbody class="table-body">
                    """);
            UserDataList userDataList = (UserDataList) servletContext.getAttribute("UserDataList");
            float xVal = Float.parseFloat(xStr);
            float yVal = Float.parseFloat(yStr);
            float rVal = Float.parseFloat(rStr);
            float a = 0;
            float b = 0;
            float c = rVal;
            float d = 0;
            float e = 0;
            float f = -rVal/2;
            boolean sector1 = (xVal >= 0 && yVal >= 0 && xVal <= rVal && yVal <= rVal);
            boolean sector2 = (xVal <= 0 && yVal >= 0 && (pow(xVal,2) + pow(yVal, 2)) <= pow(rVal/2,2));
            boolean sector4 = (xVal >= 0 && yVal <= 0 && ((xVal - a) * (d - b) - (c - a) * (yVal - b)) * ((xVal - c) * (f - d) - (e - c) * (yVal - d)) * ((xVal - e) * (b - f) - (a - e) * (yVal - f)) > 0);
            boolean isHit = sector1 || sector2 || sector4;
            Long executionTime = System.nanoTime() - nowTime;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String currentTime = dtf.format(now);
            UserData userData = new UserData(xVal, yVal, rVal, isHit, currentTime, executionTime.toString());
            if (userDataList.getUserDataList() == null) {
                userDataList.setUserDataList(new ArrayList<>());
            }
            userDataList.getUserDataList().add(userData);
            for (UserData userData1 : userDataList.getUserDataList()) {
                out.println("<tr><td scope='row'>" + userData1.getxVal() + "</td>");
                out.println("<td scope='row'>" + userData1.getyVal() + "</td>");
                out.println("<td scope='row'>" + userData1.getrVal() + "</td>");
                out.println("<td scope='row'>" + (userData1.isHit() ? "HIT" : "MISS") + "</td>");
                out.println("<td scope='row'>" + userData1.getCurrentTime() + "</td>");
                out.println("<td scope='row'>" + userData1.getExecutionTime() + "</td></tr>");
            }
            out.println("""
                                        </tbody>
                                        </table>
                    """);
            out.println("<div class=\"btn-block\">");
            out.println("<a href=\"" + contextPath + "/"
                    + "\"><button class=\"control-btn\">Back To The Main Page</button></a>");
            out.println("""
                            <button class='glow-on-hover btn-clear' id='submit' type='button'>Clear</button>
                                </div>
                            </div>
                    """);
            out.println("""
                    <script>
                        document.getElementById("submit").addEventListener('click', function (e) {
                             if (confirm('Are you sure you want to clear data table?')) {
                                window.location.replace("/cleartable");
                            }
                        });
                    </script>
                    </body>
                    </html>
                        """);
            }
        }

    public void destroy() {
    }
}