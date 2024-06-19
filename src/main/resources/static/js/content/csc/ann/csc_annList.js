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


function searchTip(element){
    var data = element.textContent;
    var url = '/csc/annListForm?allSearchKeyword=' + data;
    location.href = url;
}

searchTip.addEventListener('click', function(){


})
