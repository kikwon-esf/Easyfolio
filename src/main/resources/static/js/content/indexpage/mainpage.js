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

const items = {
    items1: document.querySelectorAll('.item1'),
    items2: document.querySelectorAll('.item2'),
    items3: document.querySelectorAll('.item3'),
    items4: document.querySelectorAll('.item4'),
    items5: document.querySelectorAll('.item5'),
};

let currentOpenedItem = null; // 현재 열려있는 아이템 추적

const closeItems = (exceptItem) => {
    Object.values(items).forEach(itemList => {
        itemList.forEach(item => {
            if (item !== exceptItem) {
                item.clicked = false;
                gsap.to(item, {
                    width: '140px',
                    duration: 2,
                    ease: 'elastic(1, .6)'
                });
            }
        });
    });
};

const expandItem = (item) => {
    item.clicked = true;
    gsap.to(item, {
        width: '415px',
        duration: 2.5,
        ease: 'elastic(1, .3)'
    });
};

const collapseItem = (item) => {
    item.clicked = false;
    gsap.to(item, {
        width: '140px',
        duration: 2,
        ease: 'elastic(1, .6)'
    });
};

const initializeItems = () => {
    // 초기에 3번 아이템만 열기
    expandItem(items.items3[0]);
    currentOpenedItem = items.items3[0]; // 현재 열려있는 아이템 업데이트

    // 나머지 아이템들은 닫기
    Object.values(items).forEach(itemList => {
        itemList.forEach(item => {
            if (item !== items.items3[0]) {
                collapseItem(item);
            }
            item.addEventListener('mouseover', handleMouseOver);
            item.addEventListener('mouseleave', handleMouseLeave);
        });
    });
};

const handleMouseOver = (event) => {
    const item = event.currentTarget;
    if (!item.clicked) {
        // 현재 열린 아이템이 있으면 닫기
        if (currentOpenedItem) {
            collapseItem(currentOpenedItem);
        }

        // 클릭된 아이템 열기
        expandItem(item);
        currentOpenedItem = item;
    }
};

const handleMouseLeave = (event) => {
    const item = event.currentTarget;
    if (item.clicked) {
        // 마우스가 벗어났을 때 닫기
        collapseItem(item);
    } else {
        // 아이템을 오버하지 않을 때 .item3만 열기
        if (currentOpenedItem !== item3) {
            closeItems();
            expandItem(item3);
            currentOpenedItem = item3;
        }
    }
};

// 초기화
initializeItems();

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
