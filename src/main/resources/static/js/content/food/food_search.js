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

document.addEventListener("DOMContentLoaded", function() {
    var currentPageUrl = window.location.href;
    if (currentPageUrl.endsWith("/food/searchFood")||currentPageUrl.endsWith("/food/searchFoodPage")) {
        var numBtnSpans = document.querySelectorAll(".page_numBtn span");
        numBtnSpans.forEach(function(span) {
            if (span.textContent.trim() === '1') {
                span.closest('.page_numBtn').classList.add('active');
            }
        });
        
    }
});




//* 즐겨찾기 추가
const addFavBtn = document.querySelectorAll(".cartBox");
const addFavURL = "/food/addFav";
console.log(addFavBtn)
for(i = 0 ; i < addFavBtn.length ; i++){
    
    addFavBtn[i].addEventListener('click', (e)=>{
        console.log(e.target)
        const heart =  e.target.querySelector("path")
        const foodCode = e.target.closest(".cartBox").querySelector(".food_code").value;
        let data = {
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify({
                "foodCode" : foodCode
            })
        }
    
        fetch(addFavURL,data)
        .then((resp)=>{
            console.log(resp);
            let status = resp["status"];
            console.log(status);
            if(status == 502){
                alert("로그인을 해주세요!");
            }else if(status == 200){
                console.log();
            }else{
                alert("서버오류");
            }
            return resp.text();
        })
        .then((data)=>{
            if(data == "addComplete"){
                fullHeart(heart);
            }else if(data == "deleteComplete"){
                emptyHeart(heart);
            }
            console.log(data);
        })

    })
}
//m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8

function fullHeart(ele){
    console.log("red")
    ele.attributes.d.nodeValue="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8"
}
function emptyHeart(ele){
    ele.attributes.d.nodeValue="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143q.09.083.176.171a3 3 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15"
}