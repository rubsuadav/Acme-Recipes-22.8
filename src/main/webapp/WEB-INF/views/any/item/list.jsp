<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="any.item.list.label.typeEntity" path="typeEntity" width="20%"/>
	<acme:list-column code="any.item.list.label.name" path="name" width="20%"/>
	<acme:list-column code="any.item.list.label.code" path="code" width="20%"/>
	<acme:list-column code="any.item.list.label.retailPrice" path="retailPrice" width="40%"/>
	
</acme:list>