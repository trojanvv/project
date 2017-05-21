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
    <link href="bootstrap.css" rel="stylesheet" media="screen">
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
        top:16px;
        right:15px;
        color: black;
      }
    </style>
  </head>
  <body>

  <!-- Fixed navbar -->
  <!-- Second navbar for sign in -->
  <nav class="navbar navbar-default">
    <div class="container">
      <!-- Brand and toggle get grouped for better mobile display -->
      <div class="navbar-header">
        <a class="navbar-brand" href="/">Cистема автоматизированного контроля доступа</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="navbar-collapse-2">
        <ul class="nav navbar-nav navbar-right">

        </ul>
        <div id="layer1">
          <span id="doc_time">  </span>
          <script type="text/javascript">
              clock();
          </script>
        </div>
      </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
  </nav><!-- /.navbar -->

  <div class="container">

    <div class="row row-offcanvas row-offcanvas-right">



      <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
        <div class="list-group">
          <a href="/view" class="list-group-item active">Информация по входам</a>
          <a href="/user" class="list-group-item">Информация по пользователям</a>
          <a href="/pc" class="list-group-item">Информация по компьютерам</a>
          <a href="/registration" class="list-group-item">Регистрация нового пользователя</a>
          <a href="/addpc" class="list-group-item">Регистрация новго компьютера</a>
        </div>
      </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->



    <hr>

    <footer>
      <p>© TROJAN 2017</p>
    </footer>

  </div><!--/.container-->


  </body>
</html>
