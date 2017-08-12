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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by virus on 16.04.2017.
 */
@WebServlet(name = "registrationServlet")
public class registrationServlet extends HttpServlet {
    private String username = null;
    private String token_id = null;
    private String pc_id = null;
    private String role = null;
    private String message = "";
    private String page = null;
    private Boolean successfully = false;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        username = request.getParameter("username");
        token_id = request.getParameter("token_id");
        pc_id = request.getParameter("pc_id");
        role = request.getParameter("role");
        page = request.getParameter("page");

        if (page == null) {
            try {
                sendPage(response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        if ((username != "") && (token_id != "") && (pc_id != "") && (role != "") && (page.equals("1"))){
            DBclass db = new DBclass();
            String sql = "INSERT INTO public.users_info(username, token_id, pc_id, role_id,password) VALUES ('" + username +"', '" + token_id + "', '" + pc_id + "', '" + role + "','1357911');\n";
            System.out.println(sql);
            logFile.write(db.insert(sql) + "(" + sql + ")");
            db.close();
            successfully = true;
            try {
                sendPage(response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            message = "Invalid fields";
            try {
                sendPage(response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sendPage(response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void sendPage(HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ConstHTML.head("Registration",response);

        out.println("<BR><div align=\"center\"><H2>Registration </H2>");
        out.println("<FORM METHOD='post' class=\"form-horizontal\">");
        out.println("<TABLE class=\"table\" style='width:30%' align='center'>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>username&nbsp;</TD>");
        out.println("<TD><INPUT class=\"form-control\" TYPE='TEXT' NAME='username'></TD>");
        out.println("<TD><INPUT TYPE='HIDDEN' NAME='page' value='1'></TD>");
        out.println("</TR>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>token_id&nbsp;</TD>");
        out.println("<TD><INPUT class=\"form-control\" TYPE='TEXT' NAME='token_id'></TD>");
        out.println("</TR>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>pc_id&nbsp;</TD>");
        out.println("<TD><select class=\"form-control\" name='pc_id' width='200px'>");
        ResultSet rs3 = new DBclass().query("SELECT pc_id, name, locate  FROM public.pc_info;");
        while (rs3.next()) {
            out.println("<option value = '" + rs3.getString(1) + "'> pc_id: " + rs3.getString(1) + " name: " + rs3.getString(2) + " locate: " + rs3.getString(3) + "</option>");
        }
        out.println("</select></TD>");

        out.println("</TR>");
        out.println("</div>");

        out.println("<div class=\"form-group\">");
        out.println("<TR>");
        out.println("<TD>role&nbsp;</TD>");
        out.println("<td><select class=\"form-control\" name=\"role\" size=\"1\">");
        out.println("<option value = '1'>1: ADMIN</option>");
        out.println("<option value = '2'>2: USER</option>");
        out.println("</select></td>");
        out.println("</TR>");
        out.println("</div>");

        out.println("<TR>");
        out.println("<TD><INPUT TYPE='RESET' class='btn btn-danger'></TD>");
        out.println("<TD><INPUT TYPE='SUBMIT' class='btn btn-success' VALUE='Submit'>");
        out.println("<div style=\"color:red\">" + message + "</div>");
        out.println("</TD><td><a class='btn btn-default' href='/'>Back</a> to main page</td><TR>");
        if (successfully) {
            out.println("<tr><td></td><td><p style=\"color:red\">Registration completed</p></td></tr>");
            successfully = false;
        }
        out.println("</div>");
        out.println("</TABLE>");
        out.println("</FORM>");
        out.println("</div>");
        ConstHTML.footer(response);
        message = "";
    }
}
