<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<h5>Рады приветствовать Вас!</h5>
<div>Это простое веб-приложение для аренды автомобилей.</div>
    </div>

<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="/static/1.jpg" style="width:800px;height:550px;" alt="First slide">
        <h5>...</h5>
            <p>...</p>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/static/1.jpg" style="width:800px;height:550px;" alt="Second slide">
    <h5>...</h5>
        <p>...</p>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/static/1.jpg" style="width:800px;height:550px;" alt="Third slide">
    <h5>...</h5>
        <p>...</p>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

</@c.page>