<%@page language="java" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags" %>

<script type="text/javascript">
	$(document).ready(function () {
		createDashboard("${items}", "${methods}");
	});
</script>