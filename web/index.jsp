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
        top:10px;
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
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-2">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
      </div>

      <!-- Collect the nav links, forms, and other content for toggling -->
      <div class="collapse navbar-collapse" id="navbar-collapse-2">
        <ul class="nav navbar-nav navbar-right">
          <li><a href="#">Home</a></li>
          <li><a href="#">About</a></li>
          <li><a href="#">Services</a></li>
          <li><a href="#">Works</a></li>
          <li><a href="#">News</a></li>
          <li><a href="#">Contact</a></li>
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

      <div class="col-xs-12 col-sm-9">
        <p class="pull-right visible-xs">
          <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
        </p>
        <div class="jumbotron">
          <h1>Hello, world!</h1>
          <p>This is an example to show the potential of an offcanvas layout pattern in Bootstrap. Try some responsive-range viewport sizes to see it in action.</p>
        </div>
        <div class="row">
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
          <div class="col-xs-6 col-lg-4">
            <h2>Heading</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-default" href="#" role="button">View details »</a></p>
          </div><!--/.col-xs-6.col-lg-4-->
        </div><!--/row-->
      </div><!--/.col-xs-12.col-sm-9-->

      <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
        <div class="list-group">
          <a href="#" class="list-group-item active">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
          <a href="#" class="list-group-item">Link</a>
        </div>
      </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>

    <footer>
      <p>© Company 2014</p>
    </footer>

  </div><!--/.container-->


  </body>
</html>
