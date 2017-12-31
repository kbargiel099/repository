<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="portlet" uri="http://java.sun.com/portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />


<nav class="navbar navbar-default" role="navigation">

  <div class="container-fluid">

    <!-- Grupowanie "marki" i przycisku rozwijania mobilnego menu -->

    <div class="navbar-header">

      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">

        <span class="sr-only">Rozwiń nawigację</span>

        <span class="icon-bar"></span>

        <span class="icon-bar"></span>

        <span class="icon-bar"></span>

      </button>

      <a class="navbar-brand" href="#">"Marka - Logo strony"</a>

    </div>



    <!-- Grupowanie elementów menu w celu lepszego wyświetlania na urządzeniach moblinych -->

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

      <ul class="nav navbar-nav">

        <li class="active"><a href="#">Link</a></li>

        <li><a href="#">Link</a></li>

        <li class="dropdown">

          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Lista rozwijana <span class="caret"></span></a>

          <ul class="dropdown-menu" role="menu">

            <li><a href="#">Akcja</a></li>

            <li><a href="#">Inna akcja</a></li>

            <li><a href="#">Coś jeszcze innego</a></li>

            <li class="divider"></li>

            <li><a href="#">Oddzielone linki</a></li>

            <li class="divider"></li>

            <li><a href="#">Jeszcze jeden oddzielony link</a></li>

          </ul>

        </li>

      </ul>

      <form class="navbar-form navbar-left" role="search">

        <div class="form-group">

          <input type="text" class="form-control" placeholder="Szukaj">

        </div>

        <button type="submit" class="btn btn-default">Wyślij</button>

      </form>

      <ul class="nav navbar-nav navbar-right">

        <li><a href="#">Link</a></li>

        <li class="dropdown">

          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Rozwijana lista <span class="caret"></span></a>

          <ul class="dropdown-menu" role="menu">

            <li><a href="#">Akcja</a></li>

            <li><a href="#">Inna akcja</a></li>

            <li><a href="#">Coś jeszcze innego</a></li>

            <li class="divider"></li>

            <li><a href="#">Oddzielone linki</a></li>

            <li class="divider"></li>

            <li><a href="#">Jeszcze jeden oddzielony link</a></li>

          </ul>

        </li>

      </ul>

    </div><!-- /.navbar-collapse -->

  </div><!-- /.container-fluid -->

</nav>

