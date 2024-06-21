// 주 재료 입력 칸 추가 함수
function mainMtrlPlus() {
    // 새 입력 칸 HTML을 생성
    var newInputHTML = `
        <div class="mtrlMt">
            <div>
           <p class="deleteMtrlMt" onclick="deleteMtrl(this)"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="#555" class="bi bi-dash-circle-fill" viewBox="0 0 16 16">
  <path d="M16 8A8 8 0 1 1 0 8a8 8 0 0 1 16 0M4.5 7.5a.5.5 0 0 0 0 1h7a.5.5 0 0 0 0-1z"/>
</svg></p>
                <p class="mtText"><input class="mtrlFirstInput recipeInput" type="text" placeholder="재료명"></p>
                <p class="mtNum"><input class="mtrlSecondInput recipeInput" type="text" placeholder="개수, 용량"></p>
                <p class="mtNum"><input class="mtrlThirdInput recipeInput" type="text" placeholder="단위 (L, 개)"></p>
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
                <p class="mtText"><input class="mtrlFirstInput recipeInput" type="text" placeholder="재료명"></p>
                <p class="mtNum"><input class="mtrlSecondInput recipeInput" type="text" placeholder="개수, 용량"></p>
                <p class="mtNum"><input class="mtrlThirdInput recipeInput" type="text" placeholder="단위 (L, 개)"></p>
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