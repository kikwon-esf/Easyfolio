function readURL(input) {
    let box = document.querySelector('.imgLookBox');
    box.innerHTML = "";
    let a = 0;
    for (const file of input.files) {
        var fileName = file.name;
        document.querySelector('.upload-name').value = fileName + " 외 " + a + "개";
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

var bigImgBlock = document.querySelector(".bigImgBlock");

var images = document.querySelectorAll(".inqImgBlock img");
images.forEach(function(image, index) {
    image.addEventListener("click", function() {
        var bigImg = document.getElementById("img01");
        bigImg.src = image.src;
        bigImgBlock.style.display = "block";
    });
});
var closeBtn = document.getElementsByClassName("close")[0];
window.onclick = function(event) {
    if (event.target == bigImgBlock) {
        bigImgBlock.style.display = "none";
    }
}
closeBtn.onclick = function() {
    bigImgBlock.style.display = "none";
}