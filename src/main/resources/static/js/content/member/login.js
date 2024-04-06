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
document.addEventListener('DOMContentLoaded',()=>{
    if(errInput.value != ''){
        alert('로그인 정보를 확인해주세요.');
        console.log(errInput.value)
    }
})
//input의 값이 공백일 경우
const regularChk = VerEx() //정규식 생성 (verbalExpression)
.startOfLine()
.whiteSpace()
.endOfLine()
;
const loginBtn = document.querySelector('.loginBtn'); //로그인 버튼
const chkList = document.querySelectorAll('.inputMem');
const inputBox = document.querySelectorAll('.inputBox');
loginBtn.addEventListener('click',(e)=>{
    if(regularChk.test(chkList[0])){
        chkList[0].value='';
        inputBox[0].classList.add('fail')
        chkList[0].placeholer="아이디값을 확인해주세요.";
        e.preventDefault();
        return;
    }else if(!regularChk.test(chkList[1])){
        chkList[1].value='';
        inputBox[1].classList.add('fail')
        chkList[1].placeholer="비밀번호를 확인해주세요.";
        e.preventDefault();
        return;
    }
    
})
for(let i = 0 ; i < chkList.length ; i++){
    chkList[i].addEventListener('click',()=>{
        inputBox[i].classList.remove('fail');
    })
}

// window.addEventListener('DOMContentLoaded', ()=>{
//     var loginError = document.getElementById('loginError').textContent;
//     if(loginError != null && loginError !== ''){
//         alert('아이디와 비밀번호를 확인 해주세요.');
//         return;
//     }
// });
