
$('.menuBtn').click(function () {
    if ($('.menuInner').css('display') == 'none') {
        $('.menuInner').stop().slideDown();
    } else {
        $('.menuInner').stop().slideUp();
    }

});

