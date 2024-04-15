

document.addEventListener("DOMContentLoaded", function () {
    $(document).ready(function () {
        const aQnaCode = $('.aQnaCode').val();
        const bQnaCodes = $('.bQnaCode');
        bQnaCodes.each(function () {
            if ($(this).val() === aQnaCode) {
                const qnaInner = $(this).closest('.qnaInner');
                qnaInner.addClass('on');
                
                // 제이쿼리로 slideToggle() 적용
                qnaInner.parent().find('.ansBox').slideToggle();
                
                qnaInner.parent().find('.qnaBox').addClass('on');
                qnaInner.parent().find('.qnaArrow').addClass('rotate');
            }
        });
    });

    var qnaBlocks = document.querySelectorAll('.qnaBlock');
    var qnaBlocksArray = Array.from(qnaBlocks);

    qnaBlocksArray.forEach(function (qnaBlock) {
        var dnuBtnBox = qnaBlock.querySelector('.dnuBtnBox');
        var qnaInner = qnaBlock.querySelector('.qnaInner');
        var ansBox = qnaBlock.querySelector('.ansBox');

        qnaInner.addEventListener('click', function () {
            $(dnuBtnBox).toggleClass('on');
            $(ansBox).slideToggle();
            $(qnaInner).find('.qnaBox').toggleClass('on');
            $(qnaInner).find('.qnaArrow').toggleClass('rotate');
        });

        ansBox.addEventListener('click', function (event) {
            event.stopPropagation();
        });
    });
});


var annSearch = document.querySelector('.annSearch');
var annSearchBlock = document.querySelector('.annSearchBlock');
if (annSearch != null) {
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




