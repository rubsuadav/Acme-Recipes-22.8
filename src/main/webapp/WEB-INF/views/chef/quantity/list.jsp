<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.item.list.label.number" path="number" width="20%"/>	
	<acme:list-column code="chef.item.list.label.item-type-entity" path="item.typeEntity" width="20%"/>
	<acme:list-column code="chef.item.list.label.item-name" path="item.name" width="20%"/>
	<acme:list-column code="chef.item.list.label.item-code" path="item.code" width="20%"/>
	<acme:list-column code="chef.item.list.label.item-retailPrice" path="item.retailPrice" width="20%"/>
</acme:list>

<acme:button test="${showCreate}" code="chef.item-recipe.list.button.create" action="/chef/quantity/create?masterId=${masterId}"/>