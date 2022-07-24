<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags" %>

<span id="epicure.dashboard.form.title">
	<acme:message code="epicure.dashboard.form.title" />
</span>
<span id="epicure.dashboard.form.label.number-of-fine-dishes-by-status">
	<acme:message code="epicure.dashboard.form.label.number-of-fine-dishes-by-status" />
</span>
<span id="epicure.dashboard.form.label.average-number-of-budgets-by-currency-and-status">
	<acme:message code="epicure.dashboard.form.label.average-number-of-budgets-by-currency-and-status" />
</span>
<span id="epicure.dashboard.form.label.deviation-of-budgets-by-currency-and-status">
	<acme:message code="epicure.dashboard.form.label.deviation-of-budgets-by-currency-and-status" />
</span>
<span id="epicure.dashboard.form.label.min-budget-by-currency-and-status">
	<acme:message code="epicure.dashboard.form.label.min-budget-by-currency-and-status" />
</span>
<span id="epicure.dashboard.form.label.max-budget-by-currency-and-status">
	<acme:message code="epicure.dashboard.form.label.max-budget-by-currency-and-status" />
</span>

<script type="text/javascript">
	$(document).ready(function () {
		createDashboard("${items}", "${methods}");
	});
</script>