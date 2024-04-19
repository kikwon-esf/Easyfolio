var bigImgBlock = document.querySelector(".bigImgBlock");

var images = document.querySelectorAll(".inqImgBlock img");
images.forEach(function(image, index) {
    image.addEventListener("click", function() {
        var bigImg = document.getElementById("img01");
        bigImg.src = image.src;
        bigImgBlock.style.display = "block";
    });
});
var closeBtn = document.getElementsByClassName("close")[0];
window.onclick = function(event) {
    if (event.target == bigImgBlock) {
        bigImgBlock.style.display = "none";
    }
}
closeBtn.onclick = function() {
    bigImgBlock.style.display = "none";
}

// 삭제 팝업

const deleteinqBtn = document.querySelector('.deleteinqBtn')

deleteinqBtn.addEventListener("click", function(){
    deletePopUp();
    puBtnYes.addEventListener('click', ()=>{
        const deleteInqCode =  document.querySelector('.deleteInqCode').value;
        const deleteResCode =  document.querySelector('.deleteResCode').value;
        var url = '/csc/deleteInq?inqCode=' + deleteInqCode +'&resCode=' + deleteResCode;
        location.href = url;
    })

});

