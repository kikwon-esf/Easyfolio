document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;

    var sideFormElements = document.querySelectorAll('.sideForm');
    sideFormElements.forEach(function (element) {
        var link = element.querySelector('a').getAttribute('href');
        
        if (currentPage === link) {
            element.classList.add('active-sideForm'); 
        }
    });

});