function readURL(input) {
    let box = document.querySelector('.imgLookBox');
    box.innerHTML = "";
    let a = 0;
    for (const file of input.files) {
        var fileName = file.name;
        document.querySelector('.upload-name').value = fileName + " 외 " + a + "개";
        var reader = new FileReader();
        reader.onload = function (e) {
            const imgElement = document.createElement("img");
            imgElement.className = "imgSlideList";
            imgElement.src = e.target.result;
            imgElement.alt = '';

            box.appendChild(imgElement);
        };
        a++;
        reader.readAsDataURL(file);
    };

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

