<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<div id="carouselExampleIndicators" class="carousel slide mt-15" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="/static/1.jpg" alt="First slide">
      <div class="carousel-caption d-none d-md-block">
        <h5>Рады приветствовать вас!</h5>
            <p>Это простое веб-приложение по аренде автомобилей</p>
            <p>(для более подробной информации свайпните направо)</p>
            </div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/static/2.png" alt="Second slide">
      <div class="carousel-caption d-none d-md-block">
    <h5>На этом сайте каждый пользователь может добавлять свои объявления</h5>
        <p>А так же просматривать объявления других пользователей</p>
        </div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="/static/3.png" alt="Third slide">
      <div class="carousel-caption d-none d-md-block">
    <h5>Просматривайте и тестируйте</h5>
        <p>Удачи!!!</p>
        <p>(авторы проекта: Исаев Атай, Ташболотов Бекбулат)</p>
        </div>
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

<#include "parts/carAdd.ftl" />

</@c.page>