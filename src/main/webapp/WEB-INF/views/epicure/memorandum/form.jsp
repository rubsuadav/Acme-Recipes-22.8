<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">	
	<acme:input-textbox code="epicure.memorandum.form.label.automatic-sequence-number" path="automaticSequenceNumber"/>	
	<acme:input-moment code="epicure.memorandum.form.label.instantiation-moment" path="instantiationMoment"/>
	<acme:input-textarea code="epicure.memorandum.form.label.report" path="report"/>
	<acme:input-url code="epicure.memorandum.form.label.link" path="link"/>
</acme:form>