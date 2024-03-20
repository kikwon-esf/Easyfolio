var list = document.querySelectorAll("a");

list.forEach((element)=>{
    element.addEventListener("click", (e) =>(

        e.preventDefault();
        console.dir("e : " + e);
        console.dir("element" + element);

    ))


})

