<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-moment code="authenticated.announcemeent.form.label.creationMoment" path="creationMoment"/>	
	<acme:input-textbox code="authenticated.announcemeent.form.label.title" path="title"/>
	<acme:input-textarea code="authenticated.announcemeent.form.label.body" path="body"/>
	<acme:input-textbox code="authenticated.announcemeent.form.label.criticalFlag" path="criticalFlag"/>
	<acme:input-url code="authenticated.announcemeent.form.label.link" path="link"/>
	
</acme:form>