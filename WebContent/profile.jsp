<!DOCTYPE html>
<%@ page errorPage="error.jsp" %>  
<%@page import="com.demo.AttributeConstants"%>
<%@page import="com.demo.data.CustomerData"%>
<%
	if(session.getAttribute(AttributeConstants.LOGGED_IN) == null)
		response.sendRedirect("index.jsp");
	else{
	%>
<html lang="en">
<head>

	<meta charset="utf-8">
	
	
	

</head>
<body id="body-index">

<jsp:include page="title.jsp" />	
	
<jsp:include page="header.jsp" />
	<div class="row-fluid">
		<div class="span12">&nbsp;</div>
	</div>

	

	<div class="container-fluid main-container">
	

		<div class="row-fluid">
			<div class="span12">

				<div class="sidebar-nav">
					<ul class="nav nav-stacked left-menu">
						<li class="accordion" id="navigation-sample-pages">
							<a href="homepage.jsp">
								<span class="hidden-tablet hidden-phone">Home</span>
							</a>
						</li>
						<li class="accordion" id="navigation-sample-pages">
							<a class="accordion-toggle" data-toggle="collapse" data-parent="#navigation-sample-pages" href="#collapse1">
								<span class="hidden-tablet hidden-phone">Trade</span>
								<span class="label pull-right hidden-tablet hidden-phone">5</span>
								<div id="collapse1" class="accordion-body collapse">
								<ul class="nav nav-stacked submenu">
									<li>
										<a href="tradepage.jsp">
											<span class="hidden-tablet hidden-phone">CURRENCIES</span>
										</a>
									</li>
									<li>
										<a href="stock.jsp">
											<span class="hidden-tablet hidden-phone">STOCK</span>
										</a>
									</li>
									<li>
										<a href="indices.jsp">
											<span class="hidden-tablet hidden-phone">INDICES</span>
										</a>
									</li>
									<li >
										<a href="commodities.jsp">
											<span class="hidden-tablet hidden-phone">COMMODITIES</span>
										</a>
									</li>
									<li class="submenu-last">
										<a href="pairs.jsp">
											<span class="hidden-tablet hidden-phone">PAIRS</span>
										</a>
									</li>
								</ul>
							</div>
							</a>
						</li>
						<li id="navigation-graphs">
							<a href="bookTrade.jsp">
								<span class="hidden-tablet hidden-phone">Book Trade</span>
							</a>
						</li>
						<li id="navigation-graphs">
							<a href="<%=session.getAttribute("website").toString() %>" target="_blank">
								<span class="hidden-tablet hidden-phone">Deposit</span>
							</a>
						</li>
						<li id="navigation-graphs" class="active" >
							<a href="profile.jsp" >
								<span class="hidden-tablet hidden-phone">My Profile</span>
							</a>
							
						</li>
						
					</ul>
				</div>
				<div class="container-fluid content-wrapper">
<%
	CustomerData customerDataSession = (CustomerData)session.getAttribute(AttributeConstants.LOGGED_IN);
	boolean autoTrade = (Boolean)session.getAttribute(AttributeConstants.AUTO_TRADE);
	if(customerDataSession == null || customerDataSession.getAccountBalance() == null
			|| Double.parseDouble(customerDataSession.getAccountBalance()) == 0){
		%>
		<jsp:include page="zeroBalance.jsp" />
		<%
	}
%>
					<%if(request.getParameter("execute") != null){ 

	if(request.getParameter("execute").equals("true")){
%>
<p style="margin: 50px;color: green;">Trade Successfully done.</p>
<%}else{%>

<p style="margin: 50px;color: red;">Trade Failed. Reason :  <%= request.getParameter("error") %>  </p>

<%}} 
 customerDataSession = (CustomerData)session.getAttribute(AttributeConstants.LOGGED_IN);
%>
<h4 class="title">My Profile</h4>
							<div class="squiggly-border"></div>
<table class="table table-index">
<thead>
</thead>
<tr>
		<td>Name :</td>
		<td colspan="3"><%=customerDataSession.getFirstName()+" "+customerDataSession.getLastName() %></td>
</tr>
<tr>
		<td>Phone No :</td>
		<td colspan="3"><%=customerDataSession.getPhone() %></td>
</tr>
<tr>
		<td>Address :</td>
		
		<td colspan="3"><%=customerDataSession.getApartmentNumber()+","+customerDataSession.getStreet()+","+customerDataSession.getCity() %></td>
</tr>
<tr>
		<td>Email ID :</td>
				<td colspan="3"><%=customerDataSession.getEmail() %></td>
</tr>
<tr>
		<td>
		Account Balance : 
		</td>
		<td colspan="3"><%=customerDataSession.getAccountBalance() %></td>
</tr>
<tr>
		<td>
		Auto Trade : 
		</td>
		<td colspan="3"><input type="checkbox" id="checkbox" 
		<%
			if(autoTrade){
				System.out.print("AutoTrade");
				%>
				checked="checked"
				<%
			}
		%>
		
		 ></input>
		<br/>Auto Trade function will automatically make the best trade of the day for you once every single day. Untick for manual trading.
		</td>
</tr>
</table>


	</div>
</div>
</div>
<!-- Footer -->

<!-- Javascript -->

<!-- Sparklines -->
<script type="text/javascript" src="scripts/sparkline.js"></script>

<script type="text/javascript">
     	switcher_div = $('#color-switcher');
		switcher_control = $('#color-switcher-control');
		switcher_is_transitioning = false;
		
		switcher_div_style = {
			'width': switcher_control.children('a:first').width(),
			'z-index': 2,
			'top': '+=78px',
			'left': '-=5px'
		};
		
		switcher_control_style = {
			'z-index': 3,
			'position': 'relative'
		};

		
		
		function get_flot_colors() {
		    if(radmin_current_theme === 'pink') {
		        return ['#E63E5D', '#97AF22', '#9D3844', '#533436', '#082D35'];
		    } else if(radmin_current_theme === 'green') {
		        return ['#42826C', '#FFC861', '#A5C77F', '#6d9f00', '#002F32'];
		    } else {
		        return ['#49AFCD', '#FF6347', '#38849A', '#BF4A35', '#999', '#555'];
		    }
		
		    return ['#49AFCD', '#FF6347', '#38849A', '#BF4A35', '#999', '#555'];
		}
		
		function get_sparkline_colors(){
			if(radmin_current_theme === 'pink') {
		        return ['#E63E5D', '#97AF22'];
		    } else if(radmin_current_theme === 'green') {
		        return ['#42826C', '#FFC861'];
		    } else {
		        return ['#49AFCD', '#FF6347'];
		    }
		
		    return ['#49AFCD', '#FF6347'];
		}
     
	    var sparkline_colors = get_sparkline_colors();
	    
    	/**
		 *  Jquery Load Event
		 *
		 */
		jQuery(function($){
			// format date inputs
			$( "a[rel=popover]" ).popover();
			
 	        $('.bar1-sparkline').sparkline([12, 8, 10, 13, 15, -6, -8, 10, 18, 10, -8, -7 ], {type: 'bar', barColor: sparkline_colors[0], negBarColor: sparkline_colors[1], barWidth: '5', height: '20'} );
	       
	        $('.bar2-sparkline').sparkline([ [5,6],[7,9],[9,5],[6,2],[10,4],[6,7],[5,4],[6,7] ], {type: 'bar',stackedBarColor: sparkline_colors, barWidth: '5', height: '20'} );
	       
	        $('.discrete-sparkline').sparkline([ 12,11,13,14,13,12,11,12,13,14,15,16,15,14,13,14,15,16,17,18,17,16,17 ], {type: 'discrete', lineColor: sparkline_colors[0], height: '20'} );
	       
	        $('.line-sparkline').sparkline([ 12,11,13,14,13,12,11,12,13,14,15,16,15,14,13,12,11,12,13,14,15,16,17,18 ], {type: 'line', lineColor: sparkline_colors[0], fillColor: sparkline_colors[0], height: '20'} );
	        
	        $('#user-popover').popover();
	        
	        /**
	         * Sets active and expands menu items based on id of body tag of current page
	         * e.g. <body id="body-index-page"> will result in the menu item with the id="navigation-index-page" having the 
	         * class 'active' added, subsequently it will look for a child div with a class of collapse and add the class 'in' 
	         * and set the height to auto
	         */
	        var body_id = $('body').attr('id');
			if(body_id != null){
				var nav_element = $('#navigation-' + body_id.replace('body-',''));
	        	nav_element.addClass('active');
	        	if(nav_element.has('div.collapse')){
	        		var child_nav = nav_element.find('div.collapse');
	        		child_nav.addClass('in');
	        		child_nav.css('height: auto;');
	        		
	        	}
	        	
	        }
	        
	        //hide the top-stats when the arrow is clicked
	        var item = $('.top-stats');
	        var target = $('#hide-top-stats');
	        if(item.length > 0 && target.length > 0){
   		        target.on('click', function() {
					item.css('position', 'relative');
					item.animate({
					    left: '-=1200',
		  		  	}, 1000, function() {
					    // Animation complete.
					    item.hide('slow');
					});
				});
			}
			
			//display the color-switcher and change theme (plus anything with comments of //used in theming logic )
			position_color_switcher(true);
			switcher_div.show();
			
			switcher_control.on('click', toggle_color_switcher);
			
			$(window).resize(function() {
				switcher_div.hide();
			});
			
			$('.color-switcher-color').bind('click', set_theme_url);
			
			
		});
		
    </script>

<!--[if lte IE 8]>
<script language="javascript" type="text/javascript" src="scripts/flot/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="scripts/flot/jquery.flot.js"></script>
<script type="text/javascript" src="scripts/flot/jquery.flot.resize.js"></script>
<script type="text/javascript" src="scripts/charts.js"></script>
<script type="text/javascript">
	jQuery(function($){
		flot_sin_cos('flot-line-graph', flot_options_sin_cos);
	});
	
	</script>

</body>
</html>
<% } %>