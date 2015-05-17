$(document).ready(function() {
	$("#diagnoseName").autocomplete({
		source: function(request, response){
			$.ajax({
				url: "http://localhost:8080/disease/diseasesList", // remote datasource
				data: request,
				success: function(data){
					response(data); // set the response
				},
				error: function(){ // handle server errors
						// $.jGrowl("Unable to retrieve Companies", {
						//theme: 'ui-state-error ui-corner-all'   
						//});
				}
			});
		},
		minLength: 2, // triggered only after minimum 2 characters have been entered.
		select: function(event, ui) { // event handler when user selects an employee from the list.
			$("#hiddenfield\\.id").val(ui.item.id); // update the hidden field.
		}
	});
});