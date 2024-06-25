
document.addEventListener("DOMContentLoaded", function () {
    var inputArray = Array.from(document.querySelectorAll('.inputMem'));

    inputArray.forEach(function (input) {
        input.addEventListener('click', function () {
            let idBox = document.querySelector('.idBox')
            let inputBox = document.querySelector('.inputBox')
            idBox.classList.remove('fail');
            input.closest('.inputBox').classList.add('on');
            input.closest('.inputBox').classList.remove('fail');
        });
    });

    document.addEventListener("click", function (event) {
        inputArray.forEach(function (input) {
            if (!input.contains(event.target)) {
                input.closest('.inputBox').classList.remove('on');
            }
        });
    });
});
let idBox = document.querySelector('.idBox')
let inputMemId = document.querySelector('.inputMem.id');
let isIdChecked = false;
function checkId() {
    if (inputMemId.value == "") {
        idBox.classList.toggle('fail');
        inputMemId.placeholder = "아이디를 입력해주세요";
    } else {
        fetch('/member/checkId', {
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify({
                memberId : document.querySelector('#memberId').value
                
            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    return;
                }
                return response.json();
            })
            .then((data) => {
                if (data) {
                    // alert('사용가능한 ID입니다.');
                    idCheckResult(true)
                    isIdChecked = true;
                    document.querySelector('.joinBtn').value = "회원가입";
                } else {
                    // alert('사용 불가능한 ID입니다.');
                    idBox.classList.toggle('fail');
                    idCheckResult(false)
                    document.querySelector('.joinBtn').value = "아이디 확인 필요";
                }
            })
            .catch((err) => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }

}
function loginGo() {
    let pw = document.querySelector('#memberPw');
    let pwCheck = document.querySelector('#memberPwch');
    let name = document.querySelector('#memberName');
    let tel = document.querySelector('#memberTel');

    if (inputMemId.value.trim() == "") {
        idBox.classList.toggle('fail');
        inputMemId.placeholder = "아이디를 입력해주세요";
    } else if(!isIdChecked){
        idBox.classList.toggle('fail');
        inputMemId.value='';
        inputMemId.placeholder = "아이디 중복을 확인 해 주세요";
    } 
    else if (pw.value === '') {
        pw.closest('.inputBox').classList.toggle('fail');
        pw.placeholder = "비밀번호를 입력해 주세요.";
    } else if (pwCheck.value === '') {
        pwCheck.closest('.inputBox').classList.toggle('fail');
        pwCheck.placeholder = "비밀번호를 확인 해 주세요.";
    } else if (pw.value !== pwCheck.value) {
        pwCheck.closest('.inputBox').classList.toggle('fail');
        pwCheck.type = "text";
        pwCheck.value='';
        pwCheck.placeholder = "비밀번호가 일치하지 않습니다.";
        // 클릭했을때 비번처럼 되게 다시 처리해야함
    } else if (name.value.trim() === "") {
        name.closest('.inputBox').classList.toggle('fail');
        name.placeholder = "이름을 입력해 주세요";
    } else if (tel.value.trim() === "") {
        tel.closest('.inputBox').classList.toggle('fail');
        tel.placeholder = "전화번호를 입력해 주세요";
    } else {
        document.querySelector('#joinForm').submit();
    }
}

function idCheckResult(bool){
    if(bool){
        const positive = document.querySelector('.positive');
        positive.classList.remove('pu_blind');
    }else{
        const negative = document.querySelector('.negative');
        negative.classList.remove('pu_blind');
    }
}
function displayOff_pu(bool){
    if(bool){
        const positive = document.querySelector('.positive');
        positive.classList.add('pu_blind');
    }else{
        const negative = document.querySelector('.negative');
        negative.classList.add('pu_blind');
    }
}