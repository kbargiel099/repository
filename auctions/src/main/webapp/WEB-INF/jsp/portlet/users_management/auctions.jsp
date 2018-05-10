<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>

<portlet:defineObjects />

<link rel="stylesheet" type="text/css" href="<c:url value="/css/common/image.css" />" >

<portlet:resourceURL id="getAuctions" var="getAuctions" />
<input type="hidden" id="getAuctionsUrl" value="${getAuctions}"></input>

<portlet:renderURL var="add">
	<portlet:param name="page" value="add"/>
</portlet:renderURL>

<portlet:renderURL var="details">
	<portlet:param name="page" value="details"/>
</portlet:renderURL>
<input type="hidden" id="detailsUrl" value="${details}"/>

<portlet:renderURL var="edit">
	<portlet:param name="page" value="edit"/>
</portlet:renderURL>
<input type="hidden" id="editUrl" value="${edit}"/>

<portlet:resourceURL id="delete" var="delete">
</portlet:resourceURL>
<input type="hidden" id="deleteUrl" value="${delete}"/>

<div class="container">

	<%@include file="/WEB-INF/jsp/portlet/users_management/menu.jsp" %>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<h4 class="user-profile-section-title"><liferay-ui:message key="adm.auctions.label" /></h4>
	</div>
	
	<div class="col-xs-12 col-sm-8 col-md-9">
		<a class="btn btn-primary btn-sm" href="${add}">
			<liferay-ui:message key="add" />
		</a>
		<table id="auctions" class="display">
		     <thead>
		            <tr>
		                <th>Id</th>
						<th>Nazwa</th>
						<th>Data utworzenia</th>
		                <th></th>
		                <th>Opcje</th>
		            </tr>
		        </thead>
		        <tfoot>
		            <tr>
		                <th>Id</th>
						<th>Nazwa</th>
						<th>Data utworzenia</th>
		                <th></th>
		                <th>Opcje</th>
		            </tr>
		        </tfoot>
		</table>
	</div>
</div>

<script src="<c:url value="/js/portlet/users_management/datatable.js"/>" /></script>
<script type="text/javascript" >
	jQuery(document).ready(function(){
		initAuctions(jQuery("#getAuctionsUrl").val());
	});
</script>