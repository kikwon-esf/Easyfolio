

document.addEventListener("DOMContentLoaded", function () {
    $(document).ready(function () {
        const aQnaCode = $('.aQnaCode').val();
        const bQnaCodes = $('.bQnaCode');
        bQnaCodes.each(function () {
            if ($(this).val() === aQnaCode) {
                const qnaInner = $(this).closest('.qnaInner');
                qnaInner.addClass('on');
                
                // 제이쿼리로 slideToggle() 적용
                qnaInner.parent().find('.ansBox').slideToggle();
                
                qnaInner.parent().find('.qnaBox').addClass('on');
                qnaInner.parent().find('.qnaArrow').addClass('rotate');
            }
        });
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

// 내용 수정 비동기

function updateQna(qnaCode, qnaQuestion, qnaAnswer) {
    fetch('/csc/updateQna', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'qnaCode': qnaCode,
            'qnaQuestion': qnaQuestion,
            'qnaAnswer': qnaAnswer,
            'inputQnaQuestion' : document.querySelector('.inputQnaQuestion').value,
            'inputQnaAnswer' : document.querySelector('.inputQnaAnswer').value

        })
    })
        .then((response) => {
            if (!response.ok) {
                alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                return;
            }

            return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
            //return response.json(); //나머지 경우에 사용
        })
        //fetch 통신 후 실행 영역
        .then((data) => {//data -> controller에서 리턴되는 데이터!
            alert('변경되었습니다.')

        })
        //fetch 통신 실패 시 실행 영역
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}

const qnaLists = document.querySelectorAll('qnaBlock');



function updateStart(element){
    const qnaBlock = element.closest('.qnaBlock');
    const ansText = qnaBlock.querySelector('.ansText');
    ansText.classList.add('updateDisplay');
    qnaBlock.querySelector('.inputQnaQuestion').classList.remove('updateDisplay');
    qnaBlock.querySelector('.summernoteBox').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update2').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.labelQnaAnswer').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.add('updateDisplay');
}

function updateComplete(element){
    const qnaBlock = element.closest('.qnaBlock');
    qnaBlock.querySelector('.inputQnaQuestion').classList.add('updateDisplay');
    qnaBlock.querySelector('.inputQnaAnswer').classList.add('updateDisplay');
    qnaBlock.querySelector('.summernoteBox').classList.add('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update2').classList.add('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaAnswer').classList.remove('updateDisplay');
    qnaBlock.querySelector('.dnuBtn.update').classList.remove('updateDisplay');
    qnaBlock.querySelector('.labelQnaQuestion').textContent = qnaBlock.querySelector('.inputQnaQuestion').value;
    qnaBlock.querySelector('.labelQnaAnswer').textContent = qnaBlock.querySelector('.inputQnaAnswer').value;
    const tag = qnaBlock.querySelector('.ansBox');
    tag.innerHTML = '';
    tag.insertAdjacentHTML('afterbegin', qnaBlock.querySelector('.editor.inputQnaAnswer').textContent);
}

// 삭제 팝업

function deletePopUp(){
    displayOn(puDelete);
}

const deleteBtns = document.querySelectorAll('.dnuBtn.delete');

deleteBtns.forEach((deleteBtn) =>{
    deleteBtn.addEventListener("click", function(){
        deletePopUp();
        puBtnYes.addEventListener('click', ()=>{
            const textCode =  deleteBtn.parentNode.querySelector('.textCode').value;
            var url = '/csc/deleteQna?qnaCode=' + textCode;
            location.href = url;
        })

    })
});


$(document).ready(function() {
    // 반복문을 통해 Summernote 생성
        // Summernote 초기화
        $('.editor').summernote({
            height: 200, // 에디터 높이 설정 (선택 사항)
            placeholder: '내용을 입력하세요...', // 플레이스홀더 설정 (선택 사항)
        });
    });