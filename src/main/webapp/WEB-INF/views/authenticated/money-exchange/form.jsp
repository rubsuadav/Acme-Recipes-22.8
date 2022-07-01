<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-money code="authenticated.money-exchange.form.label.source" path="source"/>
	<acme:input-textbox code="authenticated.money-exchange.form.label.target-currency" path="currencyTarget" placeholder="EUR, USD, GBP, ..."/>
	
	<acme:input-money code="authenticated.money-exchange.form.label.date" path="date" readonly="true" placeholder=""/>
	<acme:input-money code="authenticated.money-exchange.form.label.target" path="target" readonly="true" placeholder=""/>
		
	<acme:submit code="authenticated.money-exchange.form.button.perform" action="/authenticated/money-exchange/perform"/>
</acme:form>