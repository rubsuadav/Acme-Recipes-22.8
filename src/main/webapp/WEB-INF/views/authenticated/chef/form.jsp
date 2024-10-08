<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.chef.chef.form.label.organisation" path="organisation"/>
	<acme:input-textbox code="authenticated.chef.chef.form.label.assertion" path="assertion"/>
	<acme:input-textbox code="authenticated.chef.chef.form.label.link" path="link"/>	
	
	<acme:submit test="${command == 'create'}" code="authenticated.chef.chef.form.button.create" action="/authenticated/chef/create"/>
	<acme:submit test="${command == 'update'}" code="authenticated.chef.chef.form.button.update" action="/authenticated/chef/update"/>
</acme:form>