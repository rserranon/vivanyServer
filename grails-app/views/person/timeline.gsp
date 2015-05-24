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
						<h2 class="milestone_title">2014</h2>
						<p class="milestone_meta">Paciente: ${person}</p>
					</div>
					<g:each in="${person.entries}">
					    <p>Title: ${it.entryDate}</p>
					</g:each>
					<article class="timeline_post">
						<h1 class="tl-post_date">26-Diciembre</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">Visita: </h2>
								<p class="tl-post_tags">Médicos:<span>Dr. Luis Fernando Uriarte</span><span>Dr. Eduardo Mundo P.</span></p>					
								<p class="tl-post_tags">Lugar:<span>Laboratiorio de Imagen Hospital ABC</span></p>
								<p class="tl-post_tags">Diagnóstico:<span>Influenza</span></p>
								<br></br>								
								<p class="tl-post_meta">Se presento el paciente con sintomas de dolor abdominal y fiebre, lorep ipsum dae mate er doe nal resis dum</p>			
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">
									<img class="tl-post_image right js-lazy" data-src="http://www.radgray.com/wp-content/uploads/2011/12/Sarcoidosis-rx.jpg" src="http://www.radgray.com/wp-content/uploads/2011/12/Sarcoidosis-rx.jpg" alt="responsive web design">
									<noscript><img class="tl-post_image right" src="img/rwd.png" alt="responsive web design"></noscript>						
									<p class="tl-post_text">Ethan Marcotte coined the term responsive web design (RWD) in a May 2010 article in A List Apart.He described the theory and practice of responsive web design in his brief 2011 book titled Responsive Web Design.</p>
									<p class="tl-post_text">Responsive design was listed as #2 in Top Web Design Trends for 2012 by .net magazine after progressive enhancement at #1.</p>
									<p class="tl-post_text">Responsive web templates are designed to look great on a variety of different screen sizes, so anyone can view your site in a crisp and clear manner where ever they go.</p>
									<img class="tl-post_image js-lazy" data-src="img/placeholder.jpg" src="img/placeholder.jpg" alt="responsive web design">
									<noscript><img class="tl-post_image" src="img/rwd-1.png" alt="responsive web design"></noscript>
									<p class="tl-post_text">A site designed with RWDadapts the layout to the viewing environment by using fluid, proportion-based grids, flexible images, and CSS3 media queries, an extension of the @media rule.</p>
								</div>
							</div>
							<div class="tl-post_footer">							
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/Responsive_web_design" title="Check the article at wikipedia">Editar...</a>
							</div>
						</div>
					</article>
					<article class="timeline_post">
						<h1 class="tl-post_date">28-Diciembre</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">Estudio:</h2>
								<p class="tl-post_meta">"Radiografia de Abdomen, APEX RX con contrastste de, adem, tuesonme, so den amas.</p>
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">					
									<p class="tl-post_text">The Parallax web design trend has grown in popularity because it creates an illusion of depth (or a faux-3D effect) as you scroll through the webpage. Various images on the site will move at different speeds or change in size or at dimension to the site.</p>
									<img class="tl-post_image js-lazy" data-src="img/parallax.jpg" src="img/placeholder.jpg" alt="parallax scheme">
									<noscript><img class="tl-post_image" src="img/parallax.jpg" alt="parallax scheme"></noscript>
									<p class="tl-post_text">Additionally, proponents use parallax backgrounds as a tool to better engage users and improve the overall experience that a website provides.</p>									
								</div>
							</div>
							<div class="tl-post_footer">			
								<p class="tl-post_tags">Médicos:<span>Dr. Luis Fernando Uriarte</span><span>Dr. Eduardo Mundo P.</span></p>					
								<p class="tl-post_tags">Lugar:<span>Laboratiorio de Imagen Hospital ABC</span></p>								
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/Responsive_web_design" title="Check the article at wikipedia">Editar...</a>
							</div>
						</div>
					</article>
					<article class="timeline_post">
						<h1 class="tl-post_date">2010-nowadays</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">Flat UI Design</h2>
								<p class="tl-post_meta">Design that follows principles of  minimalist aesthetic and was influenced by the Bauhaus and Swiss Design schools </p>
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">					
									<p class="tl-post_text">Flat design refers to a style of interface design which removes any stylistic choices that give the illusion of three-dimensions (such as drop shadows, gradients, or textures) and is focused on a minimalist use of simple elements, typography and flat colors.</p>
									<img class="tl-post_image js-lazy" data-src="img/flat.jpg" src="img/placeholder.jpg" alt="flat ui design example">
									<noscript><img class="tl-post_image" src="img/flat.jpg" alt="flat ui design example"></noscript>									
									<p class="tl-post_text">A website with flat design lacks gradients, skeuomorphism, but incorporates solid colors, white space, and crisp typography.</p>									
								</div>
							</div>
							<div class="tl-post_footer">
								<p class="tl-post_tags">Tags:<span>Flat UI design</span><span>minimalism</span><span>Solid colors</span></p>
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/Flat_UI_Design" title="Check the article at wikipedia">Read more...</a>
							</div>
						</div>
					</article>
					<div class="timeline_milestone">						
						<h2 class="milestone_title">Late 2000's</h2>
						<p class="milestone_meta">Rise of HTML5, opensource, mobile web</p>
					</div>
					<article class="timeline_post">
						<h1 class="tl-post_date">April 2010</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">Rise of HTML5</h2>
								<p class="tl-post_meta">HTML5 introduces elements and attributes that reflect typical usage on modern websites</p>
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">
									<img class="tl-post_image right js-lazy" data-src="img/html5.png" src="img/placeholder.jpg" alt="html5 logo">
									<noscript><img class="tl-post_image right" src="img/html5.png" alt="html5 logo"></noscript>	
									<p class="tl-post_text">HTML5 became the topic of mainstream media around April 2010 after Apple Inc's then-CEO Steve Jobs issued a public letter titled "Thoughts on Flash" where he concludes that "[Adobe] Flash is no longer necessary to watch video or consume any kind of web content" and that "new open standards created in the mobile era, such as HTML5, will win".</p>		
									<p class="tl-post_text">In early November 2011, Adobe announced that it will discontinue development of Flash for mobile devices and reorient its efforts in developing tools utilizing HTML5.</p>	
								</div>
							</div>
							<div class="tl-post_footer">
								<p class="tl-post_tags">Tags:<span>HTML5</span><span>Semantic web</span><span>Interactive content</span></p>
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/HTML5" title="Check the article at wikipedia">Read more...</a>
							</div>
						</div>
					</article>
					<article class="timeline_post">
						<h1 class="tl-post_date">2008 - 2010</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">The mobile web</h2>
								<p class="tl-post_meta">All world in your hand. In 2008, mobile access to the internet exceeded desctop access for the first time</p>
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">
									<img class="tl-post_image left js-lazy" data-src="img/mob-1.jpg" src="img/placeholder.jpg" alt="world in your hand with mobile web">
									<noscript><img class="tl-post_image left" src="img/mob-1.jpg" alt="world in your hand with mobile web"></noscript>	
									<p class="tl-post_text">The mobile web refers to access to the world wide web, i.e. the use of browser-based Internet services, from a handheld mobile device, such as a smartphone or a feature phone, connected to a mobile network or other wireless network.</p>
									<p class="tl-post_text">Many businesses looked towards creating a condensed 'mobile' version of their site. Mobile sites contain essentials of the regular website, delivering viewers flagship content and noble features formatted for display on their device.</p>
									<img class="tl-post_image right js-lazy" data-src="img/mob-2.jpg" src="img/placeholder.jpg" alt="be more social with mobile web">
									<noscript><img class="tl-post_image right" src="img/mob-2.jpg" alt="be more social with mobile web"></noscript>
									<p class="tl-post_text">Mobile sites contain essentials of the regular website, delivering viewers flagship content and noble features formatted for display on their device.</p>
									<p class="tl-post_text">The Mobile Web has also been called Web 3.0, drawing parallels to the changes users were experiencing as Web 2.0 websites proliferated.</p>
								</div>
							</div>
							<div class="tl-post_footer">
								<p class="tl-post_tags">Tags:<span>Mobile web</span><span>Mobile sites</span><span>Mobile devices</span></p>
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/Mobile_Web" title="Check the article at wikipedia">Read more...</a>
							</div>
						</div>
					</article>
					<article class="timeline_post">
						<h1 class="tl-post_date">April 2008</h1>
						<div class="tl-post_card">
							<header class="tl-post_header">
								<h2 class="tl-post_title">Rise of the open-source</h2>
								<p class="tl-post_meta">Ready-to-use opensource software became more popular thanks for DVCS hosting such as Github</p>
							</header>
							<div class="tl-post_content">
								<div class="content_wrap">					
									<p class="tl-post_text">The pivotal moment, however, was the creation of git, which has since become the most popular DVCS.</p>
									<img class="tl-post_image right js-lazy" data-src="img/op-1.png" src="img/placeholder.jpg" alt="opensource logo">
									<noscript><img class="tl-post_image right" src="img/op-1.png" alt="opensource logo"></noscript>	
									<p class="tl-post_text">The increasing popularity of open source DVCSs such as git, and then, later, DVCS hosting sites, the most popular of which is GitHub (founded 2008), incrementally reduced the barriers to participation in free software projects still further.</p>
									<img class="tl-post_image left js-lazy" data-src="img/op-2.jpg" src="img/placeholder.jpg" alt="github octocat">
									<noscript><img class="tl-post_image left" src="img/op-2.jpg" alt="github octocat"></noscript>
									<p class="tl-post_text">With sites like GitHub, no longer did potential contributors have to do things like hunt for the URL for the source code repository (which could be in different places on each website, or sometimes tucked away in a README file or developer documentation), or work out how to generate a patch, and if necessary subscribe to the right mailing list so that their patch email would get to the right people.</p>
								</div>
							</div>
							<div class="tl-post_footer">
								<p class="tl-post_tags">Tags:<span>Opensource</span><span>DVCS hosting</span><span>Github</span><span>free software movement</span></p>
								<a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/History_of_free_and_open-source_software" title="Check the article at wikipedia">Read more...</a>
							</div>
						</div>
					</article>
				</section>	
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