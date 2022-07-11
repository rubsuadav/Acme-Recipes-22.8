<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="epicure.memorandum.list.label.instantiation-moment" path="instantiationMoment" width="50%"/>
	<acme:list-column code="epicure.memorandum.list.label.fine-dish" path="fineDishId" width="50%"/>
	
</acme:list>