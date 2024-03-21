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

// window.addEventListener('DOMContentLoaded', ()=>{
//     var loginError = document.getElementById('loginError').textContent;
//     if(loginError != null && loginError !== ''){
//         alert('아이디와 비밀번호를 확인 해주세요.');
//         return;
//     }
// });
