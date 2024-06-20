document.addEventListener('DOMContentLoaded', function () {
    var currentPage = window.location.pathname;
    console.log(currentPage);

    var sideFormElements = document.querySelectorAll('.sideForm');
    sideFormElements.forEach(function (element) {
        var link = element.querySelector('a').getAttribute('href');
        
        if (currentPage === link) {
            element.classList.add('active-sideForm'); 
        }
        if (currentPage.includes('/editInform') ) {
            document.querySelector('.sideForm.myDetails').classList.add('active-sideForm'); 
        }
    });

});