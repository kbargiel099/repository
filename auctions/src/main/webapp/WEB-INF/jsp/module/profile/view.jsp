<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/horizontal-menu.css" />" >
<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="details">
	<portlet:param name="page" value="auctionDetails"/>
</portlet:renderURL>
<input type="hidden" id="detailsUrl" value="${details}"/>

<div class="container">
	<div class="col-xs-12" style="text-align: center;">
		<div style="margin-bottom: 30px;">
			<label style="font-size: 20px; color: #68676c; font-weight: bold;" class="label-control"><liferay-ui:message key="seller.details" /></label>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6">
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="users_management.firstname.label" /></label>
	           <p>${user.firstname}</p>
			</div>
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="users_management.login.label" /></label>
	           <p>${user.username}</p>
			</div>
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="user.account.create.date" /></label>
	           <p>${user.createDate}</p>
			</div>
		</div>
		<div class="col-xs-12 col-sm-12 col-md-6">
			<div class="form-group">
	           <label class="label-control"><liferay-ui:message key="users_management.lastname.label" /></label>
	           <p>${user.lastname}</p>
			</div>
			<div class="form-group">
	           <label class="label-control" name="endDate"><liferay-ui:message key="user.email.address" /></label>
	           <p>${user.emailAddress}</p>
			</div>
	 			<div class="form-group">
	           <label class="label-control" for="subjectPrice"><liferay-ui:message key="user.last.login.date" /></label>
			   <p>${user.loginDate}</p>
			</div>
		</div>
	</div>
	<div class="col-xs-12">
		<div id="elements">
			<div style="text-align: center; margin-bottom: 30px;">
				<label style="font-size: 20px; color: #68676c; font-weight: bold;" class="label-control"> <liferay-ui:message key="see.user.auctions.label"/> ${user.username}</label>
			</div>
			<c:forEach items="${auctions}" var="item">
			
			<div class="category-view-auction row">
				<div class="col-xs-12 col-sm-12 col-md-4">
					<div style="position: relative;">
						<a href="#">
							<img class="image image-120 img-center" src="/images/${item.imageName}" />
						</a>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<strong>${item.name}</strong>
					<br><c:set var = "balance" value = "${item.subjectPrice/100}" />
					<liferay-ui:message key="price" /> - 
					<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> <liferay-ui:message key="currency" />
					<br><liferay-ui:message key="create.date" /> <p>${item.createDate}</p>
					<br><liferay-ui:message key="end.date" /> <p>${item.endDate}</p>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<input type="hidden" name="id" value="${item.id}" />
					<button class="btn btn-info" onclick="showDetails(this)" >
						<strong><liferay-ui:message key="auction.details" /></strong>
					</button>	
				</div>
			</div>
			</c:forEach>
			<c:if test="${fn:length(auctions) eq 0}">
   				<p style="text-align: center;"><liferay-ui:message key="empty.list.msg" /></p>
			</c:if>
		</div>
	</div>
	<div class="col-xs-12">
		<div style="text-align: center; margin-bottom: 30px;">
			<label style="font-size: 20px; color: #68676c; font-weight: bold;" class="label-control"><liferay-ui:message key="users.grades.label"/></label>
		</div>
	  	<c:forEach items="${grades}" var="item">
	  		<div style="margin-bottom:20px;" class="col-xs-12">
		  		<div>
		  			<div style="min-height:50px; padding-left:20px; padding-top:5px;">
						<strong>
							<label style="font-size: 24px; color:white;" class="label-control">${item.grade}</label>
						</strong>
					</div>
				</div>
				<div style="margin-top:20px;">
					<div style="margin-bottom:10px;" class="col-xs-12 col-sm-8-col-md-8">
						<div>
							<b>${item.comment}</b>
						</div>
						<div class="pull-right">
							<p>${item.screenname}</p><br>
							<p>${item.createDate}</p>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		<c:if test="${fn:length(grades) eq 0}">
   			<p style="text-align: center;"><liferay-ui:message key="empty.grades.list.msg" /></p>
		</c:if>
	</div>
</div>

<script>
 	function showDetails(obj){
 		var url = jQuery('#detailsUrl').val();
 		var id = jQuery(obj).parent().find('input[name="id"]').val();
 		window.location.href = buildUrl(url,'id',id);
 	}
</script>