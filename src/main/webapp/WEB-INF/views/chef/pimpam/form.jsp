<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
  	<jstl:if test="${command == 'show'}">
  		<acme:input-money code="chef.pimpam.form.label.conversion" path="conversion" readonly="true"/>
  	</jstl:if>

	<jstl:if test="${acme:anyOf(command, 'show, update, delete, create')}">
		<acme:input-textbox code="chef.pimpam.form.label.code" path="code" placeholder="av25-ddmmyy, ab34-ddmmyy, ..."/>
		<acme:input-moment code="chef.pimpam.form.label.instantiation-moment" path="instantiationMoment" readonly="true"/>
		<acme:input-textbox code="chef.pimpam.form.label.title" path="title"/>
		<acme:input-textarea code="chef.pimpam.form.label.desciption" path="desciption"/>
		<acme:input-moment code="chef.pimpam.form.label.initial" path="initial"/>
		<acme:input-moment code="chef.pimpam.form.label.end" path="end"/>
		<acme:input-money code="chef.pimpam.form.label.budget" path="budget"/>
		<acme:input-url code="chef.pimpam.form.label.link" path="link"/>
	</jstl:if>
	
	<jstl:if test="${canUpdateDelete || command == 'update'}">
		<acme:submit code="chef.pimpam.form.button.update" action="/chef/pimpam/update"/>
		<acme:submit code="chef.pimpam.form.button.delete" action="/chef/pimpam/delete"/>
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:submit code="chef.pimpam.form.button.create" action="/chef/pimpam/create?masterId=${masterId}"/>
	</jstl:if>
</acme:form>