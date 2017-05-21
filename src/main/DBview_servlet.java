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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by virus on 16.04.2017.
 */
@WebServlet(name = "DBview_servlet")
public class DBview_servlet extends HttpServlet {
    private String username = null;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sql;
        String sql2 = "SELECT id,username FROM public.users_info ORDER BY id;";
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        ResultSet rs2 = new DBclass().query(sql2);

        try {
            ConstHTML.head("Страница просмотра сессий",response);

            out.println("<form class=\"form-inline\" method = 'get'><div class=\"form-group\">");
            out.println("<label for=\"exampleSelect1\">Выберите пользователя</label>");

            out.println("<select class=\"form-control\" id=\"exampleSelect2\" name='username'>");
            out.println("<option value = 'all'>ALL</option>");
            while (rs2.next()) {
                out.println("<option value = '" + rs2.getString(1) + "'>" + rs2.getString(2) + "</option>");
            }
            out.println("</select></div>");
            out.println("<div class=\"form-group\"><input class=\"btn btn-primary\" type='submit'> <a class=\"btn btn-large btn-primary \"     href='/'>&nbspBack</a> to main page</div>");
            out.println("</br></br>");
            out.println("</div></form>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        username = request.getParameter("username");
        if (username == null) username = "all";

        if(username.equals("all")) {
             sql = "SELECT users_session.id, users_session.user_id, users_info.username,users_session.pc_id, users_session.time_in, users_session.time_out FROM  public.users_session, public.users_info WHERE users_session.user_id = users_info.id ORDER BY users_session.id DESC;";
        } else {
            sql = "SELECT users_session.id, users_session.user_id, users_info.username,users_session.pc_id, users_session.time_in, users_session.time_out FROM public.users_session, public.users_info WHERE user_id='" + username + "' AND users_session.user_id = users_info.id ORDER BY users_session.id DESC;";
        }

            ResultSet rs = new DBclass().query(sql);

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            out.println("<table class=\"table table-striped\"  style='width:60%' align='center' ");
            out.println("<tr>");
            for (int i = 1; i <=rsmd.getColumnCount(); i++){
                out.println("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.println("<th>Session time</th>");
            out.println("</tr>");
            while (rs.next()){
                out.println("<tr>");
                for (int i = 1; i <=rsmd.getColumnCount(); i++){
                    if (i == 3) {
                        out.println("<td><a href='/user?username=" + rs.getString(2) + "'>" + rs.getString(i) + "</a></td>");
                    } else if (i == 4){
                        out.println("<td><a href='/pc?pc_id=" + rs.getString(i) + "'>" + rs.getString(i) + "</a></td>");
                    } else{
                        out.println("<td>" + rs.getString(i) + "</td>");
                    }
                }
                out.println("<td>" + sessTime(rs.getString(5),rs.getString(6)) + "</td>");
                out.println("</tr>");

            }

            out.println("</table>");

            ConstHTML.footer(response);

            logFile.write(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private String sessTime(String in,String out) throws ParseException {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date inTime = formatForDateNow.parse(in);
        Date outTime = formatForDateNow.parse(out);
        long someLongInt = (outTime.getTime() - inTime.getTime())/1000;

        Long hours = someLongInt / 3600;
        Long minutes = (someLongInt % 3600) / 60;
            String min = null;
            if (minutes < 10)  min = "0" + minutes; else min = "" + minutes;
        Long seconds = someLongInt % 60;
        return  "0" + hours + ":" + min + ":" + seconds;
    }
}
