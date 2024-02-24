let searchText = document.querySelector('.searchText');

// $('.searchText').click(function () {
//     document.querySelector('.searchblock').style.border = "2px solid #000";
// })
var wrap = document.querySelector('#wrap')
var searchblock = document.querySelector('.searchblock')

searchText.addEventListener("click", function () {
    searchblock.style.border = "2px solid rgb(242,146,33)";
    searchblock.style.backgroundColor = "transparent"
    searchText.style.color = "#333";
    searchText.value = '';
})

document.addEventListener("click", function (event) {
    if (event.target !== searchText) {
        searchblock.style.border = "2px solid transparent";
        searchblock.style.backgroundColor = "#f4f4f4";
        searchText.style.color = "#999";
        searchText.value = '원하는 모든 레시피';
    }
});