<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<portlet:renderURL var="getBoughtRender">
	<portlet:param name="page" value="getBought"/>
</portlet:renderURL>
<portlet:renderURL var="getSoldRender">
	<portlet:param name="page" value="getSold"/>
</portlet:renderURL>
<portlet:renderURL var="mySettingsRender">
	<portlet:param name="page" value="mySettings"/>
</portlet:renderURL>

<input type="hidden" id="getBoughtUrl" value="${getBoughtRender}"></input>
<input type="hidden" id="getSoldUrl" value="${getSoldRender}"></input>
<input type="hidden" id="mySettingsUrl" value="${mySettingsRender}"></input>


<div class="container-fluid">

	<div class="col-xs-12 col-sm-8 col-md-3">
		<div class="btn-group btn-group-vertical">
		 	<a href="${getBoughtRender}" class="btn btn-primary">Zakupione</a>
		 	<a href="${getSoldRender}" class="btn btn-primary">Sprzedane</a>
		 	<a href="${mySettingsRender}" class="btn btn-primary">Moje ustawienia</a>
		</div>
	</div>
	
	<div class="col-xs-12 col-sm-8 col-md-8">
		This is the <b>User Profile</b> portlet in View mode.
		${exist}
	</div>
</div>
