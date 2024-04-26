const foodCode = document.querySelector('.foodCode_hidden').value;
const getCommentURL = "/food/comment";
const submitURL = "/myPage/comment"
const replacePosition = document.querySelector('.replacePosition');


function getCommentList(url){
    fetch(url)
    .then((resp)=>{
        console.log(resp);
        return resp.text();
    })
    .then((data)=>{
        console.log(data);
        writeContent(replacePosition, data);
        const foodCode_hide = document.querySelector('.foodCode_hide')
        foodCode_hide.value=foodCode;
    });
}
function writeContent(postion, content){
    postion.innerHTML=content;
}
window.addEventListener('load',()=>{
    console.log(getCommentURL+"?foodCode="+foodCode);
    
    getCommentList(getCommentURL+"?foodCode="+foodCode);
})

function submitComment(){   
    let formData= new FormData(document.querySelector('.newComment'));
    let options = {
        method:'POST',
        cache: 'no-cache',
        body : formData
    }
    fetch(submitURL, options)
    .then((resp)=>{
        console.log(resp);
        return resp.text();
    })
    .then((data)=>{
        writeContent(replacePosition, data);    
        const foodCode_hide = document.querySelector('.foodCode_hide')
        foodCode_hide.value=foodCode;
    })
}