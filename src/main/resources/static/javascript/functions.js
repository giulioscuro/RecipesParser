/**
 * 
 * Author: Created: Last Updated: Version: Licence:
 */


function display(data) {
	
	 $('#feedback').empty();
	 var list = $('<ul></ul>');
	$.each(data, function(i, item) {
	    
		  $('#feedback').append(list);
		    list.append("<li><a href='" + item.linkUrl +"'>" + item.titolo +  "</li></a>");
	});
}


var arrayIngredienti;

// al load del documento



$(document).ready(function() {

	$.ajax({
		url : "/ingredienti",
		context : document.body,
		dataType : "json",
		success : function(arrayIngredienti) {

			var ms = $('#ms-emails').magicSuggest({
				placeholder : 'Enter recipients...',
				data : arrayIngredienti,
				name : 'searchString',
				valueField : 'name',
				renderer : function(data) {
					return data.name + ' (<b>' + data.id + '</b>)';
				},
				resultAsString : true
			});

			$(ms).on('selectionchange', function(e, m) {
				//$("#theForm").submit();
				var url = "/ricette";
				// the script where you handle the form input.

				$.ajax({
					type : "POST",
					url : url,
					data : $("#theForm").serialize(), // serializes the form's elements.
					success : function(data) {
						
						//alert(data[0].titolo); // show response from the server.
						
						display(data);
						
					}
				});
			//	e.preventDefault(); // avoid to execute the actual submit of the form.
			})//;

		}

	});

});


