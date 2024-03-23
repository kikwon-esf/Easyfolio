
$('.menuBtn').click(function () {
    if ($('.menuInner').css('display') == 'none') {
        $('.menuInner').stop().slideDown();
    } else {
        $('.menuInner').stop().slideUp();
    }

});

document.addEventListener('DOMContentLoaded', function() {
    var queryParams = new URLSearchParams(window.location.search);
    var codeType = queryParams.keys().next().value;
    var codeValue = queryParams.get(codeType);
    
    if (codeType && codeValue) {
        var menuTextBtns = document.querySelectorAll('.menuTextBtn');
        menuTextBtns.forEach(function(btn) {
            var href = btn.parentElement.getAttribute('href');
            if (href && href.includes(codeValue)) {
                btn.classList.add('on');
            }
        });
    }
});