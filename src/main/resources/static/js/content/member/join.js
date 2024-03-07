
document.addEventListener("DOMContentLoaded", function () {
    var inputArray = Array.from(document.querySelectorAll('.inputMem'));

    inputArray.forEach(function (input) {
        input.addEventListener('click', function () {
            input.closest('.inputBox').classList.add('on');
        });
    });

    document.addEventListener("click", function (event) {
        inputArray.forEach(function (input) {
            if (!input.contains(event.target)) {
                input.closest('.inputBox').classList.remove('on');
            }
        });
    });
});