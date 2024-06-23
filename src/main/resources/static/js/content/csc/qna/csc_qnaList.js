document.addEventListener("DOMContentLoaded", function () {
    var nowPage = document.querySelector('#nowPage').value;
    var pageButtons = document.querySelectorAll('.page_numBtn');

    pageButtons.forEach(function(button) {
        if (button.textContent === nowPage) {
            button.classList.add('active'); // 여기서 'active'는 원하는 클래스명입니다.
        }
    });

    var currentPageUrl = window.location.href;
    if (currentPageUrl.endsWith("/csc/qnaListForm")) {
        var numBtnSpans = document.querySelectorAll(".page_numBtn span");
        numBtnSpans.forEach(function(span) {
            if (span.textContent.trim() === '1') {
                span.closest('.page_numBtn').classList.add('active');
            }
        });
        
    }

    const aQnaCode = $('.aQnaCode').val();
    const bQnaCodes = $('.bQnaCode');
    bQnaCodes.each(function () {
        if ($(this).val() === aQnaCode) {
            const qnaInner = $(this).closest('.qnaInner');
            qnaInner.addClass('on');

            // 제이쿼리로 slideToggle() 적용
            qnaInner.parent().find('.ansBox').slideToggle();
            const dnuBtnBox = qnaInner.siblings('.dnuBtnBox');
            dnuBtnBox.toggleClass('on');
            qnaInner.parent().find('.qnaBox').addClass('on');
            qnaInner.parent().find('.qnaArrow').addClass('rotate');
        }

    });


    var annSearch = document.querySelector('.annSearch');
    var annSearchBlock = document.querySelector('.annSearchBlock');
    if (annSearch != null) {
        annSearch.addEventListener("click", function () {
            annSearchBlock.style.border = "2px solid #F29221";
            annSearchBlock.style.backgroundColor = "transparent"
            annSearch.style.color = "#333";
            annSearch.placeholder = "";
        })
        document.addEventListener("click", function (event) {
            if (event.target !== annSearch) {
                annSearchBlock.style.border = "2px solid #ffd57a";
                annSearch.style.color = "#999";
                annSearch.placeholder = '자주 찾는 질문 검색';
            }

        });
    }
});


function removeToggleEvent(){
    var qnaBlocks = Array.from(qnaBlocks);
    qnaBlocks.forEach(function(qnaBlock){
        var qnaInner = qnaBlock.querySelector('.qnaInner');
        var ansBox = qnaBlock.querySelector('.ansBox');

        qnaInner.removeEventListener('click', qnaInnerClickHandler);
        ansBox.removeEventListener('click', ansBoxClickHandler);
    })
}

function qnaInnerClickHandler(){
    var dnuBtnBox = this.querySelector('.dnuBtnBox');
    var ansBox = this.querySelector('.ansBox');

    $(dnuBtnBox).toggleClass('on');
    $(ansBox).slideToggle();
    $(this).find('.qnaBox').toggleClass('on');
    $(this).find('.qnaArrow').toggleClass('rotate');
}

function ansBoxClickHandler(event){
    event.stopPropagation();
}

function addToggleEvent(){
    var qnaBlocks = Array.from(qnaBlocks);('.qnaBlock');
    qnaBlocks.forEach(function(qnaBlock){
        var qnaInner = qnaBlock.querySelector('.qnaInner');
        var ansBox = qnaBlock.querySelector('.ansBox');

        qnaInner.addEventListener('click', qnaInnerClickHandler);
        ansBox.addEventListener('click', ansBoxClickHandler);
    })
}

var qnaBlocks = document.querySelectorAll('.qnaBlock');
var qnaBlocksArray = Array.from(qnaBlocks);

qnaBlocksArray.forEach(function (qnaBlock) {
    var dnuBtnBox = qnaBlock.querySelector('.dnuBtnBox');
    var qnaInner = qnaBlock.querySelector('.qnaInner');
    var ansBox = qnaBlock.querySelector('.ansBox');

    qnaInner.addEventListener('click', function () {
        $(dnuBtnBox).toggleClass('on');
        $(ansBox).slideToggle();
        $(qnaInner).find('.qnaBox').toggleClass('on');
        $(qnaInner).find('.qnaArrow').toggleClass('rotate');
    });

    ansBox.addEventListener('click', function (event) {
        event.stopPropagation();
    });
});


function updateStart(element) {
    const qnaBlock = element.closest('.qnaBlock');
    const ansText = qnaBlock.querySelector('.ansText');
    if (ansText != null) {
        ansText.classList.add('updateDisplay');
        qnaBlock.querySelector('.summernoteBox').classList.remove('updateDisplay');
        qnaBlock.querySelector('.labelQnaAnswer').classList.add('updateDisplay');
    }

    qnaBlock.querySelector('.inputQnaQuestion').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update2').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.cancel').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.delete').classList.add('updateDisplay');
    

}

function updateComplete(element) {
    const qnaBlock = element.closest('.qnaBlock');

    qnaBlock.querySelector('.labelQnaQuestion').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaAnswer').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').textContent = qnaBlock.querySelector('.inputQnaQuestion').value;
    qnaBlock.querySelector('.labelQnaAnswer').textContent = qnaBlock.querySelector('.inputQnaAnswer').value;

    // 수정된 부분
    const inputQnaAnswerValue = qnaBlock.querySelector('.inputQnaAnswer').value;
    const tag = qnaBlock.querySelector('.ansBox');
    if (tag && inputQnaAnswerValue) {
        tag.innerHTML = '';
        tag.insertAdjacentHTML('afterbegin', inputQnaAnswerValue);
    }

    qnaBlock.querySelector('.inputQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update2').classList.add('updateDisplay');

}

function updateCancel(element){
    const qnaBlock = element.closest('.qnaBlock');
    const ansText = qnaBlock.querySelector('.ansText');

    ansText.classList.remove('updateDisplay');
    qnaBlock.querySelector('.summernoteBox').classList.add('updateDisplay');
    qnaBlock.querySelector('.labelQnaAnswer').classList.remove('updateDisplay');

    qnaBlock.querySelector('.inputQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update2').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.cancel').classList.add('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.delete').classList.remove('updateDisplay');

    
}

function reloadBtn(){
    window.location.reload();
}

const deleteBtns = document.querySelectorAll('.dnuBtn.delete');

deleteBtns.forEach((deleteBtn) => {
    deleteBtn.addEventListener("click", function () {
        deletePopUp();
        puBtnYes.addEventListener('click', () => {
            const textCode = deleteBtn.parentNode.querySelector('.textCode').value;
            var url = '/csc/deleteQna?qnaCode=' + textCode;
            location.href = url;
        })
    })
});

$(document).ready(function () {
    $('.editor').summernote({
        height: 200,
        placeholder: '내용을 입력하세요...'
    });
});

function qna_search(){
    document.querySelector('.qna_searchForm').submit();
}


// function updateQna(element) {
//     const target = element.closest(".qnaBlock");
//     fetch('/csc/updateQna', {
//         method: 'POST',
//         cache: 'no-cache',
//         headers: {
//             'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
//         },
//         body: new URLSearchParams({
//             'qnaCode': target.querySelector('.bQnaCode').value,
//             'qnaQuestion': target.querySelector('.labelQnaQuestion').value,
//             'qnaAnswer': target.querySelector('.labelQnaAnswer').value,
//             'inputQnaQuestion': target.querySelector('.inputQnaQuestion').value,
//             'inputQnaAnswer': target.querySelector('.inputQnaAnswer').value
//         })
//     })
//         .then((response) => {
//             if (!response.ok) {
//                 alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
//                 return;
//             }

//             return response.text();
//         })
//         .then((data) => {
//             alert('내용이 변경되었습니다.');
//         })
//         .catch(err => {
//             alert('내용이 변경되었습니다.');
//             console.log(err);
//         });
// }
