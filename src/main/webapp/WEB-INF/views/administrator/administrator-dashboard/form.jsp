<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags" %>

<span id="administrator.dashboard.form.title">
	<acme:message code="administrator.dashboard.form.title" />
</span>
<span id="administrator.dashboard.form.label.number-of-kitchen-utensils">
	<acme:message code="administrator.dashboard.form.label.number-of-kitchen-utensils" />
</span>
<span id="administrator.dashboard.form.label.average-retail-price-of-kitchen-utensils-by-currency">
	<acme:message code="administrator.dashboard.form.label.average-retail-price-of-kitchen-utensils-by-currency" />
</span>
<span id="administrator.dashboard.form.label.deviation-retail-price-of-kitchen-utensils-by-currency">
	<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-kitchen-utensils-by-currency" />
</span>
<span id="administrator.dashboard.form.label.min-retail-price-of-kitchen-utensils-by-currency">
	<acme:message code="administrator.dashboard.form.label.min-retail-price-of-kitchen-utensils-by-currency" />
</span>
<span id="administrator.dashboard.form.label.max-retail-price-of-kitchen-utensils-by-currency">
	<acme:message code="administrator.dashboard.form.label.max-retail-price-of-kitchen-utensils-by-currency" />
</span>

<span id="administrator.dashboard.form.label.number-of-ingredients">
	<acme:message code="administrator.dashboard.form.label.number-of-ingredients" />
</span>
<span id="administrator.dashboard.form.label.average-retail-price-of-ingredients-by-currency">
	<acme:message code="administrator.dashboard.form.label.average-retail-price-of-ingredients-by-currency" />
</span>
<span id="administrator.dashboard.form.label.deviation-retail-price-of-ingredients-by-currency">
	<acme:message code="administrator.dashboard.form.label.deviation-retail-price-of-ingredients-by-currency" />
</span>
<span id="administrator.dashboard.form.label.min-retail-price-of-ingredients-by-currency">
	<acme:message code="administrator.dashboard.form.label.min-retail-price-of-ingredients-by-currency" />
</span>
<span id="administrator.dashboard.form.label.max-retail-price-of-ingredients-by-currency">
	<acme:message code="administrator.dashboard.form.label.max-retail-price-of-ingredients-by-currency" />
</span>

<span id="administrator.dashboard.form.label.number-of-fine-dishes-by-status">
	<acme:message code="administrator.dashboard.form.label.number-of-fine-dishes-by-status" />
</span>
<span id="administrator.dashboard.form.label.average-budget-of-fine-dishes-by-status">
	<acme:message code="administrator.dashboard.form.label.average-budget-of-fine-dishes-by-status" />
</span>
<span id="administrator.dashboard.form.label.deviation-budget-of-fine-dishes-by-status">
	<acme:message code="administrator.dashboard.form.label.deviation-budget-of-fine-dishes-by-status" />
</span>
<span id="administrator.dashboard.form.label.min-budget-of-fine-dishes-by-status">
	<acme:message code="administrator.dashboard.form.label.min-budget-of-fine-dishes-by-status" />
</span>
<span id="administrator.dashboard.form.label.max-budget-of-fine-dishes-by-status">
	<acme:message code="administrator.dashboard.form.label.max-budget-of-fine-dishes-by-status" />
</span>

<span id="administrator.dashboard.form.label.ratio-of-items-with-pimpams">
	<acme:message code="administrator.dashboard.form.label.ratio-of-items-with-pimpams" />
</span>
<span id="administrator.dashboard.form.label.average-budget-of-pimpams-by-currency">
	<acme:message code="administrator.dashboard.form.label.average-budget-of-pimpams-by-currency" />
</span>
<span id="administrator.dashboard.form.label.deviation-budget-of-pimpams-by-currency">
	<acme:message code="administrator.dashboard.form.label.deviation-budget-of-pimpams-by-currency" />
</span>
<span id="administrator.dashboard.form.label.min-budget-of-pimpams-by-currency">
	<acme:message code="administrator.dashboard.form.label.min-budget-of-pimpams-by-currency" />
</span>
<span id="administrator.dashboard.form.label.max-budget-of-pimpams-by-currency">
	<acme:message code="administrator.dashboard.form.label.max-budget-of-pimpams-by-currency" />
</span>

<script type="text/javascript">
	$(document).ready(function () {
		createDashboard("${items}", "${methods}");
	});
</script>