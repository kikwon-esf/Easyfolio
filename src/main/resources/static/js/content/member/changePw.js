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