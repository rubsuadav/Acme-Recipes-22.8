<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.fine-dish.form.label.code" path="code" readonly="true"/>	
	<acme:input-textarea code="chef.fine-dish.form.label.request" path="request" readonly="true"/>
	<acme:input-money code="chef.fine-dish.form.label.budget" path="budget" readonly="true"/>
	<acme:input-money code="chef.item.form.label.conversion" path="conversion" readonly="true"/>
	<acme:input-moment code="chef.fine-dish.form.label.initial" path="initial" readonly="true"/>
	<acme:input-moment code="chef.fine-dish.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="chef.fine-dish.form.label.end" path="end" readonly="true"/>
	<acme:input-url code="chef.fine-dish.form.label.link" path="link" readonly="true"/>
	<acme:input-textbox code="chef.fine-dish.form.label.fullName" path="epicureProfileFullName" readonly="true"/>
	<acme:input-textbox code="chef.fine-dish.form.label.email" path="epicureProfileEmail" readonly="true"/>
	<acme:input-textbox code="chef.fine-dish.form.label.company" path="epicureCompany" readonly="true"/>
	<acme:input-textbox code="chef.fine-dish.form.label.statement" path="epicureStatement" readonly="true"/>
	<acme:input-textbox code="chef.fine-dish.form.label.epicure-link" path="epicureLink" readonly="true"/>
	
	<jstl:if test="${status!='PROPOSED'}">
		<acme:input-textbox code="chef.fine-dish.form.label.status" path="status" readonly="true"/>
	</jstl:if>
	
	<jstl:if test="${status=='PROPOSED'}">
		<acme:input-select code="chef.fine-dish.form.label.status" path="status">
		<acme:input-option code="PROPOSED" value="PROPOSED" selected="true"/>
		<acme:input-option code="ACCEPTED" value="ACCEPTED"/>
		<acme:input-option code="DENIED" value="DENIED"/>
	</acme:input-select>
	</jstl:if>
	
	<acme:submit test="${acme:anyOf(command, 'show, update') && status == 'PROPOSED'}" code="chef.fine-dish.form.button.update" action="/chef/fine-dish/update"/>
    <acme:button test="${(status == 'ACCEPTED' || status == 'DENIED') && published == true}" code="chef.fine-dish-report.list.button.create" action="/chef/memorandum/create?fine-dishId=${id}"/>
</acme:form>