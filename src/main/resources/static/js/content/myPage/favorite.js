const url = "/myPage/deleteFav"
function deleteFav(ele){
    const foodCode = ele.parentNode.querySelector(".col_1st").textContent;
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




