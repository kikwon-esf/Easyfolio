const content_URL = "/myPage/myContent/";
// const content_commentURL = "/myPage/myContent/comment";
const replacePosition = document.querySelector("#replacePosition");

const foodBtn = document.querySelector("#content_food");
const commentBtn = document.querySelector("#content_comment");


function paging(url, value){
    console.log(value)
    fetch(url)
    .then((resp)=>{
        return resp.text();
    })
    .then((data)=>{
        writeHTMl(data);
    })


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
    paging(content_URL+"food", "food");
    cookie("food");

});
commentBtn.addEventListener('click', function(){
    paging(content_URL+"comment","comment")
    cookie("comment");
});

window.addEventListener('DOMContentLoaded', ()=>{
    let requestURL = cookieCheck() == null ? "comment" : cookieCheck();
    paging(content_URL+requestURL, requestURL);
})