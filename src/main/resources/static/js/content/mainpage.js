// 메인1
document.addEventListener("DOMContentLoaded", function() {
    $('.m1_slide').slick({
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
});
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

// 메인2

