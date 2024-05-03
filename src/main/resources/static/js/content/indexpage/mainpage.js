// 메인1
document.addEventListener("mouseover", function (event) {
    var buttons = document.querySelectorAll('.btn');

    buttons.forEach(function(button) {
        var svg = button.querySelector('svg');

        if (event.target.closest('.btn') === button) {
            svg.setAttribute('fill', '#ffd57a');
        } else {
            svg.setAttribute('fill', '#f4f4f4');
        }
    });
});

$('.btn.pause').click(function () {
    $('.m1_slide').slick('slickPause');
    $(this).hide();
    $('.btn.play').show();
});
$('.btn.play').click(function () {
    $('.m1_slide').slick('slickPlay');
    $(this).hide();
    $('.btn.pause').show();
});
$(document).ready(function(){
    var m1Slide = $('.m1_slide');

    m1Slide.slick({
        autoplay: true,
        autoplaySpeed: 3000,
        speed: 1000,
        slidesToShow: 1,
        variableWidth: true,
        dots: true,
        prevArrow: '.btn.prev',
        nextArrow: '.btn.next',
        appendDots: '.btn.pager',
    });

    var totalSlides = m1Slide.slick('getSlick').slideCount;

    m1Slide.on('afterChange', function(event, slick, currentSlide){
        if(currentSlide < 10){
            $('.current-slide').text("0" + (currentSlide + 1));
        } else{
            $('.current-slide').text(currentSlide + 1); 
        }
        if(totalSlides < 10){
            $('.total-slides').text("0" + (totalSlides));
        } else{
            $('.total-slides').text(totalSlides);
        }
    });
});
// 메인1 popup
// document.addEventListener("mouseover", function (event) {
//     var seSlides = document.querySelectorAll('.sa_slides');
//     var targetElement = event.target;
//     // 'sa_slides' 클래스를 가진 요소인지 확인
//     if (targetElement.classList.contains('sa_slides')) {
//         // 해당 요소의 opacity를 1로 변경
//         targetElement.style.opacity = 1;
//     }
// });
$('.btn.menu').click(function () {
    $('.slideAll').slideDown();
});
$('.btnClose').click(function () {
    $('.slideAll').slideUp();
});

$('.mainAccomSubImg').slick({
    arrows: false,
    variableWidth: true,
    infinite: true,
    centerMode : true,
    focusOnSelect: true,
    slidesToShow: 1,
    speed: 500
});