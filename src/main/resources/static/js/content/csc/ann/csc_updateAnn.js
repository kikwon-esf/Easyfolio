function checkPopup(element){
    const target = element.closest(".qnaInner");
    target.querySelector('.commentCheckInner').classList.remove('pop_blind');
}
function youSure(){
    const commentCheckInner = document.querySelector('.commentCheckInner');
    commentCheckInner.classList.remove('pop_blind');

}
function sureOk(){
    document.querySelector('form').submit();
}

function NoThanks(){
    document.querySelector('.commentCheckInner').classList.add('pop_blind');
}