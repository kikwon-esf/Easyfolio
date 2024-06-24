// 자주 찾는 질문을 클릭해서 넘어왔을 때 해당 질문 열리는 코드
document.addEventListener("DOMContentLoaded", function () {
    var nowPage = document.querySelector('#nowPage').value;

    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });

    var currentPageUrl = window.location.href;
    if (currentPageUrl.endsWith("/food/searchFoodPage")) {
        var numBtnSpans = document.querySelectorAll(".page_numBtn span");
        numBtnSpans.forEach(function(span) {
            if (span.textContent.trim() === '1') {
                span.closest('.page_numBtn').classList.add('active');
            }
        });
        
    }
});
