<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
    <acme:input-textbox code="authenticated.systemconfiguration.form.label.accepted-currencies" path="acceptedCurrencies"/>
    <acme:input-textbox code="authenticated.systemconfiguration.form.label.system-currency" path="systemCurrency"/>
    
    <acme:button code="authenticated.systemconfiguration.form.label.money-exchange" action="/authenticated/money-exchange/perform"/>
    
</acme:form>