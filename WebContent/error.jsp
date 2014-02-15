<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.demo.AttributeConstants"%>
<%@page import="com.demo.data.CustomerData"%>
<%@ page isErrorPage="true" %>  
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<style>



body{height:100%;
   width:100%;
   background-image:url(back2.jpg);/*your background image*/  
   background-repeat:no-repeat;/*we want to have one single image not a repeated one*/  
   background-size:cover;/*this sets the image to fullscreen covering the whole screen*/  
   /*css hack for ie*/     
   filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='.image.jpg',sizingMethod='scale');
   -ms-filter:"progid:DXImageTransform.Microsoft.AlphaImageLoader(src='image.jpg',sizingMethod='scale')";
}

#background-container {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    overflow: hidden !important;
}

#background-container:after {
    position: absolute;
    content: '';
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    
}

#background-container img {
    position: absolute;
    min-width: 1920px;
    top: 0;
    left: 50%;
    margin-left: -960px;
}

header {
    width: 270px;
    position: fixed;
    top: 140px;
    left: 50%;
	margin-top:6%;
    margin-left: -600px;
    z-index: 9999;
    transition: all 0.17s ease-in-out;
    -moz-transition: all 0.17s ease-in-out;
    -webkit-transition: all 0.17s ease-in-out;
    -o-transition: all 0.17s ease-in-out;
	
}



.header-logo {
    
    text-align: center;
    
}

.main-menu > li {
    display: block;
    position: relative;
    text-align: center;
	padding:5px;
}

.main-menu > li > a {
    display: block;
    
    
    color: #fff;
    font-size: 25px;
    font-family: 'Lato', Calibri, Arial, sans-serif;
    font-weight: bold;
    letter-spacing:3px;
    text-transform: uppercase;
    text-decoration: none;
    position: relative;
    
	border: 0 none;
	border-radius: 10px;
    transition: all 0.17s ease-in-out;
    -moz-transition: all 0.17s ease-in-out;
    -webkit-transition: all 0.17s ease-in-out;
    -o-transition: all 0.17s ease-in-out;
    -webkit-backface-visibility: hidden;
}

.main-menu a:hover {
	
	background:#FFF;
	border: 0 none;
	border-radius: 10px;
	color:#000;
}

.curr {
	background:#FFF;
	border: 0 none;
	border-radius: 10px;
	
}

.current:visited {
	
	color:#000;
	
	
	
}

.border{
background: #FFF;
	border: 0 none;
	border-radius: 10px;
	box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
	padding: 20px 25px;
	
	
	width: 600px;
	height: 30px;
	margin-top: -20px;
	margin-right: 100px;
	float:right;
	margin-bottom:50px;
	
	
}
.border2{
background: #FFF;
	border: 0 none;
	border-radius: 10px;
	box-shadow: 0 0 15px 1px rgba(0, 0, 0, 0.4);
	padding: 20px 30px;
	
	
	width: 600px;
	height: 900px;
	
	margin-right: 90px;
	float:right;
	margin-bottom:50px;
	
	
	
	
}



	.flat-table {
		margin-bottom: 20px;
		border-collapse:collapse;
		font-family: 'Lato', Calibri, Arial, sans-serif;
		border: none;
                border-radius: 3px;
               -webkit-border-radius: 3px;
               -moz-border-radius: 3px;
			   
	   text-align:center;
	   width:100%;
	   height:auto;
	}
	.flat-table th, .flat-table td {
		box-shadow: inset 0 -1px rgba(0,0,0,0.25), 
			inset 0 1px rgba(0,0,0,0.25);
	}
	.flat-table th {
		font-weight: normal;
		-webkit-font-smoothing: antialiased;
		padding: 0.5em;
		color: #FFF;
		text-shadow: 0 0 1px rgba(0,0,0,0.1);
		font-size: 1.3em;
	}
	.flat-table td {
		color: #f7f7f7;
		padding: 0.7em 0.7em 0.7em 1em;
		text-shadow: 0 0 1px rgba(255,255,255,0.1);
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
		background: rgba(0,0,0,0.19);
	}
	.flat-table-2 tr:hover {
		background: rgba(0,0,0,0.1);
	}
	.flat-table-2 {
		background: #f06060;
	}
	.flat-table-3 {
		background: #52be7f;
	}
	.flat-table-3 tr:hover {
		background: rgba(0,0,0,0.1);
	}
	
	th:first-child { border-radius: 9px 0 0 0; }

th:last-child { border-radius: 0 9px 0 0; }

tr:last-child td:first-child { border-radius: 0 0 0 9px; }

tr:last-child td:last-child { border-radius: 0 0 9px 0; }



.btn, .stock, .indices, .commo {
  border: none;
  font-size: 20px;
  font-weight: normal;
  line-height: 1.4;
  border-radius: 4px;
  padding: 20px 30px;
  -webkit-font-smoothing: subpixel-antialiased;
  -webkit-transition: 0.25s linear;
  transition: 0.25s linear;
  text-transform: uppercase;
    text-decoration: none;
	font-family:'Lato', Calibri, Arial, sans-serif;
	
	
	
}
.btrade, .tdrop {
  border: none;
  font-size: 16px;
  font-weight: normal;
  line-height: 1.4;
  border-radius: 4px;
  padding: 3px 15px;
  -webkit-font-smoothing: subpixel-antialiased;
  -webkit-transition: 0.25s linear;
  transition: 0.25s linear;
  text-transform: uppercase;
    text-decoration: none;
	font-family:'Lato', Calibri, Arial, sans-serif;
	
	
	
}





.topb{
	
 border: none;
  font-size: 16px;
  font-weight: normal;
  line-height: 1.4;
  border-radius: 4px;
  padding: 3px 15px;
  -webkit-font-smoothing: subpixel-antialiased;
  -webkit-transition: 0.25s linear;
  transition: 0.25s linear;
  text-transform: uppercase;
    text-decoration: none;
	font-family:'Lato', Calibri, Arial, sans-serif;	
margin-right:7px;
}
.topb submit:hover{
	color:black;
	background-color:white;
}


.btn:hover,
.btn:focus, .btrade:hover, .topb:hover {
  color: #ffffff;
}
.btn:active,
.btn.active {
  outline: none;
  -webkit-box-shadow: none;
  box-shadow: none;
  
  
}
.btn.disabled,
.btn[disabled],
fieldset[disabled] .btn {
  background-color: #bdc3c7;
  color: rgba(255, 255, 255, 0.75);
  opacity: 0.7;
  filter: alpha(opacity=70);
}
.btn > [class^="fui-"] {
  margin: 0 1px;
  position: relative;
  line-height: 1;
  top: 1px;
}

.btn-default {
  color: #ffffff;
  background-color: #bdc3c7;
}
.btn-default:hover,
.btn-default:focus,
.btn-default:active,
.btn-default.active,
.open .dropdown-toggle.btn-default {
  color: #ffffff;
  background-color: #cacfd2;
  border-color: #cacfd2;
  
}
.btn-default:active,
.btn-default.active,
.open .dropdown-toggle.btn-default {
  background: #a1a6a9;
}

.btn-primary {
  color: #ffffff;
  background-color: #1abc9c;
}
.btn-primary:hover,
.btn-primary:focus,
.btn-primary:active,
.btn-primary.active,
.open .dropdown-toggle.btn-primary {
  color: #ffffff;
  background-color: #48c9b0;
  border-color: #48c9b0;
}

.curren {
	
float:right;
margin-top:150px;
margin-right:-20px;
 z-index:-9999; 
position:relative;


}



.stock {
  color: #ffffff;
  background-color: #e74c3c;
}

.stock:hover,
.stock:focus,
.stock:active

{
  color: #ffffff;
  background-color: #ec7063;
  border-color: #ec7063;
}
.stock:active,
.stock.active {
  background: #c44133;
}

.indices, .btrade, .tdrop {
  color: #ffffff;
  background-color: #f1c40f;
}
.tdrop {
  color: #000;
  background-color: #FFF;
}


.indices:hover,
.indices:focus,
.indices:active,
.indices.active {
  color: #ffffff;
  background-color: #f5d313;
  border-color: #f5d313;
}
indices:active,
indices.active {
  background: #cda70d;
}

.commo {
  color: #ffffff;
  background-color: #3498db;
  
}
.commo:hover,
.commo:focus,
.commo:active,
.commo.active {
  color: #ffffff;
  background-color: #5dade2;
  border-color: #5dade2;
}
.commo:active,
.commo.active {
  background: #2c81ba;
}

.btn:visited {

background: #FFF;	
	
	color: #336ca6;
	 
	
}

.welcome {

font-family:'Lato', Calibri, Arial, sans-serif;	
	float:left;
}

</style>

</head>




<body>

<jsp:include page="title.jsp" />	
<jsp:include page="Navigation.jsp" />		
<jsp:include page="header.jsp" />
<% exception.printStackTrace(); %>
<div class="border2">
<p style="background-color:lightblue; text-align:center; padding-top:10px;  height:40px; border-radius:5px;  font-size: 16px; font-weight: bold;"> Error Occured in Trading System</p>
<p style="background: lightgray ; padding:5px; border-radius:5px;font-size: 16px; font-weight: bold;">Error Message : <%= exception.getMessage() %></p>
</div>
</body>
</html>