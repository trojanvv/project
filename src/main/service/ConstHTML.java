package main.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by virus on 08.05.2017.
 */
public class ConstHTML {

    static PrintWriter out;
    public static void head(String title, HttpServletResponse response) throws IOException {
        out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<title>" + title + "</title>");
        out.println("<link href=\"bootstrap.css\" rel=\"stylesheet\" media=\"screen\">");
        out.println("</head>");
        out.println("<body>");
    }





}
