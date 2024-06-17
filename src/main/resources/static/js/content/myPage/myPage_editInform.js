const btns = document.querySelectorAll('.submit');
const submitUrl = "/myPage/submitInform"

for(let i = 0 ; i < btns.length ; i++){
    btns[i].addEventListener("click",(e)=>{

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
            }
        })
    })
}

function updateFormGo(element) {
    var form = element.closest('.form');
    var updateForm = form.querySelector('.updateForm');
    var defaultBox = form.querySelector('.defaultBox');

    if (!defaultBox.classList.contains('off')) {
        defaultBox.classList.add('off');
        updateForm.classList.remove('off');
    }
}