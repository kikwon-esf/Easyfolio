// 자주 찾는 질문 열기
$(document).ready(function () {
    $('.qnaNormalBlock, .qnaArrow').click(function () {
        openQna(this);
    });
});
// 자주 찾는 질문 여는 코드
function openQna(element) {
    var qnaInner = $(element).closest('.qnaInner');
    qnaInner.find('.ansBox').slideToggle();
    qnaInner.find('.qnaBox').toggleClass('open');
    qnaInner.find('.qnaArrow').toggleClass('rotate');
}