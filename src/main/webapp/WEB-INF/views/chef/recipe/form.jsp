<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.recipe.form.label.code" path="code"/>
	<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>	
	<acme:input-textarea code="chef.recipe.form.label.description" path="description"/>	
	<acme:input-textarea code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
	<acme:input-url code="chef.recipe.form.label.link" path="link"/>
	<acme:input-textbox code="chef.recipe.form.label.recipePrice" path="price" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish') && published == 'The recipe is not published'}">
			<acme:button code="chef.recipe.form.button.items" action="/chef/quantity/list-recipe-items?masterId=${id}"/>
			<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update"/>
			<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete"/>
			<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish"/>
		</jstl:when>
		
		<jstl:when test="${command == 'create'}">
			<acme:submit code="chef.item.form.button.create" action="/chef/recipe/create"/>
		</jstl:when>
		
		<jstl:when test="${acme:anyOf(command, 'show, update, delete, publish')}">
			<acme:button code="chef.recipe.form.button.items" action="/chef/quantity/list-recipe-items?masterId=${id}"/>
		</jstl:when>
	</jstl:choose>
</acme:form>