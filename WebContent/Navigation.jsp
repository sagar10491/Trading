<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page errorPage="error.jsp" %>  
<html>
<head>

</head>
<body>
<header class="clearfix active">
			
			
			<div class="navbar-vertical">
				<ul class="main-menu">

					<li class="drop"><a class="curr current" href="homepage.jsp" style="padding:10px;"><span>Home</span></a>
						
					</li>
					<li class="drop"><a href="tradepage.jsp" style="padding:10px;">Trade</a>
						
					</li>
					<li><a href="bookTrade.jsp" style="padding:10px;"><span>Book trading</span></a></li>
					<li class="drop"><a href="<%=session.getAttribute("website").toString() %>" style="padding:10px;"><span>Deposit</span></a>
						
					</li>
					<li><a href="profile.jsp" style="padding:10px;"><span>My profile</span></a></li>
                    
                    </ul>
					
			</div>
		</header>
</body>
</html>