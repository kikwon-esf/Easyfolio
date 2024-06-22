

const foodCode = document.querySelector('.foodCode_hidden').value;
const memberId = document.querySelector('.detailMemberId').value;
const getCommentURL = "/food/comment";
const submitURL = "/myPage/comment"
const replacePosition = document.querySelector('.replacePosition');
let response_commentId = document.querySelector("#response_commentId")?.value;
let nowPage = document.querySelector("#nowPage")?.value;
function getCommentList(url) {
    fetch(url)
        .then((resp) => {
            return resp.text();
        })
        .then((data) => {
            writeContent(replacePosition, data);
            pagingCount();
            const foodCode_hide = document.querySelector('.foodCode_hide')
            if(response_commentId != null || undefined){
                const commentNode = this.document.getElementById(String(response_commentId));
                console.dir(commentNode);
        
                window.scrollTo(0,findScrollValue(commentNode));
                response_commentId = null;
            }
        })
}
function writeContent(postion, content) {
    postion.innerHTML = content;
}
function commentPaging(pageNum){
    let pagingURL = getCommentURL + "?foodCode="+ foodCode + "&reciveMemberId=" + memberId + "&nowPage="+pageNum;
    console.log(pagingURL)
    getCommentList(pagingURL);
}
window.addEventListener('DOMContentLoaded', () => {
    getCommentList(getCommentURL + "?foodCode=" + foodCode + "&reciveMemberId=" + memberId);

})

function submitComment(target) {
    let formData = new FormData(target.closest('.submitBlock'));
    let options = {
        method: 'POST',
        cache: 'no-cache',
        body: formData
    }
    fetch(submitURL, options)
        .then((resp) => {
            console.log(resp);
            if(!resp.ok){
                throw new Error();
                return;
            }
            return resp.text();
        })
        .then((data) => {
            writeContent(replacePosition, data);
            pagingCount();
            // const foodCode_hide = document.querySelector('.foodCode_hide')
        })
        .catch(e=>{
            // alert("부정확한 값입니다!");
            pu_error();
        })


}

function insertComment(element) {
    var commentEach = element.closest('.commentEach');
    var submitBlock = commentEach.querySelector('.submitBlock');
    if (submitBlock) {
        submitBlock.style.display = "block";
    }
}

function updateCommentBtn(element) {
    var commentBlock = element.closest('.commentBlock');
    var updateInputBlock = commentBlock.querySelector('.updateInputBlock');
    var inputUpdateComment = updateInputBlock.querySelector('.input_textarea');
    var commentText = commentBlock.querySelector('.commentText');
    if (updateInputBlock) {
        inputUpdateComment.textContent = commentText.textContent;
        updateInputBlock.style.display = "block";
    }
    if (commentText) {
        commentText.style.display = "none";
    }
}
function updateCheck(element) {
    var commentCheckInner = document.querySelector('.commentCheckInner');
    var checkText = commentCheckInner.querySelector('.checkText');

    checkText.textContent = '댓글을 수정하시겠습니까?';

    commentCheckInner.style.display = "block";

    document.querySelector('.comCheckBtn.yes').onclick = function () {
        updateComment(element)
        commentCheckInner.style.display = "none";
    };

    document.querySelector('.comCheckBtn.no').onclick = function () {
        commentCheckInner.style.display = "none";
    };
}

function updateAlarmCheck() {
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    var alarmText = commentAlarmInner.querySelector('.alarmText');

    alarmText.textContent = '댓글 수정을 완료했습니다.';

    commentAlarmInner.style.display = "block";

    document.querySelector('.alarmBtn.yes').onclick = function () {
        commentAlarmInner.style.display = "none";
    };
}
function deleteAlarmCheck() {
    var commentAlarmInner = document.querySelector('.commentAlarmInner');
    var alarmText = commentAlarmInner.querySelector('.alarmText');

    alarmText.textContent = '댓글 삭제를 완료했습니다.';

    commentAlarmInner.style.display = "block";

    document.querySelector('.alarmBtn.yes').onclick = function () {
        commentAlarmInner.style.display = "none";
    };
}

function deleteCheck(element) {
    var commentCheckInner = document.querySelector('.commentCheckInner');
    var checkText = commentCheckInner.querySelector('.checkText');

    checkText.textContent = '댓글을 삭제하시겠습니까?';

    commentCheckInner.style.display = "block";

    document.querySelector('.comCheckBtn.yes').onclick = function () {
        deleteComment(element)
        commentCheckInner.style.display = "none";
    };

    document.querySelector('.comCheckBtn.no').onclick = function () {
        commentCheckInner.style.display = "none";
    };
}

function updateComment(element) {
    var commentEach = element.closest('.commentEach');
    var foodCommentId = commentEach.querySelector('.foodCommentId').value;
    var inputUpdateComment = commentEach.querySelector('.input_textarea').value;

    if (inputUpdateComment == null || inputUpdateComment == '') {
       alert('댓글을 입력해주세요')

    } else{
        fetch('/myPage/updateComment', { 
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },
            body: new URLSearchParams({
                'foodCommentId': foodCommentId,
                'content': inputUpdateComment
            })
        })
            .then((response) => {
                console.log(response)
                if (!response.ok) {
                    // alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
                    
                    throw new Error();
                    return;
                }
                return response.text();
            })
            .then((data) => {
                var commentBlock = element.closest('.commentBlock');
                var updateInputBlock = commentBlock.querySelector('.updateInputBlock');
                var commentText = commentBlock.querySelector('.commentText');
                var commentDate = commentBlock.querySelector('.commentDate');
                var updateCheckBox = commentDate.querySelector('.updateCheckBox');
                var upCheckValue = commentDate.querySelector('.upCheck').value;

                if (updateInputBlock) {
                    var inputUpdateComment = updateInputBlock.querySelector('.input_textarea');
                    if (inputUpdateComment) {
                        commentText.textContent = inputUpdateComment.value;
                    }
                    updateInputBlock.style.display = "none";
                    if(upCheckValue != 'Y'){
                        updateCheckBox.style.display = 'inline-block';
                    }
                    
                }
                if (commentText) {
                    commentText.style.display = "block";
                }
                updateAlarmCheck();
            })
            .catch(err => {
                // alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                // alert('부정확한 값입니다!!');
                pu_error();
                console.log(err);
            });
    }
   
}

function deleteComment(element) {
    var commentEach = element.closest('.commentEach');
    var foodCommentId = commentEach.querySelector('.foodCommentId').value;

    fetch('/myPage/updateComment', { 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'foodCommentId': foodCommentId
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
            var commentBlock = element.closest('.commentBlock');
            var commentText = commentBlock.querySelector('.commentText');
            var updateInputBlock = commentBlock.querySelector('.updateInputBlock');
            var commentDate = commentBlock.querySelector('.commentDate');
            var commentBtns = commentBlock.querySelector('.commentBtns');
        
            commentText.textContent = '삭제된 댓글입니다.';
            updateInputBlock.style.display = 'none';
            commentDate.style.display = 'none';
            commentBtns.style.display = 'none';
            deleteAlarmCheck();
        })  
        
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            console.log(err);
        });
}


function findScrollValue(commentNode){

    let rect = commentNode.getBoundingClientRect();
    console.log(rect.top)
    console.log(window.scrollY)
    return rect.top;
}
function pagingCount(){
    
    nowPage = document.querySelector("#nowPage").value;
    console.log("nowpage : "+nowPage)
    let nowPageNode = document.getElementById('page'+nowPage);
    nowPageNode.classList.add('active');
}