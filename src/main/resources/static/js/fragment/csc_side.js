document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;

    var sideFormElements = document.querySelectorAll('.sideForm');
    sideFormElements.forEach(function (element) {
        var link = element.querySelector('a').getAttribute('href');
        
        if (currentPage.startsWith(link)) {
            element.classList.add('active-sideForm'); 
        }

        if (currentPage === '/csc/insertAnnForm' && link === '/csc/annForm') {
            element.classList.add('active-sideForm'); 
        }
    });
});