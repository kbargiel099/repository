<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:renderURL var="stats">
	<portlet:param name="page" value="stats"/>
</portlet:renderURL>
<input type="hidden" id="statsUrl" value="${stats}"/>

<div class="container-fluid">

	<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>
	<div class="col-xs-12 col-sm-8 col-md-8">
		<h4 class="user-profile-section-title"><liferay-ui:message key="user.sold.label" /></h4>
	</div>
	<div class="col-xs-12 col-sm-8 col-md-8">
		<div id="elements">
			<c:forEach items="${auctions}" var="item">
			
			<div class="category-view-auction row">
				<div class="col-xs-12 col-sm-4 col-md-4">
					<div style="position: relative;">
						<a href="#">
							<img class="image image-120 img-center" src="/images/${item.imageName}" />
						</a>
					</div>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<strong><h4>${item.name}</h4></strong>
					<c:set var = "balance" value = "${item.subjectPrice/100}" />
					<h4>
						<liferay-ui:message key="price" /> - 
						<fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${balance}" type="number"/> <liferay-ui:message key="currency" />
					</h4> 
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<input type="hidden" name="id" value="${item.id}" />
					<button class="btn btn-info" onclick="showDetails(this)" >
						<strong><liferay-ui:message key="statistics" /></strong>
					</button>	
				</div>
			</div>
			</c:forEach>
			<c:if test="${fn:length(auctions) eq 0}">
   				<p><liferay-ui:message key="empty.list.msg" /></p>
			</c:if>
		</div>
	</div>
</div>

<script>

 	function showDetails(obj){
 		var url = jQuery('#statsUrl').val();
 		var id = jQuery(obj).parent().find('input').val();
 		location.href = buildUrl(url,'auctionId',id);
 	}
</script>