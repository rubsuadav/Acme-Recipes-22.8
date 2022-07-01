<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<h2>
	<acme:message code="patron.dashboard.form.title"/>
</h2>

<table class="table table-sm">
<caption></caption>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.number-of-patronages-by-status"/>
		</th>
		<td>
			<div>
				<canvas id="patronagesByStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.average-number-of-budgets-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="averageNumberOfBudgetsByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.deviation-of-budgets-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="deviationOfBudgetsByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.min-budget-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="minBudgetByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="patron.dashboard.form.label.max-budget-by-currency-and-status"/>
		</th>
		<td>
			<div>
				<canvas id="maxBudgetByCurrencyAndStatus"></canvas>
			</div>
		</td>
	</tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	
	function createChart(barColors,labelList, dataList, id){
		var data = {
			labels : labelList,
			datasets : [
				{
					backgroundColor: barColors,
					data : dataList
				}
			]
		};
		var options = {
			scales : {
				yAxes : [
					{
						ticks : {
							suggestedMin : 0.0,
							suggestedMax : 3.0
						}
					}
				]
		
			},
			legend : {
				display : false
			}
		};
		var canvas, context;
		canvas = document.getElementById(id);
		context = canvas.getContext("2d");
		new Chart(context, {
			type : "bar",
			data : data,
			options : options
		});
		   
	}
	
	//LABELS
	let ACCEPTED = "ACCEPTED";
	let DENIED = "DENIED";
	let PROPOSED = "PROPOSED";
	let ACCEPTEDEUR = "ACCEPTED <-> EUR";
	let ACCEPTEDGBP = "ACCEPTED <-> GBP";
	let ACCEPTEDUSD = "ACCEPTED <-> USD";
	let DENIEDEUR = "DENIED <-> EUR";
	let DENIEDGBP = "DENIED <-> GBP";
	let DENIEDUSD = "DENIED <-> USD";
	let PROPOSEDEUR = "PROPOSED <-> EUR";
	let PROPOSEDGBP = "PROPOSED <-> GBP";
	let PROPOSEDUSD = "PROPOSED <-> USD";
	
	//PATRONAGES_BY_STATUS
	let labels1 = [
		PROPOSED,ACCEPTED,DENIED
	]
	let data1 = [
		<jstl:forEach items="${numberOfPatronagesByStatus}" var="numberOfPatronagesByStatus">
			<acme:print value="${numberOfPatronagesByStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	let barColors1 = ["#334BFF","#56FF33","#FF3333"];
	
	createChart(barColors1,labels1, data1, "patronagesByStatus");
	
	//AVERAGE_NUMBER_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels2 = [
		ACCEPTEDEUR,ACCEPTEDGBP,ACCEPTEDUSD,DENIEDEUR,DENIEDGBP,DENIEDUSD,PROPOSEDEUR,PROPOSEDGBP,PROPOSEDUSD
	]
	let data2 = [
		<jstl:forEach items="${averageNumberOfBudgetsByCurrencyAndStatus}" var="averageNumberOfBudgetsByCurrencyAndStatus">
			<acme:print value="${averageNumberOfBudgetsByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	let barColors2 = ["#56FF33","#56FF33","#56FF33","#FF3333","#FF3333","#FF3333","#334BFF","#334BFF","#334BFF"];
	
	createChart(barColors2,labels2, data2, "averageNumberOfBudgetsByCurrencyAndStatus");
	
	//DESVIATION_OF_BUDGETS_BY_CURRENCY_AND_STATUS
	let labels3 = [
		ACCEPTEDEUR,ACCEPTEDGBP,ACCEPTEDUSD,DENIEDEUR,DENIEDGBP,DENIEDUSD,PROPOSEDEUR,PROPOSEDGBP,PROPOSEDUSD
	]
	let data3 = [
		<jstl:forEach items="${deviationOfBudgetsByCurrencyAndStatus}" var="deviationOfBudgetsByCurrencyAndStatus">
			<acme:print value="${deviationOfBudgetsByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>	
	]
	
	let barColors3 = ["#56FF33","#56FF33","#56FF33","#FF3333","#FF3333","#FF3333","#334BFF","#334BFF","#334BFF"];
	
	createChart(barColors3,labels3, data3, "deviationOfBudgetsByCurrencyAndStatus");
	
	//MIN_BUDGET_BY_CURRENCY_AND_STATUS
	let labels4 = [
		ACCEPTEDEUR,ACCEPTEDGBP,ACCEPTEDUSD,DENIEDEUR,DENIEDGBP,DENIEDUSD,PROPOSEDEUR,PROPOSEDGBP,PROPOSEDUSD
	]
	let data4 = [
		<jstl:forEach items="${minBudgetByCurrencyAndStatus}" var="minBudgetByCurrencyAndStatus">
			<acme:print value="${minBudgetByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	let barColors4 = ["#56FF33","#56FF33","#56FF33","#FF3333","#FF3333","#FF3333","#334BFF","#334BFF","#334BFF"];
	
	createChart(barColors4,labels4, data4, "minBudgetByCurrencyAndStatus");
	
	//MAX_BUDGET_BY_CURRENCY_AND_STATUS
	let labels5 = [
		ACCEPTEDEUR,ACCEPTEDGBP,ACCEPTEDUSD,DENIEDEUR,DENIEDGBP,DENIEDUSD,PROPOSEDEUR,PROPOSEDGBP,PROPOSEDUSD
	]
	let data5 = [
		<jstl:forEach items="${maxBudgetByCurrencyAndStatus}" var="maxBudgetByCurrencyAndStatus">
			<acme:print value="${maxBudgetByCurrencyAndStatus['value'].toString()}"></acme:print>
			<acme:print value=","></acme:print>
		</jstl:forEach>
	]
	
	let barColors5 = ["#56FF33","#56FF33","#56FF33","#FF3333","#FF3333","#FF3333","#334BFF","#334BFF","#334BFF"];
	
	createChart(barColors5,labels5, data5, "maxBudgetByCurrencyAndStatus");
	
	});
</script>

<acme:return/>