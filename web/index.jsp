<%--
  Created by IntelliJ IDEA.
  User: virus
  Date: 02.04.2017
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Cистема автоматизированного контроля доступа</title>
    <script type="text/javascript">
        function clock() {
            var d = new Date();
            var month_num = d.getMonth()
            var day = d.getDate();
            var hours = d.getHours();
            var minutes = d.getMinutes();
            var seconds = d.getSeconds();

            month=new Array("января", "февраля", "марта", "апреля", "мая", "июня",
                "июля", "августа", "сентября", "октября", "ноября", "декабря");

            if (day <= 9) day = "0" + day;
            if (hours <= 9) hours = "0" + hours;
            if (minutes <= 9) minutes = "0" + minutes;
            if (seconds <= 9) seconds = "0" + seconds;

            date_time = "Сегодня - " + day + " " + month[month_num] + " " + d.getFullYear() +
                " г.&nbsp;&nbsp;&nbsp;Текущее время - "+ hours + ":" + minutes + ":" + seconds;
            if (document.layers) {
                document.layers.doc_time.document.write(date_time);
                document.layers.doc_time.document.close();
            }
            else document.getElementById("doc_time").innerHTML = date_time;
            setTimeout("clock()", 1000);
        }
    </script>

    <style type="text/css">
      #layer1 {
        position: fixed;
        top:10px;
        right:15px;
      }
    </style>
  </head>
  <body>
  <div id="layer1">
    <span id="doc_time">  </span>
    <script type="text/javascript">
        clock();
    </script>
  </div>
  <br>
  <h1 style="text-align: center;">Cистема автоматизированного контроля доступа</h1>
  <p>&nbsp;</p>
  <p>&nbsp;</p>

  <form style="align-text: center" action="/view" method="get">
    <input type="submit" value="User session">
  </form>
  <form style="align-text: center" action="/user" method="get">
    <input type="submit" value="User info">
  </form>
  <form style="align-text: center" action="/pc" method="get">
    <input type="submit" value="PC session">
  </form>
  <form style="align-text: center" action="/registration" method="get">
    <input type="submit" value="Registration New User">
  </form>
  <form style="align-text: center" action="/addpc" method="get">
    <input type="submit" value="Registration New PC">
  </form>
  </body>
</html>
