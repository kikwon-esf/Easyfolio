const submitBtn = document.querySelector('.submit');
const secondPasswordChkUrl = "/myPage/secondPasswordChk"
const replacePosition = document.querySelector('.repalce')
const mention = document.querySelector('.mention');
const editUrl = "/myPage/editInform";



function submitSecondPasswordChk(ele) {
    let idBox = document.querySelector('.inputBox');
    let inputMemId = document.querySelector('.inputMem.pw');
    if (inputMemId.value == "" || inputMemId.value == null) {
        idBox.classList.add('fail');
        inputMemId.placeholder = "비밀번호를 입력해주세요";
        return;
    } else {
        var formData = new FormData(ele.closest('.SecondPasswordform'));

        let options = {
            method: 'POST',
            cache: 'no-cache',
            body: formData
        }
        fetch(secondPasswordChkUrl, options)
            .then((resp) => {
                console.log(resp.status)
                if (resp.status != 200) {
                    inputMemId.placeholder = "비밀번호 불일치";
                    inputMemId.value = '';
                    askDeleteFavorite();
                } else {
                    location.href = "/myPage/editInform";
                }
            })

    }

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
document.addEventListener("DOMContentLoaded", function () {
    var input = document.querySelectorAll('.inputMem');
    var inputArray = Array.from(input);

    inputArray.forEach(function (input) {
        input.addEventListener('click', function () {
            $(input).closest('.inputBox').addClass('on');
        });
    });
});

var inputMemberPw = document.querySelector('.inputMem.pw');
document.addEventListener("click", function (event) {
    if (event.target !== inputMemberPw) {
        $(inputMemberPw).closest('.inputBox').removeClass('on');
    }

});
function askDeleteFavorite(){
    var askDelete = document.querySelector('.commentAlarmInner');
    askDelete.style.display = 'block';
}

function okBtn(element){
    element.closest('.commentAlarmInner').style.display = 'none';
}