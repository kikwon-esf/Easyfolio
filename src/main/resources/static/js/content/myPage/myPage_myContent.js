const content_URL = "/myPage/myContent/";
// const content_commentURL = "/myPage/myContent/comment";
const replacePosition = document.querySelector("#replacePosition");

const foodBtn = document.querySelector("#content_food");
const commentBtn = document.querySelector("#content_comment");
let nowPage = null;

function paging(url){
    fetch(url)
    .then((resp)=>{
        
        return resp.text();
    })
    .then((data)=>{
        writeHTMl(data);
        pagingCount()
        scrollTo(0,0);
        if(url.includes("food")){
            heartFill();
        }
    })
}
function foodPaging(page){
    const foodURL = "/myPage/myContent/food?nowPage=";
    paging(foodURL+page);
    heartFill();
}
function commentPaging(page){
    const commentURL = "/myPage/myContent/comment?nowPage=";
    paging(commentURL+page);
}
function writeHTMl(data){
    replacePosition.innerHTML=data;
}
function cookie(value){
    sessionStorage.setItem("myContentCookie",value);
}
function cookieCheck(){
    return sessionStorage.getItem("myContentCookie");
}

foodBtn.addEventListener('click', function(){
    if (commentBtn.classList.contains('on')) {
        commentBtn.classList.remove('on');
    }
    paging(content_URL+"food");
    heartFill();
    pagingCount();
    foodBtn.classList.add('on');
    cookie("food");

});
commentBtn.addEventListener('click', function(){
    if (foodBtn.classList.contains('on')) {
        foodBtn.classList.remove('on');
    }
    commentBtn.classList.add('on');
    paging(content_URL+"comment")
    cookie("comment");
});


window.addEventListener('DOMContentLoaded', ()=>{
    let requestURL = cookieCheck() == null ? "comment" : cookieCheck();
    paging(content_URL+requestURL, requestURL);
})

function pagingCount(){
    
    nowPage = document?.querySelector("#nowPage").value;
    console.log(nowPage)
    let nowPageNode = document.getElementById('page'+nowPage);
    nowPageNode?.classList.add('active');
}
function deletePopUp(){
    document.querySelector('.mydeleteCheckInner').classList.remove('pop_blind');
}



function NoDelete(){
    document.querySelector('.mydeleteCheckInner').classList.add('pop_blind');
}

function deleteSelectedComments() {
    var selectedComments = [];
    var checkboxes = document.querySelectorAll('.myCheckBox:checked');
    checkboxes.forEach(function (checkbox) {
        var commentEach = checkbox.closest('.commentEach');
        var foodCommentId = commentEach.querySelector('.foodCommentId').value;
        selectedComments.push(foodCommentId);
    });
    console.log(selectedComments);

    fetch('/myPage/', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify({
           // 데이터명 : 데이터값
        })
    })
    .then((response) => {
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
    
}