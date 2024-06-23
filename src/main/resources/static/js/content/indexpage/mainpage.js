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

const closeItems = (itemsList, excludeItem) => {
    itemsList.forEach(item => {
        if (item !== excludeItem && item.clicked) {
            item.clicked = false;
            gsap.to(item, {
                width: '8vw',
                duration: 2,
                ease: 'elastic(1, .6)'
            });
        }
    });
};

const expandItem = (item) => {
    item.clicked = true;
    gsap.to(item, {
        width: '20vw',
        duration: 2.5,
        ease: 'elastic(1, .3)'
    });
};

const collapseItem = (item) => {
    item.clicked = false;
    gsap.to(item, {
        width: '8vw',
        duration: 2,
        ease: 'elastic(1, .6)'
    });
};

const toggleItem = (item) => {
    if (item.clicked) {
        collapseItem(item);
    } else {
        expandItem(item);
    }
};

const expandItems = (itemsList) => {
    itemsList.forEach(item => {
        item.addEventListener('click', () => {
            if (!item.clicked) {
                // 현재 열린 아이템 확인 후 닫기
                Object.values(items).forEach(itemList => {
                    closeItems(itemList, item);
                });

                // 클릭된 아이템 열기
                toggleItem(item);
            } else {
                // 이미 열린 아이템 클릭 시 닫기
                collapseItem(item);
            }
        });
    });
};

Object.values(items).forEach(itemList => expandItems(itemList));
