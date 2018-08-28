<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
<%@ taglib prefix = "form" uri = "http://www.springframework.org/tags/form" %>

<portlet:defineObjects />

<portlet:resourceURL id="addGrade" var="addGrade">
</portlet:resourceURL>
<input type="hidden" id="addGradeUrl" value="${addGrade}"></input>

<portlet:renderURL var="return">
</portlet:renderURL>
<input type="hidden" id="returnUrl" value="${return}"></input>

<div class="container-fluid">

<%@include file="/WEB-INF/jsp/portlet/user_profile/menu.jsp" %>

		<div class="col-xs-12 col-sm-8 col-md-8">
			<h4 class="user-profile-section-title"><liferay-ui:message key="user.add.grade.label" /></h4>
			<div class="container">
				<c:set var="selectTitle">
		  			<liferay-ui:message key="choose" />
		  		</c:set>
		  	<c:if test="${fn:length(auctions) gt 0}">
				<form id="add-grade-form" method="POST">
					<div class="row col-xs-12 col-sm-8 col-md-4">
						<div class="form-group">
				           <label class="label-control" for="auctionId"><liferay-ui:message key="auction.label" /></label>
							<select class="selectpicker form-control" id="auctionId" name="auctionId" title="${selectTitle}"> 		
								<c:forEach items="${auctions}" var="item">
									<option value="${item.id}"><liferay-ui:message key="${item.name}" /></option>
								</c:forEach>
				           </select>
						</div>
						<div class="form-group">
				           <label class="label-control" for="grade"><liferay-ui:message key="auction.grade.label" /></label>
				           <input type="text" class="form-control" id="grade" name="grade"></input>
						</div>
						<div class="form-group">
				           <label class="label-control" for="comment"><liferay-ui:message key="auction.comment.label" /></label>
				           <input type="text" class="form-control" id="comment" name="comment"></input>
						</div>
					</div>
					<div class="row col-xs-12 col-sm-8 col-md-4">
						<div class="form-group">
						  	<input class="btn btn-primary pull-right" id="submit" type="submit" value="<liferay-ui:message key="submit"/> "></input>
						</div>		  		
					</div>   
		  		</form>   
		  	</c:if>
		  	<c:if test="${fn:length(auctions) eq 0}">
   				<p><liferay-ui:message key="bought.list.empty.msg" /></p>
		  	</c:if>
			</div>      
		</div>
	</div>
	
<script src="<c:url value="/js/portlet/user_profile/add-grade.js" />"></script>	 
