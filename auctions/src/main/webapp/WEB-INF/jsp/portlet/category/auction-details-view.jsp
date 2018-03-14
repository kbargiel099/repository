<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >

<div class="container">
  	<div class="col-xs-12 col-sm-12 col-md-5">
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<a class="text-center" href="#">
					<img src="<c:url value="/images/${auction.imageName}" />" style="heigh:400px; width:400px;" />
				</a>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-12">
				<h4 class="text-center"><strong>Dane sprzedawcy</strong></h4>
				<div class="padding-left">
					<h5><strong></strong>${seller.firstname} ${seller.lastname}</h5>
					<h5><strong></strong>${seller.emailAddress}</h5>
					<h5><strong></strong>${seller.phoneNumber}</h5>
				</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div id="auction-notify" class="mygrid-wrapper-div" style=" border: solid Gray 1px;overflow: scroll;height: 200px;">
				<ul>
				</ul>
			</div>
		</div>
	</div>
	
	<div class="col-xs-12 col-sm-12 col-md-7">
  		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h4><strong>${auction.name}</strong></h4>
				<c:set var = "balance" value = "${auction.subjectPrice/100}" />
				<h5>Aktualna cena - <fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="currency"/></h5>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong>Data utworzenia</strong>  - ${auction.createDate}</h5>
				<h5><strong>Data zakończenia</strong> - ${auction.endDate}</h5>
			</div>
		</div>
		<div class="details-section row">
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong>Dostępna ilość</strong> - ${auction.subjectQuantity} sztuk</h5>
			</div>
			<div class="col-xs-12 col-sm-12 col-md-5">
				<h5><strong>Podaj ilość</strong> </h5><input  type="text" /> 
				<input type="button" class="btn btn-primary" value="Przebij"/>
			</div>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-7">
		<div class="details-section-border">
			<h5><strong>Opis przedmiotu</strong></h5>
			<h5>${auction.description}</h5>
		</div>
	</div>
	<div class="col-xs-12 col-sm-12 col-md-7">
		<div>
			<h5><strong>Dane techniczne</strong></h5>
		</div>
	</div>
	
	<div id="main-content" class="container">
    <div class="row">
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="connect">WebSocket connection:</label>
                    <button id="connect" class="btn btn-default" type="submit">Connect</button>
                    <button id="disconnect" class="btn btn-default" type="submit" disabled="disabled">Disconnect
                    </button>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <form class="form-inline">
                <div class="form-group">
                    <label for="name">What is your name?</label>
                    <input type="text" id="name" class="form-control" placeholder="Your name here...">
                </div>
                <button id="send" class="btn btn-default" type="submit">Send</button>
            </form>
        </div>
    </div>
</div>

<script src="<c:url value="/js/module/sockjs.min.js" />"></script>
<script src="<c:url value="/js/module/stomp.min.js" />"></script>
<script src="<c:url value="/js/module/app.js" />"></script>