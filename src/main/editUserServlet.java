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
 * Created by virus on 11.05.2017.
 */
@WebServlet(name = "editUserServlet")
public class editUserServlet extends HttpServlet {
    private String user_id = null;
    private String username = null;
    private String token_id = null;
    private String pc_id = null;
    private String role = null;
    private String message = "";
    private String page = null;
    private Boolean successfully = false;
    private ResultSet resultSet = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        user_id = request.getParameter("user_id");
        username = request.getParameter("username");
        token_id = request.getParameter("token_id");
        pc_id = request.getParameter("pc_id");
        role = request.getParameter("role");
        page = request.getParameter("page");

        if (page == null) {
            try {
                sendPage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        if ((username != "") && (token_id != "") && (pc_id != "") && (role != "") && (page.equals("1"))) {
            DBclass db = new DBclass();
            String sql = "UPDATE public.users_info SET  username='" + username + "', token_id='" + token_id + "', pc_id='" + pc_id + "', role_id='" + role + "' WHERE id = " + user_id +";\n";
            logFile.write(db.update(sql) + "(" + sql + ")");
            db.close();
            successfully = true;
            try {
                sendPage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            message = "Invalid fields";
            try {
                sendPage(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            sendPage(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void sendPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        response.setCharacterEncoding("UTF-8");
        if (("" + user_id) != "") {
            String sql = "SELECT * FROM public.users_info WHERE id='" + user_id + "';";

            resultSet = new DBclass().query(sql);
            while (resultSet.next()) {
                PrintWriter out = response.getWriter();
                ConstHTML.head("Edit user", response);

                out.println("<BR><div align=\"center\"><H2>Edit user </H2>");
                out.println("<FORM METHOD='post' class=\"form-horizontal\">");
                out.println("<INPUT TYPE='HIDDEN' NAME='page' value='1'>");
                out.println("<INPUT TYPE='HIDDEN' NAME='user_id' value='" + resultSet.getString(1) + "'>");

                out.println("<TABLE class=\"table\"  style='width:30%' align='center'>");

                out.println("<div class=\"form-group\">");
                out.println("<TR>");
                out.println("<TD>username&nbsp;</TD>");
                out.println("<TD><INPUT class=\"form-control\" TYPE='TEXT' NAME='username' value='" + resultSet.getString(2) + "'></TD>");
                out.println("</TR>");
                out.println("</div>");

                out.println("<div class=\"form-group\">");
                out.println("<TR>");
                out.println("<TD>token_id&nbsp;</TD>");
                out.println("<TD><INPUT class=\"form-control\" TYPE='TEXT' NAME='token_id' value='" + resultSet.getString(3) + "'></TD>");
                out.println("</TR>");
                out.println("</div>");

                out.println("<div class=\"form-group\">");
                out.println("<TR>");
                out.println("<TD>pc_id&nbsp;</TD>");
                out.println("<TD><INPUT class=\"form-control\" TYPE='TEXT' NAME='pc_id' value='" + resultSet.getString(5) + "'></TD>");
                out.println("</TR>");
                out.println("</div>");

                out.println("<div class=\"form-group\">");
                out.println("<TR>");
                out.println("<TD>role&nbsp;</TD>");
                out.println("<td><select class=\"form-control\" name=\"role\" size=\"1\">");

                if (Integer.parseInt(resultSet.getString(4)) == 1) {
                    out.println("<option selected value = '1'>1: ADMIN</option>");
                    out.println("<option value = '2'>2: USER</option>");
                } else {
                    out.println("<option  value = '1'>1: ADMIN</option>");
                    out.println("<option selected value = '2'>2: USER</option>");
                }
                out.println("</select></td>");
                out.println("</TR>");
                out.println("</div>");

                out.println("<div class=\"form-group\">");
                out.println("<TR>");
                out.println("<TD><INPUT TYPE='RESET' class='btn btn-danger'></TD>");
                out.println("<TD><INPUT TYPE='SUBMIT' class='btn btn-success' VALUE='Submit'>");
                out.println("<div style=\"color:red\">" + message + "</div>");
                out.println("</TD><td><a class='btn btn-default'  href='/'>Back</a> to main page</td><TR>");
                if (successfully) {
                    out.println("<tr><td></td><td><p style=\"color:red\">Edit completed</p></td></tr>");
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

    }
}
