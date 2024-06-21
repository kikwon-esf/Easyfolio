
const askFrame = document.querySelector('.ask_frame');
const yesBtn = document.querySelector('.ask_answer_yes');
const noBtn = document.querySelector('.ask_answer_no');
const loginURL = '/member/loginForm';

const anonymous = document.querySelectorAll('.anonymous');
for(let i = 0 ; i < anonymous.length ; i++){
    anonymous[i].addEventListener('click',(e)=>{
        e.preventDefault();
        popup(askFrame);
    })
}

function popup(element){
    element.classList.remove('pop_blind')
}
function blind(element){
    element.classList.add('pop_blind')
}

yesBtn.addEventListener('click',()=>{
    location.href = loginURL;
})
noBtn.addEventListener('click',()=>{
    blind(askFrame)
})

