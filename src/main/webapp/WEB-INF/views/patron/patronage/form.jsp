<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:if test="${command != 'create' }">
	<acme:input-textbox code="patron.patronage.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>	
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.initial" path="initial"/>
	<acme:input-moment code="patron.patronage.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.end" path="end"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<acme:input-textbox code="patron.patronage.form.label.company" path="inventorCompany" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.statement" path="inventorStatement" readonly="true"/>
	<acme:input-textbox code="patron.patronage.form.label.inventor.link" path="inventorLink"  readonly="true"/>
</jstl:if>

<jstl:if test="${command == 'create'}">
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>	
	<acme:input-textarea code="patron.patronage.form.label.legal-stuff" path="legalStuff"/>
	<acme:input-money code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-moment code="patron.patronage.form.label.initial" path="initial"/>
	<acme:input-moment code="patron.patronage.form.label.creation" path="creation" readonly="true"/>
	<acme:input-moment code="patron.patronage.form.label.end" path="end"/>
	<acme:input-url code="patron.patronage.form.label.link" path="link"/>
	<acme:input-select code="patron.patronage.form.label.inventor" path="inventorId">
		<jstl:forEach items="${inventors}" var="inventor">
			<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
		</jstl:forEach>
	</acme:input-select>
</jstl:if>

	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == false}">
			<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
			<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
		<jstl:when test="${command == 'create'}">
			<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>
	</jstl:choose>
</acme:form>