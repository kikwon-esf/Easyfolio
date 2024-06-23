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

    // var currentPage = window.location.pathname;

    // var sideFormElements = document.querySelectorAll('.sideForm');
    // sideFormElements.forEach(function (element) {
    //     var link = element.querySelector('a').getAttribute('href');
        
    //     if (currentPage === link) {
    //         element.classList.add('active-sideForm'); 
    //     }
    //     if (currentPage.includes('/csc/updateAnnForm') || currentPage.includes('/csc/annDetailForm') || currentPage === '/csc/insertAnnForm' || link === '/csc/annForm') {
    //         document.querySelector('.sideForm.ann').classList.add('active-sideForm'); 
    //     }
    //     if (currentPage.includes('/csc/inqDetailForm')) {
    //         document.querySelector('.sideForm.inquireList').classList.add('active-sideForm'); 
    //     }
    // });

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



