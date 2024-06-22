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
                <p class="mtNum"><input id="mainMtrl" class="mtrlThirdInput recipeInput" type="text" placeholder="단위 (L, 개)"></p>
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
                <p class="mtText"><input id="subMtrl1" class="mtrlFirstInput recipeInput" type="text" placeholder="재료명"></p>
                <p class="mtNum"><input id="subMtrl2" class="mtrlSecondInput recipeInput" type="text" placeholder="개수, 용량"></p>
                <p class="mtNum"><input id="subMtrl3" class="mtrlThirdInput recipeInput" type="text" placeholder="단위 (L, 개)"></p>
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
    var cateInput = document.querySelector(".cateInput");
    var ttlInput = document.querySelector(".ttlInput");
    var cateSelect = document.querySelector(".cateSelect");
    var usageSelect = document.querySelector(".usageSelect");
    var mtrlSelect = document.querySelector(".mtrlSelect");
    var typeSelect = document.querySelector(".typeSelect");
    var recipeImg = document.querySelector(".recipeImg");
    var mainMtrlList = document.querySelectorAll("#mainMtrl");
    var count = 0;
    var i = 0;
    var mainMtrlPlus = "";
    var mainMtrlResult = "";
    for (const mainMtrl of mainMtrlList) {
        i += 1;
        count += 1;
        if(count == 1){
            mainMtrlPlus += mainMtrl.value + " ";
        } else if(count == 2){
            mainMtrlPlus += mainMtrl.value
        } else if(i == mainMtrlList.length){
            mainMtrlPlus += mainMtrl.value
        } else{
            mainMtrlPlus += mainMtrl.value + "|";
            count = 0;
        }
    }
    mainMtrlResult += "[양념]" + mainMtrlPlus;

    console.log(mainMtrlResult)   
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