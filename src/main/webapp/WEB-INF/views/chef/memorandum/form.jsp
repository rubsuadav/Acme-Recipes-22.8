<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:hidden-data path="fineDishId"/>
	
	<acme:input-textbox code="chef.memorandum.form.label.automatic-sequence-number" path="automaticSequenceNumber" readonly="true"/>	
	<acme:input-moment code="chef.memorandum.form.label.instantiation-moment" path="instantiationMoment" readonly = "true"/>
	<acme:input-textarea code="chef.memorandum.form.label.report" path="report"/>
	<acme:input-url code="chef.memorandum.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="chef.memorandum.form.label.confirmation" path="confirmation"/>
	</jstl:if>
	
	<acme:submit test="${command == 'create'  && (status == 'ACCEPTED' || status == 'DENIED')}" code="chef.memorandum.list.button.create" action="/chef/memorandum/create?fineDishId=${fineDishId}"/>	
			
</acme:form>