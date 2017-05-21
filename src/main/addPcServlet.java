package main;

import main.DBwork.DBclass;
import main.service.ConstHTML;
import main.service.logFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by virus on 11.05.2017.
 */
@WebServlet(name = "addPcServlet")
public class addPcServlet extends HttpServlet {
    private String name = null;
    private String locate = null;
    private String message = "";
    private String page = null;
    private Boolean successfully = false;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        name = request.getParameter("name");
        locate = request.getParameter("locate");
        page = request.getParameter("page");

        if (page == null) {
            sendPage(response);
            return;
        }
        if ((name != "") && (locate != "")  && (page.equals("1"))){
            DBclass db = new DBclass();
            String sql = "INSERT INTO public.pc_info(name, locate) VALUES ('" + name +"', '" + locate + "');";
            System.out.println(sql);
            logFile.write(db.insert(sql) + "(" + sql + ")");
            db.close();
            successfully = true;
            sendPage(response);
        } else {
            message = "Invalid fields";
            sendPage(response);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendPage(response);
    }

    void sendPage(HttpServletResponse response) throws ServletException,IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ConstHTML.head("Registration PC",response);

        out.println("<BR><div align=\"center\"><H2>Registration PC</H2>");
        out.println("<FORM METHOD='post' class=\"form-horizontal\">");
        out.println("<TABLE class=\"table\" style='width:30%' align='center'>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>name&nbsp;</TD>");
        out.println("<TD><INPUT class=\"form-control\"  TYPE='TEXT' NAME='name'></TD>");
        out.println("<TD><INPUT class=\"form-control\"  TYPE='HIDDEN' NAME='page' value='1'></TD>");
        out.println("</TR>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>locate&nbsp;</TD>");
        out.println("<TD><INPUT class=\"form-control\"  TYPE='TEXT' NAME='locate'></TD>");
        out.println("</TR>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD><INPUT class='btn btn-danger' TYPE='RESET'></TD>");
        out.println("<TD><INPUT class='btn btn-success'      TYPE='SUBMIT' VALUE='Submit'>");
        out.println("<div style=\"color:red\">" + message + "</div>");
        out.println("</TD><td><a href='/'>Back</a> to main page</td><TR>");
        if (successfully) {
            out.println("<tr><td></td><td><p style=\"color:red\">Registration completed</p></td></tr>");
            successfully = false;
        }
        out.println("</div>");
        out.println("</TABLE>");
        out.println("</FORM>");
        out.println("</div>");
        message = "";
        ConstHTML.footer(response);
    }
}
