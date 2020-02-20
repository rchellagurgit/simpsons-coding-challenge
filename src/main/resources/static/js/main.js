$(document)
		.ready(
				function() {


					// --->create data table > start
					var tbl = '';
					tbl += '<table class="table table-hover tbl_code_with_mark">'

					// --->create table header > start
					tbl += '<thead>';
					tbl += '<tr>';
					tbl += '<th>First Name</th>';
					tbl += '<th>Last Name</th>';
					tbl += '<th>Picture</th>';
					tbl += '<th>Age</th>';
					tbl += '<th>Quote</th>';
					tbl += '<th>Options</th>';
					tbl += '</tr>';
					tbl += '</thead>';
					// --->create table header > end

					// --->create table body > start
					tbl += '<tbody>';

					$
							.ajax({
								type : "GET",
								url : "/api/getAllSimpsonCharacters",
								success : function(simpsonsCharacters) {
									console.log("SUCCESS : ",
											simpsonsCharacters.msg);

									$
											.each(
													simpsonsCharacters.result,
													function(i,
															simpsonsCharacter) {

														// loop through ajax row
														// data
														tbl += '<tr row_id="'
																+ simpsonsCharacter.id
																+ '">';
														tbl += '<td ><div class="row_data" edit_type="click" col_name="firstName">'
																+ simpsonsCharacter.firstName
																+ '</div></td>';
														tbl += '<td ><div class="row_data" edit_type="click" col_name="lastName">'
																+ simpsonsCharacter.lastName
																+ '</div></td>';
														tbl += '<td ><div class="row_data" edit_type="click" col_name="picture">'
																+ simpsonsCharacter.picture
																+ '</div></td>';
														tbl += '<td ><div class="row_data" edit_type="click" col_name="age">'
																+ simpsonsCharacter.age
																+ '</div></td>';
														tbl += '<td ><div class="row_data" edit_type="click" col_name="quote">'
																+ simpsonsCharacter.quote
																+ '</div></td>';
														//--->edit options > start
														tbl +='<td>';						 
															tbl +='<span class="btn_edit" > <a href="#" class="btn btn-link " row_id="'+simpsonsCharacter.id+'" > Edit</a> </span>';
															//only show this button if edit button is clicked
															tbl +='<a href="#" class="btn_save btn btn-link"  row_id="'+simpsonsCharacter.id+'"> Save </a>';
															tbl +='<a href="#" class="btn_cancel btn btn-link" row_id="'+simpsonsCharacter.id+'"> Cancel </a>';
															tbl +='<a href="#" class="btn_delete btn btn-link1 text-danger" row_id="'+simpsonsCharacter.id+'"> Delete</a>';
														tbl +='</td>';
														//--->edit options > end						
													tbl +='</tr>';
												});
												//--->create table body rows > end
									tbl += '</tbody>';
									// --->create table body > end

									tbl += '</table>';
									// --->create data table > end
									//add new table row
									tbl +='<div class="text-center">';
										tbl +='<span class="btn btn-primary btn_new_row">Add New Row</span>';
									tbl +='<div>';

									$(".fullDataTable").html(tbl);
								    $(document).find('.btn_save').hide();
									$(document).find('.btn_cancel').hide();
									$(document).find('.btn_delete').hide();
									 

								},
								error : function(e) {
									alert("Error loading data.");
									console.log("ERROR : ", e);
								}

							});

					$("#search-form")
							.submit(
									function(event) {
										event.preventDefault();
										var search = {}
										search["firstName"] = $(
												"#SearchfirstName").val();
										search["lastName"] = $(
												"#SearchlastName").val();

										$("#btn-search").prop("disabled", true);

										$
												.ajax({
													type : "POST",
													contentType : "application/json",
													url : "/api/search",
													data : JSON
															.stringify(search),
													dataType : 'json',
													cache : false,
													timeout : 600000,
													success : function(data) {

														if (data.result.length != 0) {
															$
																	.each(
																			data.result,
																			function(
																					i,
																					simpsonsCharacter) {
																				$(
																						"#search-result")
																						.html(
																								'<tr>'
																										+ '	<td>'
																										+ simpsonsCharacter.quote
																										+ ' </td> '
																										+ '</tr>');
																			});
														} else {
															$("#search-result")
																	.html(
																			'<tr>'
																					+ '	<td> NO Quote Found! </td> '
																					+ '</tr>');
														}
														console.log(
																"SUCCESS : ",
																data);
														$("#btn-search").prop(
																"disabled",
																false);

													},
													error : function(e) {

														$("#search-result")
																.html(
																		'<tr>'
																				+ '	<td> NO Quote Found! </td> '
																				+ '</tr>');

														console.log("ERROR : ",
																e);
														$("#btn-search").prop(
																"disabled",
																false);

													}

												});

									});

					//--->button > edit > start	
					$(document).on('click', '.btn_edit', function(event) 
					{
						event.preventDefault();
						var tbl_row = $(this).closest('tr');

						var row_id = tbl_row.attr('row_id');

						tbl_row.find('.btn_save').show();
						tbl_row.find('.btn_cancel').show();
						tbl_row.find('.btn_delete').show();

						//hide edit button
						tbl_row.find('.btn_edit').hide(); 

						//make the whole row editable
						tbl_row.find('.row_data')
						.attr('contenteditable', 'true')
						.attr('edit_type', 'button')
						.addClass('bg-warning')
						.css('padding','3px')

						//--->add the original entry > start
						tbl_row.find('.row_data').each(function(index, val) 
						{  
							//this will help in case user decided to click on cancel button
							$(this).attr('original_entry', $(this).html());
						}); 		
						//--->add the original entry > end

					});
					//--->button > edit > end


					//--->button > cancel > start	
					$(document).on('click', '.btn_cancel', function(event) 
					{
						event.preventDefault();

						var tbl_row = $(this).closest('tr');

						var row_id = tbl_row.attr('row_id');

						//hide save and cacel buttons
						tbl_row.find('.btn_save').hide();
						tbl_row.find('.btn_cancel').hide();
						tbl_row.find('.btn_delete').hide();

						//show edit button
						tbl_row.find('.btn_edit').show();

						//make the whole row editable
						tbl_row.find('.row_data')
						.attr('edit_type', 'click')
						.removeClass('bg-warning')
						.css('padding','') 

						tbl_row.find('.row_data').each(function(index, val) 
						{   
							$(this).html( $(this).attr('original_entry') ); 
						});  
					});
					//--->button > cancel > end

					
					//--->save whole row entery > start	
					$(document).on('click', '.btn_save', function(event) 
					{
						event.preventDefault();
						var tbl_row = $(this).closest('tr');

						var row_id = tbl_row.attr('row_id');

						
						//hide save and cacel buttons
						tbl_row.find('.btn_save').hide();
						tbl_row.find('.btn_cancel').hide();
						tbl_row.find('.btn_delete').hide();

						//show edit button
						tbl_row.find('.btn_edit').show();


						//make the whole row editable
						tbl_row.find('.row_data')
						.attr('edit_type', 'click')
						.removeClass('bg-warning')
						.css('padding','') 

						//--->get row data > start
						var arr = {}; 
						tbl_row.find('.row_data').each(function(index, val) 
						{   
							var col_name = $(this).attr('col_name');  
							var col_val  =  $(this).html();
							arr[col_name] = col_val;
						});
						//--->get row data > end

						//get row id value
						arr['id'] = row_id;
						//out put to show
						$('.post_msg').html( '<pre class="bg-success">Data Saved!</pre>');


						//call ajax api to update the database record
						$.ajax({
							type : "POST",
							url : "/api/save",
							data : JSON.stringify(arr),
							contentType : "application/json",
							dataType : 'json',
							cache : false,
							timeout : 600000,
							success : function(
									data) {
								
								var msg = ''
									+ '<h3>Successfully updated your entry</h3>'
									+'<pre class="bg-success">'+JSON.stringify(arr, null, 2) +'</pre>'
									+'';

									$('.post_msg').html('Data Saved!');								


							},
							error : function(e) {
								var msg = ''
									+ '<h3>There was an error while trying to update your entry</h3>'
									+'<pre class="bg-danger">'+JSON.stringify(arr, null, 2) +'</pre>'
									+'';

									$('.post_msg').html('Error Data Saved!');
							}

						});
						
					});
					//--->save whole row entery > end



					$(document).on('click', '.btn_new_row', function(event) 
					{
						event.preventDefault();
						//create a random id
						var row_id = Math.floor(Math.random() * 100);

						//get table rows
						var tbl_row = $(document).find('.tbl_code_with_mark').find('tr');	 
						var tbl = '';
						tbl +='<tr row_id="'+row_id+'">';
							tbl +='<td ><div class="new_row_data firstName bg-warning" contenteditable="true" edit_type="click" col_name="firstName"></div></td>';
							tbl +='<td ><div class="new_row_data lastName bg-warning" contenteditable="true" edit_type="click" col_name="lastName"></div></td>';
							tbl +='<td ><div class="new_row_data picture bg-warning" contenteditable="true" edit_type="click" col_name="picture"></div></td>';
							tbl +='<td ><div class="new_row_data age bg-warning" contenteditable="true" edit_type="click" col_name="age"></div></td>';
							tbl +='<td ><div class="new_row_data quote bg-warning" contenteditable="true" edit_type="click" col_name="quote"></div></td>';

							//--->edit options > start
							tbl +='<td>';			 
								tbl +='  <a href="#" class="btn btn-link btn_new" row_id="'+row_id+'" > Add Entry</a>   | ';
								tbl +='  <a href="#" class="btn btn-link btn_remove_new_entry" row_id="'+row_id+'"> Remove</a> ';
							tbl +='</td>';
							//--->edit options > end	

						tbl +='</tr>';
						tbl_row.last().after(tbl);

						$(document).find('.tbl_code_with_mark').find('tr').last().find('.firstName').focus();
					});

					
					$(document).on('click', '.btn_remove_new_entry', function(event) 
					{
						event.preventDefault();

						$(this).closest('tr').remove();
					});

					function alert_msg (msg)
					{
						return '<span class="alert_msg text-danger">'+msg+'</span>';
					}

					$(document).on('click', '.btn_new', function(event) 
					{
						event.preventDefault();
						
						var ele_this = $(this);
						var ele = ele_this.closest('tr');
						
						//remove all old alerts
						ele.find('.alert_msg').remove();

						//get row id
						var row_id = $(this).attr('row_id');

						//get field names
						var firstName = ele.find('.firstName');
						var lastName = ele.find('.lastName');
						var picture = ele.find('.picture');
						var age = ele.find('.age');
						var quote = ele.find('.quote');

						if(firstName.html() == "")
						{
							firstName.focus();
							firstName.after(alert_msg('Enter First Name'));
						}
						else if(lastName.html() == "")
						{
							lastName.focus();
							lastName.after(alert_msg('Enter Last Name'));
						}
						else if(picture.html() == "")
						{
							picture.focus();
							picture.after(alert_msg('Enter Picture'));
						}
						else if(age.html() == "")
						{
							age.focus();
							age.after(alert_msg('Enter Last Age'));
						}
						else if(quote.html() == "")
						{
							quote.focus();
							quote.after(alert_msg('Enter Quote'));
						}

						else
						{
							var data_obj=
							{
								id:row_id,
								firstName:firstName.html(),
								lastName:lastName.html(),
								picture:picture.html(),
								age:age.html(),
								quote:quote.html(),
	
							};	
							ele_this.html('<p class="bg-warning">Please wait....adding your new row</p>');
							
							
							$.ajax({
								type : "POST",
								url : "/api/save",
								data : JSON.stringify(data_obj),
								contentType : "application/json",
								dataType : 'json',
								cache : false,
								timeout : 600000,
								success : function(
										data) {
									console.log("Sucessful Adding Data!");
									
									var tbl = '';
									tbl +='<a href="#" class="btn btn-link btn_edit" row_id="'+row_id+'" > Edit</a>';
									tbl +='<a href="#" class="btn btn-link btn_save"  row_id="'+row_id+'" style="display:none;"> Save</a>';
									tbl +='<a href="#" class="btn btn-link btn_cancel" row_id="'+row_id+'" style="display:none;"> Cancel</a>';
									tbl +='<a href="#" class="btn btn-link text-warning btn_delete" row_id="'+row_id+'" style="display:none;" > Delete</a>';


									ele_this.closest('td').html(tbl);
									ele.find('.new_row_data').removeClass('bg-warning');
									ele.find('.new_row_data').toggleClass('new_row_data row_data');

								},
								error : function(e) {
									var msg = ''
										+ '<h3>There was an error while trying to add your entry</h3>'
										+'<pre class="bg-danger">'+JSON.stringify(data_obj, null, 2) +'</pre>'
										+'';

										$('.post_msg').html('Error Saving Data!');
								}

							});

						}
					});



					$(document).on('click', '.btn_delete', function(event) 
					{
						event.preventDefault();

						var ele_this = $(this);
						var row_id = ele_this.attr('row_id');
						$.ajax({
							type : 'DELETE',
							url : '/api/delete/'+row_id,
							sucess : function(data) {
									alert("Delete Sucessful"+data);
									ele_this.closest('tr').slideUp('slow');		
							},
							error : function(e) {
								alert("Error loading data.");
								console.log("ERROR : ", e);
							}
						});
						ele_this.closest('tr').slideUp('slow');		
					});

				});


