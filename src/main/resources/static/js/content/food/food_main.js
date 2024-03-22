document.addEventListener("DOMContentLoaded", function() {
    var urlParams = new URLSearchParams(window.location.search);
    var nowPage = urlParams.get('nowPage');

    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });
});

var foodSearch = document.querySelector('.searchFoodValue');
var foodSearchBlock = document.querySelector('.foodSearchBlock');

foodSearch.addEventListener("click", function () {
    foodSearchBlock.style.border = "2px solid #F29221";
    foodSearchBlock.style.backgroundColor = "transparent"
    foodSearch.style.color = "#333";
    foodSearch.placeholder = "";
})

document.addEventListener("click", function (event) {
    if (event.target !== foodSearch) {
        foodSearchBlock.style.border = "2px solid #ffd57a";
        foodSearch.style.color = "#999";
        foodSearch.placeholder = '레시피 검색';
    }

});
function searchFood(){
    document.querySelector('.searchFoodForm').submit();
}

