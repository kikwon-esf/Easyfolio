let searchText = document.querySelector('.searchText');

// $('.searchText').click(function () {
//     document.querySelector('.searchblock').style.border = "2px solid #000";
// })
var wrap = document.querySelector('#wrap')
var searchblock = document.querySelector('.searchblock')

searchText.addEventListener("click", function () {
    searchblock.style.border = "2px solid rgb(242,146,33)";
    searchblock.style.backgroundColor = "transparent"
    searchText.style.color = "#333";
    searchText.value = '';
})
var tap = document.querySelector('.tap');
var popupTap = document.querySelector('.popupTap');
var tapIcon = document.querySelector('.bi.bi-justify-left');

tap.addEventListener("click", function () {

    if(popupTap.style.display == "block"){
        popupTap.style.display = "none";
        tapIcon.setAttribute("style", "background-color:transparent;");
        tapIcon.setAttribute("fill", "#ffd57a");
    } else{
        popupTap.style.display = "block";
        tapIcon.setAttribute("style", "background-color:#e8e8e8; border-radius: 6px;");
        tapIcon.setAttribute("fill", "rgb(242,146,33)");
    }
})
tap.addEventListener("click", function (event) {
    event.stopPropagation(); // 클릭 이벤트 전파 방지
});

popupTap.addEventListener("click", function (event) {
    event.stopPropagation(); // 클릭 이벤트 전파 방지
});

document.addEventListener("click", function (event) {
    if (event.target !== searchText) {
        searchblock.style.border = "2px solid transparent";
        searchblock.style.backgroundColor = "#f4f4f4";
        searchText.style.color = "#999";
        searchText.value = '원하는 모든 레시피';
    }
    if (!event.target.closest('.tap') && !event.target.closest('.popupTap')) {
        popupTap.style.display = "none";
        tapIcon.setAttribute("style", "background-color:transparent;");
        tapIcon.setAttribute("fill", "#ffd57a");
    }


});

document.addEventListener("mouseover", function (event) {
    var icon = document.querySelectorAll('.icon');
    if (!event.target.closest('.popup')) {
        icon.forEach((element, idx) => {
            element.querySelector('.popup').style.display = "none";
        });
    }
    icon.forEach((element, idx) => {
        if (event.target.closest('.icon') === element) {
            element.querySelector('.popup').style.display = "block";
        }
    });
})

//로그아웃
var logout = document.getElementById("logout");
logout.addEventListener("click",(e)=>{
    let active = confirm("로그아웃을 하시겠습니까?");
    if(!active){
        e.preventDefault();
    }
})


// Principal 객체 검사
