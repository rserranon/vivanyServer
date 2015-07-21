<article class="timeline_post">
  <h1 class="tl-post_date"><g:formatDate format="yyyy-MM-dd" date="${it.entryDate}"/></h1>
  <div class="tl-post_card">
    <header class="tl-post_header">
      <h2 class="tl-post_title">Visita: </h2>
      <p class="tl-post_tags">Médicos:<span>${it.doctor}</span></p>
      <p class="tl-post_tags">Lugar:<span>${it.heldAt}</span></p>
      <p class="tl-post_tags">Diagnóstico:<span>${it.diagnoseName}</span></p>
      <br></br>
      <p class="tl-post_meta">${it.summary}</p>
    </header>
    <div class="tl-post_content">
      <div class="content_wrap">
        <img class="tl-post_image right js-lazy" data-src="http://www.radgray.com/wp-content/uploads/2011/12/Sarcoidosis-rx.jpg" src="http://www.radgray.com/wp-content/uploads/2011/12/Sarcoidosis-rx.jpg" alt="responsive web design">
        <noscript><img class="tl-post_image right" src="img/rwd.png" alt="responsive web design"></noscript>
        <p class="tl-post_text">${it.description}</p>
      </div>
    </div>
    <div class="tl-post_footer">
      <a class="tl-post_readmore" rel="nofollow" href="http://en.wikipedia.org/wiki/Responsive_web_design" title="Check the article at wikipedia">Editar...</a>
    </div>
  </div>
</article>
