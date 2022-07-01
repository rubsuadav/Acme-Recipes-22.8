<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronage.list.label.status" path="status" width="20%"/>
	<acme:list-column code="patron.patronage.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="patron.patronage.list.label.budget" path="budget" width="20%"/>
	<acme:list-column code="patron.patronage.list.label.creation" path="creation" width="40%"/>
	
</acme:list>

<jstl:if test="${command == 'list-mine'}">
	<acme:button code="patron.patronage.list.button.create" action="/patron/patronage/create"/>
</jstl:if>