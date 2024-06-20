document.addEventListener("DOMContentLoaded", function () {
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

function updateQna(element) {
    const target = element.closest(".qnaBlock");
    fetch('/csc/updateQna', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'qnaCode': target.querySelector('.bQnaCode').value,
            'qnaQuestion': target.querySelector('.labelQnaQuestion').value,
            'qnaAnswer': target.querySelector('.labelQnaAnswer').value,
            'inputQnaQuestion': target.querySelector('.inputQnaQuestion').value,
            'inputQnaAnswer': target.querySelector('.inputQnaAnswer').value
        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            return response.text();
        })
        .then((data) => {
            alert('내용이 변경되었습니다.');
        })
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

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
    qnaBlock.querySelector('.labelQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.add('updateDisplay');
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

function deletePopUp() {
    displayOn(puDelete);
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