document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;

    var sideFormElements = document.querySelectorAll('.sideForm');
    sideFormElements.forEach(function (element) {
        var link = element.querySelector('a').getAttribute('href');
        
        if (currentPage === link) {
            element.classList.add('active-sideForm'); 
        }
        if (currentPage.includes('/csc/updateAnnForm') || currentPage.includes('/csc/annDetailForm') || currentPage === '/csc/insertAnnForm' || link === '/csc/annForm') {
            document.querySelector('.sideForm.ann').classList.add('active-sideForm'); 
        }
        if (currentPage.includes('/csc/inqDetailForm')) {
            document.querySelector('.sideForm.inquireList').classList.add('active-sideForm'); 
        }
        if (currentPage.includes('/csc/responseInqForm')) {
            document.querySelector('.sideForm.inquireList').classList.add('active-sideForm'); 
        }
        
    });

});