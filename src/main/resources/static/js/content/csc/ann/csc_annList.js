document.addEventListener("DOMContentLoaded", function () {
    var nowPage = document.querySelector('#nowPage').value;
    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });

    var currentPageUrl = window.location.href;
    if (currentPageUrl.endsWith("/csc/qnaListForm")) {
        var numBtnSpans = document.querySelectorAll(".page_numBtn span");
        numBtnSpans.forEach(function(span) {
            if (span.textContent.trim() === '1') {
                span.closest('.page_numBtn').classList.add('active');
            }
        });

    }

    
    var activeCate = document.querySelector('.activeCate').value;
    
    if (activeCate == "") {
        document.querySelector('.annChoice.all').classList.add('active'); 
    }
    if (activeCate == 'CATE_001') {
        document.querySelector('.annChoice.CATE_001').classList.add('active'); 
    }
    if (activeCate == 'CATE_002') {
        document.querySelector('.annChoice.CATE_002').classList.add('active'); 
    }
    if (activeCate == 'CATE_003') {
        document.querySelector('.annChoice.CATE_003').classList.add('active'); 
    }
    if (activeCate == 'CATE_004') {
        document.querySelector('.annChoice.CATE_004').classList.add('active'); 
    }
    if (activeCate == 'CATE_005') {
        document.querySelector('.annChoice.CATE_005').classList.add('active'); 
    }

});

var annSearch = document.querySelector('.annSearch');
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
        annSearch.placeholder = '공지사항 검색';
    }

});

function ann_search() {
    document.querySelector(".ann_searchForm").submit();
}



