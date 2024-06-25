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
// document.addEventListener('DOMContentLoaded', function() {
//     item3.style.width = '415px';
//     currentOpenedItem = item3;
// });

const items = document.querySelectorAll('.sit_group div');
const item1 = document.querySelector('.item1');
const item2 = document.querySelector('.item2');
const item3 = document.querySelector('.item3');
const item4 = document.querySelector('.item4');
const item5 = document.querySelector('.item5');
items.forEach(item => {
    item.addEventListener('mouseover', function() {
        item.classList.add('hoverItem');
        if(item != item3 && document.querySelector('.item3.hoverItem')){
            item3.classList.remove('hoverItem')
        } // item-large 클래스 추가
    });

    item.addEventListener('mouseout', function() {
        item.classList.remove('hoverItem'); // item-large 클래스 제거
    });
});

const sitGroup = document.querySelector('.sit_group');
sitGroup.addEventListener('mouseleave', function() {
    item3.classList.add('hoverItem');
});


Splitting();

// 모든 .card 요소를 선택
/*
var cards = document.querySelectorAll('.card');

cards.forEach(function (card) {
    card.addEventListener('mousemove', function (e) {
        var x = e.offsetX;
        var y = e.offsetY;
        var rotateY = -(x - card.offsetWidth / 2) / 10; // x 위치에 따라 회전 각도를 조정
        var rotateX = (y - card.offsetHeight / 2) / 10; // y 위치에 따라 회전 각도를 조정

        card.style.transform = `perspective(350px) rotateX(${rotateX}deg) rotateY(${rotateY}deg)`;
        card.style.transition = `transform 0.5s ease-out`;
    });

    card.addEventListener('mouseleave', function () {
        card.style.transform = 'perspective(350px) rotateX(0) rotateY(0)';
        card.style.transition = 'transform 0.5s ease-out'; // 원래 상태로 되돌아가는 시간 조정
    });
});
*/

const angle = 20;

const lerp = (start, end, amount) => {
    return (1 - amount) * start + amount * end;
};

const remap = (value, oldMax, newMax) => {
    const newValue = ((value + oldMax) * (newMax * 2)) / (oldMax * 2) - newMax;
    return Math.min(Math.max(newValue, -newMax), newMax);
};

window.addEventListener("DOMContentLoaded", (event) => {
    const typeCards = document.querySelectorAll(".typeCard");
    typeCards.forEach((e) => {
        // 초기 설정
        e.dataset.rotateX = 0;
        e.dataset.rotateY = 0;
        e.style.setProperty("--rotateY", "0deg");
        e.style.setProperty("--rotateX", "0deg");

        e.addEventListener("mousemove", (event) => {
            const rect = e.getBoundingClientRect();
            const centerX = (rect.left + rect.right) / 2;
            const centerY = (rect.top + rect.bottom) / 2;
            const posX = event.clientX - centerX;
            const posY = event.clientY - centerY;
            const x = remap(posX, rect.width / 2, angle);
            const y = remap(posY, rect.height / 2, angle);
            e.dataset.rotateX = x;
            e.dataset.rotateY = -y;
        });

        e.addEventListener("mouseout", (event) => {
            e.dataset.rotateX = 0;
            e.dataset.rotateY = 0;
        });
    });

    const update = () => {
        typeCards.forEach((e) => {
            let currentX = parseFloat(e.style.getPropertyValue('--rotateY')) || 0;
            let currentY = parseFloat(e.style.getPropertyValue('--rotateX')) || 0;
            const x = lerp(currentX, parseFloat(e.dataset.rotateX), 0.05);
            const y = lerp(currentY, parseFloat(e.dataset.rotateY), 0.05);
            e.style.setProperty("--rotateY", x + "deg");
            e.style.setProperty("--rotateX", y + "deg");
        });
    };
    setInterval(update, 1000 / 60);
});
