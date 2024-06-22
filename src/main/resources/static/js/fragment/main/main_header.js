let allSearchKeyword = document.querySelector('.allSearchKeyword');

var wrap = document.querySelector('#wrap');
var searchblock = document.querySelector('.searchblock');



const bell_empty = document.getElementById('bell-empty');
const bell_fill = document.getElementById('bell-fill');
const alarmDeleteBtn = document.getElementById('delete-all-btn');






allSearchKeyword.addEventListener("click", function () {
    searchblock.style.border = "2px solid rgb(242,146,33)";
    searchblock.style.backgroundColor = "transparent"
    allSearchKeyword.style.color = "#333";
    allSearchKeyword.value = '';
})
var tap = document.querySelector('.tap');
var popupTap = document.querySelector('.popupTap');
var tapIcon = document.querySelector('.bi.bi-justify-left');

tap.addEventListener("click", function () {

    if (popupTap.style.display == "block") {
        popupTap.style.display = "none";
        tapIcon.setAttribute("style", "background-color:transparent;");
        tapIcon.setAttribute("fill", "#ffd57a");
    } else {
        popupTap.style.display = "block";
        tapIcon.setAttribute("style", "background-color:#e8e8e8; border-radius: 6px;");
        tapIcon.setAttribute("fill", "rgb(242,146,33)");
    }
})
tap.addEventListener("click", function (event) {
    event.stopPropagation(); // 클릭 이벤트 전파 방지
});

popupTap.addEventListener("click", function (event) {
    event.stopPropagation(); // 클릭 이벤트 전파 방지
});

document.addEventListener("click", function (event) {
    if (event.target !== allSearchKeyword) {
        searchblock.style.border = "2px solid transparent";
        searchblock.style.backgroundColor = "#f4f4f4";
        allSearchKeyword.style.color = "#999";
        allSearchKeyword.value = '원하는 모든 레시피';
    }
    if (!event.target.closest('.tap') && !event.target.closest('.popupTap')) {
        popupTap.style.display = "none";
        tapIcon.setAttribute("style", "background-color:transparent;");
        tapIcon.setAttribute("fill", "#ffd57a");
    }


});

document.addEventListener("mouseover", function (event) {
    var icon = document.querySelectorAll('.icon');
    if (!event.target.closest('.popup')) {
        icon.forEach((element, idx) => {
            element.querySelector('.popup').style.display = "none";
        });
    }
    icon.forEach((element, idx) => {
        if (event.target.closest('.icon') === element) {
            element.querySelector('.popup').style.display = "block";
        }
    });
})

//로그아웃
// var logout = document.getElementById("logout");
// if (logout != null) {
//     logout.addEventListener("click", (e) => {
//         let active = confirm("로그아웃을 하시겠습니까?");
//         if (!active) {
//             e.preventDefault();
//         }
//     })
// }

function allSearch() {
    document.querySelector(".allSearchForm").submit();
}



const puDelete = document.querySelector(".pu_delete");
const puBtnYes = document.querySelector(".pu_yes");

function displayOn(element) {
    element.classList.remove('pu_blind');
}

function deletePopUp() {
    displayOn(puDelete);
}

function displayOff_pu(element) {
    element.closest('.pupu').classList.add('pu_blind');
}

function deleteAlarmAll() {
    fetch('/member/deleteAlarmAll', { 
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({
        })
    })
        .then((data) => {
            deleteAlarms();
            alarmCountZero();

        })
        .catch(err => {
            alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
            // pu_error();
            console.log(err);
        });
}
function deleteAlarms() {
    var alarmBoxes = document.querySelectorAll('.alarmBox');
    var delay = 0;
    alarmBoxes.forEach(function (alarmBox, index) {
        alarmBox.style.transition = 'opacity 0.5s ease ' + delay + 's';
        alarmBox.style.opacity = '0';
        delay += 0.1;
        alarmBox.addEventListener('transitionend', function () {
            alarmBox.remove();
            if (document.querySelectorAll('.alarmBox').length === 0) {
                createZeroAlarmElement();
            }
        });
    });
}

function deleteAlarm(icon) {
    var alarmCode = icon.getAttribute('data-alarm-code');
    if (alarmCode) {
        fetch('/member/deleteAlarm', { 
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
            },

            body: new URLSearchParams({
                'alarmCode' : alarmCode
            })
        })
            .then((data) => {
                var alarmBox = icon.closest('.alarmBox'); 
                if (alarmBox) {
                    alarmBox.style.transition = 'opacity 0.5s ease';
                    alarmBox.style.opacity = '0'; 
                    alarmBox.addEventListener('transitionend', function () {
                        alarmBox.remove(); 
                        if (document.querySelectorAll('.alarmBox').length === 0) {
                            createZeroAlarmElement();  
                        }
                    });
                }
            })
            .then(()=>{
                alarmListRander();
            })
            .catch(err => {
                alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
                console.log(err);
            });
    }
}

function deleteAlarmOne(icon) {
    var alarmBox = icon.closest('.alarmBox'); 
    if (alarmBox) {
        alarmBox.style.transition = 'opacity 0.5s ease';
        alarmBox.style.opacity = '0';
        alarmBox.addEventListener('transitionend', function () {
            alarmBox.remove();
            alarmCountRender();
            if (document.querySelectorAll('.alarmBox').length === 0) {
                createZeroAlarmElement(); 
            }
        });
    }
}




function createZeroAlarmElement() {
    var alarmBlock = document.createElement('div');
    alarmBlock.classList.add('alarmBlock');
    
    var zeroAlarmText = document.createTextNode('알람이 없습니다');
    var zeroAlarmParagraph = document.createElement('p');
    zeroAlarmParagraph.classList.add('zeroAlarm');
    zeroAlarmParagraph.appendChild(zeroAlarmText);
    
    alarmBlock.appendChild(zeroAlarmParagraph);
    
    var alarmDeleteBox = document.querySelector('.alarmDeleteBox'); // 알람 삭제 박스
    if (alarmDeleteBox) {
        alarmDeleteBox.remove(); // 알람 삭제 박스 제거
    }
    
    document.querySelector('.alarmInner').appendChild(alarmBlock);
}




let alarmList = JSON.parse(sessionStorage.getItem("alarmList"));
const alarmNumber = document.querySelector('.alarmNumber');

//알람 개통
window.addEventListener('DOMContentLoaded', function(){
    alarmCountRender()
    let user = document.querySelector(".userName").value;
    if(user != null && user!='' ){
        alarmListRander();
        alarmCountRender();
        const emitter = new EventSource("http://localhost:8081/notify/getAlarm");
        sessionStorage.setItem("emitter",JSON.stringify(emitter))
        emitter.addEventListener('notification',(e)=>{
            const data = JSON.stringify(JSON.parse(e.data));
            const isEqual = JSON.stringify(alarmList) === data;
            
            if(!isEqual){
                sessionStorage.setItem("alarmList",data);
                alarmList = JSON.parse(sessionStorage.getItem("alarmList"));
                alarmListRander();
                alarmCountRender();
                return;
            }
            alarmCountRender(); 
        })
        
     
    }
    
    
    
});

const logoutDisplay = document.querySelector('.logout')
function logout(){
    displayOn(logoutDisplay);

}
function logoutYes(){
    location.href="/logout";
}
function logoutNo(){
    logoutDisplay.classList.add('pu_blind');
}

//알람 변화 감지시 replace하는 함수
function alarmListRander(){
    //replace 위치
    
    const replacePosition = document.querySelector(".alarmInner");
    const getAlarmPageurl = "/myPage/getAlarmPage";
    let data = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify(alarmList == null ? [] : alarmList)
    }
    console.log("alarmlistRender : " + data)
    fetch(getAlarmPageurl,data)
    .then((resp)=>{
        return resp.text();
    })
    .then((data)=>{
        replacePosition.innerHTML=data;
        alarmCountRender();
    })

}

function alarmCountRender(){
    let alarmCount = document.querySelector('#alarmCount')?.value;
    if(alarmCount>=0){
        alarmNumber.classList.remove("alarmCountHide");
        alarmNumber.textContent=alarmCount;
        alarmDeleteBtn.classList.remove('hide');
        bell_fill.classList.remove('hide');
        bell_empty.classList.add('hide');
    }

    if(alarmCount<=0 || alarmCount == ""||alarmCount == undefined){
        alarmNumber.classList.add("alarmCountHide");
        alarmDeleteBtn.classList.add('hide');
        bell_fill.classList.add('hide');
        bell_empty.classList.remove('hide');
    }
}
function alarmCountZero(){
    let alarmCount = document.querySelector('#alarmCount');
    alarmCount.value = 0;
    alarmCountRender();
}
function alarmListStatus(){
    let alarmCount = document.querySelector('#alarmCount')?.value;
    return alarmCount>0 ? true : false;
}
// function Switch(element, bool){
//     if(bool){
//         element.classList.remove('hide');
//     }else{
//         element.classList.add('hide')
//     }
// }

function pu_error (){
    
    const popUp = document.querySelector('.pu_error');
    console.log(popUp)
    popUp.classList.remove('pu_blind');
}
