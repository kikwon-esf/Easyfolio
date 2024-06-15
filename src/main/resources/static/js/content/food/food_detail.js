const response_commentId = document.querySelector("#response_commentId")?.value;
window.addEventListener('DOMContentLoaded', function () {
    var mtrlMtElements = document.querySelectorAll('.recipe_mtrl .mtrlMt');

    mtrlMtElements.forEach(function(element) {
        var numChildren = element.childElementCount;
        if (numChildren > 5) {
            element.classList.add('full');
        }
    });
    console.dir(response_commentId == "COMM_00008")
    if(response_commentId != null || undefined){
        const commentNode = this.document.getElementById(String(response_commentId));
        console.dir(commentNode);

        scrollTo(findScrollValue(toString(commentNode)));  
    }
    
    
});


function findScrollValue(commentNode){

    let rect = commentNode.getBoundingClientRect();
    console.log(rect.y)
    return rect.y+500;
}