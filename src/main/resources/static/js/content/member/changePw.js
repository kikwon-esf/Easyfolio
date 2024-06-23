// const btn = document.querySelectorAll('#btn');
// const submitUrl = "/myPage/submitInform";
// const form = document.querySelector('form');


// function submitFuction(e){
//     let formData = new FormData(form)
//     let options = {
//         method:'POST',
//         cache: 'no-cache',
//         body : formData
//     }
//     fetch(submitUrl, options)
//     .then((resp)=>{
//         console.log(resp);
//         if(resp.status==200){
//             location.href="/myPage/editInform"
//         }
//     })
// }
// function updateFormGo(element) {
//     var form = element.closest('.form');
//     var updateForm = form.querySelector('.updateForm');
//     var defaultBox = form.querySelector('.defaultBox');
//     var pwBtn = form.querySelector('.pw_btn')

//     if (!defaultBox.classList.contains('off')) {
//         defaultBox.classList.add('off');
//         updateForm.classList.remove('off');
//         pwBtn?.classList.remove('off');
//         form.classList.add('on');
//     }
// }

// pw_submit.addEventListener('click', (e)=>{
//     e.preventDefault();
//     let pw_input = document.querySelectorAll('.pw_input');

//     if(pw_input[0].value!= pw_input[1].value){
//         askDeleteFavorite();
//     }else{
//         submitFuction(e);
//     }

// })

// function askDeleteFavorite(){
//     var askDelete = document.querySelector('.pwAlarmInner');
//     askDelete.style.display = 'block';
// }

function changePw() {
    let pw = document.querySelector('#input_password');
    let pwCheck = document.querySelector('#repeat_password');

    
    if (pw.value === '') {
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
    }else{
        document.querySelector('form').submit();
    }
}