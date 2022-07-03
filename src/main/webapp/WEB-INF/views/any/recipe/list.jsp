<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
    <acme:list-column code="any.recipe.list.label.code" path="code" width="50%"/>
    <acme:list-column code="any.recipe.list.label.heading" path="heading" width="50%"/>
    <acme:list-payload path="payload"/>
</acme:list>