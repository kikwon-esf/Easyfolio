
$('.menuBtn').click(function () {
    if ($('.menuInner').css('display') == 'none') {
        $('.menuInner').stop().slideDown();
    } else {
        $('.menuInner').stop().slideUp();
    }

});

// document.addEventListener('DOMContentLoaded', function() {
    
    
//     if (codeType && codeValue) {
//         var menuTextBtns = document.querySelectorAll('.menuTextBtn');
//         menuTextBtns.forEach(function(btn) {
//             var href = btn.parentElement.getAttribute('href');
//             if (href && href.includes(codeValue)) {
//                 btn.classList.add('on');
//             }
//         });
//     }
// });

document.addEventListener('DOMContentLoaded', function() {
    const foodKindCode = document.querySelector('#foodKindCode').value;
    const foodMtrlCode = document.querySelector('#foodMtrlCode').value;
    const foodUsageCode = document.querySelector('#foodUsageCode').value;
    const foodTypeCode = document.querySelector('#foodTypeCode').value;
    const searchMenuList = document.querySelectorAll('.searchMenu');
    
    searchMenuList.forEach(function(searchMenu) {
        const closestMenuTextBtn = searchMenu.closest('a').querySelector('.menuTextBtn');
        if (foodKindCode === searchMenu.value) {
           closestMenuTextBtn.classList.add('on');
        } else if (foodMtrlCode === searchMenu.value) {
            closestMenuTextBtn.classList.add('on');
        } else if(foodUsageCode === searchMenu.value){
            closestMenuTextBtn.classList.add('on');
        } else if(foodTypeCode === searchMenu.value){
            closestMenuTextBtn.classList.add('on');
        }
    });
});
