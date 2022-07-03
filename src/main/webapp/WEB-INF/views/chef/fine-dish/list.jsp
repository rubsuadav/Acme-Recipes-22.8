<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.fine-dish.list.label.status" path="status" width="20%"/>
	<acme:list-column code="chef.fine-dish.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="chef.fine-dish.list.label.budget" path="budget" width="20%"/>
	<acme:list-column code="chef.fine-dish.list.label.creation" path="creation" width="40%"/>
	
</acme:list>