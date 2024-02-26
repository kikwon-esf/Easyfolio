document.addEventListener("DOMContentLoaded", function() {
    $('.m1_slide').slick({
        autoplay: true,
        autoplaySpeed: 5000,
        speed: 1000,
        slidesToShow: 1,
        variableWidth: true,
        dots: true,
        prevArrow: '.btn.prev',
        nextArrow: '.btn.next',
        appendDots: '.btn.pager',
    });
});
