<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.pimpam.list.label.code" path="code" width="20%"/>	
	<acme:list-column code="chef.pimpam.list.label.instantiation-moment" path="instantiationMoment" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.title" path="title" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.budget" path="budget" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.item" path="itemId" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="chef.pimpam.list.button.create" action="/chef/pimpam/create?masterId=${masterId}"/>