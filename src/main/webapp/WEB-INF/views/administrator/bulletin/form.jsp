<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-moment code="administrator.bulletin.form.label.instantiationMoment" path="instantiationMoment" readonly = "true"/>	
	<acme:input-textbox code="administrator.bulletin.form.label.heading" path="heading"/>
	<acme:input-textarea code="administrator.bulletin.form.label.pieceOfText" path="pieceOfText"/>
	<acme:input-textbox code="administrator.bulletin.form.label.criticalFlag" path="criticalFlag"/>
	<acme:input-url code="administrator.bulletin.form.label.link" path="link"/>
	
	<acme:input-checkbox code="administrator.bulletin.form.label.confirmation" path="confirmation"/>
	<acme:submit code="administrator.bulletin.form.button.create" action="/administrator/bulletin/create"/>
	
</acme:form>