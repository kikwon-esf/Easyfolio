window.addEventListener('DOMContentLoaded', function () {
    var mtrlMtElements = document.querySelectorAll('.recipe_mtrl .mtrlMt');

    mtrlMtElements.forEach(function(element) {
        var numChildren = element.childElementCount;
        if (numChildren > 5) {
            element.classList.add('full');
        }
    });
});