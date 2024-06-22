// 주 재료 입력 칸 추가 함수
function mainMtrlPlus() {
    // 새 입력 칸 HTML을 생성
    var newInputHTML = `
        <div class="mtrlMt">
            <div>
           <p class="deleteMtrlMt" onclick="deleteMtrl(this)"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#555" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1z"/>
</svg></p>
                <p class="mtText"><input id="mainMtrl" class="mtrlFirstInput recipeInput" type="text" placeholder="재료명"></p>
                <p class="mtNum"><input id="mainMtrl" class="mtrlSecondInput recipeInput" type="text" placeholder="개수, 용량"></p>
            </div>
        </div>`;

    // 새 입력 칸을 주 재료 .plus 클래스 앞에 삽입
    var mainPlusElement = document.querySelector('.mainMtrl .mtrlMt.plus');
    mainPlusElement.insertAdjacentHTML('beforebegin', newInputHTML);
}

// 부 재료 입력 칸 추가 함수
function subMtrlPlus() {
    // 새 입력 칸 HTML을 생성
    var newInputHTML = `
        <div class="mtrlMt">
            <div>
                <p class="deleteMtrlMt" onclick="deleteMtrl(this)"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#555" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1z"/>
</svg></p>
                <p class="mtText"><input id="subMtrl" class="mtrlFirstInput recipeInput" type="text" placeholder="재료명"></p>
                <p class="mtNum"><input id="subMtrl" class="mtrlSecondInput recipeInput" type="text" placeholder="개수, 용량"></p>
            </div>
        </div>`;

    // 새 입력 칸을 부 재료 .plus 클래스 앞에 삽입
    var subPlusElement = document.querySelector('.subMtrl .mtrlMt.plus');
    subPlusElement.insertAdjacentHTML('beforebegin', newInputHTML);
}

// mtrlMt 요소 삭제 함수
function deleteMtrl(element) {
    element.closest('.mtrlMt').remove();
}

function recipeTextPlus() {
    // 새로운 조리순서 입력란을 생성
    var newStepInput = document.createElement('div');
    newStepInput.className = 'stepsTextInputBox';

    // 새로운 조리순서 번호를 설정
    var currentNumber = document.querySelectorAll('.stepsNumber').length + 1;

    newStepInput.innerHTML = `
    <p class="deleteRecipeText" onclick="deleteRecipeText(this)"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="#555" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1z"/>
</svg></p>
        <div class="stepsNumber">
            <p>${currentNumber}</p>
        </div>
        <input class="stepsTextInput recipeTextarea" placeholder="조리 방법을 입력 해 주세요.">
    `;

    // 기존 버튼 바로 위에 새로운 입력란을 삽입
    var addButton = document.querySelector('.recipeTextPlusBtn');
    addButton.parentNode.insertBefore(newStepInput, addButton);
}

function deleteRecipeText(element) {
    // 삭제할 부모 요소 (.stepsTextInputBox)를 찾아서 제거
    var parentBox = element.parentNode;
    parentBox.parentNode.removeChild(parentBox);

    // 조리순서 번호를 다시 설정
    var stepNumbers = document.querySelectorAll('.stepsNumber p');
    stepNumbers.forEach((step, index) => {
        step.textContent = index + 1;
    });
}
function recipeInsertCheck() {
    // 입력값 가져오기
    const cateInput = document.querySelector(".cateInput");
    const ttlInput = document.querySelector(".ttlInput");
    const cateSelect = document.querySelector(".cateSelect");
    const usageSelect = document.querySelector(".usageSelect");
    const mtrlSelect = document.querySelector(".mtrlSelect");
    const typeSelect = document.querySelector(".typeSelect");
    const recipeImg = document.querySelector(".recipeImg");
    const mainMtrlList = document.querySelectorAll("#mainMtrl");
    const recipeIndcInput = document.querySelector(".recipeIndcInput");
    const stepsTextInputList = document.querySelectorAll(".stepsTextInput");
    var count = 0;
    var i = 0;
    var mainMtrlPlus = "";
    var mainMtrlResult = "";

    // 입력값 검증
    if (cateInput.value == "" || cateInput.value == null) {
        cateInput.classList.add('check');
        cateInput.placeholder = "입력 바람";
        return;
    } 

    if (ttlInput.value == "" || ttlInput.value == null) {
        ttlInput.classList.add('check');
        ttlInput.placeholder = "입력 바람";
        return;
    } 

    if (cateSelect.value == "noCheck") {
        cateSelect.classList.add('check');
        return;
    } 

    if (usageSelect.value == "noCheck") {
        usageSelect.classList.add('check');
        return;
    } 

    if (mtrlSelect.value == "noCheck") {
        mtrlSelect.classList.add('check');
        return;
    } 

    if (typeSelect.value == "noCheck") {
        typeSelect.classList.add('check');
        return;
    } 

    // if (recipeImg.value == "") {
    //     document.querySelector(".recipeInsertBox").classList.add('check');
    //     return;
    // } 

    // 주 재료 리스트 검증
    for (const mainMtrl of mainMtrlList) {
        if (mainMtrl.value == "" || mainMtrl.value == null) {
            mainMtrl.classList.add('check');
            mainMtrl.placeholder = "입력 바람";
            return;
        } else {
            i += 1;
            count += 1;
            if (count == 1) {
                mainMtrlPlus += mainMtrl.value + " ";
            } else if (count == 2) {
                mainMtrlPlus += mainMtrl.value + "|";
                count = 0;
            } else if (i == mainMtrlList.length) {
                mainMtrlPlus += mainMtrl.value;
            }
        }
    }
    mainMtrlResult += "[재료]" + mainMtrlPlus + " ";

    // 부 재료 리스트 검증
    var subMtrlList = document.querySelectorAll("#subMtrl");
    var count1 = 0;
    var i1 = 0;
    var subMtrlPlus = "";
    var subMtrlResult = "";
    for (const subMtrl of subMtrlList) {
        if (subMtrl.value == "" || subMtrl.value == null) {
            subMtrl.classList.add('check');
            subMtrl.placeholder = "입력 바람";
            return;
        } else {
            i1 += 1;
            count1 += 1;
            if (count1 == 1) {
                subMtrlPlus += subMtrl.value + " ";
            } else if (count1 == 2) {
                subMtrlPlus += subMtrl.value;
            } else if (i1 == subMtrlList.length) {
                subMtrlPlus += subMtrl.value;
            } else {
                subMtrlPlus += subMtrl.value + "|";
                count1 = 0;
            }
        }
    }
    subMtrlResult += "[양념]" + subMtrlPlus;
    foodMtrlCn = mainMtrlResult + subMtrlResult;
    document.querySelector('#foodMtrlCn').value = foodMtrlCn;

    // 이후 추가 로직을 여기에 추가
    if (recipeIndcInput.value == "" || recipeIndcInput.value == null) {
        recipeIndcInput.classList.add('check');
        recipeIndcInput.placeholder = "입력 바람";
        return;
    } 
    var count2 = 0;
    var i2 = 0;
    var stepsTextInputResult = "";
    for (const stepsTextInput of stepsTextInputList) {
        if (stepsTextInput.value == "" || stepsTextInput.value == null) {
            stepsTextInput.classList.add('check');
            stepsTextInput.placeholder = "입력 바람";
            return;
        } else {
            i2 += 1;
            stepsTextInputResult += stepsTextInput.value + "%";
        }
    }
    document.querySelector('#foodEx').value = stepsTextInputResult;

    document.querySelector('.foodUpdateForm').submit();
}

function readURL(input) {
    let box = document.querySelector('.imgLookBox');
    box.innerHTML = "";
    let a = 0;
    for (const file of input.files) {
        var fileName = file.name;
        var reader = new FileReader();
        reader.onload = function (e) {
            const imgElement = document.createElement("img");
            imgElement.className = "imgSlideList";
            imgElement.src = e.target.result;
            imgElement.alt = '';

            box.appendChild(imgElement);
        };
        a++;
        reader.readAsDataURL(file);
    };
    
}
document.addEventListener('DOMContentLoaded', function () {
    const inputs = document.querySelectorAll('input.recipeInput, textarea.recipeTextarea');
    const selects = document.querySelectorAll('select');
    const recipeInsertBox = document.querySelector('.recipeInsertBox');
    const recipeTextareas = document.querySelectorAll('.recipeTextarea');
    inputs.forEach(function (input) {
        input.addEventListener('click', function () {
            if (input.classList.contains('check')) {
                input.classList.remove('check');
            }
        });
    });
    selects.forEach(function (select) {
        select.addEventListener('click', function () {
            if (select.classList.contains('check')) {
                select.classList.remove('check');
            }
        });
    });
    recipeInsertBox.addEventListener('click', function () {
        if (recipeInsertBox.classList.contains('check')) {
            recipeInsertBox.classList.remove('check');
        }
    });
    recipeTextareas.forEach(function (recipeTextarea) {
        recipeTextarea.addEventListener('click', function () {
            if (recipeTextarea.classList.contains('check')) {
                recipeTextarea.classList.remove('check');
            }
        });
    });
});
