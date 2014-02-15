<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page errorPage="error.jsp" %>  
<%@page import="com.demo.data.CustomerData"%>
<%@page import="com.demo.AttributeConstants"%>
<%
if(session.getAttribute(AttributeConstants.LOGGED_IN) == null){
	
%>

<!--<script>
window.location.assign("index.jsp");
</script>
--><%}else{%>

<%!
	CustomerData customerData = null;
%>
<%
	customerData = (CustomerData)session.getAttribute(AttributeConstants.LOGGED_IN);
%>
<!-- Styles -->
	<!-- Logo Font - Molle -->
	<link href="css/molle.css" rel="stylesheet" type="text/css">

	<link href="css/bootstrap.css" rel="stylesheet">
	<link rel="stylesheet" href="css/icon-style.css" />
	<!--[if lte IE 7]>
	<script src="../scripts/lte-ie7.js"></script>
	<![endif]-->
	<link href="css/bootstrap-responsive.css" rel="stylesheet">
	<link href="css/radmin.css" rel="stylesheet" id="main-stylesheet">
	<link href="css/radmin-responsive.css" rel="stylesheet">

	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
	<script type="text/javascript" src="scripts/jquery.cloneposition.js"></script>
<script src="scripts/jquery-ui.js"></script>
<link rel="stylesheet" href="scripts/jquery-ui.css">
	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
	<![endif]-->
	<!--[if lte IE 9]>
	<script src="scripts/placeholder.js" type="text/javascript"></script>
	<![endif]-->
	<script type="text/javascript" src="scripts/theme.js"></script>
	<link rel="stylesheet" type="text/css" href="css/radmin-plugins.css" />
 
  <style>
#dialog .ui-dialog-titlebar-close{
    display: none;
}
</style>
  
	<script >
  $(document).ready(function(){
	  var docHeight = $(document).height();

	   $("#overlay")
	      .height(docHeight)
	      .css({
	         'opacity' : 0.4,
	         'position': 'fixed',
	         'top': 0,
	         'left': 0,
	         'background-color': 'black',
	         'width': '100%',
	         'z-index': 5000
	      }); 
	  $("#overlay").hide();
	  $("#result").hide();
	    $("#pager").hide();
	    $( "#traderesult" ).hide();
	  	$( "#dialog" ).hide();
	  	 $('#checkbox').change(function() {
		        if($(this).is(":checked")) {
		            if(confirm("Are you sure, Do you wants to enable Auto Trade ?")){
		            	$("#overlay").show();
						$.ajax(	{
							url : "AutoTradeSetting?enable=true",
							type: "GET",
							success:function() 
							{
								$("#overlay").hide();
								alert("Auto Trade is enabled.");
							},
							error: function() 
							{
								$("#overlay").hide();
								alert("Error Occured");
							}
						});
				     }
		        }else{
		        	 if(confirm("Are you sure, Do you wants to disable Auto Trade ?")){
			            	$("#overlay").show();
				        	 
							$.ajax(	{
								url : "AutoTradeSetting?enable=false",
								type: "GET",
								success:function() 
								{
									$("#overlay").hide();
									alert("Auto Trade is disabled.");
								},
								error: function() 
								{
									$("#overlay").hide();
									alert("Error Occured");
								}
							});
							 
								
					  }
				}
		                
		    });
	  	var wWidth = $(window).width();
	  	var dWidth = 1000;
	  	var wHeight = $(window).height();
	  	var dHeight = 700;
	    <%
	    if(customerData != null && Double.parseDouble(customerData.getAccountBalance()) == 0){%>
	    
	 	$( "#dialog" ).dialog({ modal:true,closeOnEscape:false,overlay: { opacity: 0.1, background: "black"  },width: dWidth,resizeable:false,height: dHeight,draggable: false});
	 	$(".ui-dialog-titlebar").hide();
	 	
		<%}%>
	//$("#table").tablesorter({widthFixed: true, widgets: ['zebra']}).tablesorterPager({container: $("#pager")}); 


			$(".ajaxform").submit(function(e)
				{
					//alert("hi");
						$("#overlay").show();
					
					var postData = $(this).serializeArray();
					var formURL = $(this).attr("action");
					$.ajax(
					{
						url : formURL,
						type: "POST",
						data : postData,
						success:function(data, textStatus, jqXHR) 
						{
							$("#overlay").hide();
							$( "#traderesult" ).html(data);
							$( "#traderesult" ).dialog({closeOnEscape:true,width:300,minHeight:50});
							//data: return data from server
						},
						error: function(xhr, textStatus, errorThrown) 
						{
							$("#overlay").hide();
							
						}
					});
				    e.preventDefault();	//STOP default action
				});
 }); 
  function loadAjaxcallAgain()  {
	  
  }
  function fun_hideShowRow(a)  {
	 // alert(a.value);
	  var rows = $('#table > tbody > tr');
	 // alert(rows.length);
	  for( i = 0 ; i < rows.length ; i++)  {
		  if(i < a.value)  {
			  rows[i].style.display="";
		  }else {
			  rows[i].style.display="none";
			  }
	  }
	  //alert("done");
  }
  </script>
  <body>
  <div id="traderesult" title="Trade Status"></div>


	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner black-gradient">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="homepage.jsp">
				<img src="logo.png" style="height:60px; width:200px;"></img>
					<!-- <span class="rad">Income</span>rush -->
				</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						<a id="user-popover" href="profile.jsp" class="navbar-link user-info"> <i class="radmin-icon radmin-user"></i>
						<% if(customerData ==null){ %>
							Guest
							<%}else{ %>
							<%= customerData.getFirstName()+ " " + customerData.getLastName()  %>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							Balance : $<%= customerData.getAccountBalance() %>
							<%
							session.setAttribute("c_name",customerData.getFirstName()+ " " + customerData.getLastName());
							%>
							<%} %>
						</a>
						<a href="LogOut" class="btn btn-mini btn-inverse navbar-link logout"> <i class="radmin-icon radmin-redo"></i>
							Logout
						</a>
					</p>

							</div>
			</div>
		</div>
	</div>
  <div id='overlay'>
  
  <div style="margin-top: 300px">
  <center>
					<img src="img/load.gif" style="height:200px; width:200px;"></img>
  </center>
  </div>
  
  
  </div>

</body>
<%} %>
