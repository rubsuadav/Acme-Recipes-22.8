<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:hidden-data path="patronageId"/>
	
	<acme:input-textbox code="inventor.patronage-report.form.label.automatic-sequence-number" path="automaticSequenceNumber" readonly="true"/>	
	<acme:input-moment code="inventor.patronage-report.form.label.creation" path="creation" readonly = "true"/>
	<acme:input-textarea code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:input-checkbox code="inventor.patronage-report.form.label.confirmation" path="confirmation"/>
	</jstl:if>
	
	<acme:submit test="${command == 'create'  && status == 'ACCEPTED'}" code="inventor.patronage-report.list.button.create" action="/inventor/patronage-report/create?patronageId=${patronageId}"/>	
			
</acme:form>