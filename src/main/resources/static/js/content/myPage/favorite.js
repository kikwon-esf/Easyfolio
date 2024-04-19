const url = "/myPage/deleteFav"
function deleteFav(ele){
    console.log('ele' + ele);
    console.log('a'+ele.closest('.recipeTextBox1'));
    const foodCode = ele.closest('.recipeTextBox1').querySelector(".foodCode").value;
    let data = {
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        body: JSON.stringify({
            foodCode
        })
    }


    fetch(url,data)
        .then((response)=>{
            return response.text();
        }).then((data)=>{
            if(data != "complete"){
                alert("실패");
            }else{
                alert("완료");
                location.reload(true);
            }
        })
}

const addFavBtn = document.querySelectorAll(".cartBox");
const addFavURL = "/myPage/addFav";
let list = null;
//페이지 로딩시 하트색칠

window.addEventListener('load',()=>{
    const listdata = document.querySelector("#favoriteList").getAttribute("data-favorite-list");
    list = JSON.parse(listdata);
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
    
})  


  
//* 즐겨찾기 추가
function addOrDelFav(ele){
    const biHeart = ele.querySelector(".bi-heart");
    const fillHeart = ele.querySelector(".bi-heart-fill");
    const foodCode = ele.closest(".recipeTextBox1").querySelector(".foodCode").value;
    const rcmmCnt = ele.closest(".recipeTextBox1").querySelector(".RcmmCnt");
    const rcmmCntVal = ele.closest(".recipeTextBox1").querySelector(".RcmmCnt").textContent;
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

