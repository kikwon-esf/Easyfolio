// 삭제 팝업


function deletePopUp(){
    displayOn(puDelete);
}

const deleteAnnBtn = document.querySelector('.deleteAnnBtn')

deleteAnnBtn.addEventListener("click", function(){
    deletePopUp();
    puBtnYes.addEventListener('click', ()=>{
        const deleteCode =  document.querySelector('.deleteCode').value;
        var url = '/csc/deleteAnn?annCode=' + deleteCode;
        location.href = url;
    })

});