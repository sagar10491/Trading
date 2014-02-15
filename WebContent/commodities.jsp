<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.demo.data.PositionsData"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Random"%>
<%@ page errorPage="error.jsp"%>
<%@page import="com.demo.AttributeConstants"%>
<%@page import="com.demo.data.CustomerData"%>
<%@page import="com.demo.data.ProductData"%>
<%@page import="com.demo.parsermanaager.ProductFetcher"%>
<%@page import="com.demo.data.CountSignals"%>
<%@page import="java.util.Map"%>

<%
	if(session.getAttribute(AttributeConstants.LOGGED_IN) == null)
		response.sendRedirect("index.jsp");
	else{
	%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>
.flat-table {
	margin-bottom: 20px;
	border-collapse: collapse;
	font-family: 'Lato', Calibri, Arial, sans-serif;
	border: none;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	text-align: center;
	width: 107%;
	height: auto;
	margin-left: -20px;
	margin-top: -10px;
}

.flat-table th,.flat-table td {
	box-shadow: inset 0 -1px rgba(0, 0, 0, 0.25), inset 0 1px
		rgba(0, 0, 0, 0.25);
}

.flat-table th {
	font-weight: normal;
	-webkit-font-smoothing: antialiased;
	padding: 0.5em;
	color: #CCC;
	text-shadow: 0 0 1px rgba(0, 0, 0, 0.1);
	font-size: 1.3em;
}

.flat-table td {
	color: #FFF;
	padding: 0.7em 0.7em 0.7em 1em;
	text-shadow: 0 0 1px rgba(255, 255, 255, 0.1);
	font-size: 1.1em;
}

.flat-table tr {
	-webkit-transition: background 0.3s, box-shadow 0.3s;
	-moz-transition: background 0.3s, box-shadow 0.3s;
	transition: background 0.3s, box-shadow 0.3s;
}

.flat-table-1 {
	border: none;
	background: #333;
	border-radius: 9px;
}

.flat-table-1 tr:hover {
	background: rgba(0, 0, 0, 0.19);
}

.flat-table-2 tr:hover {
	background: rgba(0, 0, 0, 0.1);
}

.flat-table-2 {
	background: #f06060;
}

.flat-table-3 {
	background: #52be7f;
}

.flat-table-3 tr:hover {
	background: rgba(0, 0, 0, 0.1);
}

th:first-child {
	border-radius: 9px 0 0 0;
}

th:last-child {
	border-radius: 0 9px 0 0;
}

tr:last-child td:first-child {
	border-radius: 0 0 0 9px;
}

tr:last-child td:last-child {
	border-radius: 0 0 9px 0;
}

.btrade {
	border: none;
	font-size: 30px;
	font-weight: normal;
	line-height: 1.4;
	border-radius: 4px;
	padding: 3px 15px;
	-webkit-font-smoothing: subpixel-antialiased;
	-webkit-transition: 0.25s linear;
	transition: 0.25s linear;
	text-transform: uppercase;
	text-decoration: none;
	font-family: 'Lato', Calibri, Arial, sans-serif;
}

.btrade {
	color: #ffffff;
	background-color: #f1c40f;
}

.tdrop {
	width: 100px;
	height: 25px;
}
</style>
</head>

<meta charset="utf-8">




	</head>
	<body id="body-index">

		<%
			ProductFetcher.refereshData();
		%>
		<%
			CountSignals cs = new CountSignals();
		Map<String, Map<String, Float>> mainMap = cs.getBroadCastMap();
		%>
		<!--
	<div class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-inner black-gradient">
			<div class="container-fluid">
				<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</a>
				<a class="brand" href="index.html">
					<span class="rad">Rad</span>min
				</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						<a id="user-popover" href="#" class="navbar-link user-info"> <i class="radmin-icon radmin-user"></i>
							Jack Frost
						</a>
						<a href="login.html" class="btn btn-mini btn-inverse navbar-link logout"> <i class="radmin-icon radmin-redo"></i>
							Logout
						</a>
					</p>

				<!--	<ul class="stats-sparkline pull-left hidden-phone hidden-tablet">
						<li>
							<span class="line-sparkline">Loading…</span>
							<span class="sparkline-text">Traffic</span>
						</li>
						<li>
							<span class="bar2-sparkline">Loading…</span>
							<span class="sparkline-text">Tickets</span>
						</li>
						<li>
							<span class="discrete-sparkline">Loading…</span>
							<span class="sparkline-text">Sales</span>
						</li>
						<li>
							<span class="bar1-sparkline">Loading…</span>
							<span class="sparkline-text">Revenue</span>
						</li>
					</ul>
					-->

		<!--	<form class="navbar-search pull-right">
						<input type="text" class="span2 search-query" placeholder="Search"></form>  
				</div>
			</div>
		</div>
	</div>
-->
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
							<li class="accordion" id="navigation-sample-pages"><a
								href="homepage.jsp"> <span
									class="hidden-tablet hidden-phone">Home</span> </a></li>
							<li class="accordion active" id="navigation-sample-pages"><a
								class="accordion-toggle" data-toggle="collapse"
								data-parent="#navigation-sample-pages" href="#collapse1"> <span
									class="hidden-tablet hidden-phone">Trade</span> <span
									class="label pull-right hidden-tablet hidden-phone">5</span>
									<div id="collapse1" class="accordion-body collapse">
										<ul class="nav nav-stacked submenu">
											<li><a href="tradepage.jsp"> <span
													class="hidden-tablet hidden-phone">CURRENCIES</span> </a></li>
											<li><a href="stock.jsp"> <span
													class="hidden-tablet hidden-phone">STOCK</span> </a></li>
											<li><a href="indices.jsp"> <span
													class="hidden-tablet hidden-phone">INDICES</span> </a></li>
											<li><a href="commodities.jsp"> <span
													class="hidden-tablet hidden-phone">COMMODITIES</span> </a></li>
											<li class="submenu-last"><a href="pairs.jsp"> <span
													class="hidden-tablet hidden-phone">PAIRS</span> </a></li>
										</ul>
									</div> </a></li>
							<li id="navigation-graphs"><a href="bookTrade.jsp"> <span
									class="hidden-tablet hidden-phone">Book Trade</span> </a></li>
							<li id="navigation-graphs"><a
								href="<%=session.getAttribute("website").toString() %>" target="_blank"> <span
									class="hidden-tablet hidden-phone">Deposit</span> </a></li>
							<li id="navigation-graphs"><a href="profile.jsp"> <span
									class="hidden-tablet hidden-phone">My Profile</span> </a></li>

						</ul>
					</div>
					<div class="container-fluid content-wrapper">

						Number of trades on page <select id="selectNumTrade"
							onchange="fun_hideShowRow(this)">
							<option>2</option>
							<option selected="selected">5</option>
							<option>10</option>
							<option>15</option>

						</select>

						<h4 class="title">Commodities</h4>
						<div class="squiggly-border"></div>
						<table class="flat-table flat-table-1" id="table"
							style="margin-left: 10px">
							<thead>
								<tr>
									<th>Trade</th>
									<th>Signal Rate</th>
									<th>Trade Value %</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<%
									int i = 0;
										int count = 0 ;
											Map<String, Float> map = mainMap.get("commodities");
											if (map != null && !map.isEmpty()) {
										for (Map.Entry<String, Float> entry : map.entrySet()) {
											ProductData productData = cs.getProdcutMap().get(entry.getKey());
											if(!ProductFetcher.isTradableCheck(productData))
												continue;
								%>

								<form method="post" class="ajaxform" action="TradeProduct">
									<tr
										<%if(count > 4) out.println("style='display:none'"); count++;%>>
										<%
											out.print("<input type=\"hidden\" name=\"product\" value=\""+productData.getId()+"\"></input>");
										%>
										<td>
											<%
												if(productData.getName().toLowerCase().contains("former"))	
												out.println(productData.getName().toLowerCase().replaceAll("former",""));
												else
												out.println(productData.getName());
											%>
										</td>
										<%
											if(Float.parseFloat(CountSignals.readSignal(productData.getId())) < 0  || CountSignals.readSignal(productData.getId()).contains("-"))  {
										%>
										<td style="color: #FF0014;"><%=CountSignals.readSignal(productData.getId())%><img
											src="down.png" width="20px"
											style="float: right; margin-left: -30px;" />
										</td>
										<%
											}else {
										%>
										<td style="color: #0F0;"><%=CountSignals.readSignal(productData.getId())%><img
											src="up.png" width="20px"
											style="float: right; margin-left: -30px;" />
										</td>
										<%
											}
										%>

										<td><select name="tardeAmount" class="tdrop"
											style="color: #000;">
												
												<option value="25"> $ 25</option>
												<option value="50"> $ 50</option>
												<option value="100"> $ 100</option>
												<option value="150"> $ 150</option>
												<option value="200"> $ 200</option>
												<option value="500"> $ 500</option>
												<option value="1000"> $ 1000</option>
												
										</select><br />
										<label style="font-size: 14px; color: #CCC;">Trade
												percentage: <%=productData.getRules().getProfit()%></label>
										</td>
										<td><input type="submit" class="btrade"
											style="color: #000; width: 200px; height: 50px;"
											value="Trade">
										</td>
									</tr>
								</form>
								<%
									}}
								else { %>
				<tr><td> No tradeable product is available</td></tr>
<%} %>
							</tbody>
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
					'width' : switcher_control.children('a:first').width(),
					'z-index' : 2,
					'top' : '+=78px',
					'left' : '-=5px'
				};

				switcher_control_style = {
					'z-index' : 3,
					'position' : 'relative'
				};

				function get_flot_colors() {
					if (radmin_current_theme === 'pink') {
						return [ '#E63E5D', '#97AF22', '#9D3844', '#533436',
								'#082D35' ];
					} else if (radmin_current_theme === 'green') {
						return [ '#42826C', '#FFC861', '#A5C77F', '#6d9f00',
								'#002F32' ];
					} else {
						return [ '#49AFCD', '#FF6347', '#38849A', '#BF4A35',
								'#999', '#555' ];
					}

					return [ '#49AFCD', '#FF6347', '#38849A', '#BF4A35',
							'#999', '#555' ];
				}

				function get_sparkline_colors() {
					if (radmin_current_theme === 'pink') {
						return [ '#E63E5D', '#97AF22' ];
					} else if (radmin_current_theme === 'green') {
						return [ '#42826C', '#FFC861' ];
					} else {
						return [ '#49AFCD', '#FF6347' ];
					}

					return [ '#49AFCD', '#FF6347' ];
				}

				var sparkline_colors = get_sparkline_colors();

				/**
				 *  Jquery Load Event
				 *
				 */
				jQuery(function($) {
					// format date inputs
					$("a[rel=popover]").popover();

					$('.bar1-sparkline').sparkline(
							[ 12, 8, 10, 13, 15, -6, -8, 10, 18, 10, -8, -7 ],
							{
								type : 'bar',
								barColor : sparkline_colors[0],
								negBarColor : sparkline_colors[1],
								barWidth : '5',
								height : '20'
							});

					$('.bar2-sparkline').sparkline(
							[ [ 5, 6 ], [ 7, 9 ], [ 9, 5 ], [ 6, 2 ],
									[ 10, 4 ], [ 6, 7 ], [ 5, 4 ], [ 6, 7 ] ],
							{
								type : 'bar',
								stackedBarColor : sparkline_colors,
								barWidth : '5',
								height : '20'
							});

					$('.discrete-sparkline')
							.sparkline(
									[ 12, 11, 13, 14, 13, 12, 11, 12, 13, 14,
											15, 16, 15, 14, 13, 14, 15, 16, 17,
											18, 17, 16, 17 ], {
										type : 'discrete',
										lineColor : sparkline_colors[0],
										height : '20'
									});

					$('.line-sparkline').sparkline(
							[ 12, 11, 13, 14, 13, 12, 11, 12, 13, 14, 15, 16,
									15, 14, 13, 12, 11, 12, 13, 14, 15, 16, 17,
									18 ], {
								type : 'line',
								lineColor : sparkline_colors[0],
								fillColor : sparkline_colors[0],
								height : '20'
							});

					$('#user-popover').popover();

					/**
					 * Sets active and expands menu items based on id of body tag of current page
					 * e.g. <body id="body-index-page"> will result in the menu item with the id="navigation-index-page" having the 
					 * class 'active' added, subsequently it will look for a child div with a class of collapse and add the class 'in' 
					 * and set the height to auto
					 */
					var body_id = $('body').attr('id');
					if (body_id != null) {
						var nav_element = $('#navigation-'
								+ body_id.replace('body-', ''));
						nav_element.addClass('active');
						if (nav_element.has('div.collapse')) {
							var child_nav = nav_element.find('div.collapse');
							child_nav.addClass('in');
							child_nav.css('height: auto;');

						}

					}

					//hide the top-stats when the arrow is clicked
					var item = $('.top-stats');
					var target = $('#hide-top-stats');
					if (item.length > 0 && target.length > 0) {
						target.on('click', function() {
							item.css('position', 'relative');
							item.animate({
								left : '-=1200',
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
			<script type="text/javascript"
				src="scripts/flot/jquery.flot.resize.js"></script>
			<script type="text/javascript" src="scripts/charts.js"></script>
			<script type="text/javascript">
	jQuery(function($){
		flot_sin_cos('flot-line-graph', flot_options_sin_cos);
	});
	</script>
	</body>
</html>

<%}%>