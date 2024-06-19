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
    })
}
function foodPaging(page){
    const foodURL = "/myPage/myContent/food?nowPage=";
    paging(foodURL+page);
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
    
    paging(content_URL+"food");
    cookie("food");

});
commentBtn.addEventListener('click', function(){
    paging(content_URL+"comment")
    cookie("comment");
});


window.addEventListener('DOMContentLoaded', ()=>{
    let requestURL = cookieCheck() == null ? "comment" : cookieCheck();
    paging(content_URL+requestURL, requestURL);
})

function pagingCount(){
    
    nowPage = document.querySelector("#nowPage").value;
    console.log(nowPage)
    let nowPageNode = document.getElementById('page'+nowPage);
    nowPageNode.classList.add('active');
}