<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="inventor.patronage.form.label.code" path="code" readonly="true"/>	
	<acme:input-textarea code="inventor.patronage.form.label.legal-stuff" path="legalStuff" readonly="true"/>
	<acme:input-money code="inventor.patronage.form.label.budget" path="budget" readonly="true"/>
	<acme:input-moment code="inventor.patronage.form.label.initial" path="initial" readonly="true"/>
	<acme:input-moment code="inventor.patronage.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="inventor.patronage.form.label.end" path="end" readonly="true"/>
	<acme:input-url code="inventor.patronage.form.label.link" path="link" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.fullName" path="patronProfileFullName" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.email" path="patronProfileEmail" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.company" path="patronCompany" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.statement" path="patronStatement" readonly="true"/>
	<acme:input-textbox code="inventor.patronage.form.label.patron-link" path="patronLink" readonly="true"/>
	
	<jstl:if test="${status!='PROPOSED'}">
		<acme:input-textbox code="inventor.patronage.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status=='PROPOSED'}">
		<acme:input-select code="inventor.patronage.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
		<acme:input-option code="DENIED" value="DENIED"/>
	</acme:input-select>
	</jstl:if>
	
	<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="inventor.patronage.form.button.update" action="/inventor/patronage/update"/>
    <acme:button test="${status == 'ACCEPTED' && published == true}" code="inventor.patronage-report.list.button.create" action="/inventor/patronage-report/create?patronageId=${id}"/>
</acme:form>