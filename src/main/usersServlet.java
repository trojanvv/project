package main;

import main.DBwork.DBclass;
import main.service.ConstHTML;

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
@WebServlet(name = "usersServlet")
public class usersServlet extends HttpServlet {
    private String username = null;
    private boolean access = false;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            sendPage(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void sendPage(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, SQLException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String sql = null;
        String sql2 = "SELECT id,username FROM public.users_info ORDER BY id;";
        ResultSet rs2 = new DBclass().query(sql2);

        try {
            ConstHTML.head("User information",response);
            out.println("<form class=\"form-inline\" method = 'get'> <div class=\"form-group\">");
            out.println("<label for=\"exampleSelect1\">Выберите пользователя</label>");
            out.println("<select class=\"form-control\" id=\"exampleSelect2\" name='username'>");
            while (rs2.next()) {
                out.println("<option value = '" + rs2.getString(1) + "'>" + rs2.getString(2) + "</option>");
            }
            out.println("</select></div><div class=\"form-group\">");
            out.println("<input class=\"btn btn-large btn-primary \"  type='submit'> ");
            out.println("<a class=\"btn btn-large btn-primary \"  href='/'>&nbspBack</a> to main page</div>");
            out.println("</div></form>");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        username = request.getParameter("username");
        if (username != null) {
            sql = "SELECT * FROM public.users_info WHERE id='" + username + "';";
            ResultSet rs = new DBclass().query(sql);

            while (rs.next()) {
                out.println("<BR><div align=\"center\"><H2>User information</H2>");
                out.println("<TABLE class=\"table\"  style='width:15%' align='center'>");
                out.println("<TR>");
                out.println("<TD><b>id</b>&nbsp;</TD>");
                out.println("<TD>" + rs.getString(1) + "</TD>");
                out.println("</TR>");

                out.println("<TR>");
                out.println("<TD><b>username</b>&nbsp;</TD>");
                out.println("<TD>" + rs.getString(2) + "</TD>");
                out.println("</TR>");

                out.println("<TR>");
                out.println("<TD><b>token_id</b>&nbsp;</TD>");
                out.println("<TD>" + rs.getString(3) + "</TD>");
                out.println("</TR>");

                out.println("<TR>");
                out.println("<TD><b>pc_id</b>&nbsp;</TD>");
                out.println("<TD>" + rs.getString(5) + "</TD>");
                out.println("</TR>");

                out.println("<TR>");
                out.println("<TD><b>role</b>&nbsp;</TD>");
                out.println("<td>" + rs.getString(4) + "</td>");
                out.println("</TR>");

                out.println("<TR>");
                out.println("<td></td><td>");
                out.println("<form align='right' action='/edit' method='get'>");
                out.println("<input type='hidden' name='user_id' value='" +  rs.getString(1) + "'>");
                out.println("<input type='SUBMIT' VALUE='Edit' class=\"btn btn-large btn-primary \"  >");
                out.println("</td></TR>");
                out.println("</form>");
                out.println("</TABLE>");

                out.println("</div>");
            }
        }

    }



}
