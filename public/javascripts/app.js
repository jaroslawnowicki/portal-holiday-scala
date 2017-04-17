$(document).ready(function() {
	$("#dateFrom").datepicker();
	$("#dateTo").datepicker();
	$('#saveApplication').on('click', function() {
		newApplication();
	});
});

var newApplication = function() {
	$.ajax({
		type: "POST",
		url : '/holidaycalendar/add',
		data : {dateFrom : 'dasds'}
	}).done(function(html) {
		console.log(html);
	});
}