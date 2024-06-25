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
    return null;
    // return sessionStorage.getItem("myContentCookie");
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
    let requestURL = cookieCheck() == null ? "food" : cookieCheck();
    paging(content_URL+requestURL, requestURL);
})

function pagingCount(){
    
    nowPage = document?.querySelector("#nowPage").value;
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
    document.querySelector('.mydeleteCheckInner').classList.add('pop_blind');
    var selectedComments = [];
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    var checkboxes = document.querySelectorAll('.myCheckBox:checked');
    checkboxes.forEach(function (checkbox) {
        var commentEach = checkbox.closest('.commentEach');
        var foodCommentId = commentEach.querySelector('.foodCommentId').value;
        selectedComments.push(foodCommentId);
    });
    if (selectedComments.length > 0) {
        fetch('/myPage/myDeleteComment', { 
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify(selectedComments) // 배열 형식으로 전송
        })
        .then((response) => {
            return response.json(); 
        })
        .then((data) => {//data -> controller에서 리턴되는 데이터!       
            commentAlarmInner.querySelector('.alarmText').textContent = '삭제가 완료되었습니다.';
            commentAlarmInner.querySelector('.alarmBtn').onclick = function() {
                 location.reload();
            };
            commentAlarmInner.classList.remove('pop_blind');
        })
        .catch(err=>{
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
           
        });
    } else {
        commentAlarmInner.querySelector('.alarmText').textContent = '삭제할 댓글을 선택해주세요.';
        commentAlarmInner.classList.remove('pop_blind');
    }
}
function okayBtn(){
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    commentAlarmInner.classList.add('pop_blind');
};