<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
    <jstl:if test="${command == 'show'}">
    	<acme:input-money code="chef.item.form.label.conversion" path="conversion" readonly="true"/>
    </jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show, update')}">
		<acme:input-integer code="chef.item.form.label.number" path="number"/> 
		<acme:input-textbox code="chef.item.form.label.name" path="item.name" readonly="true"/>
		<acme:input-textbox code="chef.item.form.label.code" path="item.code" readonly="true"/>
		<acme:input-textbox code="chef.item.form.label.description" path="item.description" readonly="true"/>
		<acme:input-money code="chef.item.form.label.retailPrice" path="item.retailPrice" readonly="true"/>
		<acme:input-url code="chef.item.form.label.link" path="item.link" readonly="true"/>
		<acme:input-textbox code="chef.item.form.label.published" path="published" readonly="true"/>
	</jstl:if>
						
	<jstl:if test="${recipePublished == false}">
		<acme:submit code="chef.quantity.form.button.update" action="/chef/quantity/update"/>
		<acme:submit code="chef.quantity.form.button.delete" action="/chef/quantity/delete"/>
	</jstl:if>
	
	<<jstl:if test="${command == 'create'}">
		<acme:input-integer code="chef.item.form.label.number" path="number"/> 
		<acme:input-select code="chef.item.form.label.item" path="itemId">
			<jstl:forEach items="${items}" var="item">
				<acme:input-option code="${item.getName()}" value="${item.getId()}" selected="${item.getId() == itemId }"/>
			</jstl:forEach>
		</acme:input-select>
			<acme:submit code="chef.item-recipe.form.button.create" action="/chef/quantity/create?masterId=${masterId}"/>		
	</jstl:if>
</acme:form>