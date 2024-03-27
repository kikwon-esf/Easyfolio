document.addEventListener("DOMContentLoaded", function () {
    var qnaInners = document.querySelectorAll('.qnaInner');
    var qnaInnerArray = Array.from(qnaInners);

    qnaInnerArray.forEach(function (qnaInner) {
        qnaInner.addEventListener('click', function () {
            $(qnaInner).find('.ansBox').slideToggle();
            $(qnaInner).find('.qnaBox').toggleClass('on');
            $(qnaInner).find('.qnaArrow').toggleClass('rotate');
        });
    });
});
var annSearch = document.querySelector('.annSearch');
var annSearchBlock = document.querySelector('.annSearchBlock');
if(annSearch != null){
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
}


