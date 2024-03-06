document.addEventListener("DOMContentLoaded", function () {
    var qnaArrows = document.querySelectorAll('.qnaArrow');
    var qnaArrowArray = Array.from(qnaArrows);

    qnaArrowArray.forEach(function (arrow) {
        arrow.addEventListener('click', function () {
            $(arrow).closest('.qnaInner').find('.ansBox').slideToggle();
            $(arrow).closest('.qnaInner').find('.qnaBox').toggleClass('on');
            $(arrow).toggleClass('rotate');
        });
    });
});var annSearch = document.querySelector('.annSearch');
var annSearchBlock = document.querySelector('.annSearchBlock');

annSearch.addEventListener("click", function () {
    annSearchBlock.style.border = "2px solid #F29221";
    annSearchBlock.style.backgroundColor = "transparent"
    annSearch.style.color = "#333";
    annSearch.placeholder = "";
})

document.addEventListener("click", function (event) {
    if (event.target !== annSearch) {
        annSearchBlock.style.border = "2px solid #ffd57a";
        annSearch.style.color = "#999";
        annSearch.placeholder = '자주 찾는 질문 검색';
    }

});