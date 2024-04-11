var cscSearch = document.querySelector('.csc_search');
var cscSearchBlock = document.querySelector('.csc_searchBlock');

cscSearch.addEventListener("click", function () {
    cscSearchBlock.style.border = "2px solid #F29221";
    cscSearchBlock.style.backgroundColor = "transparent"
    cscSearch.style.color = "#333";
    cscSearch.placeholder = "";
})

document.addEventListener("click", function (event) {
    if (event.target !== cscSearch) {
        cscSearchBlock.style.border = "2px solid #ffd57a";
        cscSearch.style.color = "#999";
        cscSearch.placeholder = '궁금한 점을 간단하게';
    }

});




