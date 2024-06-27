document.addEventListener("DOMContentLoaded", function () {
    var input = document.querySelectorAll('.inputMem');
    var inputArray = Array.from(input);

    inputArray.forEach(function (input) {
        input.addEventListener('click', function () {
            $(input).closest('.inputBox').addClass('on');
        });
    });
});
var inputMemberId = document.querySelector('.inputMem.id');
var inputMemberPw = document.querySelector('.inputMem.pw');
document.addEventListener("click", function (event) {
    if (event.target !== inputMemberId) {
        $(inputMemberId).closest('.inputBox').removeClass('on');
    }
    if (event.target !== inputMemberPw) {
        $(inputMemberPw).closest('.inputBox').removeClass('on');
    }

});

//로그인 에러시
const errInput = document.querySelector('#login_error');
const logoutErrorDisplay = document.querySelector('.logoutError');
document.addEventListener('DOMContentLoaded',()=>{
    if(errInput.value != ''){
        logoutErrorDisplay.classList.remove('hide')
    }else if(changeSuc == "true"){
        document.querySelector('.pwChange').style.display='block'
    }
})
function closePopup(ele){
    ele.closest('.logoutError').classList.add('hide')
}
//input의 값이 공백일 경우

const regularChk = /^(?:[^ ]+)$/;

const loginBtn = document.querySelector('.loginBtn'); //로그인 버튼
const chkList = document.querySelectorAll('.inputMem');
const inputBox = document.querySelectorAll('.inputBox');
loginBtn.addEventListener('click',(e)=>{
    const idValue = chkList[0].value;
    const result = regularChk.test(idValue);
    if(!result){
        // chkList[0].value='';
        inputBox[0].classList.add('fail')
        chkList[0].placeholder="아이디값을 확인해주세요.";
        e.preventDefault();
        return;
    }else if(chkList[1].value==''){
        inputBox[1].classList.add('fail')
        chkList[1].placeholder="비밀번호를 확인해주세요.";
        e.preventDefault();
        return;
    }
    return;
})

for(let i = 0 ; i < chkList.length ; i++){
    chkList[i].addEventListener('click',()=>{
        inputBox[i].classList.remove('fail');
        if(i==0){
            chkList[0].placeholder="아이디";
        }else{
            chkList[1].placeholder="비밀번호";
        }
    })
}

// window.addEventListener('DOMContentLoaded', ()=>{
//     var loginError = document.getElementById('loginError').textContent;
//     if(loginError != null && loginError !== ''){
//         alert('아이디와 비밀번호를 확인 해주세요.');
//         return;
//     }
// });


//비밀번호 변경후
let changeSuc = document.querySelector('#changeSuc').value;
document.querySelector('#changePwYeap').addEventListener('click',()=>{
    document.querySelector('.pwChange').style.display='none';
})