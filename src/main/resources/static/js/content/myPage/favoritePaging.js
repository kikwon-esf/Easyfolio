document.addEventListener("DOMContentLoaded", function() {
    var currentPageUrl = window.location.href;
    if (currentPageUrl.endsWith("/myPage/favorite")) {
        var numBtnSpans = document.querySelectorAll(".page_numBtn span");
        numBtnSpans.forEach(function(span) {
            if (span.textContent.trim() === '1') {
                span.closest('.page_numBtn').classList.add('active');
            }
        });

    }
});
document.addEventListener("DOMContentLoaded", function() {
    var urlParams = new URLSearchParams(window.location.search);
    var nowPage = document.querySelector('#nowPage').value;

    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });
});