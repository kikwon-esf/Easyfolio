const url = "/myPage/deleteFav"
const askDelete = document.querySelector('.commentCheckInner');
const commentAlarmInner = document.querySelector('.commentAlarmInner');
let currentElement = null;
function deleteFav(){
    

    const foodCode = currentElement.closest('.recipeTextBox1').querySelector(".foodCode").value;
    let data = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({
            "foodCode" : foodCode,
            "type" : "fetch"
        })
    }

    fetch(addFavURL,data)
    .then((resp)=>{
        let status = resp["status"];
        if(status != 200) {
            popup(askFrame);
        }
        
        return resp.text();
    })
    .then((data)=>{
        if(data == "addComplete"){
            console.log(resp)
            onOff(fillHeart,biHeart);
            onOffAnime(fillHeart,biHeart);
            rcmmCnt.innerHTML=(parseInt(rcmmCntVal)+1)
        }else if(data == "deleteComplete"){
            onOff(biHeart,fillHeart);
            onOffAnime(biHeart,fillHeart);
            console.log(resp)
            rcmmCnt.innerHTML=(parseInt(rcmmCntVal)-1)
        }
    })
    return false;
        
}

let addFavBtn = document.querySelectorAll(".cartBox");
const addFavURL = "/myPage/addFav";
var list = null;
//페이지 로딩시 하트색칠

window.addEventListener('load',()=>{
    heartFill();
})  
function heartFill(){
    let listdata = document.querySelector("#favoriteList")?.getAttribute("data-favorite-list");
    addFavBtn = document.querySelectorAll(".cartBox");
    list = JSON.parse(listdata);
    console.log(list)
    if(list != null){
        for(i = 0 ; i < addFavBtn.length ; i++){
            const foodCode = addFavBtn[i].closest('.recipe').querySelector(".foodCode").value;
            const biHeart = addFavBtn[i].closest('.recipe').querySelector(".bi-heart");
            const fillHeart = addFavBtn[i].closest('.recipe').querySelector(".bi-heart-fill");
            
            if(list.includes(foodCode)){
                onOff(fillHeart,biHeart);
            }else{
                onOff(biHeart,fillHeart);
            }       
        }
    }
}


  
//* 즐겨찾기 추가
function addOrDelFav(ele){
    const biHeart = ele.querySelector(".bi-heart");
    const fillHeart = ele.querySelector(".bi-heart-fill");
    const foodCode = ele.closest(".recipeTextBox1").querySelector(".foodCode").value;
    const rcmmCnt = ele.closest(".recipeTextBox1").querySelector(".RcmmCnt");
    const rcmmCntVal = ele.closest(".recipeTextBox1").querySelector(".RcmmCnt").textContent;

    
    let listFalg = list?.includes(foodCode);

    // if(listFalg){
    //     currentElement = ele;
    //     deletePopUp()
    // }else{
        let data = {
            method: 'POST',
            cache: 'no-cache',
            headers: {
                'Content-Type': 'application/json; charset=UTF-8'
            },
            body: JSON.stringify({
                "foodCode" : foodCode,
                "type" : "fetch"
            })
        }
    
        fetch(addFavURL,data)
        .then((resp)=>{
            let status = resp["status"];
            if(status != 200) {
                popup(askFrame);
            }
            // console.log(resp)
            return resp.text();
        })
        .then((data)=>{
            if(data == "addComplete"){
                onOff(fillHeart,biHeart);
                onOffAnime(fillHeart,biHeart);
                rcmmCnt.innerHTML=(parseInt(rcmmCntVal)+1)
            }else if(data == "deleteComplete"){
                onOff(biHeart,fillHeart);
                onOffAnime(biHeart,fillHeart);
                rcmmCnt.innerHTML=(parseInt(rcmmCntVal)-1)
            }
        })
        return false;
    }
    
    

// }
//animation mouseDown
const mouseDown = document.querySelectorAll('.mousedown');
for(let ele=0 ; ele < addFavBtn.length ; ele++){
    addFavBtn[ele].addEventListener('mousedown', ()=>{
        let each = addFavBtn[ele].querySelectorAll('svg');
        each[0].classList.add('heart_mouseDown');
        each[1].classList.add('heart_mouseDown');
    })
    addFavBtn[ele].addEventListener('mouseout', ()=>{
        let each = addFavBtn[ele].querySelectorAll('svg');
        each[0].classList.remove('heart_mouseDown');
        each[1].classList.remove('heart_mouseDown');
    })
}


//onOff함수

function onOff(onEle, offEle){
    onEle.classList.remove("heartOff");
    offEle.classList.add("heartOff");
}
function onOffAnime(onEle,offEle){
    offEle.classList.remove("toggle_anime");
    onEle.classList.add("toggle_anime");
}

function askDeleteFavorite(ele){
    currentElement = ele;
    askDelete.style.display = 'block';
}

function confirmDeleteFavorite(){
    deleteFav(currentElement);
    askDelete.style.display = 'none';
}
function NoDeleteFavorite(){
    askDelete.style.display = 'none';
}
function okBtn(element){
    element.closest('.commentAlarmInner').style.display = 'none';
}

