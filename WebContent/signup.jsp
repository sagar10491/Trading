<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Trading :: Welcome to Earn</title>
<meta name="description"
	content="An admin template from Square Turtle Studios">
<meta name="author" content="Square Turtle Studios">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/jquery.cloneposition.js"></script>

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
	<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
	<![endif]-->
<!--[if lte IE 9]>
	<script src="scripts/placeholder.js" type="text/javascript"></script>
	<![endif]-->
<script type="text/javascript" src="scripts/theme.js"></script>

<style>

/* remove codrops styles and reset the whole thing */
#container_demo {
	text-align: left;
	margin: 0;
	padding: 0;
	margin: 0 auto;
	font-family: "Trebuchet MS", "Myriad Pro", Arial, sans-serif;
}

/** fonts used for the icons **/
@font-face {
	font-family: 'FontomasCustomRegular';
	src: url('fonts/fontomas-webfont.eot');
	src: url('fonts/fontomas-webfont.eot?#iefix')
		format('embedded-opentype'), url('fonts/fontomas-webfont.woff')
		format('woff'), url('fonts/fontomas-webfont.ttf') format('truetype'),
		url('fonts/fontomas-webfont.svg#FontomasCustomRegular') format('svg');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'FranchiseRegular';
	src: url('fonts/franchise-bold-webfont.eot');
	src: url('fonts/franchise-bold-webfont.eot?#iefix')
		format('embedded-opentype'), url('fonts/franchise-bold-webfont.woff')
		format('woff'), url('fonts/franchise-bold-webfont.ttf')
		format('truetype'),
		url('fonts/franchise-bold-webfont.svg#FranchiseRegular') format('svg');
	font-weight: normal;
	font-style: normal;
}

a.hiddenanchor {
	display: none;
}
/** The wrapper that will contain our two forms **/
#wrapper {
	width: 60%;
	right: 0px;
	min-height: 560px;
	margin: 0px auto;
	width: 500px;
	position: relative;
	margin-left: -50px;
}
/**** Styling the form elements **/

/**** general text styling ****/
#wrapper a {
	color: rgb(95, 155, 198);
	text-decoration: underline;
}

#wrapper h1 {
	font-size: 48px;
	color: rgb(6, 106, 117);
	padding: 2px 0 10px 0;
	font-family: 'FranchiseRegular', 'Arial Narrow', Arial, sans-serif;
	font-weight: bold;
	text-align: center;
	padding-bottom: 30px;
}
/** For the moment only webkit supports the background-clip:text; */
#wrapper h1 {
	background: -webkit-repeating-linear-gradient(-45deg, rgb(18, 83, 93),
		rgb(18, 83, 93) 20px, rgb(64, 111, 118) 20px, rgb(64, 111, 118) 40px,
		rgb(18, 83, 93) 40px );
	-webkit-text-fill-color: transparent;
	-webkit-background-clip: text;
}

#wrapper h1:after {
	content: ' ';
	display: block;
	width: 100%;
	height: 2px;
	margin-top: 10px;
	background: -moz-linear-gradient(left, rgba(147, 184, 189, 0) 0%,
		rgba(147, 184, 189, 0.8) 20%, rgba(147, 184, 189, 1) 53%,
		rgba(147, 184, 189, 0.8) 79%, rgba(147, 184, 189, 0) 100% );
	background: -webkit-gradient(linear, left top, right top, color-stop(0%, rgba(147,
		184, 189, 0) ), color-stop(20%, rgba(147, 184, 189, 0.8) ),
		color-stop(53%, rgba(147, 184, 189, 1) ),
		color-stop(79%, rgba(147, 184, 189, 0.8) ),
		color-stop(100%, rgba(147, 184, 189, 0) ) );
	background: -webkit-linear-gradient(left, rgba(147, 184, 189, 0) 0%,
		rgba(147, 184, 189, 0.8) 20%, rgba(147, 184, 189, 1) 53%,
		rgba(147, 184, 189, 0.8) 79%, rgba(147, 184, 189, 0) 100% );
	background: -o-linear-gradient(left, rgba(147, 184, 189, 0) 0%,
		rgba(147, 184, 189, 0.8) 20%, rgba(147, 184, 189, 1) 53%,
		rgba(147, 184, 189, 0.8) 79%, rgba(147, 184, 189, 0) 100% );
	background: -ms-linear-gradient(left, rgba(147, 184, 189, 0) 0%,
		rgba(147, 184, 189, 0.8) 20%, rgba(147, 184, 189, 1) 53%,
		rgba(147, 184, 189, 0.8) 79%, rgba(147, 184, 189, 0) 100% );
	background: linear-gradient(left, rgba(147, 184, 189, 0) 0%,
		rgba(147, 184, 189, 0.8) 20%, rgba(147, 184, 189, 1) 53%,
		rgba(147, 184, 189, 0.8) 79%, rgba(147, 184, 189, 0) 100% );
}

#wrapper p {
	margin-bottom: 15px;
}

#wrapper p:first-child {
	margin: 0px;
}

#wrapper label {
	color: rgb(64, 92, 96);
	position: relative;
}

/**** advanced input styling ****/
/* placeholder */
::-webkit-input-placeholder {
	color: rgb(190, 188, 188);
	font-style: italic;
}

input:-moz-placeholder,textarea:-moz-placeholder {
	color: rgb(190, 188, 188);
	font-style: italic;
}

input {
	outline: none;
}

/* all the input except submit and checkbox */
#wrapper input:not ([type="checkbox"] ){
	width: 90%;
	margin-top: 4px;
	padding: 10px 5px 10px 32px;
	border: 1px solid rgb(178, 178, 178);
	-webkit-appearance: textfield;
	-webkit-box-sizing: content-box;
	-moz-box-sizing: content-box;
	box-sizing: content-box;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
	-moz-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
	box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.6) inset;
	-webkit-transition: all 0.2s linear;
	-moz-transition: all 0.2s linear;
	-o-transition: all 0.2s linear;
	transition: all 0.2s linear;
}

#wrapper input:not ([type="checkbox"] ):active,#wrapper input:not ([type="checkbox"]
	):focus {
	border: 1px solid rgba(91, 90, 90, 0.7);
	background: rgba(238, 236, 240, 0.2);
	-webkit-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.9) inset;
	-moz-box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.9) inset;
	box-shadow: 0px 1px 4px 0px rgba(168, 168, 168, 0.9) inset;
}

/** the magic icon trick ! **/
[data-icon]:after {
	content: attr(data-icon);
	font-family: 'FontomasCustomRegular';
	color: rgb(106, 159, 171);
	position: absolute;
	left: 10px;
	top: 35px;
}

/*styling both submit buttons */
#wrapper p.button input {
	width: 30%;
	cursor: pointer;
	background: rgb(61, 157, 179);
	padding: 8px 5px;
	font-family: 'BebasNeueRegular', 'Arial Narrow', Arial, sans-serif;
	color: #fff;
	font-size: 24px;
	border: 1px solid rgb(28, 108, 122);
	margin-bottom: 10px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, 0.5);
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	-webkit-box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset, 0px 0px
		0px 3px rgb(254, 254, 254), 0px 5px 3px 3px rgb(210, 210, 210);
	-moz-box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset, 0px 0px 0px
		3px rgb(254, 254, 254), 0px 5px 3px 3px rgb(210, 210, 210);
	box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.07) inset, 0px 0px 0px 3px
		rgb(254, 254, 254), 0px 5px 3px 3px rgb(210, 210, 210);
	-webkit-transition: all 0.2s linear;
	-moz-transition: all 0.2s linear;
	-o-transition: all 0.2s linear;
	transition: all 0.2s linear;
}

#wrapper p.button input:hover {
	background: rgb(74, 179, 198);
}

#wrapper p.button input:active,#wrapper p.button input:focus {
	background: rgb(40, 137, 154);
	position: relative;
	top: 1px;
	border: 1px solid rgb(12, 76, 87);
	-webkit-box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.2) inset;
	-moz-box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.2) inset;
	box-shadow: 0px 1px 6px 4px rgba(0, 0, 0, 0.2) inset;
}

p.login.button,p.signin.button {
	text-align: right;
	margin: 5px 0;
}

/* styling the checkbox "keep me logged in"*/
.keeplogin {
	margin-top: -5px;
}

.keeplogin input,.keeplogin label {
	display: inline-block;
	font-size: 12px;
	font-style: italic;
}

.keeplogin input#loginkeeping {
	margin-right: 5px;
}

.keeplogin label {
	width: 80%;
}

/*styling the links to change from one form to another */
p.change_link {
	margin-bottom: 100px;
	position: absolute;
	color: rgb(127, 124, 124);
	left: 0px;
	height: 20px;
	width: 440px;
	padding: 17px 30px 20px 30px;
	font-size: 16px;
	text-align: right;
	border-top: 1px solid rgb(219, 229, 232);
	-webkit-border-radius: 0 0 5px 5px;
	-moz-border-radius: 0 0 5px 5px;
	border-radius: 0 0 5px 5px;
	background: rgb(225, 234, 235);
	background: -moz-repeating-linear-gradient(-45deg, rgb(247, 247, 247),
		rgb(247, 247, 247) 15px, rgb(225, 234, 235) 15px, rgb(225, 234, 235)
		30px, rgb(247, 247, 247) 30px );
	background: -webkit-repeating-linear-gradient(-45deg, rgb(247, 247, 247),
		rgb(247, 247, 247) 15px, rgb(225, 234, 235) 15px, rgb(225, 234, 235)
		30px, rgb(247, 247, 247) 30px );
	background: -o-repeating-linear-gradient(-45deg, rgb(247, 247, 247),
		rgb(247, 247, 247) 15px, rgb(225, 234, 235) 15px, rgb(225, 234, 235)
		30px, rgb(247, 247, 247) 30px );
	background: repeating-linear-gradient(-45deg, rgb(247, 247, 247),
		rgb(247, 247, 247) 15px, rgb(225, 234, 235) 15px, rgb(225, 234, 235)
		30px, rgb(247, 247, 247) 30px );
}

#wrapper p.change_link a {
	display: inline-block;
	font-weight: bold;
	background: rgb(247, 248, 241);
	padding: 2px 6px;
	color: rgb(29, 162, 193);
	margin-left: 10px;
	text-decoration: none;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	border: 1px solid rgb(203, 213, 214);
	-webkit-transition: all 0.4s linear;
	-moz-transition: all 0.4s linear;
	-o-transition: all 0.4s linear;
	-ms-transition: all 0.4s linear;
	transition: all 0.4s linear;
}

#wrapper p.change_link a:hover {
	color: rgb(57, 191, 215);
	background: rgb(247, 247, 247);
	border: 1px solid rgb(74, 179, 198);
}

#wrapper p.change_link a:active {
	position: relative;
	top: 1px;
}
/** Styling both forms **/
#register,#login {
	position: absolute;
	top: 0px;
	width: 88%;
	padding: 18px 6% 60px 6%;
	margin: 0 0 35px 0;
	background: rgb(247, 247, 247);
	border: 1px solid rgba(147, 184, 189, 0.8);
	-webkit-box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px
		5px rgba(208, 223, 226, 0.4) inset;
	-moz-box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px 5px
		rgba(208, 223, 226, 0.4) inset;
	box-shadow: 0pt 2px 5px rgba(105, 108, 109, 0.7), 0px 0px 8px 5px
		rgba(208, 223, 226, 0.4) inset;
	-webkit-box-shadow: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

#register {
	z-index: 21;
	opacity: 0;
}

#login {
	z-index: 22;
}

#toregister:target  ~ #wrapper #register,#tologin:target  ~ #wrapper #login
	{
	z-index: 22;
	-webkit-animation-name: fadeInLeft;
	-moz-animation-name: fadeInLeft;
	-ms-animation-name: fadeInLeft;
	-o-animation-name: fadeInLeft;
	animation-name: fadeInLeft;
	-webkit-animation-delay: .1s;
	-moz-animation-delay: .1s;
	-o-animation-delay: .1s;
	-ms-animation-delay: .1s;
	animation-delay: .1s;
}

#toregister:target  ~ #wrapper #login,#tologin:target  ~ #wrapper #register
	{
	-webkit-animation-name: fadeOutLeft;
	-moz-animation-name: fadeOutLeft;
	-ms-animation-name: fadeOutLeft;
	-o-animation-name: fadeOutLeft;
	animation-name: fadeOutLeft;
}

/** the actual animation, credit where due : http://daneden.me/animate/ ***/
.animate {
	-webkit-animation-duration: 0.5s;
	-webkit-animation-timing-function: ease;
	-webkit-animation-fill-mode: both;
	-moz-animation-duration: 0.5s;
	-moz-animation-timing-function: ease;
	-moz-animation-fill-mode: both;
	-o-animation-duration: 0.5s;
	-o-animation-timing-function: ease;
	-o-animation-fill-mode: both;
	-ms-animation-duration: 0.5s;
	-ms-animation-timing-function: ease;
	-ms-animation-fill-mode: both;
	animation-duration: 0.5s;
	animation-timing-function: ease;
	animation-fill-mode: both;
}

/** yerk some ugly IE fixes 'cause I know someone will ask "why does it look ugly in IE?", no matter how many warnings I will put in the article */
.lt8 #wrapper input {
	padding: 10px 5px 10px 32px;
	width: 92%;
}

.lt8 #wrapper input[type=checkbox] {
	width: 10px;
	padding: 0;
}

.lt8 #wrapper h1 {
	color: #066A75;
}

.lt8 #register {
	display: none;
}

.lt8 p.change_link,.ie9 p.change_link {
	position: absolute;
	height: 90px;
	background: transparent;
}
</style>
</head>
<body id="body-login">

	<div class="container-fluid">

		<div class="row-fluid">
			<div class="span4"></div>

			<div class="span4 login-span">
				<div class="login-radmin align-center">
					<img src="logo.png" style="height: 100px; width: 200px;"></img>
					<!-- <h1 class="brand">
						<span class="rad">Income</span>rush
					</h1>
				 -->
				</div>
				<div id="wrapper">
					<div id="login" class="animate form">

						<form method="post" action="Register">
							<%
								if ((request.getParameter("register_invalid") != null)) {
							%>
							<br />
							<a href="#">User Already Exists!</a>
							<%
								}
							%>
							<input type="hidden" name="a_id" value="4cdsa4efdcccb" /> <input
								type="hidden" name="subCampaignId" value="2" /> <input
								type="hidden" name="campaignId" value="243" /> <input
								type="hidden" name="debugMode" value="true" /> <input
								type="hidden" name="broker" value="2" /> <input type="hidden"
								name="currency" value="USD" /> <input type="hidden"
								name="Country" value="104" /> <input type="hidden"
								name="birthday" value="1974-10-10" /> <input type="hidden"
								name="gender" placeholder="male" />
							<h1 style="font-size: 36px;">Register</h1>
							<p>
								<label for="username" class="uname"> First Name </label> <input
									id="FirstName" name="FirstName" required type="text"
									placeholder="Spot" />
							</p>
							<p>
								<label for="password" class="youpasswd"> Last Name </label> <input
									id="LastName" name="LastName" required type="text"
									placeholder="Test" />
							</p>
							<p>
								<label for="username" class="uname"> Email / Username </label> <input
									id="email" name="email" required type="text"
									placeholder="myusername or mymail@mail.com" />
							</p>
							<p>
								<label for="password" class="youpasswd"> Your password </label>
								<input id="password" name="password" required type="password"
									placeholder="eg. X8df!90EO" />
							</p>

							<p>
								<label for="username" class="uname"> Phone </label> <input
									id="Phone" name="Phone" required type="text"
									placeholder="112-1234567" />
							</p>
							<p class="keeplogin"></p>

							<p class="login button">
								<input type="submit" value="Sign Up" />
							</p>
							<p class="change_link">
								Already a member ? <a href="index.jsp" class="to_register">Go
									and log in</a>
							</p>
						</form>

					</div>




				</div>
			</div>
		</div>
	</div>

	<div class="span4"></div>
	</div>

	</div>

</body>
</html>