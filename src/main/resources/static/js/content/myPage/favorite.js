document.addEventListener("DOMContentLoaded", function() {
    var urlParams = new URLSearchParams(window.location.search);
    var nowPage = urlParams.get('nowPage');

    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });
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

var foodSearch = document.querySelector('.searchFavoriteValue');
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
        foodSearch.placeholder = '즐겨찾기 검색';
    }

});

const url = "/myPage/deleteFav"
function deleteFav(ele) {
    const foodCode = ele.parentNode.querySelector(".foodCode").value;
    let data = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({
            foodCode
        })
    }
    fetch(url, data)
        .then((response) => {
            return response.text();
        }).then((data) => {
            if (data != "complete") {
                alert("실패");
            } else {
                alert("완료");
                location.reload(true);
            }
        })
}
function searchFavorite() {
    document.querySelector('.searchFavoriteForm').submit();
}




