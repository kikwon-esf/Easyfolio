document.addEventListener("DOMContentLoaded", function () {
    var inputArray = Array.from(document.querySelectorAll('.inputMem'));

    inputArray.forEach(function (input) {
        input.addEventListener('click', function () {
            input.closest('.inputBox').classList.add('on');
            input.closest('.inputBox').classList.remove('fail');;
        });
    });

    document.addEventListener("click", function (event) {
        inputArray.forEach(function (input) {
            if (!input.contains(event.target)) {
                input.closest('.inputBox')?.classList.remove('on');
            }
        });
    });
});
let findHeaderTapId = document.querySelector('.findHeaderTap.id');
let findHeaderTapPw = document.querySelector('.findHeaderTap.pw');
let findBlockId = document.querySelector('.findBlock.id');
let findBlockPw = document.querySelector('.findBlock.pw');
let inputMemList = document.querySelectorAll('.inputMem');
let inputBoxList = document.querySelectorAll('.inputBox');
let findResultList = document.querySelectorAll('.findResult');

document.addEventListener("click", function (event) {
    // ID 탭이 클릭된 경우
    if (event.target == findHeaderTapId) {
        
        findBlockPw.classList.remove('on');
        findBlockId.classList.add('on');
        findHeaderTapPw.classList.remove('on');
        findHeaderTapId.classList.add('on');
        
        inputMemList.forEach(function (inputMem) {
            inputMem.value = '';
        });
        inputBoxList.forEach(function (inputBox) {
            console.log("InputMemFailList:", inputBoxList);
            inputBox.classList.remove('fail');
        });
        findResultList.forEach(function (findResult) {
            findResult.style.display = "none";
        });
    }

    // PW 탭이 클릭된 경우
    if (event.target == findHeaderTapPw) {
        findBlockId.classList.remove('on');
        findBlockPw.classList.add('on');
        findHeaderTapId.classList.remove('on');
        findHeaderTapPw.classList.add('on');
        inputMemList.forEach(function (inputMem) {
            inputMem.value = '';
        });
        inputBoxList.forEach(function (inputBox) {
            inputBox.classList.remove('fail');
        });
        findResultList.forEach(function (findResult) {
            findResult.style.display = "none";
        });
        
    }
});

function findId() {
    var memberName = document.getElementById("memberName").value;
    var memberTel = document.querySelector('#memberTel').value;
    let findResult = document.querySelector('.findResult');
    let resultBox = document.querySelector('.resultBox');
    let resultFail = document.querySelector('.resultFail');

    let inputName = document.querySelector('.inputMem.name');
    let inputPhone = document.querySelector('.inputMem.phoneNum');

    if (inputName.value.trim() == "") {
        inputName.closest('.inputBox').classList.toggle('fail');
        inputName.placeholder = "이름을 입력해주세요";
    } else if (inputPhone.value.trim() == "") {
        inputPhone.closest('.inputBox').classList.toggle('fail');
        inputPhone.placeholder = "전화번호를 입력해주세요";
    } else {
        if (findResult.style.display == "block") {
            if (resultBox != null) {
                resultBox.remove();
            } else if (resultFail != null) {
                resultFail.style.display = "none";
            }
        } else {
            if (resultBox != null) {
                resultBox.remove();
                findResult.style.display = "block";
            } else {
                findResult.style.display = "block";
            }
        }
        fetch('/member/findId', {
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: new URLSearchParams({
                "memberName": memberName,
                "memberTel": memberTel
            })
        })
            .then((response) => {
                if (!response.ok) {
                    alert('fetch error!\n컨트롤러로 통신 중에 오류가 발생했습니다.');
                    return;
                }

                return response.json();
            })
            .then((data) => {
                console.log(data)
                if (data.length === 0) {
                    resultFail.style.display = "block";
                } else {
                    data.forEach(data1 => {
                        console.log(data1)
                        let createResultBox = document.createElement('div');
                        createResultBox.className = "resultBox"
                        let findResult = document.querySelector('.findResult');
                        findResult.appendChild(createResultBox);

                        let resultId = document.createElement("p");
                        resultId.className = "resultId";
                        resultId.textContent = data1.memberId;
                        createResultBox.appendChild(resultId);

                        let findPwGo = document.createElement('p');
                        createResultBox.appendChild(findPwGo)
                        findPwGo.className = "findPwGo";
                        findPwGo.textContent = "비밀번호 찾기";

                        findPwGo.addEventListener('click', function () {
                            goFindPw(data1.memberId);
                        });
                    });
                }
            })
            .catch((err) => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });

    }
}
function goFindPw(memberId) {
    findBlockId.classList.remove('on');
    findBlockPw.classList.add('on');
    findHeaderTapId.classList.remove('on');
    findHeaderTapPw.classList.add('on');

    document.querySelector('.inputMem.id').value = memberId;
}
function findPw() {
    var memberId = document.getElementById("memberId").value;
    var memberTel = document.querySelector('.inputMem.tel').value;
    let findResult = document.querySelector('.findResult.pw');
    let resultBox = document.querySelector('.resultBox');
    let resultFail = document.querySelector('.resultFail.pw');

    let inputId = document.querySelector('.inputMem.id');
    let inputTel = document.querySelector('.inputMem.tel');

    if (inputId.value.trim() == "") {
        inputId.closest('.inputBox').classList.toggle('fail');
        inputId.placeholder = "아이디를 입력해주세요";
    } else if (inputTel.value.trim() == "") {
        inputTel.closest('.inputBox').classList.toggle('fail');
        inputTel.placeholder = "전화번호를 입력해주세요";
    } else {
        if (findResult.style.display == "block") {
            if (resultBox != null) {
                resultBox.remove();
            } else if (resultFail != null) {
                resultFail.style.display = "none";
            }
        } else {
            if (resultBox != null) {
                resultBox.remove();
                findResult.style.display = "block";
            } else {
                findResult.style.display = "block";
            }
        }
        fetch('/member/findPw', {
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: new URLSearchParams({
                "memberId": memberId,
                "memberTel": memberTel
            })
        })
            .then((response) => {
                if (400==response.status) {
                    new Error();
                    return;
                }else if (!response.ok) {
                    alert("!!")
                    return;
                }

                return response.json();
            })
            .then((data) => {
                console.log(data)
                // if (data.length === 0) {
                //     resultFail.style.display = "block";
                // } else {
                    // data.forEach(data1 => {
                    //     console.log(data1)
                    // });
                    console.log(data.memberId)
                // }
                location.href='/member/changePw?memberId='+data.memberId;
            })
            .catch((err) => {
                resultFail.style.display = "block";
                console.log(err);
            });

    }
}
function authenticateForChangePw(){
    const authenticateForChangePwURL = "/member/authenticateForChangePw";
    
    const options ={
        method : 'POST',
        cache : "no-cache",
        body : new FormData(document.querySelector('#joinForm'))
    }
    fetch(authenticateForChangePwURL,options)
    .then((resp)=>{
        console.log(resp);
        if(resp.status!=200){
            throw new Error();
        }else{
            location.href='/member/changePw';
        }
    })
    .catch((err)=>{
        pu_error.classList.remove('pu_blind')
    })
}
const pu_error = document.querySelector('.pu_error')
function displayOff_pu(){
    pu_error.classList.add('pu_blind')
}
