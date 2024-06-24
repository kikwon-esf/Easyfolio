const btns = document.querySelectorAll('.submit');
const submitUrl = "/myPage/submitInform";
const pw_submit = document.querySelector('#pw_submit');

for(let i = 0 ; i < btns.length ; i++){
    btns[i].addEventListener("click",(e)=>{
        submitFuction(e)
    })
}
function submitFuction(e){
    let formData = new FormData(e.target.closest('.form'))
    let options = {
        method:'POST',
        cache: 'no-cache',
        body : formData
    }
    fetch(submitUrl, options)
    .then((resp)=>{
        console.log(resp);
        if(resp.status==200){
            location.href="/myPage/editInform"
        }else if(resp.status==400){
            badGateWay();
        }
        else if(resp.status==500){
            internerServerError();
        }
    })
}
function updateFormGo(element) {
    var form = element.closest('.form');
    var updateForm = form.querySelector('.updateForm');
    var defaultBox = form.querySelector('.defaultBox');
    var pwBtn = form.querySelector('.pw_btn')

    if (!defaultBox.classList.contains('off')) {
        defaultBox.classList.add('off');
        updateForm.classList.remove('off');
        pwBtn?.classList.remove('off');
        form.classList.add('on');
    }
}

pw_submit?.addEventListener('click', (e)=>{
    e.preventDefault();
    let pw_input = document.querySelectorAll('.pw_input');

    if(pw_input[0].value!= pw_input[1].value){
        askDeleteFavorite();
    }else{
        submitFuction(e);
    }
    
})

function askDeleteFavorite(){
    var askDelete = document.querySelector('.pwAlarmInner');
    askDelete.style.display = 'block';
}
function badGateWay(){
    var askDelete = document.querySelector('.badGateWay');
    askDelete.style.display = 'block';
}
function internerServerError(){
    var askDelete = document.querySelector('.internerServerError');
    askDelete.style.display = 'block';
}
function okBtn(element){
    element.closest('.pwAlarmInner').style.display = 'none';
}
