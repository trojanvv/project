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
        out.println("<script type=\"text/javascript\">\n" +
                "        function clock() {\n" +
                "            var d = new Date();\n" +
                "            var month_num = d.getMonth()\n" +
                "            var day = d.getDate();\n" +
                "            var hours = d.getHours();\n" +
                "            var minutes = d.getMinutes();\n" +
                "            var seconds = d.getSeconds();\n" +
                "\n" +
                "            month=new Array(\"января\", \"февраля\", \"марта\", \"апреля\", \"мая\", \"июня\",\n" +
                "                \"июля\", \"августа\", \"сентября\", \"октября\", \"ноября\", \"декабря\");\n" +
                "\n" +
                "            if (day <= 9) day = \"0\" + day;\n" +
                "            if (hours <= 9) hours = \"0\" + hours;\n" +
                "            if (minutes <= 9) minutes = \"0\" + minutes;\n" +
                "            if (seconds <= 9) seconds = \"0\" + seconds;\n" +
                "\n" +
                "            date_time = \"Сегодня - \" + day + \" \" + month[month_num] + \" \" + d.getFullYear() +\n" +
                "                \" г.&nbsp;&nbsp;&nbsp;Текущее время - \"+ hours + \":\" + minutes + \":\" + seconds;\n" +
                "            if (document.layers) {\n" +
                "                document.layers.doc_time.document.write(date_time);\n" +
                "                document.layers.doc_time.document.close();\n" +
                "            }\n" +
                "            else document.getElementById(\"doc_time\").innerHTML = date_time;\n" +
                "            setTimeout(\"clock()\", 1000);\n" +
                "        }\n" +
                "    </script>\n" +
                "\n" +
                "    <style type=\"text/css\">\n" +
                "      #layer1 {\n" +
                "        position: fixed;\n" +
                "        top:16px;\n" +
                "        right:15px;\n" +
                "        color: black;\n" +
                "      }\n" +
                "    </style>");

        out.println("</head>");
        out.println("<body>");
        out.println("<nav class=\"navbar navbar-default\">\n" +
                "    <div class=\"container\">\n" +
                "      <!-- Brand and toggle get grouped for better mobile display -->\n" +
                "      <div class=\"navbar-header\">\n" +
                "        <a class=\"navbar-brand\" href=\"/\">АРМ - администратора</a>\n" +
                "      </div>\n" +
                "\n" +
                "        <div id=\"layer1\">\n" +
                "          <span id=\"doc_time\">  </span>\n" +
                "          <script type=\"text/javascript\">\n" +
                "              clock();\n" +
                "          </script>\n" +
                "        </div>\n" +
                "      </div><!-- /.navbar-collapse -->\n" +
                "    </div><!-- /.container -->\n" +
                "  </nav><!-- /.navbar -->" +
                "<div class=\"container\">");
    }


    public static void footer( HttpServletResponse response) throws IOException {
        out = response.getWriter();

        out.println("<hr>\n" +
                "\n" +
                "    <footer>\n" +
                "      <p>© TROJAN 2017</p>\n" +
                "    </footer>\n" +
                "\n" +
                "  </div><!--/.container-->\n" +
                "\n" +
                "\n" +
                "  </body>\n" +
                "</html>" +
                "</div>");
    }




}
