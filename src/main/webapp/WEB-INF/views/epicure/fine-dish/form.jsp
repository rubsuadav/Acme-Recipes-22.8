<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create' }">
	<acme:input-textbox code="epicure.fine-dish.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>	
	<acme:input-textarea code="epicure.fine-dish.form.label.request" path="request"/>
	<acme:input-money code="epicure.fine-dish.form.label.budget" path="budget"/>
	<acme:input-money code="chef.item.form.label.conversion" path="conversion" readonly="true"/>
	<acme:input-moment code="epicure.fine-dish.form.label.initial" path="initial"/>
	<acme:input-moment code="epicure.fine-dish.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="epicure.fine-dish.form.label.end" path="end"/>
	<acme:input-url code="epicure.fine-dish.form.label.link" path="link"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.company" path="chefCompany" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.statement" path="chefStatement" readonly="true"/>
	<acme:input-textbox code="epicure.fine-dish.form.label.chef.link" path="chefLink"  readonly="true"/>
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="epicure.fine-dish.form.label.code" path="code"/>	
	<acme:input-textarea code="epicure.fine-dish.form.label.request" path="request"/>
	<acme:input-money code="epicure.fine-dish.form.label.budget" path="budget"/>
	<acme:input-money code="chef.item.form.label.conversion" path="conversion" readonly="true"/>
	<acme:input-moment code="epicure.fine-dish.form.label.initial" path="initial"/>
	<acme:input-moment code="epicure.fine-dish.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="epicure.fine-dish.form.label.end" path="end"/>
	<acme:input-url code="epicure.fine-dish.form.label.link" path="link"/>
	<acme:input-select code="epicure.fine-dish.form.label.chef" path="chefId">
		<jstl:forEach items="${chefs}" var="chef">
			<acme:input-option code="${chef.getUserAccount().getUsername()}" value="${chef.getId()}" selected="${ chef.getId() == chefId }"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="epicure.fine-dish.form.button.update" action="/epicure/fine-dish/update"/>
			<acme:submit code="epicure.fine-dish.form.button.delete" action="/epicure/fine-dish/delete"/>
			<acme:submit code="epicure.fine-dish.form.button.publish" action="/epicure/fine-dish/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="epicure.fine-dish.form.button.create" action="/epicure/fine-dish/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>