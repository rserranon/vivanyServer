<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">

		<asset:stylesheet src="vertical-responsive-timeline/style.min.css"/>
        <asset:javascript src="vertical-responsive-timeline/assembled.min.js"/>

        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
	    <meta charset="utf-8">
	    <meta name="description" content="vertical responsive timeline based on html5 and css3">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<!--[if IE]>
			<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
			<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<![endif]-->
		<link href='http://fonts.googleapis.com/css?family=Roboto:400,300&subset=latin,cyrillic' rel='stylesheet' type='text/css'>
		<link href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300&subset=latin,cyrillic' rel='stylesheet' type='text/css'>

		<link rel="stylesheet" href="css/style.min.css">
		<link class="js-color-scheme" rel="stylesheet">
		<!--[if lt IE 9]>
	    	<link rel="stylesheet" href="css/ie-out.min.css">
		<![endif]-->
    </head>
    <body>

		<section class="main" role="main">
			<section class="example">
				<div class="container">
					<p class="general_text">Here you can see how real content can be represented in timeline, for example evolution of web design and web technologies.</p>
					<p class="general_text">Feel free to change color scheme of timeline, using this flat-ui palette</p>
					<div class="color-picker">
						<span class="color midnight js-color" data-color="midnight">Midnight Blue</span>
						<span class="color belize js-color" data-color="belize">Belize Hole</span>
						<span class="color turquoise js-color" data-color="turquoise">Turquoise</span>
						<span class="color nephritis js-color" data-color="nephritis">Nephritis</span>
						<span class="color sun js-color" data-color="sun">Sun Flower</span>
						<span class="color orange js-color" data-color="orange">Orange</span>
						<span class="color alizarin js-color" data-color="alizarin">Alizarin</span>
						<span class="color wisteria js-color" data-color="wisteria">Wisteria</span>
					</div>
				</div>
				<section class="timeline">
					<div class="timeline_milestone">
						<g:set var="year" value="${person?.entries[1]?.entryDate?.year}" />
						<h2 class="milestone_title"><g:formatDate format="yyyy" date="${person?.entries[1]?.entryDate}"/></h2>
						<p class="milestone_meta">Paciente: ${person}</p>
					</div>
					<g:each in="${entryList}"> <!-- not polymorphic yet, but soon-->
            /entry/${it.getClass().getSimpleName().toLowerCase()}Post
            <g:render template="/entry/${it.getClass().getSimpleName().toLowerCase()}Post" collection="${it}" var="it"/>
					</g:each>
			</section>
		</section>
		<!--[if lt IE 9]>
			<div class="browser-msg">
				<h2 class="browser-msg__title">Houston we've had a problem</h2>
				<p class="browser-msg__info">It looks like you’re using an insecure version of Internet Explorer.<br> Using an outdated browser makes your computer unsafe.</p>
				<h3 class="browser-msg__sub">For the best experience on the web, please update your browser.</h3>
				<ul class="browser-msg__list">
					<li class="browser-msg__item">
						<a href="http://www.google.com/chrome" title="Google Chrome">
							<span class="browser-item__img chrome"></span>
							<h2 class="browser-item__title">Google Chrome</h2>
							<p class="browser-item__descr">Built for the modern web and designed to be fast in every possible way.</p>
							<p class="browser-item__note">Visit official website for more info</p>
						</a>
					</li>
					<li class="browser-msg__item">
						<a href="http://www.firefox.com/" title="Mozilla Firefox">
							<span class="browser-item__img firefox"></span>
							<h2 class="browser-item__title">Mozilla Firefox</h2>
							<p class="browser-item__descr">Committed to you, your privacy and an open Web.</p>
							<p class="browser-item__note">Visit official website for more info</p>
						</a>
					</li>
					<li class="browser-msg__item">
						<a href="http://www.apple.com/safari/" title="Apple Safari">
							<span class="browser-item__img safari"></span>
							<h2 class="browser-item__title">Safari</h2>
							<p class="browser-item__descr">The smartest way to surf. Wait less and browse more.</p>
							<p class="browser-item__note">Visit official website for more info</p>
						</a>
					</li>
					<li class="browser-msg__item">
						<a href="http://www.opera.com/" title="Opera">
							<span class="browser-item__img opera"></span>
							<h2 class="browser-item__title">Opera</h2>
							<p class="browser-item__descr">Made to discover. Get a better experience.</p>
							<p class="browser-item__note">Visit official website for more info</p>
						</a>
					</li>
					<li class="browser-msg__item">
						<a href="http://windows.microsoft.com/ie" title="Microsoft Internet Explorer">
							<span class="browser-item__img ie"></span>
							<h2 class="browser-item__title">Internet Explorer</h2>
							<p class="browser-item__descr">Fast and fluid. Stay safer as you browse.</p>
							<p class="browser-item__note">Visit official website for more info</p>
						</a>
					</li>
				</ul>
				<p class="browser-msg__info">Thank you :)</p>
			</div>
		<![endif]-->
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/jquery-1.10.2.min.js"><\/script>')</script>
        <script src="js/assembled.min.js"></script>
    </body>
</html>
