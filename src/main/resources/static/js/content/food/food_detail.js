window.addEventListener('DOMContentLoaded', function () {
    var mtrlMtElements = document.querySelectorAll('.recipe_mtrl .mtrlMt');

    mtrlMtElements.forEach(function(element) {
        var numChildren = element.childElementCount;
        if (numChildren > 5) {
            element.classList.add('full');
        }
    });
    
    
    
});

// 삭제 체크 팝업 열기
function deletePopUp(){
    document.querySelector('.deleteFoodCheckInner').classList.remove('pop_blind');
}
// 삭제 취소
function NoDelete(element){
    document.querySelector('.deleteFoodCheckInner').classList.add('pop_blind');
}
// 삭제 확인 버튼 클릭
function deleteFood(){
    var commentFoodAlarmInner = document.querySelector('.commentFoodAlarmInner');
    const foodCode = document.querySelector('.foodCode_hidden').value;
    document.querySelector('.deleteFoodCheckInner').classList.add('pop_blind');
    fetch('/food/deleteFood', {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        body: new URLSearchParams({
            'foodCode': foodCode
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
                commentFoodAlarmInner.querySelector('.alarmText').textContent = "삭제가 완료되었습니다.";
                commentFoodAlarmInner.classList.remove('pop_blind');
            
        })
        .catch(err => {
            alert('에러임 ㅋㅋ.');
            console.log(err);
        });
}

function okFoodBtn(){
    var commentFoodAlarmInner = document.querySelector('.commentFoodAlarmInner');
    commentFoodAlarmInner.classList.add('pop_blind');
    history.back(); 
}