const submitBtn = document.querySelector('.submit');
const secondPasswordChkUrl = "/myPage/secondPasswordChk"
const replacePosition = document.querySelector('.repalce')
const mention = document.querySelector('.mention');
const editUrl = "/myPage/editInform";



function submitSecondPasswordChk(ele){
    var formData =new FormData(ele.closest('.SecondPasswordform'));

    let options = {
        method:'POST',
        cache: 'no-cache',
        body : formData
    }
    fetch(secondPasswordChkUrl, options)
    .then((resp)=>{
        console.log(resp.status)    
        if(resp.status !=200){
            mention.textContent="틀렸는데용?";
        }else{
            location.href="/myPage/editInform";
        }
    })
    
}
// function editInform(){

//     fetch(editUrl)
//     .then((resp)=>{
//         console.log(resp)
//         return resp.text();
//     })
//     .then((data)=>{
//         writeContent(data,replacePosition);
//     })
// }

// function writeContent(postion, content){
//     postion.innerHTML=content;
// }