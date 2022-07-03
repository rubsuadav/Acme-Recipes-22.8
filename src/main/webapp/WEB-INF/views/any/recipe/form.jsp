<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
   <acme:input-textbox code="any.recipe.form.label.code" path="code"/>
   <acme:input-textbox code="any.recipe.form.label.heading" path="heading"/>
   <acme:input-textarea code="any.recipe.form.label.description" path="description"/>	
   <acme:input-textarea code="any.recipe.form.label.preparationNotes" path="preparationNotes"/>
   <acme:input-url code="any.recipe.form.label.link" path="link"/>	
   <acme:input-textbox code="any.recipe.form.label.recipePrice" path="price"/>
   <acme:input-textbox code="any.recipe.form.label.published" path="published"/>

    <acme:button code="any.recipe.form.button.items" action="/any/item/list-recipe-items?id=${id}"/>

</acme:form>