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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by virus on 11.05.2017.
 */
@WebServlet(name = "pcServlet")
public class pcServlet extends HttpServlet {
    private String pc_id = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String sql;
        String sql2 = "SELECT * FROM public.pc_info ;";
        PrintWriter out = response.getWriter();

        ResultSet rs2 = new DBclass().query(sql2);

        try {
            ConstHTML.head("PC session", response);

            out.println("<form class=\"form-inline\" method = 'get'><div class=\"form-group\">");
            out.println("<label for=\"exampleSelect1\">Выберите компьютер</label>");
            out.println("<select class=\"form-control\" id=\"exampleSelect2\" name='pc_id'>");
            out.println("<option value = 'all'>ALL</option>");
            while (rs2.next()) {
                out.println("<option value = '" + rs2.getString(1) + "'> pc_id: " + rs2.getString(1) + " name: " + rs2.getString(2) + " locate: " + rs2.getString(3) + "</option>");
            }
            out.println("</select></div>");
            out.println("<div class=\"form-group\"><input class=\"btn btn-primary\" type='submit'> <a class=\"btn btn-large btn-primary \"  href='/'>&nbspBack</a> to main page");
            out.println("</br></br>");
            out.println("</div></form>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        pc_id = request.getParameter("pc_id");
        if (pc_id == null) pc_id = "all";

        if (pc_id.equals("all")) {
            sql = "SELECT \n" +
                    "  pc_info.pc_id, \n" +
                    "  pc_info.name, \n" +
                    "  users_session.user_id, \n" +
                    "  users_session.time_in, \n" +
                    "  users_info.username\n" +
                    "FROM \n" +
                    "  public.pc_info, \n" +
                    "  public.users_session, \n" +
                    "  public.users_info\n" +
                    "WHERE \n" +
                    "  pc_info.pc_id = users_session.pc_id AND\n" +
                    "  users_info.id = users_session.user_id\n" +
                    "ORDER BY users_session.time_in DESC ;\n";
        } else {
            sql = "SELECT \n" +
                    "  pc_info.pc_id, \n" +
                    "  pc_info.name, \n" +
                    "  users_session.user_id, \n" +
                    "  users_session.time_in, \n" +
                    "  users_info.username\n" +
                    "FROM \n" +
                    "  public.pc_info, \n" +
                    "  public.users_session, \n" +
                    "  public.users_info\n" +
                    "WHERE \n" +
                    "  pc_info.pc_id = users_session.pc_id AND\n" +
                    "  users_info.id = users_session.user_id AND\n" +
                    "  pc_info.pc_id =" + pc_id +
                    "  ORDER BY users_session.time_in DESC ;\n";
        }

        ResultSet rs = new DBclass().query(sql);

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            out.println("<table class=\"table table-striped\" style='width:50%' align='center'>");
            out.println("<tr>");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                out.println("<td>" + rsmd.getColumnName(i) + "</td>");
            }
            out.println("</tr>");
            while (rs.next()) {
                out.println("<tr>");
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    if (i == 3) {
                        out.println("<td><a href='/user?username=" + rs.getString(3) + "'>" + rs.getString(i) + "</a></td>");
                    } else {
                        out.println("<td>" + rs.getString(i) + "</td>");
                    }
                }
                out.println("</tr>");

            }

            out.println("</table>");
            out.println("</body></html>");
            logFile.write(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}