// 자주 찾는 질문을 클릭해서 넘어왔을 때 해당 질문 열리는 코드
document.addEventListener("DOMContentLoaded", function () {
    setTimeout(function() {
        const aQnaCode = $('.aQnaCode').val();
        const bQnaCodes = $('.bQnaCode');
        bQnaCodes.each(function () {
            if ($(this).val() === aQnaCode) {
                openQna(this);
            }
        });
    }, 100); 
});
// 서머노트
$(document).ready(function () {
    $('.editor').summernote({
        height: 200,
        placeholder: '내용을 입력하세요...'
    });
});
// 질문 검색
function qna_search(){
    document.querySelector('.qna_searchForm').submit();
}
// 자주 찾는 질문 열기
$(document).ready(function () {
    $('.qnaNormalBlock, .qnaArrow').click(function () {
        openQna(this);
    });
});
// 자주 찾는 질문 여는 코드
function openQna(element) {
    var qnaInner = $(element).closest('.qnaInner');
    qnaInner.find('.ansBox').slideToggle();
    qnaInner.find('.qnaBox').toggleClass('open');
    qnaInner.find('.dnuBtnBox').toggleClass('disNo');
    qnaInner.find('.qnaArrow').toggleClass('rotate');
}
// 수정 버튼 클릭
function updateStart(element) {
    toggleUpdate(element.closest('.qnaInner'));
}
// 수정 취소 버튼 클릭
function updateCancel(element) {
    toggleUpdate(element.closest('.qnaInner'));
}
// 여러 태그들 토글 부여 함수
function toggleUpdate(qnaInner) {
    var dnuBtnBox = qnaInner.querySelector('.dnuBtnBox');
    var qnaNormalBlock = qnaInner.querySelector('.qnaNormalBlock');
    var qnaUpdateBox = qnaInner.querySelector('.qnaUpdateBox');
    var qnaArrow = qnaInner.querySelector('.qnaArrow');
    $(dnuBtnBox).find('.qnaBtnBox.complate_Cancle, .qnaBtnBox.update_Delete').toggleClass('deleteBtn');
    $(qnaArrow).toggleClass('disNo');
    $(qnaNormalBlock).toggleClass('disNo');
    $(qnaUpdateBox).toggleClass('disNo');
}

///////////////// 수정 완료 버튼 클릭

// 수정 체크 팝업 열기
function checkPopup(element){
    const target = element.closest(".qnaInner");
    target.querySelector('.commentCheckInner').classList.remove('pop_blind');
}
// 수정 취소
function NoUpdate(element){
    element.closest('.commentCheckInner').classList.add('pop_blind');
}
// 수정 확인
function updateQna(element) {
    element.closest('.commentCheckInner').classList.add('pop_blind');
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    const target = element.closest(".qnaBlock");
    console.log(target.querySelector('.editor.inputQnaAnswer').value);
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
            commentAlarmInner.querySelector('.alarmText').textContent = "수정이 완료되었습니다.";
            commentAlarmInner.classList.remove('pop_blind');
        })
        .catch(err => {
            alert('에러임 ㅋㅋ.');
            console.log(err);
        });
}
// 수정 완료 팝업
function okBtn(){
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    commentAlarmInner.classList.add('pop_blind');
    location.reload();
};

//////////////////// 삭제 버튼 클릭

// 삭제 체크 팝업 열기
function deletePopUp(element){
    const target = element.closest(".qnaInner");
    target.querySelector('.deleteCheckInner').classList.remove('pop_blind');
}
// 삭제 취소
function NoDelete(element){
    element.closest('.deleteCheckInner').classList.add('pop_blind');
}
// 삭제 확인 버튼 클릭
function deleteQna(element){
    var qnaInner = element.closest('.qnaInner');
    const textCode = qnaInner.querySelector('.textCode').value;
    location.href = '/csc/deleteQna?qnaCode=' + textCode;
}